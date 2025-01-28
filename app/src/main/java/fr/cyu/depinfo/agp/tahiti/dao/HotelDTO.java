package fr.cyu.depinfo.agp.tahiti.dao;

public class HotelDTO {
    private int id;
    private String name;
    private int pricePerNight;
    private float latitude;
    private float longitude;
    private String beach;
    private float rating;
    private String address;
    private int islandId;
    public HotelDTO(String address, float rating, String beach, float longitude, float latitude, int pricePerNight, String name, String id) {
        this.address = address;
        this.rating = rating;
        this.beach = beach;
        this.longitude = longitude;
        this.latitude = latitude;
        this.pricePerNight = pricePerNight;
        this.name = name;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPricePerNight() {
        return pricePerNight;
    }

    public float getLatitude() {
        return latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public String getBeach() {
        return beach;
    }

    public float getRating() {
        return rating;
    }

    public String getAddress() {
        return address;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPricePerNight(int pricePerNight) {
        this.pricePerNight = pricePerNight;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public void setBeach(String beach) {
        this.beach = beach;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getIslandId() {
        return islandId;
    }

    public HotelDTO setIslandId(int islandId) {
        this.islandId = islandId;
        return this;
    }
}
