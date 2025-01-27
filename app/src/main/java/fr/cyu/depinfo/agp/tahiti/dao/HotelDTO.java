package fr.cyu.depinfo.agp.tahiti.dao;

public class HotelDTO {
    private String id;
    private String name;
    private int pricePerNight;
    private float latitude;
    private float longitude;
    private String beach;
    private float rating;
    private float adress;

    public HotelDTO(float adress, float rating, String beach, float longitude, float latitude, int pricePerNight, String name, String id) {
        this.adress = adress;
        this.rating = rating;
        this.beach = beach;
        this.longitude = longitude;
        this.latitude = latitude;
        this.pricePerNight = pricePerNight;
        this.name = name;
        this.id = id;
    }

    public String getId() {
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

    public float getAdress() {
        return adress;
    }

    public void setId(String id) {
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

    public void setAdress(float adress) {
        this.adress = adress;
    }
}
