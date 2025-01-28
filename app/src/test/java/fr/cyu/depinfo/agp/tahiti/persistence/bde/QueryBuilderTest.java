package fr.cyu.depinfo.agp.tahiti.persistence.bde;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QueryBuilderTest {
    private QueryBuilder queryBuilder;

    @BeforeEach
    void setUp() {
        queryBuilder = new QueryBuilder();
    }

    @Test
    void testConstructQuery_SQLQuery() {
        String query = "SELECT * FROM users";

        queryBuilder.constructQuery(query);

        ExecutionPlan executionPlan = queryBuilder.retrieveExecutionPlan();

        assertNotNull(executionPlan, "The executionPlan can't be null for a simple search");
        assertTrue(executionPlan.getTopOperator() instanceof SQLOperator,
                "The executionPlan isn't SQLOperator for a simple search");
    }

    @Test
    void testSQLQueryCreation() {
        String query = "SELECT * FROM users";

        queryBuilder.constructQuery(query);

        ExecutionPlan executionPlan = queryBuilder.retrieveExecutionPlan();

        assertNotNull(executionPlan);
    }

    @Test
    void testMixedQueryCreation() {
        String query = "SELECT * FROM users with MATCH(text)";

        queryBuilder.constructQuery(query);

        ExecutionPlan executionPlan = queryBuilder.retrieveExecutionPlan();

        assertNotNull(executionPlan);
    }
}