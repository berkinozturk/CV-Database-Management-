import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.printing.PDFPageable;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

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
        DBConnection();
        try {
            CV.generateCV(1,"a","a","a", new String[]{"a"}, new String[]{"a"}, new String[]{"a"},"a","a",
                    new String[]{"a"}, new String[]{"a"}, 1L,"a");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static void DBConnection() {
        try {
            // Load the SQLite JDBC driver
            Class.forName("org.sqlite.JDBC");

            // Check if the database file exists
            File dbFile = new File("database");
            //Create the database file
            if(!dbFile.exists()) {
                dbFile.createNewFile();

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
            }else{
                // That means we are already have file named database and tag.db file exits
                // further implementation must be here
            }
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

    }

    private void menuBar(){
        menuBar = new JMenuBar();
        // Create the Tools and Help menu
        toolsMenu = new JMenu("Tools");
        importItem = new JMenuItem("Import CV");
        printItem = new JMenuItem("Print CV");
        printItem.setEnabled(false);
        generateItem = new JMenuItem("Generate CV");
        helpMenu = new JMenu("Help");
        helpMenuItem = new JMenuItem("Help?");
        defineActionListeners();

        toolsMenu.add(importItem);
        toolsMenu.add(printItem);
        toolsMenu.add(generateItem);
        helpMenu.add(helpMenuItem);

        menuBar.add(toolsMenu);
        menuBar.add(helpMenu);
    }
    private void searchButton(){

        Icon searchIcon = new ImageIcon("image/search-icon.png");
        searchButton = new JButton(searchIcon);
        searchButton.setVerticalTextPosition(AbstractButton.CENTER);
        searchButton.setHorizontalTextPosition(AbstractButton.RIGHT);
    }
    private void editingTag(){
        JTextField  textField = new JTextField();

    }
    private void generationCVFrame(){
        JFrame tagFrame = new JFrame();
        JPanel tagPanel = new JPanel();


    }
    private void defineActionListeners(){
        JFileChooser fileChooser = new JFileChooser();
        importItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int result = fileChooser.showOpenDialog(frame);

                // If the user selected a file, copy it to the system
                if (result == JFileChooser.APPROVE_OPTION){

                    // file is set to CV object
                    File file = fileChooser.getSelectedFile();
                    printItem.setEnabled(true);
                    // her türlü otomatik çekmen lazım add kısmını doldurman gerekiyor
                    // ya otomatik tagleri çek ya da çektikten sonra girmesine izin ver
                }
            }
        });

        printItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                File file = fileChooser.getSelectedFile();
                // Load the PDF file using PDFBox
                try (PDDocument document = PDDocument.load(file)) {
                    // Create a PDFPageable object to print the document
                    PDFPageable pageable = new PDFPageable(document);
                    PrinterJob job = PrinterJob.getPrinterJob();
                    job.setPageable(pageable);
                    if (job.printDialog()) {
                        job.print();
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        generateItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // open up UI to enter information that generated into CV
                generationCVFrame();

            }
        });

    }

}



