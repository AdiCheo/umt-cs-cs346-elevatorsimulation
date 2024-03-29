package umt.cs_346.elevatorsimulation.constants;

import java.util.Calendar;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.text.SimpleDateFormat;

/**
 * Class containing static values that are referenced throughout the application.
 * @author Chris Hanshew
 */
public class Constants {
	
	public static final int LAYOUT_HORIZONAL_GAP = 5;
	public static final int LAYOUT_VERITCAL_GAP = 5;
	public static final int LAYOUT_ROWS = 2;
	public static final int LAYOUT_COLUMNS = 6;

    public static final int FLOOR_HEIGHT = 15;
    public static final int CAR_HEIGHT = 15;
    
    
    public static final int YSTART = 200;
    public static final int XSTART = 50;
    
    public static final int ELEVATOR_DELAY = 100;
    public static final int BUTTON_VALUE_OFFSET = 1;
    public static final String TITLE = "Elevator Simulation";
   
    public static final String DATE_FORMAT_NOW = "M-dd HH:mm:ss";
    
    public static final String COMMAND_URL = "http://www.chrishanshew.com/elevatorsimulation/consolecommands.html";
    
    public static final Dimension FLOOR_BUTTON_DIMENSION = new Dimension(20, 15);
    public static final Color FLOOR_BUTTON_COLOR = new Color(Color.lightGray.getRGB());
    
    public static final GridLayout GUI_BUTTON_LAYOUT = new GridLayout(12, 1, 0, 10);
    
    public static final Dimension ERROR_FRAME_DIMENSION = new Dimension(200, 300);
    
    public static String currentTime() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
        return sdf.format(cal.getTime());

      }
}
