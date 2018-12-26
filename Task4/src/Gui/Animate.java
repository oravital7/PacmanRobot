package Gui;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Timer;

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
	boolean keepGoing;
	

	public Animate(MyFrame frame, Play playS, Robot2Element cs) {
		this.frame = frame;
		this.playS=playS;
		this.cs = cs;
		keepGoing = true;
		if(!frame.running)
		playS.start();
	}
	
	@Override
	public void run() {
		while(playS.isRuning())
		{
			playS.rotate(angle);
			cs.MakeElements(playS.getBoard());			
			frame.updater();
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		frame.Result();
	}
	
	
	
	public void updateAngle(double angle)
	{
		this.angle =angle;
	}
	
	
}
