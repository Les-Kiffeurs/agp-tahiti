package fr.cyu.depinfo.agp.tahiti.persistence.bde;

import java.util.Map;

public class JoinOperator extends AbstractComplexOperator{

    public JoinOperator(OperatorInterface leftOperator, OperatorInterface rightOperator) {
        super(leftOperator, rightOperator);
    }

    @Override
    public void init(String query) {

    }

    @Override
    public Map<String, String> next() {
        return Map.of();
    }
}
