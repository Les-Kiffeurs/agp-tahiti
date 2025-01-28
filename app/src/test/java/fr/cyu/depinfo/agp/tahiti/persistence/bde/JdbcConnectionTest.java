package fr.cyu.depinfo.agp.tahiti.persistence.bde;

import org.junit.jupiter.api.Test;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class JdbcConnectionTest {

    @Test
    void connectionTest() {

        assertNotNull(JdbcConnection.getConnection());

    }

    @Test
    void testQuery() {

        String query = "select name from test_table where age=25";

        String name;
        try {
            PreparedStatement ps = JdbcConnection.getConnection().prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            rs.next();
            name = rs.getString(1);
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        assertNotNull(name);
        assertTrue(name.equals("Alice"));

    }

}