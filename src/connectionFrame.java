import javax.swing.*;
import java.sql.*;

class connectionFrame extends JFrame
{
    JFrame connectionStatus = new JFrame();
    Connection conn = null;

    connectionFrame(String alphaKey, String betaKey)
    {
        dbSetup my = new dbSetup(alphaKey, betaKey);
        //Building the connection
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://csce-315-db.engr.tamu.edu/team14_cfb",
                my.user, my.pswd);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }//end try catch

        connectionStatus.setLocation(625,325);
        connectionStatus.setSize(100, 80);
        connectionStatus.setTitle("S.S. Tracker");
        connectionStatus.setVisible(true);
        
        JPanel connectionMessage = new JPanel();
        connectionMessage.add(new JLabel("Successfully connected to the server."));
        JButton okButton = new JButton("Ok");
        okButton.addActionListener(e -> okButtonClick());
        connectionMessage.add(okButton);

        connectionStatus.add(connectionMessage);
        connectionStatus.setVisible(true);
    }

    public void okButtonClick()
    {
        connectionStatus.setVisible(false);
        mainFrame mF = new mainFrame(conn);
        connectionStatus.dispose();
    }
}