package Gui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyObserve  implements KeyListener {

	private boolean left,up,right,down;
	private MyFrame f;

	public KeyObserve(MyFrame f) {
		this.f=f;
		left=up=right=down=false;
	}

	//
	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyPressed(KeyEvent e) {
		int x = e.getKeyCode();
		if(x==KeyEvent.VK_SPACE) 
			f.keyStart();

		if(x==KeyEvent.VK_LEFT) left = true;
		if(x==KeyEvent.VK_RIGHT) right = true;
		if(x==KeyEvent.VK_UP)	 up = true;
		if(x==KeyEvent.VK_DOWN) down = true;
		SendKey();
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int x=e.getKeyCode();
		if(x==KeyEvent.VK_LEFT) left = false;
		if(x==KeyEvent.VK_RIGHT) right = false;
		if(x==KeyEvent.VK_UP)	 up = false;
		if(x==KeyEvent.VK_DOWN) down = false;		
		SendKey();
	}
	
	private void SendKey() {
		if(left && up) 
			f.controlByKey(305);
		
		else if(right && up) 
			f.controlByKey(45);
		
		else if(right && down) 
			f.controlByKey(135);
		
		else if(left && down) 
			f.controlByKey(225);
		
		else if(left) 
			f.controlByKey(270);
		
		else if(right) 
			f.controlByKey(90);
		
		else if(up) 
			f.controlByKey(0);
		
		else if(down) 
			f.controlByKey(180);
	}

}

