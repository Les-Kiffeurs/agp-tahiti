package fr.cyu.depinfo.agp.tahiti.persistence.bde;

import java.io.FileWriter;
import java.io.IOException;

public class BDeAPI {

    private QueryHandler queryHandler;

    public void setTextSearchInfo(String tableName, String key, String path){
        queryHandler.setTableName(tableName);
        queryHandler.setKeyColumnName(key);
        queryHandler.setPathToFiles(path);

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

    }

    public QueryResponse query(String query){

    }
}
