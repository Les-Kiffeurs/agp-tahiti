package fr.cyu.depinfo.agp.tahiti.dao;

import fr.cyu.depinfo.agp.tahiti.business.locations.Hotel;
import fr.cyu.depinfo.agp.tahiti.business.locations.Location;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface HotelDAOInterface extends SimpleSearchDAOInterface<Hotel>{

    List<Hotel> searchByRating(int rate);
    List<Hotel> getAllHotels();
}
