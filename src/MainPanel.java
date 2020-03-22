import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;

public class MainPanel extends JPanel
{
    private ResultPanel resultPanel;
    private ParametersPanel parametersPanel;
    private ButtonPanel buttonPanel;
    private GridBagLayout gridBagLayoutMain;
    private JPanel mainPanel;

    public MainPanel()
    {
        mainPanel = new JPanel();
        gridBagLayoutMain = new GridBagLayout();
        resultPanel = new ResultPanel();
        parametersPanel = new ParametersPanel();
        buttonPanel = new ButtonPanel();

        this.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        this.setLayout(gridBagLayoutMain);
        this.add(resultPanel, new GridBagConstraints(0, 0, 2, 3, 1, 1, GridBagConstraints.NORTHWEST, GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), 0, 0));
        this.add(parametersPanel, new GridBagConstraints(2, 0, 1, 2, 1, 1, GridBagConstraints.NORTHEAST, GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), 0, 0));
        this.add(buttonPanel, new GridBagConstraints(2, 2, 1, 1, 1, 1, GridBagConstraints.SOUTHEAST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
    } //End constructor   
} //End class