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
        // Test pour une requête SQL simple
        String query = "SELECT * FROM users";

        queryBuilder.constructQuery(query);

        ExecutionPlan executionPlan = queryBuilder.retrieveExecutionPlan();

        // Vérification de la validité de l'ExecutionPlan
        assertNotNull(executionPlan, "L'executionPlan ne doit pas être nul pour une requête SQL simple.");
        assertTrue(executionPlan.getTopOperator() instanceof SQLOperator,
                "L'opérateur dans l'ExecutionPlan doit être un SQLOperator pour une requête SQL simple.");
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