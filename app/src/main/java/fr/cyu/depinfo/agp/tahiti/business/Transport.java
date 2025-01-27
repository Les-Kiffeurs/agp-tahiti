package fr.cyu.depinfo.agp.tahiti.business;

public class Transport {
    private String type;
    private float pricePerKm;
    private float speed;

    public Transport(String type, float pricePerKm, float speed) {
        this.type = type;
        this.pricePerKm = pricePerKm;
        this.speed = speed;
    }

    public String getType() {
        return type;
    }

    public float getPricePerKm() {
        return pricePerKm;
    }

    public float getSpeed() {
        return speed;
    }
}
