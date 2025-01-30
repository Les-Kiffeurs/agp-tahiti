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
        if(departure.getIslandId() != destination.getIslandId()) {
            this.transportMode= new Transport("Boat",0,14);
        } else if (departure.getIslandId()==destination.getIslandId() && destination.distanceFrom(destination)<1) {
            this.transportMode= new Transport("Feet",0,1);
        }else{
            this.transportMode= new Transport("Bus",0,10);
        }
    }

    public int travelDistance() {
        return (int) departure.distanceFrom(destination);
    }

    public Duration duration() {
        float distance = travelDistance();
        float speed = transportMode.getSpeed();
        return Duration.ofSeconds((long) (distance / speed));
    }

    public float price() {
        int distance = Math.round(travelDistance() / 1000f);
        return distance * transportMode.getPricePerKm();
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
}
