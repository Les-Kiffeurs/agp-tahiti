package fr.cyu.depinfo.agp.tahiti.persistence.bde;

import fr.cyu.depinfo.agp.tahiti.persistence.bde.lucene.LuceneFacade;

import java.io.FileWriter;
import java.io.IOException;

public class BDeAPI {
    private static BDeAPI instance;
    private LuceneFacade luceneFacade =  new LuceneFacade();
    private QueryBuilder queryBuilder = new QueryBuilder();

    private BDeAPI() {}

    public static synchronized BDeAPI getInstance() {
        if (instance == null) {
            instance = new BDeAPI();
        }
        return instance;
    }

    public void setTextSearchInfo(String tableName, String key, String path) {
        luceneFacade.setSrcFilePath(path);
        queryBuilder.setInfoTextSearch(tableName, key);
    }

    public void addDescription(String text, String key, String path) {
        try (FileWriter fw = new FileWriter(path + "/" + key + ".txt")) {
            fw.write(text);
        } catch (IOException e) {
            System.out.println("Error writing to file");
            e.printStackTrace();
        }
    }

    public void createTextIndex(String destPath) {
        luceneFacade.createIndex(destPath);
    }

    public ExecutionPlan query(String query) {
        queryBuilder.constructQuery(query);
        return queryBuilder.retrieveExecutionPlan();
    }

    public LuceneFacade getLuceneFacade() {
        return luceneFacade;
    }

    public void setLuceneFacade(LuceneFacade luceneFacade) {
        this.luceneFacade = luceneFacade;
    }

    public QueryBuilder getQueryBuilder() {
        return queryBuilder;
    }

    public void setQueryBuilder(QueryBuilder queryBuilder) {
        this.queryBuilder = queryBuilder;
    }
}
