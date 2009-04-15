package umt.cs_346.elevatorsimulation.elevator;

import java.awt.*;
import javax.swing.*;

public class ElevatorCar extends Polygon{
	
	private int xStart;
	private int yStart;
	private int [] xValues;
	private int [] yValues;
	private Timer carTimer;
	
	public ElevatorCar(int x, int y){
		
		xStart = x;
		yStart = y;
		xValues = createXArray();
		yValues = createYArray();
		
		this.xpoints = createXArray();
		this.ypoints = createYArray();
		this.npoints = this.ypoints.length;

	}
	
	public void draw(Graphics page, int floorCoordinate){
		if(this.ypoints[4] > floorCoordinate){
			for(int i = 0; i < this.npoints; i++){
				this.ypoints[i]--;
			}
		}else{
			for(int i = 0; i < this.npoints; i++){
				this.ypoints[i]++;
			}
		}
		page.setColor(Color.yellow);
		page.drawPolygon(this);
	}
	
	private int [] createXArray(){
		int [] xCoord = {
				xStart,
				xStart + 50,
				xStart + 65,
				xStart + 65,
				xStart + 50, 
				xStart,
				xStart,
				xStart + 50,
				xStart + 50,
				xStart,
				xStart,
				xStart + 15,
				xStart + 65,
				xStart + 65,
				xStart + 15,
				xStart + 15,
				xStart,
				xStart + 15
		};
		return xCoord;
	}
	private int [] createYArray(){
		int [] yCoord = {
				yStart,
				yStart,
				yStart + 15,
				yStart,
				yStart - 15,
				yStart - 15,
				yStart,
				yStart,
				yStart - 15,
				yStart - 15,
				yStart,
				yStart + 15,
				yStart + 15,
				yStart,
				yStart,
				yStart + 15,
				yStart - 15,
				yStart,
		};
		return yCoord;
	}

	public int getY(){
		return this.ypoints[4];
	}
	public void increment(){

		for(int i = 0; i < yValues.length; i++){
			yValues[i] ++;
		}
		
	}
	
}
