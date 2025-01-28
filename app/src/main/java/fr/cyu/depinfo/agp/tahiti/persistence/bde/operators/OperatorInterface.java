package fr.cyu.depinfo.agp.tahiti.persistence.bde.operators;

import java.util.Map;

public interface OperatorInterface {

    void init();
    Map<String, String> next();
}
