package fr.cyu.depinfo.agp.tahiti.business.locations;

public class Site extends Location {
    private String type;
    private int price;
    private int intensity;

    public Site(String id, String name, String address, int islandId, float rating, String type, int price, int intensity,Position position) {
        super(id, name, address, islandId, rating,position);
        this.type = type;
        this.price = price;
        this.intensity = intensity;
    }

    public String getType() {
        return type;
    }

    public Site setType(String type) {
        this.type = type;
        return this;
    }

    public int getPrice() {
        return price;
    }

    public Site setPrice(int price) {
        this.price = price;
        return this;
    }

    public int getIntensity() {
        return intensity;
    }

    public Site setIntensity(int intensity) {
        this.intensity = intensity;
        return this;
    }
}
