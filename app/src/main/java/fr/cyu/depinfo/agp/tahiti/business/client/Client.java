package fr.cyu.depinfo.agp.tahiti.business.client;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Client {
    private List<String> keywords;
    private int numberOfHotels;
    private int comfortLevel; // Consider using an Enum
    private double minimumPrice; // Use double for prices
    private double maximumPrice; // Use double for prices
    private LocalDate arrivalDate;
    private LocalDate departureDate;

    // --- Constructors ---

    public Client() {
        keywords = new ArrayList<>();
        comfortLevel = 1;
        minimumPrice = 0;
        maximumPrice = Double.MAX_VALUE;
    }

    public Client(List<String> keywords, int numberOfHotels, int comfortLevel, double minimumPrice, double maximumPrice, LocalDate arrivalDate, LocalDate departureDate) {
        this.keywords = keywords;
        this.numberOfHotels = numberOfHotels;
        setComfortLevel(comfortLevel);
        setMinimumPrice(minimumPrice);
        setMaximumPrice(maximumPrice);
        this.arrivalDate = arrivalDate;
        this.departureDate = departureDate;
    }

    // --- Getters and Setters with Validation ---

    public List<String> getKeywords() {
        return new ArrayList<>(keywords); // Return a copy for immutability
    }

    public void setKeywords(List<String> keywords) {
        if (keywords == null) {
            throw new IllegalArgumentException("Keywords list cannot be null.");
        }
        this.keywords = new ArrayList<>(keywords); // Store a copy for immutability
    }

    public int getNumberOfHotels() {
        return numberOfHotels;
    }

    public void setNumberOfHotels(int numberOfHotels) {
        if (numberOfHotels < 1) {
            throw new IllegalArgumentException("Number of hotels must be at least 1.");
        }
        this.numberOfHotels = numberOfHotels;
    }

    public int getComfortLevel() {
        return comfortLevel;
    }

    public void setComfortLevel(int comfortLevel) {
        if (comfortLevel < 1 || comfortLevel > 5) {
            throw new IllegalArgumentException("Comfort level must be between 1 and 5.");
        }
        this.comfortLevel = comfortLevel;
    }

    public double getMinimumPrice() {
        return minimumPrice;
    }

    public void setMinimumPrice(double minimumPrice) {
        if (minimumPrice < 0) {
            throw new IllegalArgumentException("Minimum price cannot be negative.");
        }
        this.minimumPrice = minimumPrice;
    }

    public double getMaximumPrice() {
        return maximumPrice;
    }

    public void setMaximumPrice(double maximumPrice) {
        if (maximumPrice < minimumPrice) {
            throw new IllegalArgumentException("Maximum price cannot be less than minimum price.");
        }
        this.maximumPrice = maximumPrice;
    }

    public LocalDate getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(LocalDate arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public void setArrivalDate(Date arrivalDate) {
        this.arrivalDate = arrivalDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public LocalDate getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(LocalDate departureDate) {
        this.departureDate = departureDate;
    }

    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    // --- Overridden Methods ---

    @Override
    public String toString() {
        return "Client{" +
                "keywords=" + keywords +
                ", numberOfHotels=" + numberOfHotels +
                ", comfortLevel=" + comfortLevel +
                ", minimumPrice=" + minimumPrice +
                ", maximumPrice=" + maximumPrice +
                ", arrivalDate=" + arrivalDate +
                ", departureDate=" + departureDate +
                '}';
    }
}