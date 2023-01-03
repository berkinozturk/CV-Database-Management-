import javax.swing.*;
import java.sql.SQLException;

public class OpenCVScreen extends JFrame {
    private JPanel panel;
    private JButton printCVFileButton;
    private JButton deleteCVButton;
    private JButton editTagsButton;
    private JLabel Name;
    private JLabel Surname;
    private JLabel Education;
    private JLabel Experiences;
    private JLabel Languages;
    private JLabel Projects;
    private JLabel Department;
    private JLabel Address;
    private JLabel Competencies;
    private JLabel Certificates;
    private JLabel PhoneNumber;
    private JLabel About;

    public OpenCVScreen(int id, String name, String surname, String education, String[] languages, String[] experiences,
                        String[] projects, String department, String address, String[] competencies,
                        String[] certificates, Long phoneNumber, String about){
        add(panel);
        setSize(500,500);
        setTitle("CV Add");

        Name.setText(name);
        Surname.setText(surname);
        Education.setText(education);


        String languagesString = String.join(", ", languages);
        languagesString = languagesString.substring(1);
        languagesString = languagesString.substring(0, languagesString.length() - 1);
        Languages.setText(languagesString);


        String experiencesString = String.join(", ", experiences);
        experiencesString = experiencesString.substring(1);
        experiencesString = experiencesString.substring(0, experiencesString.length() - 1);
        Experiences.setText(experiencesString);

        String projectsString = String.join(", ", projects);
        projectsString = projectsString.substring(1);
        projectsString = projectsString.substring(0, projectsString.length() - 1);
        Projects.setText(projectsString);

        Department.setText(department);
        Address.setText(address);

        String competenciesString = String.join(", ", competencies);
        competenciesString = competenciesString.substring(1);
        competenciesString = competenciesString.substring(0, competenciesString.length() - 1);
        Competencies.setText(competenciesString);

        String certificatesString = String.join(", ", certificates);
        certificatesString = certificatesString.substring(1);
        certificatesString = certificatesString.substring(0, certificatesString.length() - 1);
        Certificates.setText(certificatesString);

        PhoneNumber.setText(Long.toString(phoneNumber));
        About.setText(about);

        printCVFileButton.addActionListener(event -> {
            CV.printCV(id);
        });
        deleteCVButton.addActionListener(event -> {
            try {
                CV.deleteCV(id);
                this.setVisible(false);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
}
}