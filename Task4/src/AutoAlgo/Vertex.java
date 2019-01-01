package AutoAlgo;

import java.util.ArrayList;

import Geom.Point3D;

public class Vertex {
	
	private String name;
	private Point3D p;
	private ArrayList<String> neighbors; 
	
	public Vertex(Point3D p, String name) {
		this.p = p;
		this.name = name;
		neighbors =  new ArrayList<String>();
	}
	
	public void addNeighbor(String neighbor) {
		neighbors.add(neighbor);
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public Point3D getPoint() {
		return p;
	}
	public String toString() {
		return name;
	}
}
