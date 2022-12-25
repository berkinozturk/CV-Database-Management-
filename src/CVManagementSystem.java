import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CVManagementSystem {
    public static void main(String[] args) {

        CV cv = new CV();
        CV cv2 = new CV();



        try {
            // Load the SQLite JDBC driver
            Class.forName("org.sqlite.JDBC");

            // Connect to the database
            String url = "jdbc:sqlite:/C:\\Program Files\\DB Browser for SQLite\\Tag.db";
            Connection conn = DriverManager.getConnection(url);

            // Do something with the connection...
            System.out.println("connected");

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.executeUpdate();
            conn.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        }


    }



