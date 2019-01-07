package AutoAlgo;

import Coords.Map;
import Gameboard.Fruit;
import Gameboard.Game;
import Geom.Point3D;

/**
 * 
 * @author Dana Mor & Or Avital
 * This class calculates the shortest path from given graphs
 */
public class GraphList {
	private Game game;
	private Point3D[] path;
	private int width, height, FruitId;
/**
 * @param game the game
 * @param width width of the frame
 * @param height height of the frame
 */
	public GraphList(Game game,int width, int height) {
		this.game = game;
		this.width = width;
		this.height = height;
		FruitId=0;
	}
/**
 * 
 * @return path to the closest fruit
 */
	public Point3D[] getPath() {
		GraphListBuilder();
		return path;
	}
/**
 * 
 * @return Fruit Id
 */
	public int getTargetId() {
		return FruitId;
	}
/**
 * calculates the path for each fruit and create a path to the closest fruit
 */
	private void GraphListBuilder() {
		Map map = Map.map();
		Point3D me = map.coord2pixel(game.getMe().getPoint(), width, height);
		Vertex source,target;

		double min = Integer.MAX_VALUE;
		Point3D p;
		for(Fruit f : game.getFruits()) {
			if(f.destroyed) {
				p = map.coord2pixel(f.getPoint(), width, height);
				target = new Vertex(p,-1);
				source = new Vertex(me, 0);
				GraphBuilder build = new GraphBuilder(source, target, game.getblocks(), width, height);
				double dist = build.getDistancePath();
				if(dist < min) {
					FruitId = f.getId();
					path = build.getPath();
					min = dist;
				}
			}
		}
	}

}
