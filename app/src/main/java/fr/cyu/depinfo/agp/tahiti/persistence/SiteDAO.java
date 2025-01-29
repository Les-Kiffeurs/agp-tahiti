package fr.cyu.depinfo.agp.tahiti.persistence;

import fr.cyu.depinfo.agp.tahiti.business.locations.Location;
import fr.cyu.depinfo.agp.tahiti.dao.SiteDAOInterface;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SiteDAO implements SiteDAOInterface {
    @Override
    public List<Location> searchByPrice(int minPrice, int maxPrice) {
        return List.of();
    }

    @Override
    public List<Location> searchByConfort(int confortLevel) {
        return List.of();
    }

    @Override
    public List<Location> searchByKeyword(String keyword) {
        return List.of();
    }

    @Override
    public List<Location> searchByType(String type) {
        return List.of();
    }

    @Override
    public List<Location> searchByIsland(String island) {
        return List.of();
    }
}
