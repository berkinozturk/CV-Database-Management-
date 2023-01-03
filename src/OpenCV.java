import javax.swing.*;

public class OpenCV extends JFrame {
    private JPanel panel;
    private JButton printCVFileButton;
    private JButton deleteCVButton;
    private JButton editTagsButton;

    public OpenCV(){
        add(panel);
        setSize(500,500);
        setTitle("CV Add");
    }
}
