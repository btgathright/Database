import javax.swing.*;
import java.sql.*;
import java.awt.GridLayout;

class mainFrame extends JFrame
{
    JFrame mainFrame = new JFrame();
    Connection trans = null;

    mainFrame(Connection transfer)
    {
        //define initial frame specifications
        trans = transfer;
        mainFrame.setLocation(300,25);
        mainFrame.setSize(1024, 768);
        mainFrame.setTitle("S.S. Tracker");
        mainFrame.setLayout(new GridLayout(3, 1));
        
        //Panel that displays all the options for searching
        //Top - Output options, print to file or console
        //Bottom - Search, Reset, and Exit buttons
        JPanel options = new JPanel();
        options.setLayout(new GridLayout(2,3, 10, 10));

        JLabel output = new JLabel("Output Option:");
        options.add(output);

        JCheckBox file = new JCheckBox("File");
        options.add(file);

        JCheckBox console = new JCheckBox("Console");
        options.add(console);

        JButton searchButton = new JButton("Search");
        searchButton.addActionListener(e -> searchButtonClick());
        options.add(searchButton);

        JButton resetButton = new JButton("Reset");
        resetButton.addActionListener(e -> resetButtonClick());
        options.add(resetButton);

        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(e -> exitButtonClick());
        options.add(exitButton);

        //Panel that displays all the search options for value1
        //Left - Tables within the database
        //Middle - Columns within the chosen table
        //Right - All values of the chosen column
        JPanel searchOptions1 = new JPanel();
        searchOptions1.setLayout(new GridLayout(1,3, 10, 10));

        JList tableList1 = new JList();
        tableList1.setVisibleRowCount(8);
        JScrollPane tableList1Scroll = new JScrollPane(tableList1);
        searchOptions1.add(tableList1Scroll);

        //Get values for list from tableList1 selection
        JList columnList1 = new JList();
        columnList1.setVisibleRowCount(8);
        JScrollPane columnList1Scroll = new JScrollPane(columnList1);
        searchOptions1.add(columnList1Scroll);

        //Get values for list from columnList1 selection
        JList dataList1 = new JList();
        dataList1.setVisibleRowCount(8);
        JScrollPane dataList1Scroll = new JScrollPane(dataList1);
        searchOptions1.add(dataList1Scroll);
       
        //Panel that displays all the search options for value2
        //Left - Tables within the database
        //Middle - Columns within the chosen table
        //Right - All values of the chosen column
        JPanel searchOptions2 = new JPanel();
        searchOptions2.setLayout(new GridLayout(1,3, 10, 10));

        JList tableList2 = new JList();
        tableList2.setVisibleRowCount(8);
        JScrollPane tableList2Scroll = new JScrollPane(tableList2);
        searchOptions2.add(tableList2Scroll);

        //Get values for list from tableList2 selection
        JList columnList2 = new JList();
        columnList2.setVisibleRowCount(8);
        JScrollPane columnList2Scroll = new JScrollPane(columnList2);
        searchOptions2.add(columnList2Scroll);

        //Get values for list from columnList2 selection
        JList dataList2 = new JList();
        dataList2.setVisibleRowCount(8);
        JScrollPane dataList2Scroll = new JScrollPane(dataList2);
        searchOptions2.add(dataList2Scroll);

        //Combine all the panels onto the frame
        mainFrame.add(options);
        mainFrame.add(searchOptions1);
        mainFrame.add(searchOptions2);
        mainFrame.setVisible(true);
    }

    /*
     String conf_name = "";
     try{
     //create a statement object
       Statement stmt = conn.createStatement();
       //create an SQL statement
       String sqlStatement = "SELECT * FROM public.\"Conference\"";
       //send statement to DBMS
       ResultSet result = stmt.executeQuery(sqlStatement);

       //OUTPUT
       JOptionPane.showMessageDialog(null,"Conference names from the database");
       //System.out.println("______________________________________");
       while (result.next()) {
         //System.out.println(result.getString("Name"));
         conf_name += result.getString("Name")+"\n";
       }
     } 
     catch (Exception e){
     JOptionPane.showMessageDialog(null,"Error accessing Database.");
     }
   JOptionPane.showMessageDialog(null,conf_name);
   */

    public void searchButtonClick()
    {
        mainFrame.setVisible(false);
        reassureFrame dF = new reassureFrame(trans);
        mainFrame.dispose();
    }

    public void resetButtonClick()
    {
        mainFrame.setVisible(false);
        reassureFrame dF = new reassureFrame(trans);
        mainFrame.dispose();
    }

    public void exitButtonClick()
    {
        mainFrame.setVisible(false);
        reassureFrame dF = new reassureFrame(trans);
        mainFrame.dispose();
    }
}