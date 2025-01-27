package fr.cyu.depinfo.agp.tahiti.business.locations;

public class Position {
    private float latitude;
    private float longitude;

    public float getLatitude() {
        return latitude;
    }

    public Position(float latitude, float longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Position setLatitude(float latitude) {
        this.latitude = latitude;
        return this;
    }

    public float getLongitude() {
        return longitude;
    }

    public Position setLongitude(float longitude) {
        this.longitude = longitude;
        return this;
    }

    public float distanceFrom(Position position) {

    }
}
