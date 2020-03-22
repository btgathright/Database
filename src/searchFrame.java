import javax.swing.*;
import java.awt.GridLayout;

//This is a frame that is created when a search is successful

public class SearchFrame extends JFrame
{
    JFrame searchStatus = new JFrame();

    public SearchFrame()
    {
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