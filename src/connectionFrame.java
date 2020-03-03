import javax.swing.*;
import java.sql.*;
import java.awt.GridLayout;

class connectionFrame extends JFrame
{
    JFrame connectionStatus = new JFrame();
    static Connection conn = null;

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
            System.err.println(e.getClass().getName()+": " + e.getMessage());
            System.exit(0);
        }//end try catch

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
        mainFrame mF = new mainFrame(conn);
        connectionStatus.dispose();
    }
}