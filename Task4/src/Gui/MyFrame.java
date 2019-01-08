package Gui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.event.MouseInputListener;
import javax.swing.filechooser.FileNameExtensionFilter;

import AutoAlgo.MainAlgo;
import Coords.Map;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;

import Gameboard.Blocks;
import Gameboard.Fruit;
import Gameboard.Game;
import Gameboard.Ghost;
import Gameboard.Me;
import Gameboard.Pacman;
import Geom.Point3D;
import Play.Robot2Element;
import Play.StringTranslate;
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
	private boolean stepByStepB,playB, openedGame, meB, mouse, autoB; // If is in animation progress avoid to do another commands
	private Map map; // Our image & converts
	private Play playS; 
	private double angle;
	private Animate animate;
	private MainAlgo algo;
	private Robot2Element cs;
	private StringTranslate trans;
	private BackGroundPanel panel;

	public static void main(String[] args) {
		new  MyFrame();
	}

	public MyFrame() {
		mouse = true;
		openedGame=playB=stepByStepB = false; // We start with no progress(game running animation)
		displayCoord = new JLabel();
		Font font = new Font("Tahoma", Font.PLAIN, 17);

		displayCoord.setFont(font);
		displayCoord.setForeground(Color.WHITE);
		add(displayCoord);

		score = new JLabel();
		score.setFont(font);
		score.setForeground(Color.white);
		score.setBounds(4, 10, 150, 20);

		timeLeft = new JLabel();
		timeLeft.setForeground(Color.white);
		timeLeft.setFont(font);
		timeLeft.setBounds(4, 30, 150, 20);

		totalTime = new JLabel();
		totalTime.setForeground(Color.white);
		totalTime.setFont(font);
		totalTime.setBounds(4, 50, 170, 20);

		ghostKill = new JLabel();
		ghostKill.setForeground(Color.white);
		ghostKill.setFont(font);
		ghostKill.setBounds(4, 70, 150, 20);

		outOfbox = new JLabel();
		outOfbox.setForeground(Color.white);
		outOfbox.setFont(font);
		outOfbox.setBounds(4, 90, 150, 20);
		add(score);
		add(timeLeft);
		add(totalTime);
		add(ghostKill);
		add(outOfbox);

		map = Map.map();
		panel = new BackGroundPanel(this);
		add(panel);

		game = null;
		trans = new StringTranslate();

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

		setJMenuBar(menuBar);

		ButtonsPanel FastOpen = new ButtonsPanel(this);
		menuBar.add(FastOpen);

		setTitle("Pacman Game by Dana & Or");
		setSize(1200, 800);
		setLocation(300, 50);
		setVisible(true);

		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if(o==Exit)
			System.exit(2);
		// reset the game for new one
		if(o==clear) {
			game = null;
			openedGame = stepByStepB = false;
			mouse = true;
			if(playB) {
				playB=animate.keepGoing = false;
			}

			if(autoB) {
				autoB=algo.keepGoing = false;
			}

			reUpdate();
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

		if(o==automatic && !autoB && game!=null) {
			algo = new MainAlgo(this, game,panel.getWidth(), panel.getHeight(),meB);
			if(!playS.isRuning() && meB)
				playS.start();

			algo.start();
			autoB = mouse = true;
			return;
		}

		if(game == null || !meB) {
			errorMessage();
			return;
		}

		if(o==stepByStep && !autoB) {
			mouse=stepByStepB = true;
			if(playB) 	playB = animate.keepGoing=false;		
			else playS.start();
			return;
		}

		if(o==play && !playB && !autoB) {
			setCursor(null);
			displayCoord.setVisible(true);
			stepByStepB = false;
			animate = new Animate(this);
			if(!playS.isRuning())
				playS.start();
			animate.start();
			mouse = playB = true;

			return;
		}
	}
	/**
	 * Open a new game from csv  file
	 * @param f - File to load
	 */
	public void openGameFile(File f) {
		if(playB) animate.keepGoing = false;
		if(autoB) algo.keepGoing = false;

		playS = new Play(f.getAbsolutePath());
		playS.setIDs(315392852, 311327076);
		openedGame=true;
		mouse=playB=stepByStepB=meB=autoB=false;
		map.setNewBounds(playS.getBoundingBox());
		game = new Game();
		angle=90;
		cs = new Robot2Element(game);
		cs.MakeElements(playS.getBoard()); // Translate a csv file into a new game
		reUpdate();
	}
	/**
	 * whenever a key is pressed the pacman rotates
	 * @param angle of the pacman in order to reach the fruit
	 */
	public void controlByKey(double angle) {
		this.angle = angle;
		if(stepByStepB || autoB) {
			rotate();
		}
	}
	/**
	 * rotates the pacman
	 */
	public void rotate() {
		if(!playS.isRuning()) {
			if(playB) animate.keepGoing = false;		
			if(autoB) algo.keepGoing = false;

			Result();
		}

		else {
			playS.rotate(angle);
			updater();
		}
	}
	/**
	 * remote start game(by pressing space)
	 */
	public void keyStart() {
		if(playB) stepByStep.doClick();
		else play.doClick();
	}
	/**
	 * Remote set Me player and start game if needed
	 * @param p - point to set
	 */
	public void setMe(Point3D p) {
		boolean check = playS.setInitLocation(p.x(), p.y());
		if(check) {
			meB = true;
			game.getMe().setPoint(p);
		}
		else errorMessage();

		if(autoB) playS.start();

		repaint();
	}
	/**
	 * Update the scores table each round
	 */
	private void updater() {
		cs.MakeElements(playS.getBoard());
		trans.setString(playS.getStatistics());
		score.setText(trans.getScore());		
		totalTime.setText("Total Time: "+trans.getTotalTime());
		timeLeft.setText("Time Left: "+trans.getTimeLeft());
		ghostKill.setText("Kill by ghost: "+trans.getGhostKill());
		outOfbox.setText("Out of box: "+trans.getOutOfBox());
		repaint();
	}
	/**
	 * If something goes wrong
	 */
	private void errorMessage() {
		JOptionPane.showMessageDialog(this,
				"Error while Playing \n*Load map \n*Place the pacman in a valid location ",
				"Error: Unable play the game",				
				JOptionPane.ERROR_MESSAGE);
	}

	/**
	 * Reset board
	 */
	private void reUpdate() {
		score.setText("Score: 0");		
		totalTime.setText("Total Time: 0");
		timeLeft.setText("Time Left: 0");
		ghostKill.setText("Kill by ghost: 0");
		outOfbox.setText("Out of box: 0");
		repaint();
	}

	/**
	 * Responsible to send a message, when the game finish
	 * @param TotalTime
	 * @param fruitWeight
	 * @param TotalResult
	 */
	private void Result() {
		openedGame=playB=stepByStepB=meB=autoB= false; // Change running to false that we finish right now the current game
		// content of the message
		JOptionPane.showMessageDialog(this,
				playS.getStatistics(),
				"Game Over!\n ", 
				JOptionPane.INFORMATION_MESSAGE); 
		game = null;
		reUpdate();
	}


	/**
	 * This class responsible to paint all our elements and listen to user 
	 * for each command such as Clicks, drag and etc
	 *
	 */
	private class BackGroundPanel extends JPanel implements MouseInputListener ,Serializable {
		private static final long serialVersionUID = -3626966327917598406L;

		private MyFrame f;
		private BufferedImage pacmanImg, fruitImg, ghostImg, meImg;
		private Cursor Me; // Change icon mouse accord selection
		private Orien rotate;

		public BackGroundPanel(MyFrame f) {
			addMouseListener(this);
			addMouseMotionListener(this);	
			KeyObserve keys = new KeyObserve(f);
			addKeyListener(keys);
			this.f=f;
			start();
		}

		private void start() {
			try {
				fruitImg = ImageIO.read(new File("Icon\\fruit.png"));
				pacmanImg = ImageIO.read(new File("Icon\\pacman.png"));
				ghostImg = ImageIO.read(new File("Icon\\ghost.png"));
				meImg = ImageIO.read(new File("Icon\\me.png"));
				f.setIconImage(meImg);
			} catch (IOException e) {
				e.printStackTrace();
			}

			rotate = new Orien(meImg);
			Me=Toolkit.getDefaultToolkit().createCustomCursor(meImg, new Point(0, 0), "Me");
		}


		protected void paintComponent(Graphics g) {
			super.paintComponent(g);

			Graphics2D g2d = (Graphics2D)g;

			// Draw our map as background
			g.drawImage(map.getMap(), 0, 0, getWidth(), getHeight(), this);

			if(game != null) {
				// Get the current game elements arrays
				Me me = game.getMe();

				Point3D p;
				// Ratio for scaling the Pacmans and fruit animation
				double ratioH = getHeight()/600.0;
				double ratioW = getWidth()/800.0;

				for(Fruit fruit : game.getFruits()) { // Move on fruit hash and draw them
					p = map.coord2pixel(fruit.getPoint(), getWidth(), getHeight());	// Fruit point saved geograpic coord, so convert it
					if(fruit.destroyed)
						g.drawImage(fruitImg, (int)p.x(), (int)p.y(), (int)(17*ratioW), (int)(17*ratioH), this);
				}

				for(Blocks b : game.getblocks()) { // Move on Blocks values and draw them
					g2d.setColor(Color.black);
					g2d.fill(b.getRect(getWidth(), getHeight()));
				}

				for(Pacman pacman : game.getPacmans()) { // Move on pacman values and draw them
					p = map.coord2pixel(pacman.getPoint(), getWidth(), getHeight()); // Convert to pixels coord

					if(pacman.destroyed)
						g.drawImage(pacmanImg, (int)p.x(), (int)p.y(),(int)(22*ratioW), (int)(22*ratioH), this);
				}

				for(Ghost ghost : game.getGhosts()) { // Move on ghost values and draw them
					p = map.coord2pixel(ghost.getPoint(), getWidth(), getHeight()); // Convert to pixels coord
					g.drawImage(ghostImg, (int)p.x(), (int)p.y(),(int)(22*ratioW), (int)(22*ratioH), this);
				}

				me.setOrien(angle-90);

				p = map.coord2pixel(me.getPoint(), getWidth(), getHeight()); // Convert to pixels coord
				AffineTransformOp op = rotate.getTransform(me.getOrien()); // Save a transform rotate
				g.drawImage(op.filter(meImg, null), (int)p.x(), (int)p.y(),(int)(22*ratioW), (int)(22*ratioH), this);

				if(playS.isRuning()) {
					g.setColor(new Color(1f,0f,.7f,.2f));
					g.fillRect(3, 3, 155, 110);
				}

			}
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			requestFocus();
			int x = e.getX();
			int y = e.getY();
			System.out.println("Clicks: ("+x+", "+y+')'); // Print coords for each user clicks
			Point3D p = new Point3D(x,y);
			p = map.pixel2coord(p, getWidth(), getHeight());
			System.out.println("Geograpich coords: ("+p+')'); // Print geo corrds as well

			if(openedGame && !playS.isRuning()) {
				setMe(p);
			}

			else if(meB) {
				controlByKey(map.anglePoints(game.getMe().getPoint(), p));
			}
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			if(mouse) { // If mouse selected & entert into frame turn on show coords near mouse
				displayCoord.setVisible(true);
			}
			else {
				displayCoord.setVisible(false);
				f.setCursor(Me);
			}
		}

		@Override
		public void mouseExited(MouseEvent e) { // if mouse exit frame hide it
			displayCoord.setVisible(false);
			f.setCursor(null);
		}


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
			if(mouse) { // If mouse moved print near mouse current coords
				displayCoord.setBounds(e.getX(), e.getY(), 80, 55);
				displayCoord.setText(e.getX()+", "+e.getY());
			}
		}
		
		@Override
		public void mousePressed(MouseEvent e) {}

		@Override
		public void mouseReleased(MouseEvent e) {}

	}

}
