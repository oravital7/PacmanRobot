package AutoAlgo;

import java.util.ArrayList;
import java.util.Iterator;

import Geom.Point3D;

public class Vertex {
	
	private int name;
	private Point3D p;
	private ArrayList<String> neighbors;
	
	public Vertex(Point3D p, int name) {
		this.p = p;
		this.name = name;
		neighbors =  new ArrayList<String>();
	}
	
	public void addNeighbor(String neighbor) {
		neighbors.add(neighbor);
	}
	public void setName(int name) {
		this.name = name;
	}
	
	public Point3D getPoint() {
		return p;
	}

	public int getName() {
		return name;
	}
	
	public boolean isLonely() {
		return neighbors.isEmpty();
	}
	
	public Iterator<String> getIterator() {
		return neighbors.iterator();
	}
}
