package umt.cs_346.elevatorsimulation.controller;

import umt.cs_346.elevatorsimulation.elevator.*;

import umt.cs_346.elevatorsimulation.GUI.*;
import umt.cs_346.elevatorsimulation.floorqueue.*;
import umt.cs_346.elevatorsimulation.elevator.ElevatorList;
import umt.cs_346.elevatorsimulation.elevator.Elevator;
import umt.cs_346.elevatorsimulation.constants.*;

import java.awt.*;
import java.awt.event.*;

import javax.swing.Timer;

/**
 * 
 */
public class Controller{
	
	private FloorQueue queue;
	private ElevatorList elevators;
	
	private int iElevators = 12;
	private int iFloors = 12;
	
	private Timer timer;

	private ElevatorSimulationUI ESGUI;
	
	/**
	 * 
	 */
	public Controller(){
		
		timer = new Timer(50, new updateControl());
		
		elevators = new ElevatorList();
		populateElevators();
		ESGUI = new ElevatorSimulationUI(elevators);
		ESGUI.consoleAppend("Simulation Intialized.  Welcome!  Type help for console commands.");
		start();
	}
	
	/**
	 * 
	 * @param elevatorsNum
	 */
	private void populateElevators(){

		for(int i = 0; i < iElevators; i++){
			Elevator elevator = new Elevator(i, iFloors);
			elevators.add(elevator);
		}
	}
	private void start(){
		timer.start();
	}
	
	private void startSimulation(){
		for(int i = 0; i < iElevators; i++){
			elevators.get(i).start();
			elevators.get(i).setNextFloor(1);
		}
	}

	
	private void update(){
		timer.stop();
		String action = ESGUI.getAction();
		
		if(action != null){
			action = action.toLowerCase();
			ESGUI.resetAction();
			
			if(action.equals("start")){
				startSimulation();
				serveRequest(7);
				consoleOut("Simulation start");
			}
			else{
				if(action.startsWith("queue -")){
					
					int currentFloor = 0;
					int destination = 0;
					
					for(int i = 0; i < action.length(); i++){
						
						if(action.charAt(i) == 'c'){
							currentFloor = checkChar(action.charAt(i + 1));
							System.out.println(currentFloor);
						}else{
							if(action.charAt(i) == 'd'){
								destination = checkChar(action.charAt(i + 1));
								System.out.println(destination);
							}
						}
					}
					
					consoleOut("Request added to queue at Floor: " + currentFloor
								+ " to destination: " + destination);
				}
			else{
				if(action.startsWith("help")){

					openURL(Constants.COMMAND_URL);
					consoleOut("help");
				}
			else{
				consoleOut(ConsoleCommand.UNKOWN);
			}
			}
		}
		}
		timer.start();
	}
	
	private int checkChar(char c){
		int temp = 0;
		try{
			temp = Integer.parseInt(Character.toString(c));
			System.out.println(temp);
		}catch(NumberFormatException e){
			//e.printStackTrace();
		}
		return temp;
	}
	
	private void serveRequest(int nextFloor){
		for(int i = 0; i < elevators.size(); i++){
			if(elevators.get(i).isIdle()){
				elevators.get(i).setNextFloor(nextFloor);
				elevators.get(i).start();
			}
			break;
		}
	}
	
	private void consoleOut(String s){
		ESGUI.consoleAppend(s);
	}
	/////////////////////////////////////////////////////////
	//  Bare Bones Browser Launch                          //
	//  Version 1.5 (December 10, 2005)                    //
	//  By Dem Pilafian                                    //
	//  Supports: Mac OS X, GNU/Linux, Unix, Windows XP    //
	//  Example Usage:                                     //
	//     String url = "http://www.centerkey.com/";       //
	//     BareBonesBrowserLaunch.openURL(url);            //
	//  Public Domain Software -- Free to Use as You Like  //
	/////////////////////////////////////////////////////////
	private static void openURL(String url) {
		String errMsg = "Error attempting to launch web browser";
	    String osName = System.getProperty("os.name");
	    try {
	    	if (osName.startsWith("Mac OS")) {
	    		Class fileMgr = Class.forName("com.apple.eio.FileManager");
	            java.lang.reflect.Method openURL = fileMgr.getDeclaredMethod("openURL",
	            new Class[] {String.class});
	            openURL.invoke(null, new Object[] {url});
	            }
	         else if (osName.startsWith("Windows"))
	            Runtime.getRuntime().exec("rundll32 url.dll, FileProtocolHandler " + url);
	         else { //assume Unix or Linux
	            String[] browsers = {
	               "firefox", "opera", "konqueror", "epiphany", "mozilla", "netscape" };
	            String browser = null;
	            for (int count = 0; count < browsers.length && browser == null; count++)
	               if (Runtime.getRuntime().exec(
	                     new String[] {"which", browsers[count]}).waitFor() == 0)
	                  browser = browsers[count];
	            if (browser == null)
	               throw new Exception("Could not find web browser");
	            else
	               Runtime.getRuntime().exec(new String[] {browser, url});
	            }
	         }catch (Exception e) {
	        	 e.printStackTrace();
	        	  //showMessageDialog(null, errMsg + ":\n" + e.getLocalizedMessage());
	         }
	  }
	
	
	public class updateControl implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			update();
		}

		
		
	}
}
