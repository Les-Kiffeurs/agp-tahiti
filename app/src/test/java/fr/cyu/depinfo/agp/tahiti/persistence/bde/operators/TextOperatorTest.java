package fr.cyu.depinfo.agp.tahiti.persistence.bde.operators;

import static org.junit.jupiter.api.Assertions.*;

import fr.cyu.depinfo.agp.tahiti.persistence.bde.BDeAPI;
import fr.cyu.depinfo.agp.tahiti.persistence.bde.lucene.LuceneFacade;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexableField;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Map;
import static org.mockito.Mockito.*;

class TextOperatorTest {
    private static TextOperator textOperator;
    private static BDeAPI mockBDeAPI;
    private static LuceneFacade mockLuceneFacade;
    private static TopDocs mockTopDocs;
    private static ScoreDoc[] mockScoreDocs;
    private static Document mockDocument;

    @BeforeAll
    static void setUp() {
        // Création du TextOperator avec une requête fictive
        textOperator = new TextOperator("test query");

        // Mock des dépendances
        mockBDeAPI = Mockito.mock(BDeAPI.class);
        mockLuceneFacade = Mockito.mock(LuceneFacade.class);
        mockTopDocs = Mockito.mock(TopDocs.class);
        mockDocument = Mockito.mock(Document.class);

        // Simuler la recherche Lucene et le comportement des documents
        when(mockBDeAPI.getLuceneFacade()).thenReturn(mockLuceneFacade);
        when(mockLuceneFacade.search(anyString())).thenReturn(mockTopDocs);

        // Simuler les résultats de ScoreDocs
        mockScoreDocs = new ScoreDoc[1];
        mockScoreDocs[0] = new ScoreDoc(1, 1.0f);
        when(mockTopDocs.scoreDocs).thenReturn(mockScoreDocs);

        // Simuler la récupération du document à partir de l'id
        when(mockLuceneFacade.fetchDocumentById(anyInt())).thenReturn(mockDocument);
        when(mockDocument.getFields()).thenReturn(List.of(new IndexableField[]{}));

        // Initialisation du TextOperator avec le mock BDeAPI
        textOperator.init();
    }

    @Test
    public void testInit() {
        // Vérifier que la recherche est bien appelée
        verify(mockLuceneFacade).search(anyString());
    }

    @Test
    public void testNext_ReturnsMapWithDocumentFields() {
        // Simuler les champs d'un document
        IndexableField mockField = Mockito.mock(IndexableField.class);
        when(mockField.name()).thenReturn("field1");
        when(mockDocument.get("field1")).thenReturn("value1");
        when(mockDocument.getFields()).thenReturn(List.of(new IndexableField[]{mockField}));

        // Appeler la méthode next()
        Map<String, String> result = textOperator.next();

        // Vérifier que le résultat contient le champ
        assertNotNull(result);
        assertEquals("value1", result.get("field1"));
    }

    @Test
    public void testNext_ReturnsNullWhenNoDocuments() {
        // Simuler aucun résultat de recherche
        when(mockTopDocs.scoreDocs).thenReturn(new ScoreDoc[]{});

        // Appeler la méthode next()
        Map<String, String> result = textOperator.next();

        // Vérifier que le résultat est null
        assertNull(result);
    }
}