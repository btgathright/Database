import javax.swing.*;
import java.sql.*;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Dimension;

class mainFrame extends JFrame
{
    JFrame mainFrame = new JFrame();
    Connection trans = null;
    JRadioButton console = new JRadioButton("Console");
    JRadioButton file = new JRadioButton("File");
    JRadioButton both = new JRadioButton("Both");

    mainFrame(Connection transfer)
    {
        //define initial frame specifications
        trans = transfer;
        mainFrame.setLocation(300,25);
        mainFrame.setSize(1024, 768);
        mainFrame.setTitle("S.S. Tracker");
        mainFrame.setLayout(new BorderLayout(10, 10));
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //Panel that displays all the options for searching
        //Top - Output options, print to file or console
        //Bottom - Search, Reset, and Exit buttons
        JPanel options = new JPanel();
        options.setLayout(new GridLayout(3,6, 10, 10));

        JLabel empty = new JLabel();

        ButtonGroup outputButtons = new ButtonGroup();
        JLabel output = new JLabel("Output");
        output.setHorizontalAlignment(SwingConstants.CENTER);

        file.setHorizontalAlignment(SwingConstants.CENTER);
        console.setHorizontalAlignment(SwingConstants.CENTER);
        both.setHorizontalAlignment(SwingConstants.CENTER);

        outputButtons.add(file);
        outputButtons.add(console);
        outputButtons.add(both);
        file.setSelected(true);

        //Search type and drop down menu
        JLabel sType = new JLabel("Search Type");
        sType.setHorizontalAlignment(SwingConstants.CENTER);

        String[] selections = {"Single", "Double"};
        JComboBox sSelections = new JComboBox(selections);

        //Table 1 and drop down menu
        JLabel Table1 = new JLabel("Table 1");
        Table1.setHorizontalAlignment(SwingConstants.CENTER);

        String[] TableValues = {"Test1, Test2"};
        JComboBox tableList1 = new JComboBox(TableValues); 

        //Table 2 and drop down menu
        JLabel Table2 = new JLabel("Table 2");
        Table2.setHorizontalAlignment(SwingConstants.CENTER);

        JComboBox tableList2 = new JComboBox(TableValues); 

        //Columns and drop down menu
        JLabel Column = new JLabel("Column");
        Column.setHorizontalAlignment(SwingConstants.CENTER);

        String[] ColumnValues = {"Test1", "Test2"};
        JComboBox columnList = new JComboBox(ColumnValues);

        //Data values and drop down menu
        JLabel Values = new JLabel("Values");
        Values.setHorizontalAlignment(SwingConstants.CENTER);

        String[] dataValues = {"Test1", "Test2"};
        JComboBox valuesList = new JComboBox(dataValues);

        //Buttons for reset and search
        JButton searchButton = new JButton("Search");
        searchButton.setPreferredSize(new Dimension(20,20));
        searchButton.addActionListener(e -> searchButtonClick());

        JButton resetButton = new JButton("Reset");
        resetButton.setPreferredSize(new Dimension(20,20));
        resetButton.addActionListener(e -> resetButtonClick());

        JButton disconnectButton = new JButton("Disconnect");
        disconnectButton.setPreferredSize(new Dimension(20,20));
        disconnectButton.addActionListener(e -> disconnectButtonClick());

        //First row
        options.add(output);
        options.add(file);
        options.add(console);
        options.add(both);
        options.add(empty);
        options.add(disconnectButton);

        //Second row
        options.add(sType);
        options.add(Table1);
        options.add(Table2);
        options.add(Column);
        options.add(Values);
        options.add(searchButton);
        
        //Third row
        options.add(sSelections);
        options.add(tableList1);
        options.add(tableList2);
        options.add(columnList);
        options.add(valuesList);
        options.add(resetButton);

        //Combine all the panels onto the frame
        mainFrame.add(options, BorderLayout.PAGE_START);
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

    public void disconnectButtonClick()
    {
        mainFrame.setVisible(false);
        reassureFrame dF = new reassureFrame(trans);
        mainFrame.dispose();
    }
}