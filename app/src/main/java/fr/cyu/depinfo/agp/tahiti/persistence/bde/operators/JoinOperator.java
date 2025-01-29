package fr.cyu.depinfo.agp.tahiti.persistence.bde.operators;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JoinOperator extends AbstractComplexOperator{

    private String joinKey;

    public JoinOperator(OperatorInterface leftOperator, OperatorInterface rightOperator, String joinKey, List<String> finalAttributes) {
        super(leftOperator, rightOperator, finalAttributes);
        this.joinKey = joinKey;
    }

    @Override
    public void init() {
        getLeftOperator().init();
        getRightOperator().init();
    }

    @Override
    public Map<String, Object> next() {
        Map<String, Object> map = null;
        while(map == null) {
            Map<String,Object> nextLeftResult = getLeftOperator().next();
            if (nextLeftResult != null) {
                Map<String,Object> nextRightResult = getRightOperator().next();
                while (nextRightResult != null) {

                    if(nextRightResult.get(joinKey).equals(nextLeftResult.get(joinKey))) {
                        map = buildResultLine(nextLeftResult, nextRightResult);
                        break;
                    }

                    nextRightResult = getRightOperator().next();
                }
                if (map == null) {
                    getRightOperator().init();
                }
            }
            else{
                getRightOperator().init();
            }
        }
        return map;
    }

    private Map<String, Object> buildResultLine(Map<String, Object> leftResult, Map<String, Object> rightResult) {
        Map<String, Object> resultLine = new HashMap<String, Object>();

        for (String attribute : getFinalAttributes()) {
            if (leftResult.containsKey(attribute)) {
                resultLine.put(attribute, leftResult.get(attribute));
            }

            if (rightResult.containsKey(attribute)) {
                resultLine.put(attribute, rightResult.get(attribute));
            }
        }

        return resultLine;
    }
}
