package AutoAlgo;



import Coords.Map;
import Gameboard.Fruit;
import Gameboard.Game;
import Geom.Point3D;


public class GraphList {
	private Game game;
	private GraphBuilder shortGraph;
	private int width, height, FruitId;
	
	public GraphList(Game game,int width, int height) {
		this.game = game;
		this.width = width;
		this.height = height;
	}
	
	public Point3D[] getPath() {
		GraphListBuilder();
		Point3D[] path = shortGraph.getPath();	
		return path;
	}
	
	public int getTargetId() {
		return FruitId;
	}
	
	private void GraphListBuilder() {
		Map map = Map.map();
		Point3D me = map.coord2pixel(game.getMe().getPoint(), width, height);
		Vertex source = new Vertex(me, 0);
		Vertex target;
		double min = Integer.MAX_VALUE;
		shortGraph = null;
		
		for(Fruit f : game.getFruits()) {
			target = new Vertex(f.getPoint(),-1);
			GraphBuilder build = new GraphBuilder(source, target, game.getblocks(), width, height);
			double dist = build.getDistancePath();
			if(dist < min) {
				FruitId = f.getId();
				min = dist;
				shortGraph = build;
			}		
		}
	}
	
}
