package fr.cyu.depinfo.agp.tahiti.persistence.bde;

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
}
