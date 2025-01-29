package fr.cyu.depinfo.agp.tahiti.persistence.bde.operators;

import fr.cyu.depinfo.agp.tahiti.persistence.bde.BDeAPI;
import fr.cyu.depinfo.agp.tahiti.persistence.bde.TextResults;
import fr.cyu.depinfo.agp.tahiti.persistence.bde.lucene.LuceneFacade;
import org.apache.lucene.search.TopDocs;

import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class TextOperator extends AbstractFinalOperator{

    private TopDocs topDocs;

    public TextOperator(String query) {
        super(query);
    }

    @Override
    public void init() {
        BDeAPI api = BDeAPI.getInstance();
        LuceneFacade luceneFacade = api.getLuceneFacade();

        TopDocs topDocs = luceneFacade.search(getQuery());
    }

    @Override
    public Map<String, String> next() {
        if (topDocs == null || topDocs.scoreDocs.length == 0) {
            return null;
        }

        Map<String, String> map = new HashMap<>();
        try {
            for (int i = 0; i < topDocs.totalHits.value(); i++) {
                int docId = topDocs.scoreDocs[i].doc;

                BDeAPI api = BDeAPI.getInstance();
                LuceneFacade luceneFacade = api.getLuceneFacade();
                org.apache.lucene.document.Document doc = luceneFacade.fetchDocumentById(docId);

                for (org.apache.lucene.index.IndexableField field : doc.getFields()) {
                    String fieldName = field.name();
                    String fieldValue = doc.get(fieldName);
                    map.put(fieldName, fieldValue);
                }

                return map;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return null;
    }


}
