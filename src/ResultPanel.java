import javax.swing.*;
import java.awt.*;

public class ResultPanel extends JPanel
{
    private JTextArea resultText;
    private JScrollPane resultPane;

    public ResultPanel()
    {
        this.setBorder(BorderFactory.createTitledBorder("Results"));
        resultText = new JTextArea(); 
        resultPane = new JScrollPane(resultText, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        resultPane.setPreferredSize(new Dimension(1000,500));
        this.setLayout(new BorderLayout());
        this.add(resultPane, BorderLayout.CENTER);
    } //End constructor
} //End class