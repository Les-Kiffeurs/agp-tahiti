package fr.cyu.depinfo.agp.tahiti.business;

import fr.cyu.depinfo.agp.tahiti.business.locations.ExcursionCannotContainMoreSitesToVisitException;
import fr.cyu.depinfo.agp.tahiti.business.locations.Hotel;
import fr.cyu.depinfo.agp.tahiti.business.locations.Site;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Excursion {
    public static final int MAX_TRIPS = 3;

    private final Date date;
    private final Hotel departure;
    private final Hotel destination;
    private final List<Trip> trips = new ArrayList<>();

    public Excursion(Date date, Hotel departure, Hotel destination) {
        this.date = date;
        this.departure = departure;
        this.destination = destination;
    }

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
}
