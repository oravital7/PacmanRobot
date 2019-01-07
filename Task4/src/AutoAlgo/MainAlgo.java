package AutoAlgo;


import java.util.Random;

import Coords.Map;
import Gameboard.Fruit;
import Gameboard.Game;
import Geom.Point3D;
import Gui.MyFrame;

public class MainAlgo extends Thread {
	private MyFrame f;
	private int width, height;
	private Game game;
	public boolean keepGoing,me;

	public MainAlgo(MyFrame f, Game game,int width, int height, boolean me) {
		this.f = f;
		this.width = width;
		this.height = height;
		this.game = game;
		keepGoing = true;
		this.me = me;
	}

	@Override
	public void run() {
		double angle;

		if(!me) {
			Random generator = new Random();
			Object[] values = game.getFruits().toArray();
			Fruit randomValue = (Fruit) values[generator.nextInt(values.length)];
			f.setMe(randomValue.getPoint());
		}

		while(keepGoing) {
			GraphList create = new GraphList(game, width, height);
			Point3D path[] = create.getPath();
			int id = create.getTargetId();

			if(game.numOfFruits()==0) {
				f.rotate();
			}		

			int i=0;
			while(game.isExist(id) && keepGoing) {
				double dist = getDist(game.getMe().getPoint(),path[i]);
				while(game.isExist(id) && keepGoing  && (dist > 1 || dist==0 ))  {
					angle = getAngle(game.getMe().getPoint(),path[i]);

					f.controlByKey(angle);
					try {
						Thread.sleep(30);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					dist = getDist(game.getMe().getPoint(),path[i]);
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