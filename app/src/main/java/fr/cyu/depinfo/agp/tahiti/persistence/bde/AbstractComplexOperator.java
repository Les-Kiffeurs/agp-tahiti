package fr.cyu.depinfo.agp.tahiti.persistence.bde;

public abstract class AbstractComplexOperator implements OperatorInterface{

    private OperatorInterface leftOperator;
    private OperatorInterface rightOperator;

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
}
