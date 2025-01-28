package fr.cyu.depinfo.agp.tahiti.persistence.bde.lucene;

import fr.cyu.depinfo.agp.tahiti.persistence.bde.TextResults;
import org.apache.lucene.document.Document;
import org.apache.lucene.search.TopDocs;

import java.nio.file.FileSystems;
import java.nio.file.Path;

public class LuceneFacade {

    private IndexHandler indexHandler;
    private SearchHandler searchHandler;

    private String srcFilePath;

    public LuceneFacade() {
        indexHandler = new IndexHandler();
        searchHandler = new SearchHandler();
    }

    public Document fetchDocumentById(int docID) {
        return searchHandler.searchDocById(docID);
    }

    public void setSrcFilePath(String srcFilePath){
        this.srcFilePath = srcFilePath;
    }

    public void createIndex(String destPath) {

        if (srcFilePath == null) {
            System.err.println("srcFilePath is not set");
            throw new RuntimeException("srcFilePath is not set");
        }

        indexHandler.createIndex(destPath);
        searchHandler.setIndexPath(indexHandler.getIndexPath());
        indexHandler.addFilesToIndex(srcFilePath);
        indexHandler.closeWriter();
    }

    public TopDocs search(String keyword) {
        return searchHandler.search(keyword);
    }

}
