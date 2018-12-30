package Gui;

/**
 * Responsible to update points of a single Pacman
 * accord path direction that solve by ShortestPathAlgo
 * Class extends thread so can work simultaneously
 *
 */
public class Animate extends Thread  {
	private MyFrame frame; // Use Gui frame for repaint the frame
	boolean keepGoing;


	public Animate(MyFrame frame) {
		this.frame = frame;
		keepGoing = true;
	}

	@Override
	public void run() {
		while(keepGoing)
		{
			frame.rotate();
			try {
				Thread.sleep(30);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

}
