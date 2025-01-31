package fr.cyu.depinfo.agp.tahiti.persistence.bde.operators;

import java.util.List;

public abstract class AbstractComplexOperator implements OperatorInterface{

    private OperatorInterface leftOperator;
    private OperatorInterface rightOperator;
    private List<String> finalAttributes;

    public AbstractComplexOperator(OperatorInterface leftOperator, OperatorInterface rightOperator, List<String> finalAttributes) {
        this.leftOperator = leftOperator;
        this.rightOperator = rightOperator;
        this.finalAttributes = finalAttributes;
    }

    public OperatorInterface getRightOperator() {
        return rightOperator;
    }

    public AbstractComplexOperator setRightOperator(OperatorInterface rightOperator) {
        this.rightOperator = rightOperator;
        return this;
    }

    public OperatorInterface getLeftOperator() {
        return leftOperator;
    }

    public AbstractComplexOperator setLeftOperator(OperatorInterface leftOperator) {
        this.leftOperator = leftOperator;
        return this;
    }

    public List<String> getFinalAttributes() {
        return finalAttributes;
    }

    public void setFinalAttributes(List<String> finalAttributes) {
        this.finalAttributes = finalAttributes;
    }
}
