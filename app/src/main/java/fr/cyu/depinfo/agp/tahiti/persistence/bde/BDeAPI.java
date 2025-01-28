package fr.cyu.depinfo.agp.tahiti.persistence.bde;

import fr.cyu.depinfo.agp.tahiti.persistence.bde.lucene.LuceneFacade;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;

public class BDeAPI {

    private LuceneFacade luceneFacade;
    private QueryBuilder queryBuilder;

    public void setTextSearchInfo(String tableName, String key, String path){
        luceneFacade.setSrcFilePath(path);
        queryBuilder.setInfoTextSearch(tableName, key);
    }

    public void addDescription(String text, String key, String path){
        try {
            FileWriter fw = new FileWriter(path + "/" + key + ".txt");
            fw.write(text);
            fw.close();
        } catch (IOException e) {
            System.out.println("Error writing to file");
            e.printStackTrace();
        }
    }

    public void createTextIndex(String destPath){
        luceneFacade.createIndex(destPath);
    }

}
