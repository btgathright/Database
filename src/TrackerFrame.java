import javax.swing.*;
import java.awt.*;

public class TrackerFrame extends JFrame
{
    private static TrackerFrame inst = null;
    private MainPanel mainPanel;
    private GridBagLayout gridBagLayoutTrackFrame;

    public TrackerFrame()
    {
        this.setTitle("S.S. Tracker");
        this.setMinimumSize(Toolkit.getDefaultToolkit().getScreenSize());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        gridBagLayoutTrackFrame = new GridBagLayout();
        mainPanel = new MainPanel();

        this.setLayout(gridBagLayoutTrackFrame);
        this.getContentPane().add(mainPanel, new GridBagConstraints(0,0,1,1,1,1,GridBagConstraints.CENTER,GridBagConstraints.BOTH,new Insets(10,10,10,10),0,0));
    } //End constructor

    public static TrackerFrame getInstance()
    {
        if(inst == null)
        {
            inst = new TrackerFrame();
        }
        return inst;
    } //End function
} //End class