import javax.swing.*;
import java.sql.*;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Dimension;

//This is the main frame for the program.
//This frame allows the user to build a custom query for whatever
//they are searching for within the database.
//They then have the option to have these results outputted to the
//console or to a file to be saved.
//Finally the user has the option to reset the current frame or disconnect from the server.

class mainFrame extends JFrame
{
    JFrame mainFrame = new JFrame();
    DBController DBcont = null;
    JRadioButton console = new JRadioButton("Console");
    JRadioButton file = new JRadioButton("File");
    JRadioButton both = new JRadioButton("Both");
    JComboBox tableList1, tableList2, columnList, sSelections;
    JTextField searchValue;
    JTextArea outputText;

    mainFrame(DBController transfer)
    {
        //define initial frame specifications
        DBcont = transfer;
        mainFrame.setLocation(300,25);
        mainFrame.setSize(1024, 768);
        mainFrame.setTitle("S.S. Tracker");
        mainFrame.setLayout(new BorderLayout(10, 10));
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
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
        sSelections = new JComboBox(selections);

        //Table 1 and drop down menu
        JLabel Table1 = new JLabel("Table 1");
        Table1.setHorizontalAlignment(SwingConstants.CENTER);

        String[] TableValues = DBHelper.get_table_names(DBcont);
        tableList1 = new JComboBox(TableValues);
        tableList1.addActionListener(e -> tableSelect());

        //Table 2 and drop down menu
        JLabel Table2 = new JLabel("Table 2");
        Table2.setHorizontalAlignment(SwingConstants.CENTER);

        tableList2 = new JComboBox(TableValues); 

        //Columns and drop down menu
        JLabel Column = new JLabel("Column");
        Column.setHorizontalAlignment(SwingConstants.CENTER);

        //Data values and drop down menu
        JLabel Values = new JLabel("Value");
        Values.setHorizontalAlignment(SwingConstants.CENTER);

        searchValue = new JTextField();

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
        options.add(searchValue);
        options.add(resetButton);

        JPanel outputArea = new JPanel();
        outputArea.add(outputText);

        mainFrame.add(options, BorderLayout.PAGE_START);
        mainFrame.add(outputArea, BorderLayout.CENTER);
        mainFrame.setVisible(true);
    }

    public void searchButtonClick()
    {
        if (tableList2.getSelectedItem() == null)
        {
            Query singleTable = new Query((String)(tableList1.getSelectedItem()), (String)(columnList.getSelectedItem()), searchValue.getText());
            ResultSet results = DBcont.query(singleTable);
        }
        else
        {
            Query doubleTable = new Query((String)(tableList1.getSelectedItem()), (String)(tableList2.getSelectedItem()), (String)(columnList.getSelectedItem()), searchValue.getText(), DBcont);
            ResultSet results = DBcont.query(doubleTable);
        }
        //Modify results

        if (file.isSelected())
        {
            //print results to file
        }
        else if (console.isSelected())
        {
            outputText = new JTextArea("Test"); //Put text to output in here.
            mainFrame.repaint();
        }
        else
        {
            //print results to file
            outputText = new JTextArea("Test"); //Put text to output in here.
            mainFrame.repaint();
        }   
        searchFrame sF = new searchFrame();
    }

    public void resetButtonClick()
    {
        mainFrame.setVisible(false);
        mainFrame mF = new mainFrame(DBcont); 
        mainFrame.dispose();
    }

    public void disconnectButtonClick()
    {
        mainFrame.setVisible(false);
        reassureFrame dF = new reassureFrame(DBcont, mainFrame);
        mainFrame.dispose();
    }

    public void tableSelect()
    {
        String tableSelection = (String)(tableList1.getSelectedItem());
        columnList = new JComboBox(DBHelper.get_column_names(DBcont, tableSelection));
        mainFrame.repaint();
    }
}