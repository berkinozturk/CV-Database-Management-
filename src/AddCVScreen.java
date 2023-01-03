import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddCVScreen extends JFrame {
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JTextField textField6;
    private JTextField textField7;
    private JTextField textField8;
    private JTextField textField9;
    private JTextField textField10;
    private JTextField textField11;
    private JTextField textField12;
    private JButton addCVToDatabaseButton;
    private JPanel panel;
    private JButton chooseCVFileButton;

    public AddCVScreen(){
        add(panel);
        setSize(500,500);
        setTitle("CV Add");

        chooseCVFileButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CV.addCV();
            }
        });

    }


}
