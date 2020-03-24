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

public class ButtonPanel extends JPanel
{
    DBController cont;
    private JButton searchButton, disconnectButton;
    private GridBagLayout gridBagLayoutButtonPanel;

    public ButtonPanel(DBController controller)
    {
        cont = controller;
        searchButton = new JButton("Search");
        disconnectButton = new JButton("Disconnect");
        gridBagLayoutButtonPanel = new GridBagLayout();

        searchButton.addActionListener(e -> searchSelect());
        searchButton.setPreferredSize(new Dimension(250, 25));

        disconnectButton.addActionListener(e -> disconnectSelect());
        disconnectButton.setPreferredSize(new Dimension(250, 25));

        this.setLayout(gridBagLayoutButtonPanel);
        this.add(searchButton, new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
        this.add(disconnectButton, new GridBagConstraints(0, 1, 1, 1, 1, 1, GridBagConstraints.SOUTH, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
    } //End constructor

    public void searchSelect()
    {
        SearchFrame sf = new SearchFrame(cont);
    }

    public void disconnectSelect()
    {
        ReassureFrame rf = new ReassureFrame(cont);
    }
    
} //End class