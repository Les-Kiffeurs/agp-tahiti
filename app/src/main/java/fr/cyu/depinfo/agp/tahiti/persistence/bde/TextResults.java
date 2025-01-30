package fr.cyu.depinfo.agp.tahiti.persistence.bde;

import java.util.Iterator;
import java.util.Map;
import java.util.List;

public class TextResults implements Iterator<Map<String, Integer>> {

    private List<Map<String, Integer>> results;
    private int index;

    @Override
    public boolean hasNext() {
        return false;
    }

    @Override
    public Map<String, Integer> next() {
        return Map.of();
    }
}
