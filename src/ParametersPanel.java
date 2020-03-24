/*
Brandon Gathright
Seth Hamilton
Samiha Elahi
Johnny Hernandez
CSCE 315
3/23/2020
 */

import javax.swing.*;
import java.awt.*;
import java.util.Vector;

public class ParametersPanel extends JPanel
{
    private DBController cont;
    private static JTabbedPane jTabbedPane;
    private GridLayout gridLayoutSimple, gridLayoutSimpleButtons, gridLayoutChain, gridLayoutAdvanced, gridLayoutAdvancedButtons;
    private GridBagLayout gridBagLayoutParamPanel, tableLayout;
    private static ButtonGroup simpleGroup, advancedGroup;
    private static JTextField valueText, value1Text, value1Text2, value2Text, value2Text2;
    private static JRadioButton forButton, againstButton, unionButton, intersectionButton;
    private Vector<String> columnComboVals;
    private String tablenames[], questions[] = {"Q1", "Q2", "Q3"};

    private static JComboBox tableComboBox, tableComboBox2, table1ComboBox, table2ComboBox, columnComboBox, columnComboBox2,
        column1ComboBox, column2ComboBox, questionComboBox;

    private JLabel tableLabel, tableLabel2, table1Label, table2Label, columnLabel, columnLabel2, column1Label, column2Label,
        valueLabel, value1Label, value1Label2, value2Label, value2Label2, questionLabel;

    private JPanel simplePanel, simplePanelButtons, chainPanel, advancedPanel, advancedPanelButtons, tablePanel, tablePanel2,
        table1Panel, table2Panel, columnPanel, columnPanel2, column1Panel, column2Panel, valuePanel, value1Panel, value1Panel2,
        value2Panel, value2Panel2, questionPanel;

