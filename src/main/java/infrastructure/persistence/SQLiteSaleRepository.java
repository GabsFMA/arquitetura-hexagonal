package infrastructure.persistence;

import core.model.Sale;
import core.ports.SaleRepository;
import java.sql.*;

public class SQLiteSaleRepository implements SaleRepository {
    private final Connection connection;

    public SQLiteSaleRepository(String dbPath) throws SQLException {
        this.connection = DriverManager.getConnection("jdbc:sqlite:" + dbPath);
        createTable();
    }

    private void createTable() throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS sales (" +
                     "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                     "total REAL NOT NULL," +
                     "timestamp DATETIME DEFAULT CURRENT_TIMESTAMP)";
        try (Statement stmt = connection.createStatement()) {
            stmt.execute(sql);
        }
    }

    @Override
    public void save(Sale sale) {
        String sql = "INSERT INTO sales(total) VALUES(?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setDouble(1, sale.getTotal());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}