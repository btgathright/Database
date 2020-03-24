/*
Brandon Gathright
Seth Hamilton
Samiha Elahi
Johnny Hernandez
CSCE 315
3/23/2020
 */

import javax.swing.*;
import java.awt.GridLayout;

//This is a frame that is created when a search is successful

public class SearchFrame extends JFrame
{
    JFrame searchStatus = new JFrame();

    public SearchFrame(DBController controller)
    {
        String searchType = ParametersPanel.getSearchType();
        String values[], results = null;
        if (searchType == "Simple")
        {
            values = ParametersPanel.getSimpleValues();
            results = Searches.simpleSearch(controller, values[0]);
        }
        else if (searchType == "Chain")
        {
            values = ParametersPanel.getChainValues();
            if (values[0] == "Team")
            {
                results = Searches.chainSearchTeam(controller, values[1], values[2]);
            }
            else if (values[0] == "Player")
            {
                results = Searches.chainSearchPlayer(controller, values[1], values[2]);
            }
        }
        else if (searchType == "Advanced")
        {
            values = ParametersPanel.getAdvancedValues();
            results = "Advanced Search";
        }

        //Updates result panel
        ResultPanel.showResults(results);

        searchStatus.setLocation(625,325);
        searchStatus.setSize(300, 100);
        searchStatus.setTitle("S.S. Tracker");
        searchStatus.setVisible(true);
        searchStatus.setLayout(new GridLayout(2,1));
        searchStatus.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JPanel searchMessage = new JPanel();
        searchMessage.add(new JLabel("Search Completed."));

        JPanel searchButton = new JPanel();
        JButton okButton = new JButton("Ok");
        okButton.addActionListener(e -> okButtonClick());
        searchButton.add(okButton);

        searchStatus.add(searchMessage);
        searchStatus.add(searchButton);
        searchStatus.setVisible(true);
    }

    public void okButtonClick()
    {
        searchStatus.setVisible(false);
        searchStatus.dispose();
    }
}
