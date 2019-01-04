package AutoAlgo;


import Coords.Map;
import Gameboard.Game;
import Geom.Point3D;
import Gui.MyFrame;

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
		double angle;
		
		while(keepGoing) {
			GraphList create = new GraphList(game, width, height);
			Point3D path[] = create.getPath();
			int id = create.getTargetId();
			
			if(game.numOfFruits()==0) {
				f.rotate();
			}
			
			int i=0;
			while(game.isExist(id) && keepGoing) {
				while(game.isExist(id) &&  getDist(game.getMe().getPoint(),path[i]) > 1 && keepGoing)  {
					angle = getAngle(game.getMe().getPoint(),path[i]);
					f.controlByKey(angle);
					
					try {
						Thread.sleep(30);
					} catch (InterruptedException e) {
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