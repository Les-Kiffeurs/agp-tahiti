package fr.cyu.depinfo.agp.tahiti.dao;

import fr.cyu.depinfo.agp.tahiti.business.locations.Location;
import fr.cyu.depinfo.agp.tahiti.business.locations.Position;
import fr.cyu.depinfo.agp.tahiti.business.locations.Site;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface SiteDAOInterface extends SimpleSearchDAOInterface<Site> {

    List<Site> searchByKeyword(String keyword);

    List<Site> searchByType(String type);

    Site getSiteById(String id);
}
