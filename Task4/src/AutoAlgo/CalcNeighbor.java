package AutoAlgo;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import Gameboard.Blocks;
import Geom.Point3D;

/**
 * @author Dana Mor & Or Avital
 * This class calculates the neighbors of each vertex.
 */

public class CalcNeighbor {

	private Vertex source, target;
	private	Collection<Blocks> blockArr;
	private ArrayList<Vertex> points;
	private int id;
	private HashMap<String, Point3D> hash;
	private int height, width;
/**
 * 
 * @param blockArr block array 
 * @param source source vertex
 * @param target target vertex
 * @param width width of the frame
 * @param height height of the frame
 */
	public CalcNeighbor(Collection<Blocks> blockArr, Vertex source, Vertex target,int width, int height) {
		this.source = source;
		this.target = target;
		this.blockArr = blockArr;
		points = new ArrayList<Vertex>();
		hash = new HashMap<String, Point3D>();
		id = 1;
		this.height = height;
		this.width = width;
	}

	/**
	 * 
	 * @return 
	 */
	public ArrayList<Vertex> getSkeleton() {
		buildPointArray();
		addNeighbor();

		return points;
	}
	/**
	 * 
	 * @param key id of the vertex
	 * @return point of the vertex
	 */
	public Point3D getPoint(String key) {
		Point3D p = hash.get(key);
		return p;
	}

	/**
	 * creates the array of all the vertexes in the game
	 */
	private void buildPointArray() {
		points.add(source);
		hash.put(""+source.getName(), source.getPoint());
		for(Blocks b : blockArr) {
			Rectangle r = b.getRect(width,height);
			r.grow(2, 2);
			points.add(createVrtex(r.getMaxX(),r.getMaxY()));
			points.add(createVrtex(r.getMaxX(),r.getMinY()));
			points.add(createVrtex(r.getMinX(),r.getMinY()));
			points.add(createVrtex(r.getMinX(),r.getMaxY()));
		}
		target.setName(points.size());
		hash.put(""+points.size(), target.getPoint());
		points.add(target);
	}
	/**
	 * 
	 * @param x x coordinate
	 * @param y y coordinate
	 * @return vertex
	 */
	private Vertex createVrtex(double x,double y) {
		Point3D p = new Point3D(x,y);
		hash.put(""+id, p);
		return new Vertex(p,id++);
	}
	/**
	 * @param p1 point
	 * @param p2 point
	 * @return true if two vertexes are neighbors and false if they aren't
	 */
	private boolean isNeighbor(Point3D p1,Point3D p2) {
		boolean flag = true;
		for(Blocks b : blockArr) {
			Rectangle r = b.getRect(width,height);
			flag = r.intersectsLine(p1.x(), p1.y(), p2.x(),p2.y());
			if(flag) return false;
		}
		return true;
	}
	/**
	 * add neighbors for each vertex
	 */
	private void addNeighbor() {
		Queue<Vertex> queue = new LinkedList<Vertex>();
		boolean wasInQueue[] = new boolean[points.size()];

		queue.add(source);	
		wasInQueue[source.getName()] = true;

		while(!queue.isEmpty()) {
			Vertex current = queue.poll();

			if(!current.equals(target)) {
				for(Vertex v : points) {
					if(v.getName()!=0 && !current.equals(v) && isNeighbor(current.getPoint(), v.getPoint())) {
						current.addNeighbor(""+v.getName());
						if(!wasInQueue[v.getName()]) {
							wasInQueue[v.getName()] = true;
							queue.add(v);
						}
					}
				}
			}
		}
	}

}
