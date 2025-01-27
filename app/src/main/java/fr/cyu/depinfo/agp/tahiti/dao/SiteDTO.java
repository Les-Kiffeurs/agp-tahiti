package fr.cyu.depinfo.agp.tahiti.dao;

public class SiteDTO {

    private int id;
    private String name;
    private String type;
    private int price;
    private float latitude;
    private float longitude;
    private int intensity;
    private String address;
    private int note;
    private int islandId;

    public SiteDTO(int id, String name, String type, int price, float latitude, float longitude, int intensity, String address, int note) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.price = price;
        this.latitude = latitude;
        this.longitude = longitude;
        this.intensity = intensity;
        this.address = address;
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

    public String getAddress() {
        return address;
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

    public void setAddress(String address) {
        this.address = address;
    }

    public void setNote(int note) {
        this.note = note;
    }

    public int getIslandId() {
        return islandId;
    }

    public SiteDTO setIslandId(int islandId) {
        this.islandId = islandId;
        return this;
    }
}
