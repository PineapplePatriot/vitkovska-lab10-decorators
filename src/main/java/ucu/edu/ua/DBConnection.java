package ucu.edu.ua;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBConnection {
    private static final String DB_URL = "jdbc:sqlite:cache.db";
    private static DBConnection instance;

    private DBConnection() {
        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            String createTableQuery = "CREATE TABLE IF NOT EXISTS document_cache (gcsPath TEXT PRIMARY KEY, content TEXT)";
            try (Statement stmt = conn.createStatement()) {
                stmt.execute(createTableQuery);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static DBConnection getInstance() {
        if (instance == null) {
            instance = new DBConnection();
        }
        return instance;
    }

    public String getDocument(String gcsPath) {
        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            String query = "SELECT content FROM document_cache WHERE gcsPath = ?";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, gcsPath);
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    return rs.getString("content");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void saveDocument(String gcsPath, String content) {
        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            String insertQuery = "INSERT OR REPLACE INTO document_cache (gcsPath, content) VALUES (?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(insertQuery)) {
                stmt.setString(1, gcsPath);
                stmt.setString(2, content);
                stmt.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
