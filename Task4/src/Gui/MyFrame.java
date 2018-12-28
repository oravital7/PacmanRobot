package Gui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.KeyStroke;
import javax.swing.event.MouseInputListener;
import javax.swing.filechooser.FileNameExtensionFilter;


import Coords.Map;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;

import File_format.Robot2Element;
import File_format.StringTranslate;
import Gameboard.Blocks;
import Gameboard.Fruit;
import Gameboard.Game;
import Gameboard.Ghost;
import Gameboard.Me;
import Gameboard.Pacman;
import Geom.Point3D;
import Robot.Play;

/**
 * This class is responsible for the graphical
 *  representation of the game and user experience
 *
 */
public class MyFrame extends JFrame implements ActionListener ,Serializable  {
	private static final long serialVersionUID = 2344533353395219312L;

	private JLabel displayCoord, score, timeLeft,totalTime,ghostKill, outOfbox; // Label for hover mouse (show current pixles)
	private JMenuItem open, clear, stepByStep, Exit, play, automatic;
	private Game game; // Current game
	private Map map; // Our image & converts
	private BufferedImage pacmanImg, fruitImg, ghostImg, meImg;
	private Cursor Me; // Change icon mouse accord selection
	private JRadioButton mouseRadio, meRadio; // Radio button to switch between Pacman, Fruit and Mouse
	private boolean stepByStepB,playB, openedGame, meB; // If is in animation progress avoid to do another commands
	private Orien rotate;
	private Play playS; 
	private double angle;
	private Animate animate;
	private Robot2Element cs;
	private StringTranslate trans;

	public static void main(String[] args) {
		new  MyFrame();
	}

	public MyFrame() {

		openedGame=playB=stepByStepB = false; // We start with no progress(game running animation)
		displayCoord = new JLabel();
		displayCoord.setFont(new Font("Tahoma", Font.PLAIN, 15));
		displayCoord.setForeground(Color.white);
		add(displayCoord);

		score = new JLabel();
		score.setFont(new Font("Tahoma", Font.PLAIN, 17));
		score.setForeground(Color.white);
		score.setBounds(4, 10, 150, 20);

		timeLeft = new JLabel();
		timeLeft.setForeground(Color.white);
		timeLeft.setFont(new Font("Tahoma", Font.PLAIN, 17));
		timeLeft.setBounds(4, 30, 150, 20);

		totalTime = new JLabel();
		totalTime.setForeground(Color.white);
		totalTime.setFont(new Font("Tahoma", Font.PLAIN, 17));
		totalTime.setBounds(4, 50, 170, 20);

		ghostKill = new JLabel();
		ghostKill.setForeground(Color.white);
		ghostKill.setFont(new Font("Tahoma", Font.PLAIN, 17));
		ghostKill.setBounds(4, 70, 150, 20);

		outOfbox = new JLabel();
		outOfbox.setForeground(Color.white);
		outOfbox.setFont(new Font("Tahoma", Font.PLAIN, 17));
		outOfbox.setBounds(4, 90, 150, 20);

		add(score);
		add(timeLeft);
		add(totalTime);
		add(ghostKill);
		add(outOfbox);

		trans = new StringTranslate();
		map = Map.map();
		BackGroundPanel panel = new BackGroundPanel();

		add(panel);

		game = null;

		JMenuBar menuBar = new JMenuBar();
		JMenu File = new JMenu("File");
		open = new JMenuItem("Open File...",new ImageIcon("Icon\\open.png"));
		open.addActionListener(this);
		Exit = new JMenuItem("Exit");
		Exit.addActionListener(this);

		File.setMnemonic(KeyEvent.VK_S);
		File.add(open);
		File.add(Exit);

		menuBar.add(File);
		open.setAccelerator(KeyStroke.getKeyStroke(
				KeyEvent.VK_S, ActionEvent.CTRL_MASK));

		JMenu GameMenu = new JMenu("Game");
		stepByStep = new JMenuItem("Play step By Step", new ImageIcon("Icon\\play.png")); // Invoke image icon
		stepByStep.addActionListener(this);
		play = new JMenuItem("Play normal", new ImageIcon("Icon\\play.png")); // Invoke image icon
		play.addActionListener(this);
		automatic = new JMenuItem("Automatic", new ImageIcon("Icon\\play.png")); // Invoke image icon
		automatic.addActionListener(this);
		clear = new JMenuItem("Clear", new ImageIcon("Icon\\clear.png"));
		clear.addActionListener(this);

		GameMenu.add(play);
		GameMenu.add(stepByStep);
		GameMenu.add(automatic);
		GameMenu.add(clear);
		GameMenu.addActionListener(this);
		menuBar.add(GameMenu);

		JLabel mouse = new JLabel(new ImageIcon("Icon\\mouse.png"));
		mouseRadio = new JRadioButton("Pointer");
		mouseRadio.setSelected(true);
		mouseRadio.addActionListener(this);
		menuBar.add(mouse);
		menuBar.add(mouseRadio);

		JLabel Me = new JLabel(new ImageIcon("Icon\\me.png"));
		meRadio = new JRadioButton("Me");
		meRadio.addActionListener(this);
		menuBar.add(Me);
		menuBar.add(meRadio);

		setJMenuBar(menuBar);
		ButtonGroup group = new ButtonGroup(); // Create a groupButton To synchronize the buttons
		group.add(mouseRadio);
		group.add(meRadio);
		meRadio.setEnabled(false);

		FastFileOpen FastOpen = new FastFileOpen(this);
		menuBar.add(FastOpen);


		start();

		setSize(1200, 800);
		setLocation(300, 50);
		setVisible(true);

		setDefaultCloseOperation(EXIT_ON_CLOSE);

	}

