package fr.cyu.depinfo.agp.tahiti.persistence;

import fr.cyu.depinfo.agp.tahiti.dao.HotelDAOInterface;

import java.util.HashMap;
import java.util.List;

public class HotelDAO implements HotelDAOInterface {
    @Override
    public List<HashMap<String, String>> searchByPrice(int minPrice, int maxPrice) {
        return List.of();
    }

    @Override
    public List<HashMap<String, String>> searchByConfort(int confortLevel) {
        return List.of();
    }

    @Override
    public List<HashMap<String, String>> searchByIsland(String island) {
        return List.of();
    }

    @Override
    public List<HashMap<String, String>> searchByRating(int rate) {
        return List.of();
    }
}
