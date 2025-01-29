package fr.cyu.depinfo.agp.tahiti.business.locations;

public abstract class Location {
    private int id;
    private String name;
    private String address;
    private int islandId;
    private float rating;
    final Position position;

    public Location(int id, String name, String address, int islandId, float rating, Position position) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.islandId = islandId;
        this.rating = rating;
        this.position = position;
    }
    public int getId() {
        return id;
    }

    public Location setId(int id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Location setName(String name) {
        this.name = name;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public Location setAddress(String address) {
        this.address = address;
        return this;
    }

    public int getIslandId() {
        return islandId;
    }

    public Location setIslandId(int islandId) {
        this.islandId = islandId;
        return this;
    }

    public float getRating() {
        return rating;
    }

    public Location setRating(float rating) {
        this.rating = rating;
        return this;
    }
    public double distanceFrom(Location location) {
       return this.position.distanceFrom(location.position);
    }

    @Override
    public String toString() {
        return "Location{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", islandId=" + islandId +
                ", rating=" + rating +
                ", position=" + position +
                '}';
    }
}
