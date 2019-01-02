package AutoAlgo;

import java.awt.Rectangle;
import java.awt.geom.Line2D;
import java.util.ArrayList;

import Gameboard.Blocks;
import Geom.Point3D;

public class CalcNeighbor {
	
	private Vertex source, target;
	private	ArrayList<Blocks> blockArr;
	private ArrayList<Point3D> points;
	
	public CalcNeighbor(ArrayList<Blocks> blockArr, Vertex source, Vertex target) {
		this.source = source;
		this.target = target;
		this.blockArr = blockArr;
		points = new ArrayList<Point3D>();
	}
	
	private void buildPointArray() {
		points.add(source.getPoint());
		for(Blocks b : blockArr) {
			Rectangle r = b.getRect();
			points.add(new Point3D(r.getMaxX(),r.getMaxY()));
			points.add(new Point3D(r.getMaxX(),r.getMinY()));
			points.add(new Point3D(r.getMinX(),r.getMinY()));
			points.add(new Point3D(r.getMinX(),r.getMaxY()));
		}
		points.add(target.getPoint());
	}
	
	private boolean intersect(Point3D p,Point3D p2) {
		boolean flag=true;
		for(Blocks b : blockArr) {
			Rectangle r = b.getRect();
			flag = r.intersectsLine(p.x()-1, p.y()-1, p2.x()-1, p2.y()-1);
			if(!flag) return false;
		}
		return true;

	}

}
