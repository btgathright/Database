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

    public MainPanel(DBController controller)
    {
        mainPanel = new JPanel();
        gridBagLayoutMain = new GridBagLayout();
        resultPanel = new ResultPanel();
        parametersPanel = new ParametersPanel(controller);
        buttonPanel = new ButtonPanel(controller);

        resultPanel.setPreferredSize(new Dimension(479, 568));
        parametersPanel.setPreferredSize(new Dimension(300, 464));
        buttonPanel.setPreferredSize(new Dimension(300, 80));

        this.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        this.setLayout(gridBagLayoutMain);
        this.add(resultPanel, new GridBagConstraints(0, 0, 2, 2, 1, 1, GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(5, 10, 5, 10), 0, 0));
        this.add(parametersPanel, new GridBagConstraints(2, 0, 1, 1, 1, 1, GridBagConstraints.NORTHEAST, GridBagConstraints.NONE, new Insets(5, 10, 10, 5), 0, 0));
        this.add(buttonPanel, new GridBagConstraints(2, 1, 1, 1, 1, 1, GridBagConstraints.SOUTHEAST, GridBagConstraints.NONE, new Insets(10, 5, 10, 5), 0, 0));
    } //End constructor   
} //End class