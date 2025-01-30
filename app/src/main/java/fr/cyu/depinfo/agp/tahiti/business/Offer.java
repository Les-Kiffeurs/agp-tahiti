package fr.cyu.depinfo.agp.tahiti.business;

import fr.cyu.depinfo.agp.tahiti.business.locations.Destination;

import java.util.*;

public class Offer {
    private final Date arrivalDate;
    private final Date departureDate;
    private final Destination destination;
    private final Map<Date, Excursion> excursions = new HashMap<>();
    private float score;
    private double price;

    public Offer(Date arrivalDate, Date departureDate, Destination destination) {
        this.arrivalDate = arrivalDate;
        this.departureDate = departureDate;
        this.destination = destination;
    }

    public Date getArrivalDate() {
        return arrivalDate;
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    public Destination getDestination() {
        return destination;
    }

    public Map<Date, Excursion> getExcursions() {
        return excursions;
    }

    public Excursion getExcursion(Date date) {
        return excursions.get(date);
    }

    public Offer addExcursion(Excursion excursion) {
        excursions.put(excursion.getDate(), excursion);
        return this;
    }

    public float getScore() {
        return score;
    }

    public Offer setScore(float score) {
        this.score = score;
        return this;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double calculatePrice() {
        double total = 0.0;
        for (Excursion excursion : this.getExcursions().values()) {
            for(Trip trip : excursion.getTrips()){
                 double distance = trip.getDistance();
                 double pricePerKM = trip.getTransportMode().getPricePerKm();
                 total += distance/1000 * pricePerKM;
            }
        }
        return total;
    }
}
