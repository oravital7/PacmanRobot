package Gameboard;

import java.util.Collection;
import java.util.HashMap;

import Geom.Point3D;
/**
 * This class represent a game - collections of Pacmans, Fruits, Blocks, Ghost
 * and one player - Me
 */
public class Game {
	private HashMap<Integer, Fruit > fruitHash;
	private HashMap<Integer, Pacman > pacmanHash;
	private HashMap<Integer, Blocks > blocksHash;
	private HashMap<Integer, Ghost > ghostHash;
	private int  numOfFruits;
	private Me me;

	public Game()
	{
		fruitHash = new HashMap<>();
		pacmanHash = new HashMap<>();
		blocksHash = new HashMap<>();
		ghostHash = new HashMap<>();
		numOfFruits = 0;
	}

	/**
	 * @param me set a new player
	 */
	public void setMe (Me me)
	{
		this.me=me;
	}
	/**
	 * 
	 * @return Me player
	 */
	public Me getMe() {
		return me;
	}
	/**
	 * @return Ghost Values
	 */
	public Collection<Ghost> getGhosts() {
		return ghostHash.values();
	}
	/**
	 * @return Fruit Values
	 */
	public Collection<Fruit> getFruits() {
		return fruitHash.values();
	}
	/**
	 * @return Pacman Values
	 */
	public Collection<Pacman> getPacmans() {
		return pacmanHash.values();
	}
	/**
	 * @return Blocks Values
	 */
	public Collection<Blocks> getblocks() {
		return blocksHash.values();
	}
	/**
	 * add New Fruit into the game
	 * @param f New Fruit
	 */
	public void addFruit(Fruit f)
	{
		numOfFruits++;
		fruitHash.put(f.getId(),f);
	}
	/**
	 * add New Blocks into the game
	 * @param b new Blocks
	 */
	public void addBlock(Blocks b)
	{
		blocksHash.put(b.getId(),b);
	}
	/**
	 * add New Pacman into the game
	 * @param p -New Pacman
	 */
	public void addPacman(Pacman p)
	{
		pacmanHash.put(p.getId(),p);
	}
	/**
	 * add New Pacman into the game
	 * @param g New Ghost
	 */
	public void addGhost(Ghost g)
	{
		ghostHash.put(g.getId(), g);
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
	 * check if fruit is still not eaten
	 * @param id of fruit
	 * @return state of fruit
	 */
	public boolean isExist(int  id) {
		Fruit f = fruitHash.get(id);
		if(f==null) return false;
		return f.destroyed;
	}
	/**
	 * Just update Ghost point
	 * @param id - id ghost in the hash
	 * @param point - point to update
	 */
	public void updateGhost(int id,Point3D point) {
		Ghost g = ghostHash.get(id);
		g.setPoint(point);
	}
	/**
	 * Just update Pacman point
	 * @param id - Pacman in the hash
	 * @param point
	 */
	public void updatePacman(int id,Point3D point) {
		Pacman p = pacmanHash.get(id);
		p.setPoint(point);
		p.destroyed = true;
	}
	/**
	 * Just update Fruit state
	 * if need to update change state to true(still in the game)
	 * @param id - id Fruit in the hash
	 */
	public void updateFruit(int id) {
		Fruit f = fruitHash.get(id);
		f.destroyed = true;
		numOfFruits++;
	}
	/**
	 * Generally check if the game init
	 * @return fruits state
	 */
	public boolean isEmpty() {
		return fruitHash.isEmpty();
	}
	/**
	 *	assumed that all Fruits and Pacmans were eaten each round
	 */
	public void clear()
	{
		for(Fruit f : fruitHash.values()) {
			f.destroyed=false;
		}
		for(Pacman p : pacmanHash.values()) {
			p.destroyed=false;
		}
		numOfFruits=0;
	}
}
