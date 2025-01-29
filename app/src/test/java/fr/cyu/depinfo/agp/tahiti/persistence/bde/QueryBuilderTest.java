package fr.cyu.depinfo.agp.tahiti.persistence.bde;

import fr.cyu.depinfo.agp.tahiti.persistence.bde.operators.SQLOperator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class QueryBuilderTest {
    private QueryBuilder queryBuilder;

    @BeforeEach
    void setUp() {
        queryBuilder = new QueryBuilder();
    }

    @Test
    void testConstructQuery_SQLQuery() {
        String query = "SELECT * FROM test_table";

        queryBuilder.constructQuery(query);

        ExecutionPlan executionPlan = queryBuilder.retrieveExecutionPlan();

        assertNotNull(executionPlan, "The executionPlan can't be null for a simple search");
        assertTrue(executionPlan.getTopOperator() instanceof SQLOperator,
                "The executionPlan isn't SQLOperator for a simple search");
    }

    @Test
    void testSQLQueryCreation() {
        String query = "SELECT * FROM test_table";

        queryBuilder.constructQuery(query);

        ExecutionPlan executionPlan = queryBuilder.retrieveExecutionPlan();

        assertNotNull(executionPlan);
        assertTrue(executionPlan.getTopOperator() instanceof SQLOperator);

        Map<String, String> result = executionPlan.next();

        assertNotNull(result);
        assertTrue(result.containsKey("name"));
        assertTrue(result.containsKey("email"));
        assertTrue(result.containsKey("age"));
        System.out.println(result);
    }
}