package fr.cyu.depinfo.agp.tahiti.business.locations;

public class Destination {
    private String islandName;

    public Destination(String islandName) {
        this.islandName = islandName;
    }

    public String getIslandName() {
        return islandName;
    }

    public Destination setIslandName(String islandName) {
        this.islandName = islandName;
        return this;
    }

}
