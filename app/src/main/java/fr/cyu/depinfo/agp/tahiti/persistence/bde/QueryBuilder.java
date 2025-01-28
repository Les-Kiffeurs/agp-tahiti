package fr.cyu.depinfo.agp.tahiti.persistence.bde;

import fr.cyu.depinfo.agp.tahiti.persistence.bde.operators.JoinOperator;
import fr.cyu.depinfo.agp.tahiti.persistence.bde.operators.SQLOperator;
import fr.cyu.depinfo.agp.tahiti.persistence.bde.operators.TextOperator;

public class QueryBuilder {

    private ExecutionPlan executionPlan;

    private String tableName;
    private String keyColumnName;

    public QueryBuilder() {
    }

    public void setInfoTextSearch(String tableName, String keyColumnName) {
        this.tableName = tableName;
        this.keyColumnName = keyColumnName;
    }

    public void constructQuery(String query) {
        if(query.contains("with")) {
            mixedQueryCreation(query);
        }
        else{
            SQLQueryCreation(query);
        }
    }

    public ExecutionPlan retrieveExecutionPlan(){
        return executionPlan;
    }

    private void mixedQueryCreation(String query) {
        String[] queryParts = query.split("with");

        SQLOperator sqlOperator = new SQLOperator(queryParts[0]);

        TextOperator textOperator = new TextOperator(queryParts[1]);

        JoinOperator joinOperator = new JoinOperator(sqlOperator, textOperator);

        executionPlan = new ExecutionPlan(joinOperator);
    }

    private void SQLQueryCreation(String query){
        SQLOperator sqlOperator = new SQLOperator(query);

        executionPlan = new ExecutionPlan(sqlOperator);
    }
}
