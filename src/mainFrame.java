import javax.swing.*;
import java.sql.*;
import java.awt.GridLayout;
import java.awt.Dimension;

class mainFrame extends JFrame
{
    JFrame mainFrame = new JFrame();
    Connection trans = null;
    JRadioButton file = new JRadioButton("File");
    JRadioButton console = new JRadioButton("Console");

    mainFrame(Connection transfer)
    {
        //define initial frame specifications
        trans = transfer;
        mainFrame.setLocation(300,25);
        mainFrame.setSize(1024, 768);
        mainFrame.setTitle("S.S. Tracker");
        mainFrame.setLayout(new GridLayout(3, 1));
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //Panel that displays all the options for searching
        //Top - Output options, print to file or console
        //Bottom - Search, Reset, and Exit buttons
        JPanel options = new JPanel();
        options.setLayout(new GridLayout(2,3, 10, 20));

        ButtonGroup outputButtons = new ButtonGroup();
        JLabel output = new JLabel("Output Option:");
        output.setHorizontalAlignment(SwingConstants.CENTER);
        options.add(output);

        file.setHorizontalAlignment(SwingConstants.CENTER);
        options.add(file);

        console.setHorizontalAlignment(SwingConstants.CENTER);
        options.add(console);

        outputButtons.add(file);
        outputButtons.add(console);
        file.setSelected(true);

        JButton searchButton = new JButton("Search");
        searchButton.setPreferredSize(new Dimension(20,20));
        searchButton.addActionListener(e -> searchButtonClick());
        options.add(searchButton);

        JButton resetButton = new JButton("Reset");
        resetButton.setPreferredSize(new Dimension(40,40));
        resetButton.addActionListener(e -> resetButtonClick());
        options.add(resetButton);

        JButton exitButton = new JButton("Exit");
        exitButton.setPreferredSize(new Dimension(40,40));
        exitButton.addActionListener(e -> exitButtonClick());
        options.add(exitButton);

        //Panel that displays all the search options for value1
        //Left - Tables within the database
        //Middle - Columns within the chosen table
        //Right - All values of the chosen column
        JPanel searchOptions1 = new JPanel();
        searchOptions1.setLayout(new GridLayout(1,3, 10, 10));

        JComboBox tableList1 = new JComboBox(); //Add list of tables in new object declaration
        searchOptions1.add(tableList1);

        //Get values for list from tableList1 selection
        JComboBox columnList1 = new JComboBox(); //Add columns from chosen table into new object declaration
        searchOptions1.add(columnList1);

        //Get values for list from columnList1 selection
        JComboBox dataList1 = new JComboBox(); //Add values from chosen column into new object declaration
        searchOptions1.add(dataList1);
       
        //Panel that displays all the search options for value2
        //Left - Tables within the database
        //Middle - Columns within the chosen table
        //Right - All values of the chosen column
        JPanel searchOptions2 = new JPanel();
        searchOptions2.setLayout(new GridLayout(1,3, 10, 10));

        JComboBox tableList2 = new JComboBox(); //Add list of tables in new object declaration
        searchOptions2.add(tableList2);

        //Get values for list from tableList2 selection
        JComboBox columnList2 = new JComboBox(); //Add columns from chosen table in new object declaration
        searchOptions2.add(columnList2);

        //Get values for list from columnList2 selection
        JComboBox dataList2 = new JComboBox(); //Add values from chosen column in new object declaration
        searchOptions2.add(dataList2);

        //Combine all the panels onto the frame
        mainFrame.add(options);
        mainFrame.add(searchOptions1);
        mainFrame.add(searchOptions2);
        mainFrame.setVisible(true);
    }

    public void searchButtonClick()
    {
        if (file.isSelected())
        {
            //Run query from selected options above when the search is selected.
            //Combine results into some organized format for displaying.
            //print output to output.txt
        }
        else
        {
            //Run query from selected options above when the search is selected.
            //Combine results into some organized format for displaying.
            //print output to console
        }
        searchFrame sF = new searchFrame();
    }

    public void resetButtonClick()
    {
        mainFrame.setVisible(false);
        mainFrame mF = new mainFrame(trans); 
        //Possibly reset this to clear lists
        mainFrame.dispose();
    }

    public void exitButtonClick()
    {
        mainFrame.setVisible(false);
        reassureFrame dF = new reassureFrame(trans);
        mainFrame.dispose();
    }
}