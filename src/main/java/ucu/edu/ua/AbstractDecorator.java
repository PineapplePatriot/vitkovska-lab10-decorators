package ucu.edu.ua;

public abstract class AbstractDecorator implements Document {
    protected Document document;

    public AbstractDecorator(Document document) {
        this.document = document;
    }

    @Override
    public String parse() {
        return document.parse();
    }

    @Override
    public String getGcsPath() {
        return document.getGcsPath();
    }
}
