package Gameboard;

import Geom.Point3D;

public class Blocks  extends Character {

	private Point3D point2;
	
	public Blocks (int id, Point3D p1, Point3D p2, double attribute)
	{
		super(p1,id,attribute);
		this.point2=p2;
	}

	public Point3D getPoint2() {
		return point2;
	}

	public void setPoint2(Point3D p2) {
		this.point2 = p2;
	}
	
}
