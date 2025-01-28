package fr.cyu.depinfo.agp.tahiti.persistence.bde.lucene;

import org.apache.lucene.document.Document;
import org.apache.lucene.search.TopDocs;

public class LuceneFacade {

    private IndexHandler indexHandler;
    private SearchHandler searchHandler;

    public LuceneFacade() {
        indexHandler = new IndexHandler();
        searchHandler = new SearchHandler();
    }

    public void createIndex(String srcPath, String destPath) {
        indexHandler.createIndex(destPath);
        System.out.println(indexHandler.getIndexPath());
        searchHandler.setIndexPath(indexHandler.getIndexPath());
        indexHandler.addFilesToIndex(srcPath);
        indexHandler.closeWriter();
    }

    public TopDocs search(String keyword) {
        return searchHandler.search(keyword);
    }

    public Document fetchDocumentById(int docID) {
        return searchHandler.searchDocById(docID);
    }

}
