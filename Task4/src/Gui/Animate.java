package Gui;


import File_format.Robot2Element;
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
	private Robot2Element cs;
	boolean keepGoing;
	

	public Animate(MyFrame frame, Play playS, Robot2Element cs,double angle) {
		this.frame = frame;
		this.playS=playS;
		this.cs = cs;
		keepGoing = true;
		this.angle = angle;
		if(!playS.isRuning())
		playS.start();
	}
	
	@Override
	public void run() {
		while(playS.isRuning() && keepGoing)
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
		if(keepGoing)
		frame.Result();
	}
	
	public void updateAngle(double angle)
	{
		this.angle =angle;
	}
	
	
}
