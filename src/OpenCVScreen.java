import javax.swing.*;

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

    public OpenCVScreen(String name, String surname, String education, String[] languages, String[] experiences,
                        String[] projects, String department, String address, String[] competencies,
                        String[] certificates, Long phoneNumber, String about){
        add(panel);
        setSize(500,500);
        setTitle("CV Add");

        Name.setText(name);
        Surname.setText(surname);
        Education.setText(education);

        String languagesString = String.join(", ", languages);
        Languages.setText(languagesString);

        String experiencesString = String.join(", ", experiences);
        Experiences.setText(experiencesString);

        String projectsString = String.join(", ", projects);
        Projects.setText(projectsString);

        Department.setText(department);
        Address.setText(address);

        String competenciesString = String.join(", ", competencies);
        Competencies.setText(competenciesString);

        String certificatesString = String.join(", ", certificates);
        Certificates.setText(certificatesString);

        PhoneNumber.setText(Long.toString(phoneNumber));
        About.setText(about);

    }
}
