import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CVManagementSystem {
    private JFrame frame;
    private JPanel panel;
    private JMenuBar menuBar;
    private JMenu toolsMenu;
    private JMenu helpMenu;
    private JMenuItem importItem;
    private JMenuItem printItem;
    private JMenuItem generateItem;
    private JMenuItem helpMenuItem;
    private JButton searchButton;
    private JTextField searchField;
    private final int width = 800,height = 600;

    public CVManagementSystem() {
        frame = new JFrame("CV DATABASE");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menuBar();
        addPanel();
        frame.setJMenuBar(menuBar);
        frame.add(panel);
        //TODO bunları sonra screen size değişirse bile aynı oranda olacak şekilde çalıştır
        frame.setSize(width, height);
        frame.setLocation(300,100);
        frame.setVisible(true);
    }
    public static void main(String[] args) {
        new CVManagementSystem();
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

            //PreparedStatement stmt = conn.prepareStatement(sql);
            //stmt.executeUpdate();
            conn.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

    }
    private void addPanel(){
        panel = new JPanel();
        searchButton();
        searchField = new JTextField();
        searchField.setColumns(30);
        panel.add(searchField);
        panel.add(searchButton);

    }

    private void menuBar(){
        menuBar = new JMenuBar();
        // Create the Tools and Help menu
        toolsMenu = new JMenu("Tools");
        importItem = new JMenuItem("Import CV");
        printItem = new JMenuItem("Print CV");
        generateItem = new JMenuItem("Generate CV");
        helpMenu = new JMenu("Help");
        helpMenuItem = new JMenuItem("Help?");

        toolsMenu.add(importItem);
        toolsMenu.add(printItem);
        toolsMenu.add(generateItem);
        helpMenu.add(helpMenuItem);

        menuBar.add(toolsMenu);
        menuBar.add(helpMenu);
    }
    private void searchButton(){

        Icon searchIcon = new ImageIcon("res/search-icon.png");
        searchButton = new JButton(searchIcon);
        searchButton.setVerticalTextPosition(AbstractButton.CENTER);
        searchButton.setHorizontalTextPosition(AbstractButton.RIGHT);
    }


}



