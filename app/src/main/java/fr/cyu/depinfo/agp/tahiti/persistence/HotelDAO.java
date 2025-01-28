package fr.cyu.depinfo.agp.tahiti.persistence;

import fr.cyu.depinfo.agp.tahiti.dao.HotelDAOInterface;
import fr.cyu.depinfo.agp.tahiti.persistence.bde.BDeAPI;
import fr.cyu.depinfo.agp.tahiti.persistence.bde.ExecutionPlan;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HotelDAO implements HotelDAOInterface {

    private final BDeAPI bdeAPI;

    public HotelDAO(BDeAPI bdeAPI) {
        this.bdeAPI = bdeAPI;
    }

    @Override
    public List<Map<String, String>> searchByPrice(int minPrice, int maxPrice) {

        String query = String.format("SELECT * FROM hotel WHERE price >= %d AND price <= %d", minPrice, maxPrice);

        ExecutionPlan executionPlan = bdeAPI.query(query);

        executionPlan.init();

        return createListResult(executionPlan);
    }

    @Override
    public List<Map<String, String>> searchByConfort(int confortLevel) {
        return List.of();
    }

    @Override
    public List<Map<String, String>> searchByIsland(String island) {
        return List.of();
    }

    @Override
    public List<Map<String, String>> searchByRating(int rate) {
        return List.of();
    }

    private List<Map<String, String>> createListResult(ExecutionPlan executionPlan) {
        Map<String, String> result;
        ArrayList<Map<String, String>> results = new ArrayList<>();

        while ((result = executionPlan.next()) != null) {
            results.add(result);
        }

        return results;
    }
}
