package fr.cyu.depinfo.agp.tahiti.persistence.bde.operators;

import java.util.Map;

public interface OperatorInterface {

    void init();
    void executeQuery();
    Map<String, Object> next();

}
