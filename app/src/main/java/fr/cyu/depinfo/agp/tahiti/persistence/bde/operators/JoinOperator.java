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
    public void executeQuery() {
        getLeftOperator().executeQuery();
        getRightOperator().executeQuery();
    }

    @Override
    public Map<String, Object> next() {
        Map<String, Object> map = null;
        Map<String, Object> nextLeftResult;
        while (map == null && (nextLeftResult = getLeftOperator().next()) != null) {
            Map<String, Object> nextRightResult = getRightOperator().next();
            while (nextRightResult != null) {
                String leftKey = nextLeftResult.get(joinKey).toString();
                leftKey = leftKey.trim().replaceAll(" ", "-");
                if (nextRightResult.get(joinKey).equals(leftKey)) {

                    map = buildResultLine(nextLeftResult, nextRightResult);
                    break;
                }
                nextRightResult = getRightOperator().next();
            }
            if (map == null) {
                getRightOperator().init();
            }
        }
        return map;
    }

    private Map<String, Object> buildResultLine(Map<String, Object> leftResult, Map<String, Object> rightResult) {
        Map<String, Object> resultLine = new HashMap<String, Object>();

        for (String attribute : getFinalAttributes()) {

            if (attribute.equals("*")){
                resultLine.putAll(leftResult);
                break;
            }

            if (leftResult.containsKey(attribute)) {
                resultLine.put(attribute, leftResult.get(attribute));
            }

            if (rightResult.containsKey(attribute)) {
                resultLine.put(attribute, rightResult.get(attribute));
            }
        }
        resultLine.put("score", rightResult.getOrDefault("score", null));
        return resultLine;
    }
}
