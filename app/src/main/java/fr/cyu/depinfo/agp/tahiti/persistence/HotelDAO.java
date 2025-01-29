package fr.cyu.depinfo.agp.tahiti.persistence;

import fr.cyu.depinfo.agp.tahiti.business.locations.Hotel;
import fr.cyu.depinfo.agp.tahiti.business.locations.Location;
import fr.cyu.depinfo.agp.tahiti.business.locations.Position;
import fr.cyu.depinfo.agp.tahiti.business.locations.Rank;
import fr.cyu.depinfo.agp.tahiti.dao.HotelDAOInterface;
import fr.cyu.depinfo.agp.tahiti.persistence.bde.BDeAPI;
import fr.cyu.depinfo.agp.tahiti.persistence.bde.ExecutionPlan;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HotelDAO implements HotelDAOInterface {

    private final BDeAPI bdeAPI;

    public HotelDAO() {
        this.bdeAPI = BDeAPI.getInstance();
    }

    @Override
    public List<Location> searchByPrice(int minPrice, int maxPrice) {

        String query = String.format("SELECT * FROM hotel WHERE pricePerNight >= %d AND pricePerNight <= %d", minPrice, maxPrice);

        return executeQuery(query);
    }

    @Override
    public List<Location> searchByConfort(int confortLevel) {
        return List.of();
    }

    @Override
    public List<Location> searchByIsland(String island) {
        return List.of();
    }

    @Override
    public List<Location> searchByRating(int rate) {
        String query = String.format("SELECT * FROM hotel WHERE rating >= %d", rate);

        return executeQuery(query);
    }

    private List<Location> createListResult(ExecutionPlan executionPlan) {
        Map<String, Object> result;
        ArrayList<Location> results = new ArrayList<>();

        while ((result = executionPlan.next()) != null) {
            int pricePerNight = (int) result.get("pricepernight");
            String name = (String) result.get("name");
            int id = (int) result.get("id");
            double latitude = ((BigDecimal) result.get("latitude")).doubleValue();
            double longitude = ((BigDecimal) result.get("longitude")).doubleValue();
            Position pos = new Position(latitude, longitude);
            String beach = (String) result.get("beach");
            int islandId = (int) result.get("islandid");
            float rating = (Float) result.get("rating");
            String address = (String) result.get("address");
            String rank = (String) result.get("rank");



            Hotel hotel = new Hotel(id,name,address,islandId,rating,pos, Rank.valueOf(rank),beach,pricePerNight);

            results.add(hotel);
        }

        return results;
    }

    private List<Location> executeQuery(String query) {
        ExecutionPlan executionPlan = bdeAPI.query(query);
        return createListResult(executionPlan);
    }
}
