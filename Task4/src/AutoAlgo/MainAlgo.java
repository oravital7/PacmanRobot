package AutoAlgo;


<<<<<<< HEAD
import java.util.Iterator;
=======
import java.util.Random;
>>>>>>> 7df3e8d5fa10a4d24a603bed94341a9adc77ee94

import Coords.Map;
import Gameboard.Fruit;
import Gameboard.Game;
import Gameboard.Me;
import Geom.Point3D;
import Gui.MyFrame;

public class MainAlgo extends Thread {
	private MyFrame f;
	private int width, height;
	private Game game;
<<<<<<< HEAD
	public boolean keepGoing, me;

	public MainAlgo(MyFrame f, Game game,int width, int height,boolean me) {
=======
	public boolean keepGoing,me;

	public MainAlgo(MyFrame f, Game game,int width, int height, boolean me) {
>>>>>>> 7df3e8d5fa10a4d24a603bed94341a9adc77ee94
		this.f = f;
		this.width = width;
		this.height = height;
		this.game = game;
		keepGoing = true;
<<<<<<< HEAD
		this.me= me;
=======
		this.me = me;
>>>>>>> 7df3e8d5fa10a4d24a603bed94341a9adc77ee94
	}

	@Override
	public void run() {
		double angle;
<<<<<<< HEAD
				
=======

		if(!me) {
			Random generator = new Random();
			Object[] values = game.getFruits().toArray();
			Fruit randomValue = (Fruit) values[generator.nextInt(values.length)];
			f.setMe(randomValue.getPoint());
		}

>>>>>>> 7df3e8d5fa10a4d24a603bed94341a9adc77ee94
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
<<<<<<< HEAD

=======
>>>>>>> 7df3e8d5fa10a4d24a603bed94341a9adc77ee94
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
<<<<<<< HEAD
		System.out.println("dana אין akum'm'sdmmsjkmc'ajakeirgi!!!!");
=======
>>>>>>> 7df3e8d5fa10a4d24a603bed94341a9adc77ee94

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