package fr.cyu.depinfo.agp.tahiti.persistence.bde.lucene;

import java.io.*;
import java.nio.file.*;


import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.*;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.*;
import org.apache.lucene.store.*;

public class SearchHandler {

    private static final int MAX_RESULTS = 100;
    private Path indexPath;

    public SearchHandler() {}

    public TopDocs search(String query) {
        TopDocs resultats;
        try {
            Directory index = FSDirectory.open(indexPath);
            DirectoryReader ireader = DirectoryReader.open(index);

            IndexSearcher searcher = new IndexSearcher(ireader);

            Analyzer analyseur = new StandardAnalyzer();

            QueryParser qp = new QueryParser("contenu", analyseur);
            Query req = qp.parse(query);

            resultats = searcher.search(req, MAX_RESULTS);
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
        return resultats;
    }

    public SearchHandler setIndexPath(Path indexPath) {
        this.indexPath = indexPath;
        return this;
    }
}
