package fr.cyu.depinfo.agp.tahiti.beans;



import java.util.List;


public class SimpleBean {
    private String keyword;
    private List<Object> searchResults; // Replace Object with your result type

    // Getters and setters

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public List<Object> getSearchResults() {
        return searchResults;
    }

    public void setSearchResults(List<Object> searchResults) {
        this.searchResults = searchResults;
    }

    public String search() {
        // Perform simple search based on the keyword
        // Example (replace with your actual search logic):
        // searchResults = dataAccessService.findEntitiesByKeyword(keyword);

        return "simple-search-results";
    }
}