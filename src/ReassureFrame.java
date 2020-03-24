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
import java.awt.Window;
import java.util.List;
import java.util.ArrayList;

//This is a frame that is created when the user disconnects from the server.
//This frame will prompt the user if this is what they intended to do before disconnecting.

public class ReassureFrame extends JFrame
{
    JFrame reassureStatus = new JFrame();
    DBController sending = null;

    public ReassureFrame(DBController transfer)
    {
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
        List<Window> openWindows = new ArrayList<Window>();
        for (Window w: Window.getWindows())
        {
            if (w.isShowing())
            {
                openWindows.add(w);
            }
        }
        for (Window w: openWindows)
        {
            w.setVisible(false);
            w.dispose();
        }
        DisconnectFrame dF = new DisconnectFrame(sending);
        reassureStatus.dispose();
    }

    public void noButtonClick()
    {
        reassureStatus.setVisible(false);
        TrackerFrame tf = TrackerFrame.getInstance();
        tf.setVisible(true);
        reassureStatus.dispose();
    }
}
