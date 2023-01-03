import javax.swing.*;

public class GenerateCVScreen extends JFrame {
    private JTextField Surname;
    private JTextField Education;
    private JTextField Languages;
    private JTextField Experiences;
    private JTextField Projects;
    private JTextField Departmant;
    private JTextField Address;
    private JTextField Competencies;
    private JTextField Certificates;
    private JTextField PhoneNumber;
    private JTextField About;
    private JButton addCVToDatabaseButton;
    private JPanel panel;
    private JTextField Name;

    public GenerateCVScreen(){
        add(panel);
        setSize(500,500);
        setTitle("CV Generate");

        addCVToDatabaseButton.addActionListener(event -> {
            String name = Name.getText();
            String surname = Surname.getText();
            String education = Education.getText();
            String[] languages = Languages.getText().split(",");  // split the text on the comma
            String[] experiences = Experiences.getText().split(",");  // split the text on the comma
            String[] projects = Projects.getText().split(",");  // split the text on the comma
            String department = Departmant.getText();
            String address = Address.getText();
            String[] competencies = Competencies.getText().split(",");  // split the text on the comma
            String[] certificates = Certificates.getText().split(",");  // split the text on the comma
            Long phoneNumber = Long.parseLong(PhoneNumber.getText());  // parse the phone number as a long
            String about = About.getText();

            CV.generateCV(name, surname, education, languages, experiences, projects, department, address, competencies,
                    certificates,phoneNumber,about);
            this.setVisible(false);
        });



    }
}
