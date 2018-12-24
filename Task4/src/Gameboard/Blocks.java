package Gameboard;

import Geom.Point3D;

public class Blocks {

	private int ID;
	private Point3D p1, p2;
	private double attribute;

	public Blocks (int ID, Point3D p1, Point3D p2, double attribute)
	{
		this.ID=ID;
		this.p1=p1;
		this.p2=p2;
		this.attribute=attribute;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public Point3D getP1() {
		return p1;
	}

	public void setP1(Point3D p1) {
		this.p1 = p1;
	}

	public Point3D getP2() {
		return p2;
	}

	public void setP2(Point3D p2) {
		this.p2 = p2;
	}

	public double getAttribute() {
		return attribute;
	}

	public void setAttribute(double attribute) {
		this.attribute = attribute;
	}
	
	
}
