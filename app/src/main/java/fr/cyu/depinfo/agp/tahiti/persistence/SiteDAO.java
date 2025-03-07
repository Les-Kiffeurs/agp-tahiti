package fr.cyu.depinfo.agp.tahiti.persistence;

import fr.cyu.depinfo.agp.tahiti.business.locations.*;
import fr.cyu.depinfo.agp.tahiti.dao.SiteDAOInterface;
import fr.cyu.depinfo.agp.tahiti.persistence.bde.BDeAPI;
import fr.cyu.depinfo.agp.tahiti.persistence.bde.ExecutionPlan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class SiteDAO implements SiteDAOInterface {
    private BDeAPI bdeAPI;

    public BDeAPI getBdeAPI() {
        return bdeAPI;
    }

    @Autowired
    public SiteDAO setBdeAPI(BDeAPI bdeAPI) {
        this.bdeAPI = bdeAPI;
        return this;
    }

    @Override
    public List<Site> searchByPrice(int minPrice, int maxPrice) {
        return List.of();
    }

    @Override
    public List<Site> searchByConfort(int confortLevel) {
        return List.of();
    }

    @Override
    public List<Site> getAllActivities() {
        String query = "SELECT * FROM site";
        return executeQuery(query);
    }

    @Override
    public List<Site> searchByKeyword(String keywords) {
        String query = String.format("SELECT * FROM site with %s", keywords);

        return executeQuery(query);
    }

    @Override
    public List<Site> searchByType(String type) {
        String query = String.format("SELECT * FROM site WHERE type = %s", type);

        return executeQuery(query);
    }

    @Override
    public Site getSiteById(String id) {
        String query = String.format("SELECT * FROM site WHERE id = %s", id);
        List<Site> sites = this.executeQuery(query);
        if (sites.isEmpty()) {
            return null;
        }
        return sites.getFirst();
    }


    @Override
    public List<Site> searchByIsland(String island) {
        return List.of();
    }

    private List<Site> createListResult(ExecutionPlan executionPlan) {
        Map<String, Object> result;
        ArrayList<Site> results = new ArrayList<>();
        HashMap<Float, Site> resultBeforeSort = new HashMap<>();

        while ((result = executionPlan.next()) != null) {
            int price = (int) result.get("price");
            String name = (String) result.get("name");
            int id = (int) result.get("id");
            double latitude = ((Float) result.get("latitude")).doubleValue();
            double longitude = ((Float) result.get("longitude")).doubleValue();
            Position pos = new Position(latitude, longitude);
            String type = (String) result.get("type");
            int islandId = (int) result.get("island_id");
            float rating = (Float) result.get("rating");
            String address = (String) result.get("address");
            int intensity = (int) result.get("intensity");

            Site site = new Site(id, name, address, islandId, rating, type, price, intensity, pos);

            if (result.get("score") != null) {
                resultBeforeSort.put((Float) result.get("score"), site);
            } else {
                results.add(site);
            }
        }

        if (!resultBeforeSort.isEmpty()) {
            resultBeforeSort.entrySet().stream()
                    .sorted(Map.Entry.<Float, Site>comparingByKey().reversed())
                    .forEach(entry -> results.add(entry.getValue()));
        }

        return results;
    }

    private List<Site> executeQuery(String query) {
        ExecutionPlan executionPlan = bdeAPI.query(query);
        executionPlan.executeQuery();
        return createListResult(executionPlan);
    }
}
