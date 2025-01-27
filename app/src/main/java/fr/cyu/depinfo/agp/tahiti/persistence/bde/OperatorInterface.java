package fr.cyu.depinfo.agp.tahiti.persistence.bde;

import java.util.Map;

public interface OperatorInterface {

    void init(String query);
    Map<String, String> next();
}
