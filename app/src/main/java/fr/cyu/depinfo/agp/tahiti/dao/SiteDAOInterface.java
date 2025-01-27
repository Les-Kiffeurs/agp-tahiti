package fr.cyu.depinfo.agp.tahiti.dao;

import java.util.HashMap;
import java.util.List;

public interface SiteDAOInterface extends SimpleSearchDAOInterface {

    List<HashMap<String, String>> searchByKeyword(String keyword);

    List<HashMap<String, String>> searchByType(String type);
}
