package fr.cyu.depinfo.agp.tahiti.persistence.bde.lucene;

import org.apache.lucene.document.Document;
import org.apache.lucene.search.TopDocs;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class LuceneFacadeTest {

    private static final String TEST_DIR = "test-dir";
    private static final String TEST_DIR_INDEX = "test-dir-index";
    private static LuceneFacade luceneFacade = new LuceneFacade();

    @BeforeAll
    static void initFiles() {
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
            FileWriter fwA = new FileWriter(TEST_DIR + "/apple.txt");
            fwA.write("apple");
            fwA.close();

            FileWriter fwB = new FileWriter(TEST_DIR + "/banana.txt");
            fwB.write("banana");
            fwB.close();
        } catch (IOException e) {
            System.out.println("Erreur lors de la création des fichiers de test.");
            e.printStackTrace();
        }
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
    void checkFileCreation() {
        // Vérifier que les fichiers ont bien été créés
        File appleFile = new File(TEST_DIR + "/apple.txt");
        assertTrue(appleFile.exists(), "Le fichier apple.txt devrait exister.");

        File bananaFile = new File(TEST_DIR + "/banana.txt");
        assertTrue(bananaFile.exists(), "Le fichier banana.txt devrait exister.");
    }

    @Test
    void checkIndexCreation() {
        // Appel de la méthode pour créer l'index
        luceneFacade.setSrcFilePath(TEST_DIR);
        luceneFacade.createIndex(TEST_DIR_INDEX);

        // Vérification de l'existence du répertoire d'index
        File indexDir = new File(TEST_DIR_INDEX);
        assertTrue(indexDir.exists(), "Le répertoire d'index devrait exister après l'appel à createIndex.");

        // Vérification de la présence de fichiers dans le répertoire d'index
        // Par exemple, tu peux vérifier un fichier typique d'index Lucene comme .cfs
        boolean indexFilesExist = false;
        for (File file : indexDir.listFiles()) {
            if (file.getName().endsWith(".cfs")) {  // Vérifie la présence d'un fichier d'index
                indexFilesExist = true;
                break;
            }
        }

        assertTrue(indexFilesExist, "Le répertoire d'index devrait contenir des fichiers d'index.");
    }

    @Test
    void checkSearch() {
        TopDocs results = luceneFacade.search("apple");

        assertNotNull(results);

        int docId = results.scoreDocs[0].doc;
        Document d = luceneFacade.fetchDocumentById(docId);
        assertNotNull(d);
        assertTrue(d.get("nom").equals("apple.txt"));

    }
}