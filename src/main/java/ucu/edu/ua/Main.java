package ucu.edu.ua;

public class Main {
    public static void main(String[] args) {
        Document mockedDocument = new MockedDocument("gs://mocked-path/document.txt");

        //TimedDocument decorator
        Document timedDocument = new TimedDocument(mockedDocument);
        System.out.println(timedDocument.parse());

        //CachedDocument decorator
        Document cachedDocument = new CachedDocument(mockedDocument);
        System.out.println(cachedDocument.parse());
        System.out.println(cachedDocument.parse());
    }
}
