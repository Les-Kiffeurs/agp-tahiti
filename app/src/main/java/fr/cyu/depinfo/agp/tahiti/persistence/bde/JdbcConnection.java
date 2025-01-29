package fr.cyu.depinfo.agp.tahiti.persistence.bde;

import java.sql.Connection;
import java.sql.DriverManager;

public class JdbcConnection {

    private static String user = "root";
    private static String password = "toor";
    private static String url = "jdbc:postgresql://database:5432/test_database";

    /**
     * Lazy singleton instance.
     */
    private static Connection connection;


    public static Connection getConnection() {
        if (connection == null) {
            try {
                DriverManager.registerDriver(new org.postgresql.Driver());
                connection = DriverManager.getConnection(url, user, password);
            } catch (Exception e) {
                System.err.println("Connection failed : " + e.getMessage());
            }
        }
        return connection;
    }

}
