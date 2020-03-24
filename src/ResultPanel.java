/*
Brandon Gathright
Seth Hamilton
Samiha Elahi
Johnny Hernandez
CSCE 315
3/23/2020
 */

import javax.swing.*;
import java.awt.*;

public class ResultPanel extends JPanel
{
    private static JTextArea resultText;
    private static JScrollPane resultPane;

    public ResultPanel()
    {
        this.setBorder(BorderFactory.createTitledBorder("Results"));
        resultText = new JTextArea(); 
        resultPane = new JScrollPane(resultText, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        resultPane.setPreferredSize(new Dimension(1000,500));
        this.setLayout(new BorderLayout());
        this.add(resultPane, BorderLayout.CENTER);
    } //End constructor

    public static void showResults(String results)
    {
        resultText.setText(results);
        resultText.setLineWrap(true);
        resultText.setWrapStyleWord(true);
    }

} //End class