package fr.cyu.depinfo.agp.tahiti.business.locations;

public class Hotel extends Location {
    private int pricePerNight;
    private String beach;
    final Rank rank;

    public Hotel(String id, String name, String address, int islandId, float rating, Position position, Rank rank, String beach, int pricePerNight) {
        super(id, name, address, islandId, rating, position);
        this.rank = rank;
        this.beach = beach;
        this.pricePerNight = pricePerNight;
    }

    public Hotel setPricePerNight(int pricePerNight) {
        this.pricePerNight = pricePerNight;
        return this;
    }
    public String getBeach() {
        return beach;
    }

    public Hotel setBeach(String beach) {
        this.beach = beach;
        return this;
    }

    public Rank getRank() {
        return rank;
    }

    public int getPricePerNight() {
        return pricePerNight;
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "pricePerNight=" + pricePerNight +
                ", beach='" + beach + '\'' +
                ", rank=" + rank +
                "} " + super.toString();
    }
}
