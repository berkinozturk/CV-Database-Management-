import javax.swing.*;
import java.io.File;

public class EditCVScreen extends JFrame {
    private JPanel panel;
    private JTextField Name;
    private JTextField Surname;
    private JTextField Education;
    private JTextField Languages;
    private JTextField Experiences;
    private JTextField Department;
    private JTextField Address;
    private JTextField Competencies;
    private JTextField Certificates;
    private JTextField PhoneNumber;
    private JTextField About;
    private JButton updateTagsButton;
    private JTextField Projects;
    private JTable table1;

    public EditCVScreen(int id){
        add(panel);
        setSize(500,500);
        setTitle("CV Add");


        updateTagsButton.addActionListener(event -> {
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

            CV.updateCV(name, surname,education,languages,experiences,projects,department,address,competencies,id,certificates,phoneNumber,about);

            this.setVisible(false);
        });
    }


}
