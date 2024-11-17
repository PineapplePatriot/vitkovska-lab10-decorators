import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.*;
import ucu.edu.ua.Document;
import ucu.edu.ua.MockedDocument;
import ucu.edu.ua.TimedDocument;

class TimedDocumentTest {
    @Test
    void testTimedParse() {
        Document mockedDocument = new MockedDocument("gs://mock-path/mock-document.txt");
        TimedDocument timedDocument = new TimedDocument(mockedDocument);

        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));
        String result = timedDocument.parse();
        assertEquals("Mocked content from: gs://mock-path/mock-document.txt", result);
        assertTrue(output.toString().contains("Parsing took"));
    }
}
