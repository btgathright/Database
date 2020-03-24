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

public class TrackerFrame extends JFrame
{
    private static TrackerFrame inst = null;
    private MainPanel mainPanel;
    private GridBagLayout gridBagLayoutTrackFrame;

    public TrackerFrame(DBController controller)
    {
        this.setTitle("S.S. Tracker");
        //this.setMinimumSize(Toolkit.getDefaultToolkit().getScreenSize());
        this.setSize(850, 638);
        this.setLocation(350, 75);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        gridBagLayoutTrackFrame = new GridBagLayout();
        mainPanel = new MainPanel(controller);

        this.setLayout(gridBagLayoutTrackFrame);
        this.getContentPane().add(mainPanel, new GridBagConstraints(0,0,1,1,1,1,GridBagConstraints.CENTER,GridBagConstraints.BOTH,new Insets(10,10,10,10),0,0));
        inst = this;
    } //End constructor

    public static TrackerFrame getInstance()
    {
        return inst;
    } //End function
} //End class