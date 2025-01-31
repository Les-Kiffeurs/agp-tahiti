package fr.cyu.depinfo.agp.tahiti.business.select;

import fr.cyu.depinfo.agp.tahiti.business.select.Duree;
import fr.cyu.depinfo.agp.tahiti.business.Excursion;
import fr.cyu.depinfo.agp.tahiti.business.Offer;
import fr.cyu.depinfo.agp.tahiti.business.Trip;
import fr.cyu.depinfo.agp.tahiti.business.locations.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import fr.cyu.depinfo.agp.tahiti.business.locations.Position;

import java.util.*;

/**
 * Example JUnit test for the FindSejour class.
 */
public class FindSejourTest {

    @Test
    public void testFindSejour_singleHotelMultipleDays() {
        // 1) Prepare test data

        // Create a single hotel
        Hotel hotelA = new Hotel(1,
                "Taharu'u Guest House By The Beach",
                "PK 38,8 C/mer, Lotissement Pitate, Papara, 98712 Papara, Polynésie française",
                1,
                9.0f,
                new Position(-17.7617237,-149.5038687),
                "black sand beach",
                149
        );

        // Create a few mock locations for the single hotel
        Location beach = new Site(1,
                "Tahiti Safari Islander",
                "Papeete, Polynésie française, 98714",
                1,
                4.7f,
                "Activity",
                69,
                2,
                new Position(-17.5324608,-149.5677151)
                );
        Location museum = new Site(2,
                "École De Surf Tura'i Mataare",
                "Punaauia, Polynésie française, 98718",
                1,
                7.2f,
                "Activity",
                69,
                2,
                new Position(-17.5717472,-149.6133526)
        );
        Location temple = new Site(3,
                "Tahiti Limousine - Service Vip",
                "Papeete, Polynésie française, 98714",
                1,
                3.3f,
                "Activity",
                69,
                2,
                new Position(-17.6400771,-149.6088438)
        );
        Location snorkeling =new Site(4,
                "Centre Equestre L'Eperon",
                "Pirae, 98716",
                1,
                4.5f,
                "Activity",
                69,
                2,
                new Position(-17.7617237,-149.5038687)
        );

        // Put them in a list
        List<Location> hotelAActivities = new ArrayList<>(List.of(beach, museum, temple, snorkeling));

        // Build a Map <Hotel, List<Location>>
        Map<Hotel, List<Location>> activitiesPerHotel = new HashMap<>();
        activitiesPerHotel.put(hotelA, hotelAActivities);

        // We will pass just one hotel in the list
        List<Hotel> hotels = new ArrayList<>(List.of(hotelA));

        // 2) Instantiate FindSejour and set up parameters
        FindSejour findSejour = new FindSejour();
        Duree duree = new Duree(4); // 4 days
        int comfort = 3; // user comfort
        Destination destination = new Destination("Tahiti");

        // 3) Call findSejour
        Offer offer = findSejour.findSejour(
                hotels,
                activitiesPerHotel,
                duree,
                comfort,
                destination
        );

        // 4) Verify results
        Assertions.assertNotNull(offer, "Offer should not be null");
        Assertions.assertEquals(destination, offer.getDestination(), "Destination must match the one provided");

        // We expect an excursion for each of the 4 days (unless we run out of activities)
        // But let's see how many were actually created
        Map<Date, Excursion> excursions = offer.getExcursions();
        Assertions.assertFalse(excursions.isEmpty(), "Excursions map should not be empty");

        // We can iterate day by day over the map
        // This is one possible approach to see how many unique days we got
        int dayCount = excursions.size();
        Assertions.assertTrue(dayCount <= 4, "We should have up to 4 days of excursions");

        // Check that we only used the single hotel
        for (Excursion excursion : excursions.values()) {
            Assertions.assertEquals(hotelA, excursion.getDeparture(),
                    "All excursions should depart from the single hotel");
        }

        // Optionally, check if we have any leftover activities
        // or if we used them all. The logic depends on
        // your nbActMaxParJour (which is probabilistic!)
        // For example, let's see how many total Trips were created:
        int totalTrips = 0;
        for (Excursion excursion : excursions.values()) {
            List<Trip> trips = excursion.getSites();
            totalTrips += trips.size();
        }

        // Because comfort = 3, nbActMaxParJour is random from {0,1,2,3}
        // but let's just ensure we have at least 1 trip across all 4 days
        // (the chance of 0 each day is tiny, but possible).
        Assertions.assertTrue(totalTrips >= 1,
                "We expect at least 1 trip across 4 days (unless random chance was extremely unlucky).");
    }


}
