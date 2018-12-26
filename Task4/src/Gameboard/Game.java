package Gameboard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import Geom.Point3D;
/**
 * 
 * @author Dana Mor and Or Avital
 * this class represent a game - a collections of Pacmans and fruits.
 */
public class Game {
//	private ArrayList<Fruit> fruitArray;
//	private ArrayList<Pacman> pacmanArray;
//	private ArrayList<Blocks> blocksArray;
//	private ArrayList<Ghost> ghostArray;
	
	HashMap<Integer, Fruit > fruitArray;
	HashMap<Integer, Pacman > pacmanArray;
	HashMap<Integer, Blocks > blocksArray;
	HashMap<Integer, Ghost > ghostArray;

	


	
	private int maxIdPacman, maxIdFruit, maxIdBlocks, maxIdGhost;
	private Me me;

	public Game()
	{
		fruitArray = new HashMap<>();
		pacmanArray = new HashMap<>();
		blocksArray = new HashMap<>();
		ghostArray = new HashMap<>();
		maxIdGhost=maxIdBlocks=maxIdPacman=maxIdFruit=0;
	}
	
	public void setMe (Me me)
	{
		this.me=me;
	}
	
	
	public Me getMe() {
		return me;
	}

	public HashMap<Integer, Ghost > getGhostArray() {
		return ghostArray;
	}

	public void setMaxIdFruit(int maxIdFruit) {
		this.maxIdFruit = maxIdFruit;
	}
	
	public HashMap<Integer, Blocks > getBlocksArray() {
		return blocksArray;
	}

	/**
	 * 
	 * @param f  -fruit
	 */
	public void addFruit(Fruit f)
	{
		if(f.getId()>maxIdFruit) maxIdFruit = f.getId();
		fruitArray.put(f.getId(),f);
	}
	
	public void addBlock(Blocks b)
	{
		if(b.getID() > maxIdBlocks) maxIdBlocks = b.getID();
		blocksArray.put(b.getID(),b);
	}
/**
 * 
 * @param p - pacman
 */
	public void addPacman(Pacman p)
	{
		if(p.getId()>maxIdPacman) maxIdPacman = p.getId();
		pacmanArray.put(p.getId(),p);
	}
	
	public void addGhost(Ghost g)
	{
		if(g.getId() > maxIdGhost) maxIdGhost = g.getId();
		ghostArray.put(g.getId(), g);
	}
/**
 * 
 * @return  number Of Fruits
 */
	public int numOfFruits()
	{
		return fruitArray.size();
	}
/**
 * 
 * @return number Of Pacman
 */
	public int numOfpacmans()
	{
		return pacmanArray.size();
	}

/**
 * 
 * @return the array of the fruits
 */
	public HashMap<Integer, Fruit >  getFruitArray() {
		return fruitArray;
	}
/**
 * 
 * @return the array of the pacmans
 */
	public HashMap<Integer, Pacman >  getPacmanArray() {
		return pacmanArray;
	}
/**
 * 
 * @return the id of the pacman
 */
	public int getMaxIdPacman() {
		return maxIdPacman;
	}
	/**
	 * 
	 * @return the id of the fruit
	 */	
	public int getMaxIdFruit() {
		return maxIdFruit;
	}
	
	public void clear()
	{
		for(Fruit f : fruitArray.values()) {
			f.destroyed=false;
		}
		for(Pacman p : pacmanArray.values()) {
			p.destroyed=false;
		}
		maxIdGhost=maxIdBlocks=maxIdPacman=maxIdFruit=0;
	}
}
