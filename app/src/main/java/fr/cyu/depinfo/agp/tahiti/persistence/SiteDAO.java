package fr.cyu.depinfo.agp.tahiti.persistence;

import fr.cyu.depinfo.agp.tahiti.dao.SiteDAOInterface;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SiteDAO implements SiteDAOInterface {
    @Override
    public List<Map<String, String>> searchByPrice(int minPrice, int maxPrice) {
        return List.of();
    }

    @Override
    public List<Map<String, String>> searchByConfort(int confortLevel) {
        return List.of();
    }

    @Override
    public List<Map<String, String>> searchByKeyword(String keyword) {
        return List.of();
    }

    @Override
    public List<Map<String, String>> searchByType(String type) {
        return List.of();
    }

    @Override
    public List<Map<String, String>> searchByIsland(String island) {
        return List.of();
    }
}
