package ucu.edu.ua;

public class MockedDocument implements Document {
    private final String gcsPath;

    public MockedDocument(String gcsPath) {
        this.gcsPath = gcsPath;
    }

    @Override
    public String parse() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "Mocked content from: " + gcsPath;
    }

    @Override
    public String getGcsPath() {
        return gcsPath;
    }
}
