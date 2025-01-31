package fr.cyu.depinfo.agp.tahiti.business.locations;
import java.lang.Math;

import static java.lang.Math.*;

public class Position {
    private double latitude;
    private double longitude;

    public double getLatitude() {
        return latitude;
    }

    public Position(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Position setLatitude(double latitude) {
        this.latitude = latitude;
        return this;
    }

    public double getLongitude() {
        return longitude;
    }

    public Position setLongitude(double longitude) {
        this.longitude = longitude;
        return this;
    }

    public double distanceFrom(Position position) {
        double p1=toRadians(this.latitude);
        double p2=toRadians(position.latitude);
        double l1=toRadians(this.longitude);
        double l2=toRadians(position.longitude);
        double rayonTerre= 6371009.72;
        return  (2*rayonTerre*asin(sqrt(pow(sin((p1-p2)/2),2)+cos(p1)*cos(p2)*pow(sin((l1-l2)/2),2))));
    }

    @Override
    public String toString() {
        return "Position{" +
                "latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}
