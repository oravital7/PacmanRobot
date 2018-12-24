package Gameboard;

import java.util.ArrayList;
import java.util.Iterator;

import Geom.Point3D;
/**
 * 
 * @author Dana Mor and Or Avital
 * this class represent a game - a collections of Pacmans and fruits.
 */
public class Game {
	private ArrayList<Fruit> fruitArray;
	private ArrayList<Pacman> pacmanArray;
	private ArrayList<Blocks> blocksArray;
	private ArrayList<Ghost> ghostArray;
	private int maxIdPacman, maxIdFruit, maxIdBlocks, maxIdGhost;
	private Me me;
	private PacmanComperator compare = new PacmanComperator();

	public Game()
	{
		fruitArray = new ArrayList<Fruit>();
		pacmanArray = new ArrayList<Pacman>();
		blocksArray = new ArrayList<Blocks>();
		ghostArray = new ArrayList<Ghost>();
		maxIdGhost=maxIdBlocks=maxIdPacman=maxIdFruit=0;
	}
	
	public void setMe (Me me)
	{
		this.me=me;
	}
	
	
	public Me getMe() {
		return me;
	}

	public ArrayList<Blocks> getBlocksArray() {
		return blocksArray;
	}

	public ArrayList<Ghost> getGhostArray() {
		return ghostArray;
	}

	public void setMaxIdFruit(int maxIdFruit) {
		this.maxIdFruit = maxIdFruit;
	}

	/**
	 * 
	 * @param f  -fruit
	 */
	public void addFruit(Fruit f)
	{
		if(f.getId()>maxIdFruit) maxIdFruit = f.getId();
		fruitArray.add(f);
	}
	
	public void addBlock(Blocks b)
	{
		if(b.getID() > maxIdBlocks) maxIdBlocks = b.getID();
		blocksArray.add(b);
	}
/**
 * 
 * @param p - pacman
 */
	public void addPacman(Pacman p)
	{
		if(p.getId()>maxIdPacman) maxIdPacman = p.getId();
		pacmanArray.add(p);
		pacmanArray.sort(compare);
	}
	
	public void addGhost(Ghost g)
	{
		if(g.getId() > maxIdGhost) maxIdGhost = g.getId();
		ghostArray.add(g);
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
 * @return pacmanArray iterator
 */
	public Iterator<Pacman> getPacmanIterator()
	{
		return pacmanArray.iterator();
	}
/**
 * 
 * @return fruitArray iterator
 */
	public Iterator<Fruit> getFruitIterator()
	{
		return fruitArray.iterator();
	}
/**
 * 
 * @return the array of the fruits
 */
	public ArrayList<Fruit> getFruitArray() {
		return fruitArray;
	}
/**
 * 
 * @return the array of the pacmans
 */
	public ArrayList<Pacman> getPacmanArray() {
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
	
}
