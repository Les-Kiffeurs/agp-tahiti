package fr.cyu.depinfo.agp.tahiti.persistence;

import fr.cyu.depinfo.agp.tahiti.business.locations.Hotel;
import fr.cyu.depinfo.agp.tahiti.business.locations.Position;
import fr.cyu.depinfo.agp.tahiti.business.locations.Rank;
import fr.cyu.depinfo.agp.tahiti.dao.HotelDAOInterface;
import fr.cyu.depinfo.agp.tahiti.persistence.bde.BDeAPI;
import fr.cyu.depinfo.agp.tahiti.persistence.bde.ExecutionPlan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class HotelDAO implements HotelDAOInterface {

    private BDeAPI bdeAPI;

    public BDeAPI getBdeAPI() {
        return bdeAPI;
    }

    @Autowired
    public void setBdeAPI(BDeAPI bdeAPI) {
        this.bdeAPI = bdeAPI;
    }

    public HotelDAO() {
    }

    @Override
    public List<Hotel> searchByPrice(int minPrice, int maxPrice) {
        String query = String.format("SELECT * FROM hotel WHERE price_per_night >= %d AND price_per_night <= %d", minPrice, maxPrice);

        return executeQuery(query);
    }

    @Override
    public List<Hotel> getAllHotels() {
        String query = String.format("SELECT * FROM hotel");

        return executeQuery(query);
    }

    @Override
    public List<Hotel> searchByConfort(int confortLevel) {
        String query = "";

        return executeQuery(query);
    }

    @Override
    public List<Hotel> searchByIsland(String island) {
        String query = String.format("SELECT hotel.* FROM hotel, island WHERE island.id = hotel.island_id AND island.name = '%s'", island);

        return executeQuery(query);
    }

    @Override
    public List<Hotel> searchByRating(int rate) {
        String query = String.format("SELECT * FROM hotel WHERE rating >= %d", rate);

        return executeQuery(query);
    }

    private List<Hotel> createListResult(ExecutionPlan executionPlan) {
        Map<String, Object> result;
        ArrayList<Hotel> results = new ArrayList<>();

        while ((result = executionPlan.next()) != null) {
            int pricePerNight = (int) result.get("price_per_night");
            String name = (String) result.get("name");
            int id = (int) result.get("id");
            double latitude = ((Float) result.get("latitude")).doubleValue();
            double longitude = ((Float) result.get("longitude")).doubleValue();
            Position pos = new Position(latitude, longitude);
            String beach = (String) result.get("beach");
            int islandId = (int) result.get("island_id");
            float rating = (Float) result.get("rating");
            String address = (String) result.get("address");



            Hotel hotel = new Hotel(id,name,address,islandId,rating,pos,beach,pricePerNight);

            results.add(hotel);
        }

        return results;
    }

    private List<Hotel> executeQuery(String query) {
        ExecutionPlan executionPlan = bdeAPI.query(query);
        executionPlan.executeQuery();
        return createListResult(executionPlan);
    }
}
