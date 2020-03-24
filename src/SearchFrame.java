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
            //Do simple search with these values
            results = "Simple search";
        }
        else if (searchType == "Chain")
        {
            values = ParametersPanel.getChainValues();
            //Do chain search with these values
            results = "Chain Search";
        }
        else if (searchType == "Advanced")
        {
            values = ParametersPanel.getAdvancedValues();
            //Do advanced search with these values
            results = "Advanced Search";
        }

        //Formats the results
        //format results here

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
