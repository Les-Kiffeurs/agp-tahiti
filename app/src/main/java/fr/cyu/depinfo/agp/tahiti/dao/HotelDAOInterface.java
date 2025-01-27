package fr.cyu.depinfo.agp.tahiti.dao;

import java.util.HashMap;
import java.util.List;

public interface HotelDAOInterface extends SimpleSearchDAOInterface{

    List<HashMap<String, String>> searchByRating(int rate);
}
