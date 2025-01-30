package fr.cyu.depinfo.agp.tahiti.persistence;

import fr.cyu.depinfo.agp.tahiti.business.locations.Hotel;
import fr.cyu.depinfo.agp.tahiti.business.locations.Site;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SiteDAOTest {

    private static SiteDAO siteDAO;
    private static final String TEST_DIR = "test-dir";
    private static final String TEST_DIR_INDEX = "test-dir-index";

    @BeforeAll
    static void setUpBeforeClass() throws Exception {
        siteDAO = new SiteDAO();

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

        siteDAO.getBdeAPI().setTextSearchInfo("activity", "id", TEST_DIR);
        siteDAO.getBdeAPI().createTextIndex(TEST_DIR_INDEX);
    }

    @Test
    void testKeywordsSearch() {
        String keyword = "escalade";

        List<Site> results = siteDAO.searchByKeyword(keyword);

        assertNotNull(results);

        for (Site result : results) {
            assertNotNull(result);
            assertTrue(result instanceof Site);
            System.out.println(result);
        }

    }

}