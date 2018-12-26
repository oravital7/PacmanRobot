package Gui;

import java.util.Iterator;

import Coords.Map;
import File_format.Robot2Element;
import Gameboard.Game;
import Gameboard.Pacman;
import Geom.Point3D;
import Roads.PathData;
import Robot.Play;

/**
 * Responsible to update points of a single Pacman
 * accord path direction that solve by ShortestPathAlgo
 * Class extends thread so can work simultaneously
 *
 */
public class Animate extends Thread  {
	private MyFrame frame; // Use Gui frame for repaint the frame
	private Play playS;
	private double angle;
	Robot2Element cs;
	
	

	public Animate(MyFrame frame, Play playS, Robot2Element cs) {
		this.frame = frame;
		this.playS=playS;
		this.angle=0;
		this.cs = cs;
		playS.start();
	}
	
	@Override
	public void run() {
		int i=0;
		while(i<1000)
		{
			playS.rotate(angle);
			cs.MakeElements(playS.getBoard());			
			frame.update();
			
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void updateAngle(double angle)
	{
		this.angle =angle;
	}
	

	
}
