package fr.cyu.depinfo.agp.tahiti.persistence.bde.lucene;

import java.io.*;
import java.nio.file.*;


import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.*;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.*;
import org.apache.lucene.store.*;

public class SearchHandler {

    private static final int MAX_RESULTS = 100;
    private Path indexPath;

    private IndexSearcher searcher;
    private DirectoryReader ireader;

    public SearchHandler() {}

    public TopDocs search(String query) {

        TopDocs resultats;

        String finalQuery = parseQuery(query);
        try {
            Directory index = FSDirectory.open(indexPath);
            ireader = DirectoryReader.open(index);

            searcher = new IndexSearcher(ireader);

            Analyzer analyseur = new StandardAnalyzer();

            QueryParser qp = new QueryParser("contenu", analyseur);
            Query req = qp.parse(finalQuery);

            resultats = searcher.search(req, MAX_RESULTS);
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
        return resultats;
    }

    public void closeReader() {
        try {
            ireader.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public Document searchDocById(int docId) {
        StoredFields storedFields;
        Document doc = null;
        try {
            storedFields = searcher.storedFields();
            doc = storedFields.document(docId);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return doc;
    }

    public void setIndexPath(Path indexPath) {
        this.indexPath = indexPath;
    }

    public String parseQuery(String query) {

        String fuzzyQuery = addFuzzyToWords(query);

        String finalQuery = fuzzyQuery.replaceAll(" ", " OR ");
        return finalQuery;
    }

    private String addFuzzyToWords(String query) {
        StringBuilder result = new StringBuilder();
        String[] tokens = query.split("\\s+");

        for (String token : tokens) {
            if (!token.equals("") && !token.startsWith("\"") && !token.endsWith("\"")) {
                result.append(token).append("~ "); // Ajouter "~" aux mots normaux
            } else {
                result.append(token).append(" "); // Garder les opérateurs intacts
            }
        }

        return result.toString().trim();
    }

}
