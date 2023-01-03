import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import java.sql.*;
import java.sql.SQLException;
import java.sql.PreparedStatement;
class testing {
    @Test
    void testDeleteCV() throws SQLException {
        int ID = 1;
        // Call the deleteCV method
        CV.deleteCV(ID);

        // Verify that the CV was correctly deleted from the database
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            // Connect to the database
            conn = DriverManager.getConnection("jdbc:sqlite:Tag.db");

            // Query the database for the test CV
            String sql = "SELECT COUNT(*) FROM Tag WHERE Id = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, ID);
            ResultSet rs = stmt.executeQuery();
            int count = rs.getInt(1);

            // Verify that the test CV was not found in the database
            assertEquals(0, count);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            // Close the connection and statement
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }
}
