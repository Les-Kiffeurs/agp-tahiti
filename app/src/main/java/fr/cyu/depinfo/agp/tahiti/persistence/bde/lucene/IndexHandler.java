package fr.cyu.depinfo.agp.tahiti.persistence.bde.lucene;

import java.io.*;
import java.nio.file.*;

import org.apache.lucene.analysis.*;
import org.apache.lucene.analysis.standard.*;
import org.apache.lucene.document.*;
import org.apache.lucene.index.*;
import org.apache.lucene.store.*;


import java.io.IOException;

public class IndexHandler {

    private IndexWriter indexWriter;
    private Path indexPath;

    public void createIndex(String destPath) {
        try {
            indexPath = FileSystems.getDefault().getPath(destPath);
            File lockFile = new File(indexPath.toFile(), "write.lock");
            if (lockFile.exists()) {
                lockFile.delete(); // Supprimer le verrou s'il existe
            }

            Analyzer analyzer = new StandardAnalyzer();
            Directory index = FSDirectory.open(indexPath);  // Création de l'index sur disque
            IndexWriterConfig config = new IndexWriterConfig(analyzer);
            indexWriter = new IndexWriter(index, config);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public void addFilesToIndex(String filesPath) {
        File dir = new File(filesPath);
        if (!dir.exists() || !dir.isDirectory()) {
            throw new IllegalArgumentException("Le chemin spécifié n'existe pas ou n'est pas un répertoire : " + filesPath);
        }
        for (File file : dir.listFiles()) {
            if (!file.isDirectory()) {
                Document doc = new Document();
                doc.add(new Field("nom", file.getName(), TextField.TYPE_STORED));
                try {
                    doc.add(new TextField("contenu", new FileReader(file)));
                    indexWriter.addDocument(doc);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }


    public void closeWriter() {
        try {
            indexWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Path getIndexPath() {
        return indexPath;
    }
}
