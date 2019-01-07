package Gameboard;

import java.awt.Rectangle;

import Coords.Map;
import Geom.Point3D;

/**
 * This class represents Blocks
 * Which has 4 points and is able to return representation of a rectangle
 */
public class Blocks extends Character {
	private Map map = Map.map();
	
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
	
	/**
	 * Convert blocks points geo to rectangle object java
	 * @param width - of the frame
	 * @param height
	 * @return this rectangle represented by a pixels
	 */
	public Rectangle getRect(int width, int height) {
			Point3D pUp = map.coord2pixel(point, width, height);
			Point3D pDown = map.coord2pixel(point2, width, height);

			Rectangle r = new Rectangle(); 
			r.x = (int) pUp.x();
			r.y = (int) pUp.y();
			r.add(pDown.x(),pDown.y());

			return r;
	}	
}
