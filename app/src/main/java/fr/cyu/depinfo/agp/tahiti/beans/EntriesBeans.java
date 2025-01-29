package fr.cyu.depinfo.agp.tahiti.beans;

import fr.cyu.depinfo.agp.tahiti.business.client.Client;


import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;



public class EntriesBeans {
    private Client client = new Client();
    private String keywords;
    private int numberOfHotels;
    private int comfortLevel;
    private double minimumPrice;
    private double maximumPrice;
    private String arrivalDate;
    private String departureDate;

    // Getter and Setter for keywords
    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
        List<String> keywordsList = new ArrayList<>(Arrays.asList(keywords.split(",")));
        client.setKeywords(keywordsList);
    }

    // Getters and Setters for other fields
    public int getNumberOfHotels() {
        return numberOfHotels;
    }

    public void setNumberOfHotels(int numberOfHotels) {
        this.numberOfHotels = numberOfHotels;
        client.setNumberOfHotels(numberOfHotels);
    }

    public int getComfortLevel() {
        return comfortLevel;
    }

    public void setComfortLevel(int comfortLevel) {
        this.comfortLevel = comfortLevel;
        client.setComfortLevel(comfortLevel);
    }

    public double getMinimumPrice() {
        return minimumPrice;
    }

    public void setMinimumPrice(double minimumPrice) {
        this.minimumPrice = minimumPrice;
        client.setMinimumPrice(minimumPrice);
    }

    public double getMaximumPrice() {
        return maximumPrice;
    }

    public void setMaximumPrice(double maximumPrice) {
        this.maximumPrice = maximumPrice;
        client.setMaximumPrice(maximumPrice);
    }

    public String getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(String arrivalDate) {
        this.arrivalDate = arrivalDate;
        try {
            Date parsedDate = new SimpleDateFormat("yyyy-MM-dd").parse(arrivalDate);
            client.setArrivalDate(parsedDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public String getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
        try {
            Date parsedDate = new SimpleDateFormat("yyyy-MM-dd").parse(departureDate);
            client.setDepartureDate(parsedDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    // Method to process the travel request
    public String processRequest() {
        System.out.println("Keywords: " + client.getKeywords());
        System.out.println("Number of Hotels: " + client.getNumberOfHotels());
        System.out.println("Comfort Level: " + client.getComfortLevel());
        System.out.println("Minimum Price: " + client.getMinimumPrice());
        System.out.println("Maximum Price: " + client.getMaximumPrice());
        System.out.println("Arrival Date: " + client.getArrivalDate());
        System.out.println("Departure Date: " + client.getDepartureDate());

        return "result";
    }
}