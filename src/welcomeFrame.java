/*
Brandon Gathright
Seth Hamilton
Samiha Elahi
Johnny Hernandez
CSCE 315
2/29/2020
 */

import javax.swing.*;
//This is the first frame that opens when the program is executed.
//The user will be prompted for a username and password which will then
//be used to log into the database.

public class WelcomeFrame extends JFrame
{
    private JTextField username = new JTextField(15);
    private JPasswordField password = new JPasswordField(15);

    public WelcomeFrame()
    {
        //Initialize frame location and size
        this.setLocation(512, 384);
        this.setSize(500, 125);
        this.setTitle("S.S. Tracker");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create panel with welcome text and login fields
        JPanel welcomePanel = new JPanel();

        //Create welcome text fields for new panel
        welcomePanel.add(new JLabel("Welcome to the S.S. Tracker!"));
        welcomePanel.add(new JLabel("Enter your database credentials below to continue."));

        //Create login fields for username and password
        welcomePanel.add(new JLabel("Username"));
        welcomePanel.add(username);
        welcomePanel.add(new JLabel("Password"));
        welcomePanel.add(password);

        //Create login button at bottom of panel
        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(e -> buttonLoginClick(username.getText(), password.getPassword()));
        welcomePanel.add(loginButton);

        //Add new panel to display frame and set visible
        this.add(welcomePanel);
        this.setVisible(true);
    }

    //Listener for button click event
    public void buttonLoginClick(String alphaVal, char[] betaVal)
    {
        String betaValString = new String(betaVal);
        if ((alphaVal.length() == 0) || (betaValString.length() == 0))
        {
            JOptionPane.showMessageDialog(null, "No username/password specified.");
        }
        else
        {
            this.setVisible(false);
            String pass = new String(password.getPassword());
            DBSetup my = new DBSetup(username.getText(), pass);
            DBController connection = new DBController(my);
            this.dispose();
        }
    }
}