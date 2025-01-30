package fr.cyu.depinfo.agp.tahiti.persistence.bde.hibernate;

import fr.cyu.depinfo.agp.tahiti.business.Excursion;
import fr.cyu.depinfo.agp.tahiti.business.locations.Destination;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Entity
public class OfferData {
    @Id
    @GeneratedValue
    private int id;
    private Date arrivalDate;
    private Date departureDate;
    private Destination destination;
    private Map<Date, Excursion> excursions = new HashMap<>();
    private float score;

    public OfferData(Date arrivalDate, Date departureDate, Destination destination, Map<Date, Excursion> excursions, float score) {
        this.arrivalDate = arrivalDate;
        this.departureDate = departureDate;
        this.destination = destination;
        this.excursions = excursions;
        this.score = score;
    }

    public int getId() {
        return id;
    }

    public OfferData setId(int id) {
        this.id = id;
        return this;
    }

    public Date getArrivalDate() {
        return arrivalDate;
    }

    public OfferData setArrivalDate(Date arrivalDate) {
        this.arrivalDate = arrivalDate;
        return this;
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    public OfferData setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
        return this;
    }

    public Destination getDestination() {
        return destination;
    }

    public OfferData setDestination(Destination destination) {
        this.destination = destination;
        return this;
    }

    public Map<Date, Excursion> getExcursions() {
        return excursions;
    }

    public OfferData setExcursions(Map<Date, Excursion> excursions) {
        this.excursions = excursions;
        return this;
    }

    public float getScore() {
        return score;
    }

    public OfferData setScore(float score) {
        this.score = score;
        return this;
    }
}
