/*
Brandon Gathright
Seth Hamilton
Samiha Elahi
Johnny Hernandez
CSCE 315
2/29/2020
 */

public class jdbcpostgreSQLGUI {
  public static void main(String args[]) {
    //Open frame for welcome user prompt
    //WelcomeFrame wF = new WelcomeFrame();
    TrackerFrame tf = TrackerFrame.getInstance();
    tf.setVisible(true);
  }//end main
}//end Class


//TODO List

//Add autofill for the value text box
//Figure out how to compress to .jar executable file

/*
import javax.swing.*;
import java.sql.*;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Dimension;
import java.io.*;

//This is the main frame for the program.
//This frame allows the user to build a custom query for whatever
//they are searching for within the database.
//They then have the option to have these results outputted to the
//console or to a file to be saved.
//Finally the user has the option to reset the current frame or disconnect from the server.

class mainFrame extends JFrame
{
    JFrame main = new JFrame();
    DBController DBcont = null;
    JRadioButton console = new JRadioButton("Console");
    JRadioButton file = new JRadioButton("File");
    JRadioButton both = new JRadioButton("Both");
    JComboBox tableList1, tableList2, columnList, sSelections;
    JTextField searchValue;
    JTextArea outputText;
    String curOutput;

        // do you want everything to not be static? Cause that's totally fine
    mainFrame (DBController transfer){
      //define initial frame specifications
        this.DBcont = transfer;
        this.main.setLocation(300,25);
        this.main.setSize(1024, 768);
        this.main.setTitle("S.S. Tracker");
        this.main.setLayout(new BorderLayout(10, 10));
        this.main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


		this.columnList = new JComboBox();
        
        JPanel options = new JPanel();
        options.setLayout(new GridLayout(3,6, 10, 10));

        JLabel empty = new JLabel();

        ButtonGroup outputButtons = new ButtonGroup();
        JLabel output = new JLabel("Output");
        output.setHorizontalAlignment(SwingConstants.CENTER);

        this.file.setHorizontalAlignment(SwingConstants.CENTER);
        this.console.setHorizontalAlignment(SwingConstants.CENTER);
        this.both.setHorizontalAlignment(SwingConstants.CENTER);

        outputButtons.add(file);
        outputButtons.add(console);
        outputButtons.add(both);
        this.both.setSelected(true);

        
        //Search type and drop down menu
        JLabel sType = new JLabel("Search Type");
        sType.setHorizontalAlignment(SwingConstants.CENTER);
        
        String[] selections = {"Single", "Double"};
        sSelections = new JComboBox(selections);
        
        //Table 1 and drop down menu
        JLabel Table1 = new JLabel("Table 1");
        Table1.setHorizontalAlignment(SwingConstants.CENTER);
        
        String[] TableValues = DBHelper.get_table_names(DBcont);
        this.tableList1 = new JComboBox(TableValues);
        this.tableList1.addActionListener(e -> table1Select());
        
        //Table 2 and drop down menu
        JLabel Table2 = new JLabel("Table 2");
        Table2.setHorizontalAlignment(SwingConstants.CENTER);
        
        this.tableList2 = new JComboBox(TableValues);
        this.tableList2.addActionListener(e -> table2Select());
        
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
        outputText = new JTextArea(); 
        JScrollPane outputPane = new JScrollPane(outputText, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        outputPane.setPreferredSize(new Dimension(1000,500));
        outputArea.add(outputPane);
        
        this.main.add(options, BorderLayout.PAGE_START);
        this.main.add(outputArea, BorderLayout.CENTER);
        this.main.setVisible(true);
    }

    mainFrame(DBController transfer, String sTypeSelection, String table1, String table2, String table_name, String str_output)
    {
        //define initial frame specifications
        curOutput = str_output;
        this.DBcont = transfer;
        this.main.setLocation(300,25);
        this.main.setSize(1024, 768);
        this.main.setTitle("S.S. Tracker");
        this.main.setLayout(new BorderLayout(10, 10));
        this.main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


		this.columnList =  new JComboBox(DBHelper.get_column_names(DBcont, table_name));
        
        JPanel options = new JPanel();
        options.setLayout(new GridLayout(3,6, 10, 10));

        JLabel empty = new JLabel();

        ButtonGroup outputButtons = new ButtonGroup();
        JLabel output = new JLabel("Output");
        output.setHorizontalAlignment(SwingConstants.CENTER);

        this.file.setHorizontalAlignment(SwingConstants.CENTER);
        this.console.setHorizontalAlignment(SwingConstants.CENTER);
        this.both.setHorizontalAlignment(SwingConstants.CENTER);

        outputButtons.add(file);
        outputButtons.add(console);
        outputButtons.add(both);
        this.both.setSelected(true);

        
        //Search type and drop down menu
        JLabel sType = new JLabel("Search Type");
        sType.setHorizontalAlignment(SwingConstants.CENTER);
        
        String[] selections = {"Single", "Double"};
        sSelections = new JComboBox(selections);
        sSelections.setSelectedItem(sTypeSelection);
        
        //Table 1 and drop down menu
        JLabel Table1 = new JLabel("Table 1");
        Table1.setHorizontalAlignment(SwingConstants.CENTER);
        
        String[] TableValues = DBHelper.get_table_names(DBcont);
        this.tableList1 = new JComboBox(TableValues);
        this.tableList1.setSelectedItem(table1);
        this.tableList1.addActionListener(e -> table1Select());
        
        //Table 2 and drop down menu
        JLabel Table2 = new JLabel("Table 2");
        Table2.setHorizontalAlignment(SwingConstants.CENTER);
        
        this.tableList2 = new JComboBox(TableValues);
        this.tableList2.setSelectedItem(table2);
        this.tableList2.addActionListener(e -> table2Select());
        
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
        outputText = new JTextArea(str_output); 
        JScrollPane outputPane = new JScrollPane(outputText, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        outputPane.setPreferredSize(new Dimension(1000,500));
        outputArea.add(outputPane);
        
        this.main.add(options, BorderLayout.PAGE_START);
        this.main.add(outputArea, BorderLayout.CENTER);
        this.main.setVisible(true);
    }
    
    public void searchButtonClick()
    {
        ResultSet results;
        if (sSelections.getSelectedItem() == "Single")
        {
            Query singleTable = new Query((String)(tableList1.getSelectedItem()), (String)(columnList.getSelectedItem()), searchValue.getText());
            results = DBcont.query(singleTable);
        }
        else
        {
            Query doubleTable = new Query((String)(tableList1.getSelectedItem()), (String)(tableList2.getSelectedItem()), (String)(columnList.getSelectedItem()), searchValue.getText(), DBcont);
            results = DBcont.query(doubleTable);
        }
        //Modify results

        if (file.isSelected())
        {
            StringBuilder sb = new StringBuilder();
            try {
                int n = results.getMetaData().getColumnCount();

                while (results.next()) {
                    for (int i = 1; i <= n; i++) {
                        sb.append(results.getString(i));
                        sb.append(", ");
                    }
                    sb.append("\n");
                }
                FileWriter fw = new FileWriter("./output.csv");
                fw.write(sb.toString());
                fw.close();
            } catch (Exception e) {
                System.out.println("There was an issue writing to file");
                System.out.println(String.format("Error: %s", e.toString()));
            }
            this.main.setVisible(false);
		    this.main.dispose();
            this.main = new mainFrame(DBcont, (String)sSelections.getSelectedItem(),
                (String)tableList1.getSelectedItem(), (String)tableList2.getSelectedItem(), 
                (String)tableList1.getSelectedItem(), "");
        }
        else if (console.isSelected())
        {
            StringBuilder sb = new StringBuilder();
            try {
                int n = results.getMetaData().getColumnCount();
                                
                while (results.next()) {
                    for (int i = 1; i <= n; i++) {
                        sb.append(results.getString(i));
                        sb.append(", ");
                    }
                    sb.append("\n");
                }
            } catch (Exception e) {
                System.out.println("There was an issue writing to file");
                System.out.println(String.format("Error: %s", e.toString()));
            }
            this.main.setVisible(false);
		    this.main.dispose();
            this.main = new mainFrame(DBcont, (String)sSelections.getSelectedItem(),
                (String)tableList1.getSelectedItem(), (String)tableList2.getSelectedItem(), 
                (String)tableList1.getSelectedItem(), sb.toString()); 
        }
        else
        {
            StringBuilder sb = new StringBuilder();
            try {
                int n = results.getMetaData().getColumnCount();

                while (results.next()) {
                    for (int i = 1; i <= n; i++) {
                        sb.append(results.getString(i));
                        sb.append(", ");
                    }
                    sb.append("\n");
                }
                FileWriter fw = new FileWriter("./output.csv");
                fw.write(sb.toString());
                fw.close();
            } catch (Exception e) {
                System.out.println("There was an issue writing to file");
                System.out.println(String.format("Error: %s", e.toString()));
            }
            this.main.setVisible(false);
		    this.main.dispose();
            this.main = new mainFrame(DBcont, (String)sSelections.getSelectedItem(),
                (String)tableList1.getSelectedItem(), (String)tableList2.getSelectedItem(), 
                (String)tableList1.getSelectedItem(), sb.toString());
        }   
        searchFrame sF = new searchFrame();
    }

    public void resetButtonClick()
    {
        this.main.setVisible(false);
        this.main.dispose();
        this.main = new mainFrame(DBcont); 
    }

    public void disconnectButtonClick()
    {
        this.main.setVisible(false);
        reassureFrame dF = new reassureFrame(DBcont, (String)sSelections.getSelectedItem(),
            (String)tableList1.getSelectedItem(), (String)tableList2.getSelectedItem(), 
            (String)tableList1.getSelectedItem(), curOutput);
        this.main.dispose();
    }

    public void table1Select()
    {
        String tableSelection = (String)(tableList1.getSelectedItem());
        // columnList.revalidate();
        // columnList.repaint();
        this.main.setVisible(false);
		this.main.dispose();
        this.main = new mainFrame(DBcont, (String)sSelections.getSelectedItem(),
            (String)tableList1.getSelectedItem(), (String)tableList2.getSelectedItem(), 
            (String)tableList1.getSelectedItem(), "");
    }

    public void table2Select() 
    {
        String tableSelection = (String)(tableList2.getSelectedItem());
        // columnList.repaint();
        // mainFrame.repaint();
        // mainFrame.revalidate();
        // columnList.revalidate();
        // columnList.repaint();
        this.main.setVisible(false);
        this.main.dispose();
        this.main = new mainFrame(DBcont, (String)sSelections.getSelectedItem(),
            (String)tableList1.getSelectedItem(), (String)tableList2.getSelectedItem(), 
            (String)tableList2.getSelectedItem(), "");
    }
}
*/