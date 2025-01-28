package fr.cyu.depinfo.agp.tahiti.persistence;

import fr.cyu.depinfo.agp.tahiti.dao.SiteDAOInterface;

import java.util.HashMap;
import java.util.List;

public class SiteDAO implements SiteDAOInterface {
    @Override
    public List<HashMap<String, String>> searchByPrice(int minPrice, int maxPrice) {
        return List.of();
    }

    @Override
    public List<HashMap<String, String>> searchByConfort(int confortLevel) {
        return List.of();
    }

    @Override
    public List<HashMap<String, String>> searchByKeyword(String keyword) {
        return List.of();
    }

    @Override
    public List<HashMap<String, String>> searchByType(String type) {
        return List.of();
    }

    @Override
    public List<HashMap<String, String>> searchByIsland(String island) {
        return List.of();
    }
}
