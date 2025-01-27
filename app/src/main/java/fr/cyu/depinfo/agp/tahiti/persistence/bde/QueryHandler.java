package fr.cyu.depinfo.agp.tahiti.persistence.bde;

public class QueryHandler {

    private String tableName;
    private String pathToFiles;
    private String keyColumnName;

    public QueryResponse search(String query){

    }

    private QueryResponse handleSQL(String sql){

    }

    private QueryResponse handleMixed(String sql){

    }

    public String getTableName() {
        return tableName;
    }

    public QueryHandler setTableName(String tableName) {
        this.tableName = tableName;
        return this;
    }

    public String getPathToFiles() {
        return pathToFiles;
    }

    public QueryHandler setPathToFiles(String pathToFiles) {
        this.pathToFiles = pathToFiles;
        return this;
    }

    public String getKeyColumnName() {
        return keyColumnName;
    }

    public QueryHandler setKeyColumnName(String keyColumnName) {
        this.keyColumnName = keyColumnName;
        return this;
    }
}
