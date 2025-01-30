package fr.cyu.depinfo.agp.tahiti.business;

import fr.cyu.depinfo.agp.tahiti.business.locations.Location;

import java.time.Duration;

public class Trip {
    private Transport transportMode;
    private final Location departure;
    private final Location destination;
    private double distance;
    private double price;

    public Trip(Location departure, Location destination) {
        this.departure = departure;
        this.destination = destination;
        if(departure.getIslandId() != destination.getIslandId()) {
            this.transportMode= new Transport("Boat",0.1f,14);
        } else if (departure.getIslandId()==destination.getIslandId() && departure.distanceFrom(destination)<1) {
            this.transportMode= new Transport("Feet",0,1);
        }else{
            this.transportMode= new Transport("Bus",0.05f,10);
        }
        this.distance = travelDistance();
        this.price = distance/1000 * transportMode.getPricePerKm();
    }

    public double travelDistance() {
        return departure.distanceFrom(destination);
    }

    public Duration duration() {
        double distance = travelDistance();
        float speed = transportMode.getSpeed();
        return Duration.ofSeconds((long) (distance / speed));
    }

    public double getPrice() {
        return price;
    }

    public Trip setPrice(double price) {
        this.price = price;
        return this;
    }

    public Location getDeparture() {
        return departure;
    }

    public Location getDestination() {
        return destination;
    }

    public Transport getTransportMode() {
        return transportMode;
    }

    public Trip setTransportMode(Transport transportMode) {
        this.transportMode = transportMode;
        return this;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }
}
