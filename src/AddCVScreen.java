import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class AddCVScreen extends JFrame {
    private JTextField Name;
    private JTextField Surname;
    private JTextField Education;
    private JTextField Languages;
    private JTextField Experiences;
    private JTextField Projects;
    private JTextField Department;
    private JTextField Address;
    private JTextField Competencies;
    private JTextField Certificates;
    private JTextField PhoneNumber;
    private JTextField About;
    private JButton addCVToDatabaseButton;
    private JPanel panel;
    private JButton chooseCVFileButton;
    private String filePath;


    public AddCVScreen(){
        add(panel);
        setSize(500,500);
        setTitle("CV Add");

        chooseCVFileButton.addActionListener(event -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setCurrentDirectory(new File("/"));
            int result = fileChooser.showOpenDialog(chooseCVFileButton);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                System.out.println("Selected file: " + selectedFile.getAbsolutePath());
                filePath = selectedFile.getAbsolutePath();
            }
        });


        addCVToDatabaseButton.addActionListener(event -> {
            String name = Name.getText();
            String surname = Surname.getText();
            String education = Education.getText();
            String[] languages = Languages.getText().split(",");  // split the text on the comma
            String[] experiences = Experiences.getText().split(",");  // split the text on the comma
            String[] projects = Projects.getText().split(",");  // split the text on the comma
            String department = Department.getText();
            String address = Address.getText();
            String[] competencies = Competencies.getText().split(",");  // split the text on the comma
            String[] certificates = Certificates.getText().split(",");  // split the text on the comma
            Long phoneNumber = Long.parseLong(PhoneNumber.getText());  // parse the phone number as a long
            String about = About.getText();

            CV.addCV(name, surname, education, languages, experiences, projects, department, address, competencies,
                    certificates,phoneNumber,about, filePath);

            this.setVisible(false);
      });
        }


}