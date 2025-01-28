package fr.cyu.depinfo.agp.tahiti.persistence.bde;

import java.util.Iterator;
import java.util.Map;

public abstract class AbstractFinalOperator implements OperatorInterface {

    private String query;

    public AbstractFinalOperator(String query) {
        this.query = query;
    }

    private Iterator<Map<String, String>> resultIterator;

}
