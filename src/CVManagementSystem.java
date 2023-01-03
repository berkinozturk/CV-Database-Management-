import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.*;

public class CVManagementSystem {
    private JFrame frame;
    private JPanel panel;
    private JMenuBar menuBar;
    private JMenu toolsMenu;
    private JMenu helpMenu;
    private JMenuItem addItem;
    private JMenuItem printItem;
    private JMenuItem generateItem;
    private JMenuItem helpMenuItem;
    private JButton searchButton;
    private JTextField searchField;

    private JButton TESTBUTON;
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
        DBConnection();


    }

    public static void DBConnection(){
        try {
            // Load the SQLite JDBC driver
            Class.forName("org.sqlite.JDBC");


            // Check if the database file exists
            File dbFile = new File("database");
            if(!dbFile.exists()){
                //Create the database file
                dbFile.createNewFile();
            }

            // Connect to the database
            String url = "jdbc:sqlite:Tag.db";
            Connection conn = DriverManager.getConnection(url);

            // Create the Tag & CV tables
            String sql =  "CREATE TABLE \"Tag\" (\n" +
                    "\t\"Name\"\tTEXT ,\n" +
                    "\t\"Surname\"\tTEXT ,\n" +
                    "\t\"Education\"\tTEXT,\n" +
                    "\t\"Languages\"\tTEXT,\n" +
                    "\t\"Experiences\"\tTEXT,\n" +
                    "\t\"Projects\"\tTEXT,\n" +
                    "\t\"Department\"\tTEXT,\n" +
                    "\t\"Address\"\tTEXT UNIQUE,\n" +
                    "\t\"ID\"\tINTEGER,\n" +
                    "\t\"Competencies\"\tTEXT,\n" +
                    "\t\"Certificates\"\tTEXT,\n" +
                    "\t\"PhoneNumber\"\tREAL UNIQUE,\n" +
                    "\t\"Date\"\tTEXT,\n" +
                    "\t\"About\"\tTEXT,\n" +
                    "\t\"CVFile\"\tBLOB,\n" +
                    "\tPRIMARY KEY(\"ID\" AUTOINCREMENT)\n" +
                ");";


            Statement stmt = conn.createStatement();
            stmt.executeUpdate(sql);

            System.out.println("connected");

            conn.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    private void addPanel(){
        panel = new JPanel();
        searchButton();
        searchField = new JTextField();
        searchField.setColumns(30);
        panel.add(searchField);
        panel.add(searchButton);

        TESTBUTON = new JButton("TEST OPEN CV id=1");
        panel.add(TESTBUTON);

        TESTBUTON.addActionListener(event -> {
                CV.openCV(1);
        });


    }

    private void menuBar(){
        menuBar = new JMenuBar();
        // Create the Tools and Help menu
        toolsMenu = new JMenu("Tools");
        addItem = new JMenuItem("Add CV");
        generateItem = new JMenuItem("Generate CV");
        helpMenu = new JMenu("Help");
        helpMenuItem = new JMenuItem("Help?");

        toolsMenu.add(addItem);
        toolsMenu.add(generateItem);
        helpMenu.add(helpMenuItem);

        menuBar.add(toolsMenu);
        menuBar.add(helpMenu);

        addItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AddCVScreen addCVFrame = new AddCVScreen();
                addCVFrame.setVisible(true);
            }
        });
        generateItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                GenerateCVScreen generateCVFrame = new GenerateCVScreen();
                generateCVFrame.setVisible(true);
            }
        });

    }
    private void searchButton(){

        Icon searchIcon = new ImageIcon("image/search-icon.png");
        searchButton = new JButton(searchIcon);
        searchButton.setVerticalTextPosition(AbstractButton.CENTER);
        searchButton.setHorizontalTextPosition(AbstractButton.RIGHT);
    }

}



