package fr.cyu.depinfo.agp.tahiti.persistence.bde;

import java.util.Map;

public class TextOperator extends AbstractFinalOperator{

    private TextResults textResults;

    public TextOperator(String query) {
        super(query);
    }

    @Override
    public void init(String query) {

    }

    @Override
    public Map<String, String> next() {
        return Map.of();
    }
}
