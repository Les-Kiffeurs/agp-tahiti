package fr.cyu.depinfo.agp.tahiti.dao;

import fr.cyu.depinfo.agp.tahiti.business.locations.Location;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface SimpleSearchDAOInterface<T> {

    List<T> searchByPrice(int minPrice, int maxPrice);

    List<T> searchByConfort(int confortLevel);

    List<T> searchByIsland(String island);
}