	private void start() {
		try {
			fruitImg = ImageIO.read(new File("Icon\\fruit.png"));
			pacmanImg = ImageIO.read(new File("Icon\\pacman.png"));
			ghostImg = ImageIO.read(new File("Icon\\ghost.png"));
			meImg = ImageIO.read(new File("Icon\\me.png"));
			rotate = new Orien(meImg);
		} catch (IOException e) {
			e.printStackTrace();
		}
		Me=Toolkit.getDefaultToolkit().createCustomCursor(meImg, new Point(0, 0), "Me");
	}

	/**
	 * Didn't find if repaint is a synchronized method
	 * so for double check this method ensures that
	 */
	public synchronized void updater() {
		trans.setString(playS.getStatistics());
		score.setText(trans.getScore());		
		totalTime.setText("Total Time: "+trans.getTotalTime());
		timeLeft.setText("Time Left: "+trans.getTimeLeft());
		ghostKill.setText("Kill by ghost: "+trans.getGhostKill());
		outOfbox.setText("Out of box: "+trans.getOutOfBox());
		repaint();
	}

	/**
	 * Responsible to send a message, when the game finish
	 * @param TotalTime
	 * @param fruitWeight
	 * @param TotalResult
	 */
	public void Result() {
		openedGame=playB=stepByStepB=meB = false; // Change running to false that we finish right now the current game
		// content of the message
		JOptionPane.showMessageDialog(this,
				playS.getStatistics(),
				"Game Over!\n ", 
				JOptionPane.INFORMATION_MESSAGE); 
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if(o==Exit)
			System.exit(2);
		// reset the game for new one
		if(o==clear) {
			game = null;
			openedGame=stepByStepB = false;
			if(playB) {
				playB = false;
				animate.keepGoing=false;
			}
			repaint(); 
			return;
		}

		if(o==open) {
			JFileChooser fc = new JFileChooser(); 
			fc.setDialogTitle("Select a csv file only");
			// Only csv file accepted
			FileNameExtensionFilter restrict = new FileNameExtensionFilter(".csv", "csv"); 
			fc.setFileFilter(restrict);
			int s = fc.showOpenDialog(null); // Open a file Chooser dialog
			if(s == JFileChooser.APPROVE_OPTION) { // The user select a csv file if doesn't do nothing
				File f = fc.getSelectedFile();
				openGameFile(f);
			}
			return;
		}

		if(o==meRadio) { // Fruit choose, change cursor
			setCursor(Me);
			displayCoord.setVisible(false);
			return;
		}
		if(o==mouseRadio) { // Mouse choose, change cursor
			setCursor(null);
			return;
		}

		if(o==stepByStep) {
			mouseRadio.doClick();
			if(game == null||!meB) // if try to play a empty game send error
				errorMessage();

			else { // Ok let's start the game
				stepByStepB = true;
				if(playB) 	playB = animate.keepGoing=false;		
				else playS.start();
			}
			return;
		}

		if(o==play) {
			if(game == null||!meB)
				errorMessage();
			else {
				stepByStepB = false;
				mouseRadio.doClick();
				meRadio.setEnabled(false);
				animate = new Animate(this,playS,cs);
				animate.start();
				playB = true;
			}
		}
	}

	public void openGameFile(File f) {
		if(playB) animate.keepGoing=false;
		playS = new Play(f.getAbsolutePath());
		playS.setIDs(5555, 66666);
		openedGame=true;
		playB=stepByStepB=meB=false;
		map.setNewBounds(playS.getBoundingBox());
		meRadio.setEnabled(true);
		meRadio.doClick();
		game = new Game();
		angle=90;
		cs = new Robot2Element(game);
		cs.MakeElements(playS.getBoard()); // Translate a csv file into a new game
		repaint();
	}

	private void errorMessage() {
		JOptionPane.showMessageDialog(this,
				"Error while Playing \nload map and insert your pacman",
				"Error: Unable play the game",				
				JOptionPane.ERROR_MESSAGE);
	}


