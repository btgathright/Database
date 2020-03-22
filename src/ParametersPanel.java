import javax.swing.*;
import java.awt.*;

public class ParametersPanel extends JPanel
{
    private JTabbedPane jTabbedPane;
    private JPanel simplePanel, simplePanelButtons, chainPanel, advancedPanel, advancedPanelButtons, tablePanel, tablePanel2,
        table1Panel, table2Panel, columnPanel, columnPanel2, column1Panel, column2Panel, valuePanel, value1Panel, value1Panel2,
        value2Panel, value2Panel2, questionPanel;
    private GridLayout gridLayoutSimple, gridLayoutSimpleButtons, gridLayoutChain, gridLayoutAdvanced,
        gridLayoutAdvancedButtons, tableLayout;
    private GridBagLayout gridBagLayoutParamPanel;
    private ButtonGroup simpleGroup, advancedGroup;
    private JTextField valueText, value1Text, value1Text2, value2Text, value2Text2;
    private JRadioButton forButton, againstButton, unionButton, intersectionButton;
    private JComboBox tableComboBox, tableComboBox2, table1ComboBox, table2ComboBox, columnComboBox, columnComboBox2,
        column1ComboBox, column2ComboBox, questionComboBox;
    private JLabel tableLabel, tableLabel2, table1Label, table2Label, columnLabel, columnLabel2, column1Label, column2Label,
        valueLabel, value1Label, value1Label2, value2Label, value2Label2, questionLabel;

    public ParametersPanel()
    {
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

        //Initialize all combo boxes
        tableComboBox = new JComboBox(); //Fill with values
        tableComboBox2 = new JComboBox(); //Fill with values
        table1ComboBox = new JComboBox(); //Fill with values
        table2ComboBox = new JComboBox(); //Fill with values
        columnComboBox = new JComboBox(); //Fill once table is selected
        columnComboBox2 = new JComboBox(); //Fill once table is selected
        column1ComboBox = new JComboBox(); //Fill once table1 is selected
        column2ComboBox = new JComboBox(); //Fill once table2 is selected
        questionComboBox = new JComboBox(); //Fill with values

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

        forButton.setHorizontalAlignment(SwingConstants.RIGHT);
        againstButton.setHorizontalAlignment(SwingConstants.CENTER);
        unionButton.setHorizontalAlignment(SwingConstants.RIGHT);
        intersectionButton.setHorizontalAlignment(SwingConstants.CENTER);

        //Create table panel
        tablePanel = new JPanel();
        tableLayout = new GridLayout(1,2, 10, 0);
        tablePanel.setLayout(tableLayout);
        tablePanel.add(tableLabel);
        tablePanel.add(tableComboBox);

        //Create table panel2
        tablePanel2 = new JPanel();
        tablePanel2.setLayout(tableLayout);
        tablePanel2.add(tableLabel2);
        tablePanel2.add(tableComboBox2);

        //Create table1 panel
        table1Panel = new JPanel();
        table1Panel.setLayout(tableLayout);
        table1Panel.add(table1Label);
        table1Panel.add(table1ComboBox);
        
        //Create table2 panel
        table2Panel = new JPanel();
        table2Panel.setLayout(tableLayout);
        table2Panel.add(table2Label);
        table2Panel.add(table2ComboBox);

        //Create column panel
        columnPanel = new JPanel();
        columnPanel.setLayout(tableLayout);
        columnPanel.add(columnLabel);
        columnPanel.add(columnComboBox);

        //Create column panel2
        columnPanel2 = new JPanel();
        columnPanel2.setLayout(tableLayout);
        columnPanel2.add(columnLabel2);
        columnPanel2.add(columnComboBox2);

        //Create column1 panel
        column1Panel = new JPanel();
        column1Panel.setLayout(tableLayout);
        column1Panel.add(column1Label);
        column1Panel.add(column1ComboBox);

        //Create column2 panel
        column2Panel = new JPanel();
        column2Panel.setLayout(tableLayout);
        column2Panel.add(column2Label);
        column2Panel.add(column2ComboBox);

        //Create value panel
        valuePanel = new JPanel();
        valuePanel.setLayout(tableLayout);
        valuePanel.add(valueLabel);
        valuePanel.add(valueText);

        //Create value1 panel
        value1Panel = new JPanel();
        value1Panel.setLayout(tableLayout);
        value1Panel.add(value1Label);
        value1Panel.add(value1Text);

        //Create value1 panel2
        value1Panel2 = new JPanel();
        value1Panel2.setLayout(tableLayout);
        value1Panel2.add(value1Label2);
        value1Panel2.add(value1Text2);

        //Create value2 panel
        value2Panel = new JPanel();
        value2Panel.setLayout(tableLayout);
        value2Panel.add(value2Label);
        value2Panel.add(value2Text);

        //Create value2 panel2
        value2Panel2 = new JPanel();
        value2Panel2.setLayout(tableLayout);
        value2Panel2.add(value2Label2);
        value2Panel2.add(value2Text2);

        //Create question panel
        questionPanel = new JPanel();
        questionPanel.setLayout(tableLayout);
        questionPanel.add(questionLabel);
        questionPanel.add(questionComboBox);

        //Create for/against button panel
        simplePanelButtons = new JPanel();
        gridLayoutSimpleButtons = new GridLayout(1, 2);
        simplePanelButtons.setLayout(gridLayoutSimpleButtons);
        simplePanelButtons.add(forButton);
        simplePanelButtons.add(againstButton);
        //simpleGroup.add(forButton);
        //simpleGroup.add(againstButton);

        //Create union/intersection button panel
        advancedPanelButtons = new JPanel();
        gridLayoutAdvancedButtons = new GridLayout(2, 1);
        advancedPanelButtons.setLayout(gridLayoutAdvancedButtons);
        advancedPanelButtons.add(unionButton);
        advancedPanelButtons.add(intersectionButton);
        //advancedGroup.add(unionButton);
        //advancedGroup.add(intersectionButton);



        //Simple Parameters
        //Initialize simple panel layout
        simplePanel = new JPanel();
        gridLayoutSimple = new GridLayout(5, 1, 0, 30);
        simplePanel.setLayout(gridLayoutSimple);

        //Add components to simple panel
        simplePanel.add(tablePanel);
        simplePanel.add(columnPanel);
        simplePanel.add(valuePanel);
        simplePanel.add(questionPanel);
        simplePanel.add(simplePanelButtons);



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
} //End class