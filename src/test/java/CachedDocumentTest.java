import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import ucu.edu.ua.Document;
import ucu.edu.ua.MockedDocument;
import ucu.edu.ua.CachedDocument;
import ucu.edu.ua.DBConnection;

class CachedDocumentTest {
    private DBConnection dbConnection;

    @BeforeEach
    void setup() {
        dbConnection = DBConnection.getInstance();
        dbConnection.saveDocument("gs://mock-path/mock-document.txt", "Mocked cached content");
    }

    @Test
    void testCacheHit() {
        Document mockedDocument = new MockedDocument("gs://mock-path/mock-document.txt");
        CachedDocument cachedDocument = new CachedDocument(mockedDocument);
        String result = cachedDocument.parse();
        assertEquals("Mocked cached content", result);
    }

    @Test
    void testCacheMiss() {
        Document mockedDocument = new MockedDocument("gs://mock-path/uncached-document.txt");
        CachedDocument cachedDocument = new CachedDocument(mockedDocument);

        String result = cachedDocument.parse();
        assertEquals("Mocked content from: gs://mock-path/uncached-document.txt", result);
        String cachedContent = dbConnection.getDocument("gs://mock-path/uncached-document.txt");
        assertEquals(result, cachedContent);
    }
}