	/**
	 * This class responsible to paint all our elements and listen to user 
	 * for each command such as Clicks, drag and etc
	 *
	 */
	private class BackGroundPanel extends JPanel implements MouseInputListener ,Serializable {
		private static final long serialVersionUID = -3626966327917598406L;

		public BackGroundPanel() {
			addMouseListener(this);
			addMouseMotionListener(this);
		}

		protected void paintComponent(Graphics g) {
			super.paintComponent(g);

			// Draw our map as background
			g.drawImage(map.getMap(), 0, 0, getWidth(), getHeight(), this);

			if(game != null) {
				Graphics2D g2d = (Graphics2D) g; // the 2d drawing java paint
				// Get the current game elements arrays
				Me me = game.getMe();

				Point3D p;
				// Ratio for scaling the Pacmans and fruit animation
				double ratioH = getHeight()/600.0;
				double ratioW = getWidth()/800.0;

				for(Fruit fruit : game.getFruits()) { // Move all fruit array and draw them
					p = map.coord2pixel(fruit.getPoint(), getWidth(), getHeight());	// Fruit point saved geograpic coord, so convert it
					if(fruit.destroyed)
						g.drawImage(fruitImg, (int)p.x(), (int)p.y(), (int)(17*ratioW), (int)(17*ratioH), this);
				}

				for(Blocks b : game.getblocks()) {			
					g.setColor(Color.black);
					Point3D pUp = map.coord2pixel(b.getPoint(), getWidth(), getHeight());
					Point3D pDown = map.coord2pixel(b.getPoint2(), getWidth(), getHeight());

					Rectangle r = new Rectangle(); 
					r.x = (int) pUp.x();
					r.y = (int) pUp.y();
					r.add(pDown.x(),pDown.y());
					g2d.fill(r);
				}

				for(Pacman pacman : game.getPacmans()) { // Move all pacman array and draw them
					p = map.coord2pixel(pacman.getPoint(), getWidth(), getHeight()); // Convert to pixels coord
					// Draw with a correct rotate
					if(pacman.destroyed)
						g2d.drawImage(pacmanImg, (int)p.x(), (int)p.y(),(int)(22*ratioW), (int)(22*ratioH), this);
				}

				for(Ghost ghost : game.getGhosts()) { // Move all pacman array and draw them
					p = map.coord2pixel(ghost.getPoint(), getWidth(), getHeight()); // Convert to pixels coord
					g2d.drawImage(ghostImg, (int)p.x(), (int)p.y(),(int)(22*ratioW), (int)(22*ratioH), this);
				}

				me.setOrien(angle-90);

				p = map.coord2pixel(me.getPoint(), getWidth(), getHeight()); // Convert to pixels coord
				AffineTransformOp op = rotate.getTransform(me.getOrien()); // Save a transform rotate
				g2d.drawImage(op.filter(meImg, null), (int)p.x(), (int)p.y(),(int)(22*ratioW), (int)(22*ratioH), this);

				if(playS.isRuning()) {
					g2d.setColor(new Color(1f,0f,0.7f,.2f ));
					g2d.fillRect(3, 3, 155, 110);
				}

			}
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			int x = e.getX();
			int y = e.getY();
			System.out.println("Clicks: ("+x+", "+y+')'); // Print coords for each user clicks
			Point3D p = new Point3D(x,y);
			p = map.pixel2coord(p, getWidth(), getHeight());
			System.out.println("Geograpich coords: ("+p+')'); // Print geo corrds as well
			if(openedGame && !playS.isRuning() ) {
				playS.setInitLocation(p.x(), p.y());
				game.getMe().setPoint(p);
				meB=true;
				repaint();
			}

			else if(stepByStepB) {
				if(playS.isRuning()) {
					angle = map.anglePoints(game.getMe().getPoint(), p);
					playS.rotate(angle);
					cs.MakeElements(playS.getBoard());
					updater();
				}
				else Result();
			}

			else if(playB) {
				angle = map.anglePoints(game.getMe().getPoint(), p);
				animate.updateAngle(angle);
			}	
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			if(mouseRadio.isSelected()) // If mouse selected & entert into frame turn on show coords near mouse
				displayCoord.setVisible(true);
		}

		@Override
		public void mouseExited(MouseEvent e) { // if mouse exit frame hide it
			displayCoord.setVisible(false);
			mouseRadio.doClick();
		}

		@Override
		public void mousePressed(MouseEvent e) {}

		@Override
		public void mouseReleased(MouseEvent e) {}

		@Override
		public void mouseDragged(MouseEvent e) { // Accept drag drawing, so when drag add it as character
			mouseClicked(e);
			try {
				Thread.sleep(40);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			if(mouseRadio.isSelected()) { // If mouse moved print near mouse current coords
				displayCoord.setBounds(e.getX(), e.getY(), 80, 55);
				displayCoord.setText(e.getX()+", "+e.getY());
			}
		}

	}

}
