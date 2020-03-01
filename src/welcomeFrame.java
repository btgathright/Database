import javax.swing.*;

class welcomeFrame extends JFrame
{
    String name;
    String pass;

    welcomeFrame()
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
        JTextField username = new JTextField(15);
        welcomePanel.add(username);
        welcomePanel.add(new JLabel("Password"));
        JPasswordField password = new JPasswordField(15);
        welcomePanel.add(password);

        //Create login button at bottom of panel
        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(e -> buttonLoginClick(username.getText(), password.getPassword()));
        welcomePanel.add(loginButton);

        //Add new panel to display frame and set visible
        this.add(welcomePanel);
        this.setVisible(true);
    }

    public void buttonLoginClick(String alphaVal, char[] betaVal)
    {
        String betaValString = new String(betaVal);
        if ((alphaVal.length() == 0) || (betaValString.length() == 0))
        {
            JOptionPane.showMessageDialog(null, "No username/password specified.");
        }
        else
        {
            name = alphaVal;
            pass = betaValString;
            this.setVisible(false);
            connectionFrame cF = new connectionFrame(name, pass);
            this.dispose();
        }
    }
}