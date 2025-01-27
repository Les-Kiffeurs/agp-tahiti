package fr.cyu.depinfo.agp.tahiti.business.locations;

import java.util.ArrayList;
import java.util.Date;

public class Offer {
    private Date arrivalDate;
    private Date departureDate;
    private float score;
    private ArrayList<Hotel> hotels;

    public Offer(Date arrivalDate, Date departureDate, float score, ArrayList<Hotel> hotels) {
        this.arrivalDate = arrivalDate;
        this.departureDate = departureDate;
        this.score = score;
        this.hotels = hotels;
    }

    public Date getArrivalDate() {
        return arrivalDate;
    }

    public Offer setArrivalDate(Date arrivalDate) {
        this.arrivalDate = arrivalDate;
        return this;
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    public Offer setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
        return this;
    }

    public float getScore() {
        return score;
    }

    public Offer setScore(float score) {
        this.score = score;
        return this;
    }

    public ArrayList<Hotel> getHotels() {
        return hotels;
    }

    public Offer setHotels(ArrayList<Hotel> hotels) {
        this.hotels = hotels;
        return this;
    }
}
