package fr.cyu.depinfo.agp.tahiti.business.select;

import fr.cyu.depinfo.agp.tahiti.business.Offer;
import fr.cyu.depinfo.agp.tahiti.business.Excursion;
import fr.cyu.depinfo.agp.tahiti.business.Trip;
import fr.cyu.depinfo.agp.tahiti.business.locations.Hotel;
import fr.cyu.depinfo.agp.tahiti.business.locations.Location; // or "Site"
import fr.cyu.depinfo.agp.tahiti.business.locations.Destination;
import fr.cyu.depinfo.agp.tahiti.business.select.Duree;

import java.util.Date;
import java.util.Calendar;
import java.util.List;
import java.util.Map;


public class FindSejour {


    public int nbActMaxParJour(int comfort) {
        double[][] probability_matrix = {
                {0.01, 0.05, 0.47, 0.47},  // comfort=1
                {0.02, 0.04, 0.54, 0.40},  // comfort=2
                {0.15, 0.15, 0.50, 0.20},  // comfort=3
                {0.40, 0.54, 0.04, 0.02},  // comfort=4
                {0.47, 0.47, 0.05, 0.01}   // comfort=5
        };

        // Bound comfort between 1 and 5
        if (comfort < 1) {
            comfort = 1;
        } else if (comfort > 5) {
            comfort = 5;
        }

        double[] probas = probability_matrix[comfort - 1];
        double randomValue = Math.random();
        double cumulativeProbability = 0.0;

        for (int i = 0; i < probas.length; i++) {
            cumulativeProbability += probas[i];
            if (cumulativeProbability >= randomValue) {
                return i; // i will be 0..3
            }
        }

        return probas.length - 1;
    }


    public Offer findSejour(List<Hotel> hotels,
                            Map<Hotel, List<Location>> activitiesPerHotel,
                            Duree duree,
                            int comfort,
                            Destination destination) {

        // 1) Arrival/Departure logic
        Date arrivalDate = duree.getArrivalDate();
        int totalDays = duree.getNbJours();
        Date departureDate = duree.getDepartureDate();

        Offer sejour = new Offer(arrivalDate, departureDate, destination);

        // 2) Probabilistic number of daily activities
        int nbActMaxParJour = nbActMaxParJour(comfort);

        // 3) Keep track of which hotel we are "in" right now
        int hotelIndex = 0;
        Hotel currentHotel = hotels.isEmpty() ? null : hotels.get(hotelIndex);

        // 4) For each day:
        for (int day = 0; day < totalDays; day++) {

            if (currentHotel == null) {
                // No hotels left, no point in continuing
                break;
            }

            // Create date for this day
            Date dateForDay = getDateFromNow(day);

            // Create a new Excursion departing from currentHotel
            Excursion excursion = new Excursion(dateForDay, currentHotel);

            // We'll keep track of our "current position" as we chain activities
            Location lastLocation = currentHotel;  // *** Start day at the current hotel

            // Grab the relevant activities for this hotel
            List<Location> relevantActivities = activitiesPerHotel.get(currentHotel);

            int activitiesAddedToday = 0;

            // 5) Pick up to nbActMaxParJour from this hotel
            if (relevantActivities != null && !relevantActivities.isEmpty()) {

                // Example approach: up to nbActMaxParJour if available
                int limitToday = Math.min(nbActMaxParJour, relevantActivities.size());

                for (int i = 0; i < limitToday; i++) {
                    Location site = relevantActivities.get(i);

                    // Create a Trip from lastLocation -> site
                    Trip trip = new Trip(lastLocation, site);
                    try {
                        excursion.addSite(trip);
                        activitiesAddedToday++;
                    } catch (Exception e) {
                        // Excursion might have a max capacity: if full, just stop
                        break;
                    }
                    // Update our last location for chaining
                    lastLocation = site;
                }

                // Remove the activities we used from the list
                // This ensures we don't pick them again tomorrow
                for (int i = 0; i < activitiesAddedToday; i++) {
                    relevantActivities.remove(0);
                }
            }

            // 6) End-of-day: come back to a hotel
            // If we DID add at least one activity and there's more left in current hotel,
            // we return to the SAME hotel. Otherwise, we move on.

            boolean stillHasActivities = (relevantActivities != null && !relevantActivities.isEmpty());

            if (activitiesAddedToday > 0 && stillHasActivities) {
                // a) We had activities, and there's more left => come back to same hotel
                Trip returnTrip = new Trip(lastLocation, currentHotel);
                try {
                    excursion.addSite(returnTrip);
                } catch (Exception e) {
                    // ignore or log (if excursion is full)
                }

                // We'll stay in the same currentHotel for the next day
                excursion.setDestination(currentHotel);

            } else {
                // b) No more activities left in this hotel (or we did zero):
                //    Move to the next hotel, if it exists
                if (hotelIndex + 1 < hotels.size()) {
                    Hotel nextHotel = hotels.get(++hotelIndex);

                    // Add a final trip from lastLocation -> nextHotel
                    // but only if we actually visited something this day
                    // or if you want an explicit "move" even if we had zero activities
                    if (activitiesAddedToday > 0) {
                        Trip moveTrip = new Trip(lastLocation, nextHotel);
                        try {
                            excursion.addSite(moveTrip);
                        } catch (Exception e) {
                            // ignore or log
                        }
                    }
                    // Set new "currentHotel"
                    currentHotel = nextHotel;
                    excursion.setDestination(nextHotel);
                } else {
                    // No more hotels left at all
                    // Optionally create a return trip if day had at least 1 activity:
                    if (activitiesAddedToday > 0) {
                        Trip moveTrip = new Trip(lastLocation, currentHotel);
                        try {
                            excursion.addSite(moveTrip);
                        } catch (Exception e) {
                            // ignore
                        }
                    }
                    // Then break from the loop (end the vacation)
                    excursion.setDestination(currentHotel);
                    currentHotel = null; // so next day won't process
                }
            }

            // 7) Add the excursion to our Offer
            sejour.addExcursion(excursion);
        }


        sejour.setPrice(sejour.calculatePrice());
        return sejour;
    }

    /**
     * Example: produce a Date offset by 'dayOffset' from today.
     * Adapt if your logic differs.
     */
    private Date getDateFromNow(int dayOffset) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_YEAR, dayOffset);
        return cal.getTime();
    }

}
