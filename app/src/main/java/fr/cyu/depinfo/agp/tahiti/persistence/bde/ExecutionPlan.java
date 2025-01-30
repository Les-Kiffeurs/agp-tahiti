package fr.cyu.depinfo.agp.tahiti.persistence.bde;

import fr.cyu.depinfo.agp.tahiti.persistence.bde.operators.OperatorInterface;

import java.util.Map;

public class ExecutionPlan {
    OperatorInterface topOperator;

    public ExecutionPlan(OperatorInterface topOperator) {
        this.topOperator = topOperator;
    }

    public OperatorInterface getTopOperator() {
        return topOperator;
    }

    public ExecutionPlan setTopOperator(OperatorInterface topOperator) {
        this.topOperator = topOperator;
        return this;
    }

    public void init(){
        topOperator.init();
    }

    public void executeQuery(){
        topOperator.executeQuery();
    }

    public Map<String, Object> next(){
        return topOperator.next();
    }
}
