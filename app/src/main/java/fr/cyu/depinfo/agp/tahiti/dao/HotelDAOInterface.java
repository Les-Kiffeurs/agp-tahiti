package fr.cyu.depinfo.agp.tahiti.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface HotelDAOInterface extends SimpleSearchDAOInterface{

    List<Map<String, String>> searchByRating(int rate);
}
