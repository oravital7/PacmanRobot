package Gameboard;

import java.util.Collection;
import java.util.HashMap;

import Geom.Point3D;
/**
 * 
 * @author Dana Mor and Or Avital
 * this class represent a game - a collections of Pacmans and fruits.
 */
public class Game {
	private HashMap<Integer, Fruit > fruitArray;
	private HashMap<Integer, Pacman > pacmanArray;
	private HashMap<Integer, Blocks > blocksArray;
	private HashMap<Integer, Ghost > ghostArray;
	private int  numOfFruits;
	private Me me;

	public Game()
	{
		fruitArray = new HashMap<>();
		pacmanArray = new HashMap<>();
		blocksArray = new HashMap<>();
		ghostArray = new HashMap<>();
		numOfFruits=0;
	}

	public void setMe (Me me)
	{
		this.me=me;
	}

	public Me getMe() {
		return me;
	}
	public Collection<Ghost> getGhosts() {
		return ghostArray.values();
	}
	
	public Collection<Fruit> getFruits() {
		return fruitArray.values();
	}
	
	public Collection<Pacman> getPacmans() {
		return pacmanArray.values();
	}
	
	public Collection<Blocks> getblocks() {
		return blocksArray.values();
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
		blocksArray.put(b.getId(),b);
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
	
	public boolean isExist(int  id) {
		Fruit f = fruitArray.get(id);
		return f.destroyed;
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
	
	public boolean isEmpty() {
		return fruitArray.isEmpty();
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
