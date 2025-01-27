package fr.cyu.depinfo.agp.tahiti.dao;

public class SiteDTO {

    private int id;
    private String name;
    private String type;
    private int price;
    private float latitude;
    private float longitude;
    private int intensity;
    private String adress;
    private int note;

    public SiteDTO(int id, String name, String type, int price, float latitude, float longitude, int intensity, String adress, int note) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.price = price;
        this.latitude = latitude;
        this.longitude = longitude;
        this.intensity = intensity;
        this.adress = adress;
        this.note = note;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public int getPrice() {
        return price;
    }

    public float getLatitude() {
        return latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public int getIntensity() {
        return intensity;
    }

    public String getAdress() {
        return adress;
    }

    public int getNote() {
        return note;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public void setIntensity(int intensity) {
        this.intensity = intensity;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public void setNote(int note) {
        this.note = note;
    }
}
