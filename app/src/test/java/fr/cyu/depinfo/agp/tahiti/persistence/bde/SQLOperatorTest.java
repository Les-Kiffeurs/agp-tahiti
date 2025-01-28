package fr.cyu.depinfo.agp.tahiti.persistence.bde;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class SQLOperatorTest {

    private static SQLOperator sqloperator;

    @BeforeAll
    static void setUpBeforeClass() throws Exception {
        String query = "SELECT name, email FROM test_table WHERE age>=30";

        sqloperator = new SQLOperator(query);
    }

    @Test
    void testNext(){

        sqloperator.init();

        Map<String, String> result = sqloperator.next();

        assertNotNull(result);
        assertTrue(result.containsKey("name"));
        assertTrue(result.containsKey("email"));
        System.out.println(result);

        result = sqloperator.next();
        assertNotNull(result);
        assertTrue(result.containsKey("name"));
        assertTrue(result.containsKey("email"));
        System.out.println(result);

        result = sqloperator.next();

        assertNull(result);
    }

}