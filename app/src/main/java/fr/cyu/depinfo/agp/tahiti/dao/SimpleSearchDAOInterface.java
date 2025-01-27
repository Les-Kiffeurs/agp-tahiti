package fr.cyu.depinfo.agp.tahiti.dao;

import java.util.HashMap;
import java.util.List;

public interface SimpleSearchDAOInterface {

    List<HashMap<String, String>> searchByPrice(int minPrice, int maxPrice);

    List<HashMap<String, String>> searchByConfort(int confortLevel);

    List<HashMap<String, String>> searchByIsland(String island);
}
