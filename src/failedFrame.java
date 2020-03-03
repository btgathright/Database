import javax.swing.*;
import java.sql.*;
import java.awt.GridLayout;

//This is a frame that is created when connection to the server is failed.
//Regardless of poor connection or invalid credentials this frame will prompt
//the user to retry logging in.

class failedFrame extends JFrame
{
    JFrame failedStatus = new JFrame();

    failedFrame()
    {
        failedStatus.setLocation(625,325);
        failedStatus.setSize(100, 100);
        failedStatus.setTitle("S.S. Tracker");
        failedStatus.setVisible(true);
        failedStatus.setLayout(new GridLayout(2,1));
        failedStatus.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JPanel failedMessage = new JPanel();
        JLabel failed = new JLabel("Connection failed. Try again.");
        failed.setVerticalAlignment(SwingConstants.CENTER);
        failedMessage.add(failed);

        JPanel failedButton = new JPanel();
        JButton okButton = new JButton("Ok");
        okButton.addActionListener(e -> okButtonClick());
        failedButton.add(okButton);

        failedStatus.add(failedMessage);
        failedStatus.add(failedButton);
        failedStatus.setVisible(true);
    }

    public void okButtonClick()
    {
        failedStatus.setVisible(false);
        welcomeFrame wF = new welcomeFrame();
        failedStatus.dispose();
    }
}