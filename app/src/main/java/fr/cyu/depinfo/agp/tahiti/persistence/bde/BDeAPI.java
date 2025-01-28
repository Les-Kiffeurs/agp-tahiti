package fr.cyu.depinfo.agp.tahiti.persistence.bde;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;

public class BDeAPI {

    public void setTextSearchInfo(String tableName, String key, String path){
        createTextIndex();
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

    public void createTextIndex(){
        //luceneFacade = LuceneFacade();


    }

}
