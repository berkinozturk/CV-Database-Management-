import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.sql.*;
import java.util.ArrayList;

public class CVManagementSystem {
    private JFrame frame;
    private JPanel panel;
    private JMenuBar menuBar;
    private JMenu toolsMenu;
    private JMenu helpMenu;
    private JMenuItem addItem;
    private JMenuItem generateItem;
    private JMenuItem helpMenuItem;
    private JButton searchButton;
    private JTextField searchField;
    private final int width = 800,height = 600;
    private JScrollPane jScrollPane1;
    private JTable jTable_CVs;

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
        DBConnection();
        new CVManagementSystem();

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
                    "\t\"Address\"\tTEXT,\n" +
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

    public ArrayList<Tag> ListUsers(String ValToSearch)
    {
        ArrayList<Tag> cvs = new ArrayList<Tag>();

        Statement st;
        ResultSet rs;

        try{
            Connection con = DriverManager.getConnection("jdbc:sqlite:Tag.db");
            st = con.createStatement();
            String searchQuery = "SELECT * FROM Tag WHERE (Name || Surname || Education || Languages || Experiences || Projects || Department || Address || Competencies || Certificates || PhoneNumber || About) LIKE '%"+ValToSearch+"%'";
            rs = st.executeQuery(searchQuery);

            Tag tag;

            while(rs.next())
            {
                tag = new Tag(rs.getString("Name"),
                        rs.getString("Surname"),
                        rs.getString("Education"),
                        rs.getString("Languages").split(","),
                        rs.getString("Experiences").split(","),
                        rs.getString("Projects").split(","),
                        rs.getString("Department"),
                        rs.getString("Address"),
                        rs.getInt("ID"),
                        rs.getString("Competencies").split(","),
                        rs.getString("Certificates").split(","),
                        rs.getLong("PhoneNumber"),
                        rs.getString("Date"),
                        rs.getString("About"));

                cvs.add(tag);
            }

        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }

        return cvs;
    }
    private void addPanel(){
        panel = new JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable_CVs = new javax.swing.JTable();
        searchButton();
        searchField = new JTextField();
        searchField.setColumns(30);
        panel.add(searchField);
        panel.add(searchButton);
        panel.add(jScrollPane1);
        panel.add(jTable_CVs);

        searchButton.addActionListener(event -> {

            ArrayList<Tag> CVs = ListUsers(searchField.getText());
            DefaultTableModel model = new DefaultTableModel();
            model.setColumnIdentifiers(new Object[]{"ID","Name","Surname","PhoneNumber"});
            Object[] row = new Object[4];

            for(int i = 0; i < CVs.size(); i++)
            {
                row[0] = CVs.get(i).getID();
                row[1] = CVs.get(i).getName();
                row[2] = CVs.get(i).getSurname();
                row[3] = CVs.get(i).getPhoneNumber();
                model.addRow(row);
            }
            jTable_CVs.setModel(model);

        });


        jTable_CVs.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {},
                new String [] {
                        "ID","Name","Surname","PhoneNumber"
                }
        ));

        jScrollPane1.setViewportView(jTable_CVs);

        jTable_CVs.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                CV.openCV((Integer) jTable_CVs.getValueAt(jTable_CVs.getSelectedRow(),0));
            }
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
        Help();

    }
    private void searchButton(){
        Icon searchIcon = new ImageIcon("search-icon.png");
        searchButton = new JButton(searchIcon);
        searchButton.setVerticalTextPosition(AbstractButton.CENTER);
        searchButton.setHorizontalTextPosition(AbstractButton.RIGHT);
    }
    private void Help(){
        helpMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("Help?");
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.setSize(1000, 600);
                JPanel panel = new JPanel();
                panel.setBorder(new TitledBorder(new EtchedBorder()));
                JTextArea textArea = new JTextArea(30,80);
                JScrollPane pane = new JScrollPane(textArea);
                pane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

                textArea.setEditable(false);
                JButton button = new JButton("Move Up");
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        pane.getVerticalScrollBar().setValue(pane.getVerticalScrollBar().getMinimum());
                    }
                });

                StringBuilder stringBuilder = new StringBuilder();

                try {
                    File file = new File("help.txt");
                    BufferedReader br = new BufferedReader( new FileReader(file));
                    String st;

                    while ((st = br.readLine()) != null ){
                        stringBuilder.append(st);
                        stringBuilder.append("\n");
                    }
                }catch (Exception exception){
                    exception.printStackTrace();
                }
                String last = stringBuilder.toString();

                textArea.setText(last);
                panel.add(pane);
                panel.add(button);
                frame.add(panel);
                pane.getVerticalScrollBar().setValue(pane.getVerticalScrollBar().getMinimum());
                frame.setVisible(true);
            }
        });
    }

}



