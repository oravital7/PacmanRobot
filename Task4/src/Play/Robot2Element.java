package Play;

import java.util.ArrayList;

import Gameboard.Blocks;
import Gameboard.Fruit;
import Gameboard.Game;
import Gameboard.Ghost;
import Gameboard.Me;
import Gameboard.Pacman;
import Geom.Point3D;


/**
 * @author Dana Mor & Or Avital
 * This class creates the game
 *
 */
public class Robot2Element {
	private Game game;
	private boolean init;
	/**
	 * 
	 * @param game The game
	 */
	public Robot2Element(Game game) {
		this.game = game;
	}

	/**
	 * create element from every line of the string
	 */
	public void MakeElements(ArrayList<String> data) {
		game.clear();
		init = game.isEmpty();
		for(String s : data) {
			MakeElements(s.split(","));
		}
	}
	/**
	 * @param make different character - either ghost, block, pacman or me
	 */
	private void MakeElements(String make[]) {
		int id = convert2Int(make[1]);
		double x = convert2Double(make[2]);
		double y = convert2Double(make[3]);
		double z = convert2Double(make[4]);
		double attribute = convert2Double(make[5]);

		Point3D p = new Point3D(x,y,z);

		if(make[0].toUpperCase().contains("B")) {
			if(init) 
				addBlock(make, p, id);
		}

		else if(make[0].toUpperCase().contains("F")) {
			addFruit(p, id, attribute);
		}

		else if(make[0].toUpperCase().contains("M"))  {
			Me me = new Me(p,id,attribute);
			game.setMe(me);
		}

		else {
			double radius = convert2Double(make[6]);

			if(make[0].toUpperCase().contains("P")) {
				addPacman(p, id, attribute, radius);
			}

			else  {
				addGhosts(p, id, attribute, radius);
			}
		}
	}
	/**
	 * adding a block
	 * @param make different character - either ghost, block, pacman or me
	 * @param point point
	 * @param id id of character
	 */
	private void addBlock(String make[], Point3D point,int id) {
		double x2 = convert2Double(make[5]);
		double y2 = convert2Double(make[6]);
		double z2 = convert2Double(make[7]);
		Point3D point2 = new Point3D(x2,y2,z2);
		double attribute = convert2Double(make[8]);
		Blocks b = new Blocks(id,point,point2,attribute);
		game.addBlock(b);
	}
	/**
	 * adding a fruit
	 * @param p point 
	 * @param id id of fruit
	 * @param attribute attribute of character
	 */
	private void addFruit(Point3D p,int id, double attribute) {
		if(!init) 
			game.updateFruit(id);

		else {
			Fruit f = new Fruit(p, id,attribute);
			game.addFruit(f);
		}
	}
	/**
	 * adding a pacman
	 * @param p point 
	 * @param id id of fruit
	 * @param attribute attribute of character
	 * @param radius radius of eating
	 */
	private void addPacman(Point3D p,int id, double attribute,double radius) {
		if(!init)
			game.updatePacman(id, p);

		else {
			Pacman pc = new Pacman(p, id, attribute, radius);
			game.addPacman(pc);
		}		
	}
	/**
	 * adding a ghost
	 * @param p point 
	 * @param id id of fruit
	 * @param attribute attribute of character
	 * @param radius radius of eating
	 */
	private void addGhosts(Point3D p,int id, double attribute,double radius) {
		if(!init) 
			game.updateGhost(id, p);		

		else {
			Ghost g = new Ghost(p, id, attribute, radius);
			game.addGhost(g);
		}
	}

	/**
	 * Parse to int
	 * @param s
	 * @return conversion of the string to integer
	 */
	private int convert2Int(String s) {
		int x = Integer.parseInt(s);
		return x;
	}

	/**
	 * Parse to double
	 * @param s
	 * @return conversion of the string to double
	 */
	private double convert2Double(String s) {
		double x = Double.parseDouble(s);
		return x;
	}

}
