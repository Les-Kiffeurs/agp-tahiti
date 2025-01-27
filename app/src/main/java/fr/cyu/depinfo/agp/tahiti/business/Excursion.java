package fr.cyu.depinfo.agp.tahiti.business;

import fr.cyu.depinfo.agp.tahiti.business.locations.ExcursionCannotContainMoreSitesToVisitException;
import fr.cyu.depinfo.agp.tahiti.business.locations.Hotel;
import fr.cyu.depinfo.agp.tahiti.business.locations.Site;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Excursion {
    public static final int MAX_SITES = 3;

    private final Date date;
    private final Hotel departure;
    private final Hotel destination;
    private final List<Site> sites = new ArrayList<>();

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

    public List<Site> getSites() {
        return sites;
    }

    public Excursion addSite(Site site) throws ExcursionCannotContainMoreSitesToVisitException {
        if (sites.size() >= MAX_SITES) {
            throw new ExcursionCannotContainMoreSitesToVisitException("Excursion is full");
        }
        sites.add(site);
        return this;
    }
}
