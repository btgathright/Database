import javax.swing.*;
import java.sql.*;
import java.awt.GridLayout;

//This is a frame that is created when the user disconnects from the server.
//This frame will prompt the user if this is what they intended to do before disconnecting.

class reassureFrame extends JFrame
{
    JFrame reassureStatus = new JFrame();
    DBController sending = null;

    reassureFrame(DBController transfer)
    {
        sending = transfer;
        reassureStatus.setLocation(625,325);
        reassureStatus.setSize(300, 100);
        reassureStatus.setTitle("S.S. Tracker");
        reassureStatus.setVisible(true);
        reassureStatus.setLayout(new GridLayout(2,1));
        reassureStatus.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JPanel reassureMessage = new JPanel();
        reassureMessage.add(new JLabel("Are you sure you want to disconnect?"));

        JPanel reassureButtons = new JPanel();
        JButton yesButton = new JButton("Yes");
        yesButton.addActionListener(e -> yesButtonClick());
        reassureButtons.add(yesButton);
        JButton noButton = new JButton("No");
        noButton.addActionListener(e -> noButtonClick());
        reassureButtons.add(noButton);

        reassureStatus.add(reassureMessage);
        reassureStatus.add(reassureButtons);
        reassureStatus.setVisible(true);
    }

    public void yesButtonClick()
    {
        reassureStatus.setVisible(false);
        disconnectFrame dF = new disconnectFrame(sending);
        reassureStatus.dispose();
    }

    public void noButtonClick()
    {
        reassureStatus.setVisible(false);
        mainFrame mF = new mainFrame(sending);
        reassureStatus.dispose();
    }
}