package fr.cyu.depinfo.agp.tahiti.persistence.bde.operators;

import fr.cyu.depinfo.agp.tahiti.persistence.bde.BDeAPI;
import fr.cyu.depinfo.agp.tahiti.persistence.bde.lucene.LuceneFacade;
import lombok.Getter;
import org.apache.lucene.document.Document;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import java.util.*;

public class TextOperator extends AbstractFinalOperator {
    @Autowired
    @Getter
    private LuceneFacade luceneFacade;

    private TopDocs topDocs;
    private List<Map<String, Object>> docs;
    private Iterator<Map<String, Object>> iter;
    private String keyColumnName;

    public TextOperator(String query, String keyColumnName) {
        super(query);
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
        this.keyColumnName = keyColumnName;
    }

    @Override
    public void init() {
        iter = docs.iterator();
    }

    @Override
    public void executeQuery() {
        topDocs = luceneFacade.search(getQuery());

        docs = new ArrayList<>();

        for (ScoreDoc scoreDoc : topDocs.scoreDocs) {
            Map<String, Object> map = new HashMap<>();
            map.put("score", scoreDoc.score);
            Document d = luceneFacade.fetchDocumentById(scoreDoc.doc);
            String key = d.get("nom").split(".txt")[0];
            map.put(keyColumnName, key);
            docs.add(map);
        }

        iter = docs.iterator();
    }

    @Override
    public Map<String, Object> next() {
        if (iter.hasNext()) {
            return iter.next();
        }
        return null;
    }
}
