package fr.cyu.depinfo.agp.tahiti.persistence.bde;

import fr.cyu.depinfo.agp.tahiti.persistence.bde.lucene.LuceneFacade;
import fr.cyu.depinfo.agp.tahiti.persistence.bde.operators.JoinOperator;
import fr.cyu.depinfo.agp.tahiti.persistence.bde.operators.SQLOperator;
import fr.cyu.depinfo.agp.tahiti.persistence.bde.operators.TextOperator;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class QueryBuilderTest {

    private QueryBuilder queryBuilder;

    @BeforeEach
    void setUp() {
        queryBuilder = new QueryBuilder();
        queryBuilder.setInfoTextSearch("activity", "id");
    }

    @AfterEach
    void tearDown() {}

    @Test
    void testConstructQuerySQL(){
        String query = "SELECT * FROM hotel";

        queryBuilder.constructQuery(query);

        ExecutionPlan executionPlan = queryBuilder.retrieveExecutionPlan();
        assertNotNull(executionPlan);
        assertTrue(executionPlan.getTopOperator() instanceof SQLOperator);
    }

    @Test
    void testConstructQueryMixed(){
        String query = "SELECT * FROM activity with Test";

        queryBuilder.constructQuery(query);

        ExecutionPlan executionPlan = queryBuilder.retrieveExecutionPlan();
        assertNotNull(executionPlan);
        assertTrue(executionPlan.getTopOperator() instanceof JoinOperator);

        JoinOperator joinOperator = (JoinOperator) executionPlan.getTopOperator();
        assertTrue(joinOperator.getLeftOperator() instanceof SQLOperator);
        assertTrue(joinOperator.getRightOperator() instanceof TextOperator);
    }
}