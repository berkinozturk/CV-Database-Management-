import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
    @Test
    public void testUpdateCV() throws SQLException {
        String name = "John";
        String surname = "Doe";
        String education = "Bachelor's Degree in Computer Science";
        String[] languages = {"Java", "C++", "Python"};
        String[] experiences = {"Software Engineer at Company X", "Intern at Company Y"};
        String[] projects = {"Project 1", "Project 2"};
        String department = "Engineering";
        String address = "123 Main St";
        String[] competencies = {"Problem solving", "Communication"};
        int id = 123;
        String[] certificates = {"Certificate 1", "Certificate 2"};
        long phoneNumber = 1234567890;
        String about = "I am a software engineer with experience in Java, C++, and Python.";

        CV.updateCV(name, surname, education, languages, experiences, projects, department, address, competencies, id,
                certificates, phoneNumber, about);

        Connection connect = null;
        PreparedStatement state = null;
        try {
            // Connect to the database
            connect = DriverManager.getConnection("jdbc:sqlite:Tag.db");

            // Retrieve the updated CV from the database
            state = connect.prepareStatement("SELECT * FROM TAG WHERE ID=?");
            state.setInt(1, id);
            ResultSet result = state.executeQuery();

            // Verify that the values in the CV are correct
            if (result.next()) {
                assertEquals(name, result.getString("Name"));
                assertEquals(surname, result.getString("Surname"));
                assertEquals(education, result.getString("Education"));
                assertArrayEquals(languages, result.getString("Languages").split(", "));
                assertArrayEquals(experiences, result.getString("Experiences").split(", "));
                assertArrayEquals(projects, result.getString("Projects").split(", "));
                assertEquals(department, result.getString("Department"));
                assertEquals(address, result.getString("Address"));
                assertArrayEquals(competencies, result.getString("Competencies").split(", "));
                assertArrayEquals(certificates, result.getString("Certificates").split(", "));
                assertEquals(phoneNumber, result.getLong("PhoneNumber"));
                assertEquals(about, result.getString("About"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            // Close the statement and connection
            if (state != null) {
                state.close();
            }
            if (connect != null) {
                connect.close();
            }
        }
    }

}
