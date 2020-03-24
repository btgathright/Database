import javax.swing.*;
import java.awt.GridLayout;

//This is a frame that is displayed when connection to the database is successful
//The user will then click "ok" to continue to the main panel of the program.

public class ConnectionFrame extends JFrame
{
    JFrame connectionStatus = new JFrame();
    DBController sending = null;

    public ConnectionFrame(DBController transfer)
    {
        sending = transfer;
        connectionStatus.setLocation(625,325);
        connectionStatus.setSize(100, 100);
        connectionStatus.setTitle("S.S. Tracker");
        connectionStatus.setVisible(true);
        connectionStatus.setLayout(new GridLayout(2,1));
        connectionStatus.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JPanel connectionMessage = new JPanel();
        JLabel success = new JLabel("Successfully connected to the server.");
        success.setVerticalAlignment(SwingConstants.CENTER);
        connectionMessage.add(success);

        JPanel connectionButton = new JPanel();
        JButton okButton = new JButton("Ok");
        okButton.addActionListener(e -> okButtonClick());
        connectionButton.add(okButton);

        connectionStatus.add(connectionMessage);
        connectionStatus.add(connectionButton);
        connectionStatus.setVisible(true);
    }

    public void okButtonClick()
    {
        connectionStatus.setVisible(false);
        TrackerFrame tf = new TrackerFrame(sending);
        tf.setVisible(true);
        connectionStatus.dispose();
    }
}
