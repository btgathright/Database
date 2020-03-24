import javax.swing.*;
import java.awt.GridLayout;

//This is the final frame that is created when the user is disconnecting from the server.
//The user will then click "ok" and the program will terminate.

class DisconnectFrame extends JFrame
{
    JFrame disconnectStatus = new JFrame();

    DisconnectFrame(DBController DBdisconnect)
    {
        try {DBdisconnect.conn.close();}
        catch  (Exception e){}
        disconnectStatus.setLocation(625,325);
        disconnectStatus.setSize(100, 100);
        disconnectStatus.setTitle("S.S. Tracker");
        disconnectStatus.setVisible(true);
        disconnectStatus.setLayout(new GridLayout(2,1));
        disconnectStatus.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JPanel disconnectMessage = new JPanel();
        JLabel disconnect = new JLabel("Disconnected from server.");
        disconnect.setVerticalAlignment(SwingConstants.CENTER);
        disconnectMessage.add(disconnect);

        JPanel disconnectButton = new JPanel();
        JButton okButton = new JButton("Ok");
        okButton.addActionListener(e -> okButtonClick());
        disconnectButton.add(okButton);

        disconnectStatus.add(disconnectMessage);
        disconnectStatus.add(disconnectButton);
        disconnectStatus.setVisible(true);
    }

    public void okButtonClick()
    {
        disconnectStatus.setVisible(false);
        disconnectStatus.dispose();
    }
}