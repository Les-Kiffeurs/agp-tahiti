package fr.cyu.depinfo.agp.tahiti.dao;

import fr.cyu.depinfo.agp.tahiti.business.locations.Location;
import fr.cyu.depinfo.agp.tahiti.business.locations.Position;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface SiteDAOInterface extends SimpleSearchDAOInterface {

    List<Location> searchByKeyword(String keyword);

    List<Location> searchByType(String type);
}
