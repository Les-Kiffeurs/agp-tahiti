package fr.cyu.depinfo.agp.tahiti.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface SiteDAOInterface extends SimpleSearchDAOInterface {

    List<Map<String, String>> searchByKeyword(String keyword);

    List<Map<String, String>> searchByType(String type);
}
