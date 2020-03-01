import javax.swing.*;
import java.sql.*;

class disconnectFrame extends JFrame
{
    JFrame disconnectStatus = new JFrame();

    disconnectFrame(Connection conn)
    {
        try {conn.close();}
        catch  (Exception e){}
        disconnectStatus.setLocation(625,325);
        disconnectStatus.setSize(100, 80);
        disconnectStatus.setTitle("S.S. Tracker");
        disconnectStatus.setVisible(true);
        
        JPanel disconnectMessage = new JPanel();
        disconnectMessage.add(new JLabel("Disconnected from server."));
        JButton okButton = new JButton("Ok");
        okButton.addActionListener(e -> okButtonClick());

        disconnectStatus.add(disconnectMessage);
        disconnectStatus.setVisible(true);
    }

    public void okButtonClick()
    {
        disconnectStatus.setVisible(false);
        disconnectStatus.dispose();
    }
}