package fr.cyu.depinfo.agp.tahiti.business;

import fr.cyu.depinfo.agp.tahiti.business.locations.ExcursionCannotContainMoreSitesToVisitException;
import fr.cyu.depinfo.agp.tahiti.business.locations.Hotel;
import fr.cyu.depinfo.agp.tahiti.business.locations.Site;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Excursion {
    public static final int MAX_TRIPS = 3;

    private Date date;
    private Hotel departure;
    private Hotel destination;
    private List<Trip> trips = new ArrayList<>();

    public Excursion(Date date, Hotel departure, Hotel destination) {
        this.date = date;
        this.departure = departure;
        this.destination = destination;
    }

    public Excursion(Date date, Hotel departure){
        this.date = date;
        this.departure = departure;
        this.destination = null;
    };

    public Date getDate() {
        return date;
    }

    public Hotel getDeparture() {
        return departure;
    }

    public Hotel getDestination() {
        return destination;
    }

    public List<Trip> getSites() {
        return trips;
    }

    public Excursion addSite(Trip trip) throws ExcursionCannotContainMoreSitesToVisitException {
        if (trips.size() >= MAX_TRIPS) {
            throw new ExcursionCannotContainMoreSitesToVisitException("Excursion is full");
        }
        trips.add(trip);
        return this;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setDeparture(Hotel departure) {
        this.departure = departure;
    }

    public void setDestination(Hotel destination) {
        this.destination = destination;
    }

    public void setTrips(List<Trip> trips) {
        this.trips = trips;
    }

    public List<Trip> getTrips() {
        return trips;
    }
}
