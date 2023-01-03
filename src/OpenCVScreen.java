import javax.swing.*;

public class OpenCVScreen extends JFrame {
    private JPanel panel;
    private JButton printCVFileButton;
    private JButton deleteCVButton;
    private JButton editTagsButton;

    public OpenCVScreen(){
        add(panel);
        setSize(500,500);
        setTitle("CV Add");
    }
}
