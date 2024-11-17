package ucu.edu.ua;

public class CachedDocument extends AbstractDecorator {
    private final DBConnection dbConnection;

    public CachedDocument(Document document) {
        super(document);
        this.dbConnection = DBConnection.getInstance();
    }

    @Override
    public String parse() {
        String gcsPath = document.getGcsPath();
        String cachedContent = dbConnection.getDocument(gcsPath);

        if (cachedContent != null) {
            System.out.println("Cache hit!");
            return cachedContent;
        }

        System.out.println("Cache miss. Fetching from source...");
        String content = super.parse();
        dbConnection.saveDocument(gcsPath, content);
        return content;
    }
}
