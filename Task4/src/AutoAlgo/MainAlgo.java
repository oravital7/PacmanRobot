package AutoAlgo;

import Coords.Map;
import Gameboard.Game;
import Gameboard.Me;
import Geom.Point3D;
import Gui.MyFrame;
import jdk.javadoc.internal.doclets.toolkit.BaseConfiguration.Hidden;

public class MainAlgo extends Thread {
	private MyFrame f;
	private int width, height;
	private Game game;
	public boolean keepGoing;

	public MainAlgo(MyFrame f, Game game,int width, int height) {
		this.f = f;
		this.width = width;
		this.height = height;
		this.game = game;
		keepGoing = true;
	}

	@Override
	public void run() {
		Map map = Map.map();
		while(keepGoing) {
			GraphList create = new GraphList(game, width, height);
			Point3D path[] = create.getPath();

			int id = create.getTargetId();
			int i=0;

			while(game.isExist(id) && getDist(game.getMe().getPoint(),path[path.length-1]) > 0.5) {
				
				while(game.isExist(id) &&  getDist(game.getMe().getPoint(),path[i]) > 0.5)  {
					double angle = getAngle(game.getMe().getPoint(),path[i]);
					f.controlByKey(angle);
					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				i++;
			}
		}
	}

	private double getDist(Point3D geo, Point3D pixel) {
		Map map = Map.map();
		geo = map.coord2pixel(geo, width, height);
		double dist = map.distanceGpsPixles(geo, pixel, width, height);
		return dist;
	}
	
	private double getAngle(Point3D geo, Point3D pixel) {
		Map map = Map.map();
		pixel = map.pixel2coord(pixel, width, height);
		
		double angle = map.anglePoints(geo, pixel);
		return angle;

	}
}