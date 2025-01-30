package fr.cyu.depinfo.agp.tahiti.persistence.bde;

import fr.cyu.depinfo.agp.tahiti.persistence.bde.lucene.LuceneFacade;
import fr.cyu.depinfo.agp.tahiti.persistence.bde.operators.JoinOperator;
import fr.cyu.depinfo.agp.tahiti.persistence.bde.operators.SQLOperator;
import fr.cyu.depinfo.agp.tahiti.persistence.bde.operators.TextOperator;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class QueryBuilderTest {
    private static QueryBuilder queryBuilder;
    private static final String TEST_DIR = "test-dir";
    private static final String TEST_DIR_INDEX = "test-dir-index";
    private static BDeAPI bdeAPI = BDeAPI.getInstance();

    @BeforeAll
    static void setUp() {

        // Créer le répertoire pour les tests
        File directory = new File(TEST_DIR);
        if (!directory.exists()) {
            if (directory.mkdir()) {
                System.out.println("Répertoire créé : " + TEST_DIR);
            } else {
                throw new RuntimeException("Impossible de créer le répertoire pour les tests.");
            }
        }

        // Créer les fichiers de test
        try {
            FileWriter fwA = new FileWriter(TEST_DIR + "/1.txt");
            fwA.write("escalade");
            fwA.close();

            FileWriter fwB = new FileWriter(TEST_DIR + "/2.txt");
            fwB.write("plongée");
            fwB.close();

            FileWriter fwC = new FileWriter(TEST_DIR + "/3.txt");
            fwC.write("Escalader en escalade");
            fwC.close();
        } catch (IOException e) {
            System.out.println("Erreur lors de la création des fichiers de test.");
            e.printStackTrace();
        }

        bdeAPI.setTextSearchInfo("site", "id", TEST_DIR);
        bdeAPI.createTextIndex(TEST_DIR_INDEX);
        queryBuilder = bdeAPI.getQueryBuilder();
    }

    @AfterAll
    static void deleteFiles() {
        // Supprimer les fichiers et le répertoire
        try {
            File directory = new File(TEST_DIR);
            if (directory.exists()) {
                // Supprimer tous les fichiers dans le répertoire
                for (File file : directory.listFiles()) {
                    if (!file.delete()) {
                        System.out.println("Impossible de supprimer le fichier : " + file.getName());
                    }
                }
                // Supprimer le répertoire
                if (!directory.delete()) {
                    System.out.println("Impossible de supprimer le répertoire : " + TEST_DIR);
                } else {
                    System.out.println("Répertoire supprimé : " + TEST_DIR);
                }
            }

            // Supprimer le répertoire d'index après le test
            File indexDirectory = new File(TEST_DIR_INDEX);
            if (indexDirectory.exists()) {
                for (File file : indexDirectory.listFiles()) {
                    if (!file.delete()) {
                        System.out.println("Impossible de supprimer le fichier d'index : " + file.getName());
                    }
                }
                if (!indexDirectory.delete()) {
                    System.out.println("Impossible de supprimer le répertoire d'index : " + TEST_DIR_INDEX);
                } else {
                    System.out.println("Répertoire d'index supprimé : " + TEST_DIR_INDEX);
                }
            }

        } catch (Exception e) {
            System.out.println("Erreur lors de la suppression des fichiers de test.");
            e.printStackTrace();
        }
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

        Map<String, Object> result = executionPlan.next();

        assertNotNull(result);
        assertTrue(result.containsKey("name"));
        assertTrue(result.containsKey("email"));
        assertTrue(result.containsKey("age"));
        System.out.println(result);
    }

    @Test
    void testMixedQueryCreation() {
        String query = "SELECT * FROM site with Escalade";

        queryBuilder.constructQuery(query);
        ExecutionPlan executionPlan = queryBuilder.retrieveExecutionPlan();

        assertNotNull(executionPlan);
        assertTrue(executionPlan.getTopOperator() instanceof JoinOperator);

        JoinOperator joinOperator = (JoinOperator) executionPlan.getTopOperator();
        assertNotNull(joinOperator);
        assertNotNull(joinOperator.getLeftOperator());
        assertNotNull(joinOperator.getRightOperator());
        assertTrue(joinOperator.getLeftOperator() instanceof SQLOperator);
        assertTrue(joinOperator.getRightOperator() instanceof TextOperator);

        Map<String, Object> result;
        while ((result = executionPlan.next()) != null){
            assertTrue(result.containsKey("id"));
            System.out.println(result);
        }

    }
}