package fr.cyu.depinfo.agp.tahiti.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface SimpleSearchDAOInterface {

    List<Map<String, String>> searchByPrice(int minPrice, int maxPrice);

    List<Map<String, String>> searchByConfort(int confortLevel);

    List<Map<String, String>> searchByIsland(String island);
}
