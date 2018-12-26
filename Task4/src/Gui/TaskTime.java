package Gui;

import java.util.TimerTask;

import File_format.StringTranslate;
import Robot.Play;

public class TaskTime extends TimerTask {
	private Animate animate;
	private Play playS;
	private StringTranslate trans;
	
	public TaskTime(Animate animate, Play playS) {
		this.playS = playS;
		this.animate = animate;
		trans = new StringTranslate();
	}

	@Override
	public void run() {
		trans.setString(playS.getStatistics());
		System.out.println(trans.getTimeLeft());
		if(trans.getTimeLeft()==0) {
			animate.keepGoing=false;
			cancel();
		}

	}


}
