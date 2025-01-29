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

        String SQLQuery = makeSQLQuery(queryParts[0]);

        SQLOperator sqlOperator = new SQLOperator(SQLQuery);

        TextOperator textOperator = new TextOperator(queryParts[1]);

        JoinOperator joinOperator = new JoinOperator(sqlOperator, textOperator, keyColumnName, null);

        executionPlan = new ExecutionPlan(joinOperator);
    }

    private String makeSQLQuery(String queryPart) {
        String whereClause = queryPart.split("WHERE")[1];
        String fromClause = queryPart.split("WHERE")[0].split("FROM")[1];
        String selectClause = queryPart.split("FROM")[0].split("SELECT")[1];
        return "";
    }

    private void SQLQueryCreation(String query){
        SQLOperator sqlOperator = new SQLOperator(query);

        executionPlan = new ExecutionPlan(sqlOperator);
    }
}
