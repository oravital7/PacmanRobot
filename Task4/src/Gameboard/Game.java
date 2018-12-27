package Gameboard;

import java.util.HashMap;

import Geom.Point3D;
/**
 * 
 * @author Dana Mor and Or Avital
 * this class represent a game - a collections of Pacmans and fruits.
 */
public class Game {
	public boolean init;
	HashMap<Integer, Fruit > fruitArray;
	HashMap<Integer, Pacman > pacmanArray;
	HashMap<Integer, Blocks > blocksArray;
	HashMap<Integer, Ghost > ghostArray;

	private int  numOfFruits;
	private Me me;

	public Game()
	{
		fruitArray = new HashMap<>();
		pacmanArray = new HashMap<>();
		blocksArray = new HashMap<>();
		ghostArray = new HashMap<>();
		numOfFruits=0;
		init=false;
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


	public HashMap<Integer, Blocks > getBlocksArray() {
		return blocksArray;
	}

	/**
	 * 
	 * @param f  -fruit
	 */
	public void addFruit(Fruit f)
	{
		numOfFruits++;
		fruitArray.put(f.getId(),f);
	}

	public void addBlock(Blocks b)
	{
		blocksArray.put(b.getID(),b);
	}
	/**
	 * 
	 * @param p - pacman
	 */
	public void addPacman(Pacman p)
	{
		pacmanArray.put(p.getId(),p);
	}

	public void addGhost(Ghost g)
	{
		ghostArray.put(g.getId(), g);
	}
	/**
	 * 
	 * @return  number Of Fruits
	 */
	public int numOfFruits()
	{
		return numOfFruits;
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

	public void updateGhost(int id,Point3D point) {
		Ghost g = ghostArray.get(id);
		g.setPoint(point);
	}
	public void updatePacman(int id,Point3D point) {
		Pacman p = pacmanArray.get(id);
		p.setPoint(point);
		p.destroyed = true;
	}
	public void updateFruit(int id) {
		Fruit f = fruitArray.get(id);
		f.destroyed = true;
		numOfFruits++;
	}

	public void clear()
	{
		for(Fruit f : fruitArray.values()) {
			f.destroyed=false;
		}
		for(Pacman p : pacmanArray.values()) {
			p.destroyed=false;
		}
		numOfFruits=0;
	}
}