    public ParametersPanel(DBController controller)
    {
        cont = controller;

        //Initialize panel components
        gridBagLayoutParamPanel = new GridBagLayout();
        jTabbedPane = new JTabbedPane();

        //Initialize all text fields
        valueText = new JTextField();
        value1Text = new JTextField();
        value1Text2 = new JTextField();
        value2Text = new JTextField();
        value2Text2 = new JTextField();

        //Initialize all radio buttons
        forButton = new JRadioButton("For");
        againstButton = new JRadioButton("Against");
        unionButton = new JRadioButton("Union");
        intersectionButton = new JRadioButton("Intersection");

        //Initialize button groups
        simpleGroup = new ButtonGroup();
        advancedGroup = new ButtonGroup();

        //Initialize all string arrays
        tablenames = DBHelper.get_table_names(controller);
        columnComboVals = new Vector<String>();
        for (String s : DBHelper.get_column_names(controller, "Conference"))
        {
            columnComboVals.add(s);
        }

        //Initialize all combo boxes
        tableComboBox = new JComboBox(tablenames);
        tableComboBox2 = new JComboBox(tablenames);
        table1ComboBox = new JComboBox(tablenames);
        table2ComboBox = new JComboBox(tablenames);
        columnComboBox = new JComboBox(columnComboVals);
        columnComboBox2 = new JComboBox(columnComboVals);
        column1ComboBox = new JComboBox(columnComboVals);
        column2ComboBox = new JComboBox(columnComboVals);
        questionComboBox = new JComboBox(questions);

        //Initialize all event listeners
        tableComboBox.addActionListener(e -> tableSelect());
        tableComboBox2.addActionListener(e -> tableSelect2());
        table1ComboBox.addActionListener(e -> table1Select());
        table2ComboBox.addActionListener(e -> table2Select());

        //Initialize all labels
        tableLabel = new JLabel("Table");
        tableLabel2 = new JLabel("Table");
        table1Label = new JLabel("Table 1");
        table2Label = new JLabel("Table 2");
        columnLabel = new JLabel("Column");
        columnLabel2 = new JLabel("Column");
        column1Label = new JLabel("Column 1");
        column2Label = new JLabel("Column 2");
        valueLabel = new JLabel("Value");
        value1Label = new JLabel("Value 1");
        value1Label2 = new JLabel("Value 1");
        value2Label = new JLabel("Value 2");
        value2Label2 = new JLabel("Value 2");
        questionLabel = new JLabel("Question");

        //Set label positions
        tableLabel.setVerticalAlignment(JLabel.CENTER);
        tableLabel.setHorizontalAlignment(JLabel.RIGHT);
        tableLabel2.setVerticalAlignment(JLabel.CENTER);
        tableLabel2.setHorizontalAlignment(JLabel.RIGHT);
        table1Label.setVerticalAlignment(JLabel.CENTER);
        table1Label.setHorizontalAlignment(JLabel.RIGHT);
        table2Label.setVerticalAlignment(JLabel.CENTER);
        table2Label.setHorizontalAlignment(JLabel.RIGHT);
        columnLabel.setVerticalAlignment(JLabel.CENTER);
        columnLabel.setHorizontalAlignment(JLabel.RIGHT);
        columnLabel2.setVerticalAlignment(JLabel.CENTER);
        columnLabel2.setHorizontalAlignment(JLabel.RIGHT);
        column1Label.setVerticalAlignment(JLabel.CENTER);
        column1Label.setHorizontalAlignment(JLabel.RIGHT);
        column2Label.setVerticalAlignment(JLabel.CENTER);
        column2Label.setHorizontalAlignment(JLabel.RIGHT);
        valueLabel.setVerticalAlignment(JLabel.CENTER);
        valueLabel.setHorizontalAlignment(JLabel.RIGHT);
        value1Label.setVerticalAlignment(JLabel.CENTER);
        value1Label.setHorizontalAlignment(JLabel.RIGHT);
        value1Label2.setVerticalAlignment(JLabel.CENTER);
        value1Label2.setHorizontalAlignment(JLabel.RIGHT);
        value2Label.setVerticalAlignment(JLabel.CENTER);
        value2Label.setHorizontalAlignment(JLabel.RIGHT);
        value2Label2.setVerticalAlignment(JLabel.CENTER);
        value2Label2.setHorizontalAlignment(JLabel.RIGHT);
        questionLabel.setVerticalAlignment(JLabel.CENTER);
        questionLabel.setHorizontalAlignment(JLabel.RIGHT);

        forButton.setHorizontalAlignment(SwingConstants.CENTER);
        againstButton.setHorizontalAlignment(SwingConstants.CENTER);
        unionButton.setHorizontalAlignment(SwingConstants.CENTER);
        intersectionButton.setHorizontalAlignment(SwingConstants.CENTER);

        //Set component sizes
        tableComboBox.setPreferredSize(new Dimension(200, 25));
        tableComboBox2.setPreferredSize(new Dimension(200, 25));
        table1ComboBox.setPreferredSize(new Dimension(200, 25));
        table2ComboBox.setPreferredSize(new Dimension(200, 25));
        columnComboBox.setPreferredSize(new Dimension(200, 25));
        columnComboBox2.setPreferredSize(new Dimension(200, 25));
        column1ComboBox.setPreferredSize(new Dimension(200, 25));
        column2ComboBox.setPreferredSize(new Dimension(200, 25));
        questionComboBox.setPreferredSize(new Dimension(200, 25));
        valueText.setPreferredSize(new Dimension(200, 25));
        //value1Text.setPreferredSize(new Dimension(200, 25)); //this text box won't format to the proper size
        //If working then change GridBagContraints in value1Panel to NONE instead of HORIZONTAL
        value1Text2.setPreferredSize(new Dimension(200, 25));
        //value2Text.setPreferredSize(new Dimension(200, 25)); //this text box won't format to the proper size
        //If working then change GridBagContraints in value2Panel to NONE instead of HORIZONTAL
        value2Text2.setPreferredSize(new Dimension(200, 25));

        //Create table panel
        tablePanel = new JPanel();
        tableLayout = new GridBagLayout();
        tablePanel.setLayout(tableLayout);
        tablePanel.add(tableLabel, new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
        tablePanel.add(tableComboBox, new GridBagConstraints(1, 0, 1, 1, 1, 1, GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));

        //Create table panel2
        tablePanel2 = new JPanel();
        tablePanel2.setLayout(tableLayout);
        tablePanel2.add(tableLabel2, new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
        tablePanel2.add(tableComboBox2, new GridBagConstraints(1, 0, 1, 1, 1, 1, GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));

        //Create table1 panel
        table1Panel = new JPanel();
        table1Panel.setLayout(tableLayout);
        table1Panel.add(table1Label, new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
        table1Panel.add(table1ComboBox, new GridBagConstraints(1, 0, 1, 1, 1, 1, GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
        
        //Create table2 panel
        table2Panel = new JPanel();
        table2Panel.setLayout(tableLayout);
        table2Panel.add(table2Label, new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
        table2Panel.add(table2ComboBox, new GridBagConstraints(1, 0, 1, 1, 1, 1, GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));

        //Create column panel
        columnPanel = new JPanel();
        columnPanel.setLayout(tableLayout);
        columnPanel.add(columnLabel, new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
        columnPanel.add(columnComboBox, new GridBagConstraints(1, 0, 1, 1, 1, 1, GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));

        //Create column panel2
        columnPanel2 = new JPanel();
        columnPanel2.setLayout(tableLayout);
        columnPanel2.add(columnLabel2, new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
        columnPanel2.add(columnComboBox2, new GridBagConstraints(1, 0, 1, 1, 1, 1, GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));

        //Create column1 panel
        column1Panel = new JPanel();
        column1Panel.setLayout(tableLayout);
        column1Panel.add(column1Label, new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
        column1Panel.add(column1ComboBox, new GridBagConstraints(1, 0, 1, 1, 1, 1, GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));

        //Create column2 panel
        column2Panel = new JPanel();
        column2Panel.setLayout(tableLayout);
        column2Panel.add(column2Label, new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
        column2Panel.add(column2ComboBox, new GridBagConstraints(1, 0, 1, 1, 1, 1, GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));

        //Create value panel
        valuePanel = new JPanel();
        valuePanel.setLayout(tableLayout);
        valuePanel.add(valueLabel, new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
        valuePanel.add(valueText, new GridBagConstraints(1, 0, 1, 1, 1, 1, GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));

        //Create value1 panel
        value1Panel = new JPanel();
        value1Panel.setLayout(tableLayout);
        value1Panel.add(value1Label, new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
        value1Panel.add(value1Text, new GridBagConstraints(1, 0, 1, 1, 1, 1, GridBagConstraints.EAST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));

        //Create value1 panel2
        value1Panel2 = new JPanel();
        value1Panel2.setLayout(tableLayout);
        value1Panel2.add(value1Label2, new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
        value1Panel2.add(value1Text2, new GridBagConstraints(1, 0, 1, 1, 1, 1, GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));

        //Create value2 panel
        value2Panel = new JPanel();
        value2Panel.setLayout(tableLayout);
        value2Panel.add(value2Label, new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
        value2Panel.add(value2Text, new GridBagConstraints(1, 0, 1, 1, 1, 1, GridBagConstraints.EAST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));

        //Create value2 panel2
        value2Panel2 = new JPanel();
        value2Panel2.setLayout(tableLayout);
        value2Panel2.add(value2Label2, new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
        value2Panel2.add(value2Text2, new GridBagConstraints(1, 0, 1, 1, 1, 1, GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));

        //Create question panel
        questionPanel = new JPanel();
        questionPanel.setLayout(tableLayout);
        questionPanel.add(questionLabel, new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
        questionPanel.add(questionComboBox, new GridBagConstraints(1, 0, 1, 1, 1, 1, GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));

        //Create for/against button panel
        simplePanelButtons = new JPanel();
        gridLayoutSimpleButtons = new GridLayout(1, 2);
        simplePanelButtons.setLayout(gridLayoutSimpleButtons);
        simplePanelButtons.add(forButton);
        simplePanelButtons.add(againstButton);
        simpleGroup.add(forButton);
        simpleGroup.add(againstButton);

        //Create union/intersection button panel
        advancedPanelButtons = new JPanel();
        gridLayoutAdvancedButtons = new GridLayout(1, 2);
        advancedPanelButtons.setLayout(gridLayoutAdvancedButtons);
        advancedPanelButtons.add(unionButton);
        advancedPanelButtons.add(intersectionButton);
        advancedGroup.add(unionButton);
        advancedGroup.add(intersectionButton);



        //Simple Parameters
        //Initialize simple panel layout
        simplePanel = new JPanel();
        gridLayoutSimple = new GridLayout(5, 1, 0, 30);
        simplePanel.setLayout(gridLayoutSimple);

        //Add components to simple panel
        simplePanel.add(questionPanel);
        simplePanel.add(simplePanelButtons);
        simplePanel.add(tablePanel);
        simplePanel.add(columnPanel);
        simplePanel.add(valuePanel);



        //Chain Parameters
        //Initialize chain panel layout
        chainPanel = new JPanel();
        gridLayoutChain = new GridLayout(4, 1, 0, 30);
        chainPanel.setLayout(gridLayoutChain);

        //Add components to simple panel
        chainPanel.add(tablePanel2);
        chainPanel.add(columnPanel2);
        chainPanel.add(value1Panel2);
        chainPanel.add(value2Panel2);



        //Advanced Parameters
        advancedPanel = new JPanel();
        gridLayoutAdvanced = new GridLayout(7, 1, 0, 30);
        advancedPanel.setLayout(gridLayoutAdvanced);

        //Add components to simple panel
        advancedPanel.add(table1Panel);
        advancedPanel.add(column1Panel);
        advancedPanel.add(value1Panel);
        advancedPanel.add(advancedPanelButtons);
        advancedPanel.add(table2Panel);
        advancedPanel.add(column2Panel);
        advancedPanel.add(value2Panel);


        //Combine panes into single tabbed pane
        jTabbedPane.add("Simple", simplePanel);
        jTabbedPane.add("Chain", chainPanel);
        jTabbedPane.add("Advanced", advancedPanel);

        //Add tabbed pane to panel
        this.setLayout(gridBagLayoutParamPanel);
        this.add(jTabbedPane, new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), 0, 0));
        this.setBorder(BorderFactory.createTitledBorder("Parameters"));
    } //End constructor

    public void tableSelect()
    {
        columnComboBox.removeAllItems();
        String[] newVals = DBHelper.get_column_names(cont, (String)tableComboBox.getSelectedItem());
        for (String s: newVals)
        {
            String newItem;
            //Build newItem string for just the column name and not "tablename.columnname"
            columnComboBox.addItem(s); //Replace s with newItem once above is done
        }
        this.invalidate();
        this.validate();
        this.repaint();
    }

    public void tableSelect2()
    {
        columnComboBox2.removeAllItems();
        String[] newVals = DBHelper.get_column_names(cont, (String)tableComboBox2.getSelectedItem());
        for (String s: newVals)
        {
            String newItem;
            //Build newItem string for just the column name and not "tablename.columnname"
            columnComboBox2.addItem(s); //Replace s with newItem once above is done
        }
        this.invalidate();
        this.validate();
        this.repaint();
    }

    public void table1Select()
    {
        column1ComboBox.removeAllItems();
        String[] newVals = DBHelper.get_column_names(cont, (String)table1ComboBox.getSelectedItem());
        for (String s: newVals)
        {
            String newItem;
            //Build newItem string for just the column name and not "tablename.columnname"
            column1ComboBox.addItem(s); //Replace s with newItem once above is done
        }
        this.invalidate();
        this.validate();
        this.repaint();
    }

    public void table2Select()
    {
        column2ComboBox.removeAllItems();
        String[] newVals = DBHelper.get_column_names(cont, (String)table2ComboBox.getSelectedItem());
        for (String s: newVals)
        {
            String newItem;
            //Build newItem string for just the column name and not "tablename.columnname"
            column2ComboBox.addItem(s); //Replace s with newItem once above is done
        }
        this.invalidate();
        this.validate();
        this.repaint();
    }

    public static String getSearchType()
    {
        int searchIndex = jTabbedPane.getSelectedIndex();
        String search = null;

        if (searchIndex == 0)
        {
            search = "Simple";
        }
        else if (searchIndex == 1)
        {
            search = "Chain";
        }
        else if (searchIndex == 2)
        {
            search = "Advanced";
        }
        return search;
    }

    public static String[] getSimpleValues()
    {
        String simpleButtonSelection = "for";
        if (againstButton.isSelected())
        {
            simpleButtonSelection = "against";
        }

        String[] values = {
            (String)questionComboBox.getSelectedItem(),
            simpleButtonSelection,
            (String)tableComboBox.getSelectedItem(),
            (String)columnComboBox.getSelectedItem(),
            (String)valueText.getText()
        };
        return values;
    }

    public static String[] getChainValues()
    {
        String[] values = {
            (String)tableComboBox2.getSelectedItem(),
            (String)columnComboBox2.getSelectedItem(),
            (String)value1Text2.getText(),
            (String)value2Text2.getText()
        };
        return values;
    }

    public static String[] getAdvancedValues()
    {
        String advancedButtonSelection = "union";
        if (intersectionButton.isSelected())
        {
            advancedButtonSelection = "intersection";
        }

        String[] values = {
            (String)table1ComboBox.getSelectedItem(),
            (String)column1ComboBox.getSelectedItem(),
            (String)value1Text.getText(),
            advancedButtonSelection,
            (String)table2ComboBox.getSelectedItem(),
            (String)column2ComboBox.getSelectedItem(),
            (String)value2Text.getText()
        };
        return values;
    }

} //End class