package Gameboard;

import java.awt.Rectangle;

import Coords.Map;
import Geom.Point3D;

public class Blocks  extends Character {
	Map map = Map.map();
	
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
	
	public Rectangle getRect() {
			Point3D pUp = map.coord2pixel(point, 1200, 800);
			Point3D pDown = map.coord2pixel(point2, 1200, 800);

			Rectangle r = new Rectangle(); 
			r.x = (int) pUp.x();
			r.y = (int) pUp.y();
			r.add(pDown.x(),pDown.y());
			
			return r;
	}
	
}
