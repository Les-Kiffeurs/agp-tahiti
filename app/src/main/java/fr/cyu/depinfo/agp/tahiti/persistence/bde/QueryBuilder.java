package fr.cyu.depinfo.agp.tahiti.persistence.bde;

import fr.cyu.depinfo.agp.tahiti.persistence.bde.operators.JoinOperator;
import fr.cyu.depinfo.agp.tahiti.persistence.bde.operators.SQLOperator;
import fr.cyu.depinfo.agp.tahiti.persistence.bde.operators.TextOperator;

import java.util.ArrayList;
import java.util.List;

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

        ArrayList<String> SQLQuery = makeSQLQuery(queryParts[0]);

        SQLOperator sqlOperator = new SQLOperator(SQLQuery.get(1));

        TextOperator textOperator = new TextOperator(queryParts[1], keyColumnName);

        List<String> finalAttributes = new ArrayList<String>();
        for (String attribute : SQLQuery.getFirst().split(",")) {
            finalAttributes.add(attribute.trim());
        }
        JoinOperator joinOperator = new JoinOperator(sqlOperator, textOperator, keyColumnName, finalAttributes);

        executionPlan = new ExecutionPlan(joinOperator);
    }

    private ArrayList<String> makeSQLQuery(String queryPart) {

        String whereClause = null;
        if (queryPart.contains("WHERE")){
            whereClause = queryPart.split("WHERE")[1];
        }

        String fromClause = queryPart.split("WHERE")[0].split("FROM")[1];
        String selectClause = queryPart.split("FROM")[0].split("SELECT")[1];

        ArrayList<String> result = new ArrayList<String>();

        result.add(selectClause);

        if(!fromClause.contains(tableName)){

            String table = fromClause.trim();

            fromClause += ", " + tableName;

            if (whereClause != null) {
                whereClause += " AND " + tableName + "." + keyColumnName + "=" + table + "." + keyColumnName ;
            }
            else {
                whereClause = tableName + "." + keyColumnName + "=" + table + "." + keyColumnName ;
            }
        }

        if (!selectClause.contains(keyColumnName) && !selectClause.contains("*")){
            selectClause += ", " + keyColumnName;
        }

        String newQuery = "SELECT " + selectClause.trim() +  " FROM " + fromClause.trim();
        if (whereClause != null){
            newQuery += " WHERE " + whereClause;
        }

        result.add(newQuery);
        return result;
    }

    private void SQLQueryCreation(String query){
        SQLOperator sqlOperator = new SQLOperator(query);

        executionPlan = new ExecutionPlan(sqlOperator);
    }
}
