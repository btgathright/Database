import javax.swing.*;
import java.awt.*;

public class ButtonPanel extends JPanel
{
    private JButton searchButton, disconnectButton;
    private GridBagLayout gridBagLayoutButtonPanel;

    public ButtonPanel()
    {
        searchButton = new JButton("Search");
        disconnectButton = new JButton("Disconnect");
        gridBagLayoutButtonPanel = new GridBagLayout();

        searchButton.addActionListener(e -> searchSelect());
        disconnectButton.addActionListener(e -> disconnectSelect());

        this.setLayout(gridBagLayoutButtonPanel);
        this.add(searchButton, new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), 0, 0));
        this.add(disconnectButton, new GridBagConstraints(0, 1, 1, 1, 1, 1, GridBagConstraints.SOUTH, GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), 0, 0));
    } //End constructor

    public void searchSelect()
    {
        SearchFrame sf = new SearchFrame();
    }

    public void disconnectSelect()
    {
        ReassureFrame rf = new ReassureFrame(null, null, null, null, null, null);
    }
} //End class