package fr.cyu.depinfo.agp.tahiti.persistence;

import fr.cyu.depinfo.agp.tahiti.business.locations.Hotel;
import fr.cyu.depinfo.agp.tahiti.business.locations.Location;
import fr.cyu.depinfo.agp.tahiti.business.locations.Position;
import fr.cyu.depinfo.agp.tahiti.business.locations.Rank;
import fr.cyu.depinfo.agp.tahiti.dao.HotelDAOInterface;
import fr.cyu.depinfo.agp.tahiti.persistence.bde.BDeAPI;
import fr.cyu.depinfo.agp.tahiti.persistence.bde.ExecutionPlan;

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
        Map<String, String> result;
        ArrayList<Location> results = new ArrayList<>();

        while ((result = executionPlan.next()) != null) {
            int pricePerNight = Integer.parseInt(result.get("pricepernight"));
            String name = result.get("name");
            String id = result.get("id");
            double latitude = Double.parseDouble(result.get("latitude"));
            double longitude = Double.parseDouble(result.get("longitude"));
            Position pos = new Position(latitude, longitude);
            String beach = result.get("beach");
            int islandId = Integer.parseInt(result.get("islandid"));
            float rating = Float.parseFloat(result.get("rating"));
            String address = result.get("address");
            String rank = result.get("rank");



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
