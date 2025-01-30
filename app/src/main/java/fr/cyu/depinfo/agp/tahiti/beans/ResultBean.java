package fr.cyu.depinfo.agp.tahiti.beans;

public class ResultBean {

    private String results = "Hello from ResultBean!";

    public String getResults(String keywords, String numberOfHotels, String comfortLevel, String minPrice, String maxPrice, String arrivalDate, String departureDate) {
        return results;
    }

    public void setResults(String results) {
        this.results = results;
    }
}