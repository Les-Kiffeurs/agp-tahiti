package fr.cyu.depinfo.agp.tahiti.persistence.bde;

import fr.cyu.depinfo.agp.tahiti.persistence.bde.lucene.LuceneFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.io.IOException;

@Component
public class BDeAPI {
    private LuceneFacade luceneFacade;
    private QueryBuilder queryBuilder;

    public void setTextSearchInfo(String tableName, String key, String path) {
        luceneFacade.setSrcFilePath(path);
        queryBuilder.setInfoTextSearch(tableName, key);
    }

    public void addDescription(String text, String key, String path) {
        try (FileWriter fw = new FileWriter(path + "/" + key + ".txt")) {
            fw.write(text);
        } catch (IOException e) {
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

    @Autowired
    public void setLuceneFacade(LuceneFacade luceneFacade) {
        this.luceneFacade = luceneFacade;
    }

    public QueryBuilder getQueryBuilder() {
        return queryBuilder;
    }

    @Autowired
    public void setQueryBuilder(QueryBuilder queryBuilder) {
        this.queryBuilder = queryBuilder;
    }
}
