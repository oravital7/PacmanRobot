package AutoAlgo;

import java.util.ArrayList;
import java.util.Iterator;

import Geom.Point3D;
/**
 * 
 * @author Dana Mor & Or Avital
 * This class represent a vertex.
 */
public class Vertex {

	private int name;
	private Point3D p;
	private ArrayList<String> neighbors;
	/**
	 * 	
	 * @param p point
	 * @param name name of vertex
	 */
	public Vertex(Point3D p, int name) {
		this.p = p;
		this.name = name;
		neighbors =  new ArrayList<String>();
	}
	/**
	 * 	adding neighbor to vertex
	 * @param neighbor name of vertex
	 */
	public void addNeighbor(String neighbor) {
		neighbors.add(neighbor);
	}
	/**
	 * setting the name of the vertex
	 * @param name name of vertex
	 */
	public void setName(int name) {
		this.name = name;
	}
	/**
	 * 	
	 * @return point
	 */
	public Point3D getPoint() {
		return p;
	}
	/**
	 * 
	 * @return name of vertex
	 */
	public int getName() {
		return name;
	}
	/**
	 * 	
	 * @return true if a vertex has no neighbors and false if otherwise
	 */
	public boolean isLonely() {
		return neighbors.isEmpty();
	}
	/**
	 * 
	 * @return iterator of neighbors
	 */
	public Iterator<String> getIterator() {
		return neighbors.iterator();
	}
}
