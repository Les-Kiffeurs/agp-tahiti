package fr.cyu.depinfo.agp.tahiti.business;

import fr.cyu.depinfo.agp.tahiti.business.locations.Location;

import java.time.Duration;

public class Trip {
    private Transport transportMode;
    private final Location departure;
    private final Location destination;

    public Trip(Location departure, Location destination) {
        this.departure = departure;
        this.destination = destination;
    }

    public int travelDistance() {
        return (int) departure.distanceFrom(destination);
    }

    public Duration duration() {
        float distance = travelDistance();
        float speed = transportMode.getSpeed();
        return Duration.ofSeconds((long) (distance / speed));
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

    public double getPrice() {
        return travelDistance()/1000f * transportMode.getPricePerKm();
    }

    public Trip setTransportMode(Transport transportMode) {
        this.transportMode = transportMode;
        return this;
    }

}
