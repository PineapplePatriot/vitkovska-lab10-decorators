import org.junit.jupiter.api.Test;
import ucu.edu.ua.Document;
import ucu.edu.ua.MockedDocument;

import static org.junit.jupiter.api.Assertions.*;

class MockedDocumentTest {
    @Test
    void testParse() {
        Document mockedDocument = new MockedDocument("gs://mock-path/mock-document.txt");
        String result = mockedDocument.parse();

        assertEquals("Mocked content from: gs://mock-path/mock-document.txt", result);
    }

    @Test
    void testGetGcsPath() {
        Document mockedDocument = new MockedDocument("gs://mock-path/mock-document.txt");
        assertEquals("gs://mock-path/mock-document.txt", mockedDocument.getGcsPath());
    }
}
