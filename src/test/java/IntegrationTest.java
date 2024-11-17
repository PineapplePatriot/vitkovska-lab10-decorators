import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.*;
import ucu.edu.ua.Document;
import ucu.edu.ua.MockedDocument;
import ucu.edu.ua.CachedDocument;
import ucu.edu.ua.TimedDocument;


class IntegrationTest {
    @Test
    void testDecoratorsIntegration() {
        Document mockedDocument = new MockedDocument("gs://mock-path/integration-test.txt");
        Document cachedDocument = new CachedDocument(mockedDocument);
        Document timedDocument = new TimedDocument(cachedDocument);
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        String result = timedDocument.parse();
        assertEquals("Mocked content from: gs://mock-path/integration-test.txt", result);
        assertTrue(output.toString().contains("Parsing took"));

        String cachedResult = timedDocument.parse();
        assertEquals(result, cachedResult);
        assertTrue(output.toString().contains("Cache hit"));
    }
}
