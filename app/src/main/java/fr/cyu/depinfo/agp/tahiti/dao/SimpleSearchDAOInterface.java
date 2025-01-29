package fr.cyu.depinfo.agp.tahiti.dao;

import fr.cyu.depinfo.agp.tahiti.business.locations.Location;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface SimpleSearchDAOInterface {

    List<Location> searchByPrice(int minPrice, int maxPrice);

    List<Location> searchByConfort(int confortLevel);

    List<Location> searchByIsland(String island);
}
