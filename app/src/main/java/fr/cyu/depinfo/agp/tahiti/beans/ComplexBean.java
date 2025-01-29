package fr.cyu.depinfo.agp.tahiti.beans;

import java.util.List;

import fr.cyu.depinfo.agp.tahiti.business.client.Client;


public class ComplexBean {
    private String keyword;
    private String priceRange;
    private int comfortLevel;
    private List<Object> searchResults;


    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getPriceRange() {
        return priceRange;
    }

    public void setPriceRange(String priceRange) {
        this.priceRange = priceRange;
    }

    public int getComfortLevel() {
        return comfortLevel;
    }

    public void setComfortLevel(int comfortLevel) {
        this.comfortLevel = comfortLevel;
    }

    public List<Object> getSearchResults() {
        return searchResults;
    }

    public void setSearchResults(List<Object> searchResults) {
        this.searchResults = searchResults;
    }

    public String search() {
        return "complex-search-results";
    }
}