package AutoAlgo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import Coords.Map;
import Gameboard.Blocks;
import Geom.Point3D;
import graph.Graph;
import graph.Graph_Algo;
import graph.Node;

/**
 * 
 * @author Dana Mor & Or Avital
 * This class builds a graph for a given source and destination
 *
 */
public class GraphBuilder {
	private Vertex source, target;
	private	Collection<Blocks> blockArr;
	private	Graph G;
	private ArrayList<Vertex> VetrexList;
	private CalcNeighbor calc;
	private Node node;
	private int height, width;
	/** 
	 * @param blockArr block array 
	 * @param source source vertex
	 * @param target target vertex
	 * @param width width of the frame
	 * @param height height of the frame
	 */
	public GraphBuilder(Vertex source, Vertex target, Collection<Blocks> blockArr,int width, int height) {
		G = new Graph();
		this.source = source;
		this.target = target;
		this.blockArr = blockArr;
		this.height = height;
		this.width = width;
	}
	/**
	 * Builds the graph, and runs dijkstra's algoritjm to find the distance of the path
	 * @return the shortest path to the fruit
	 */
	public double getDistancePath() {
		buildGraph();
		Graph_Algo.dijkstra(G, ""+source.getName());
		node = G.getNodeByName(""+target.getName());
		double dist = node.getDist();
		return dist;
	}
	/**
	 * @return path of points  
	 */
	public Point3D[] getPath() {
		ArrayList<String> path = node.getPath();
		Point3D[] resultPath = new Point3D[path.size()];
		int i=0;
		for(String s : path) {
			if(!s.equals("0"))
				resultPath[i++] = calc.getPoint(s);
		}
		resultPath[path.size()-1] = calc.getPoint(""+target.getName());
		return resultPath;
	}
	/**
	 * build the graph - adding vertexes if they neighbors
	 */
	private void buildGraph() {
		calc = new CalcNeighbor(blockArr, source, target,width,height);
		VetrexList = calc.getSkeleton();
		for(Vertex v : VetrexList) {
			if(!v.isLonely()) {
				Node node = new Node(""+v.getName());
				G.add(node);

			}
		}
		G.add(new Node(""+target.getName()));
		addEdge();
	}
/**
 * adding edges to the graph
 */
	private void addEdge() {
		for(Vertex v : VetrexList) {
			if(!v.isLonely()) {
				Iterator<String> it = v.getIterator();	
				String s;
				while(it.hasNext()) {
					s = it.next();
					G.addEdge(""+v.getName(),s ,getDistance(s,v.getPoint()));
				}
			}
		}
	}
/**
 * 
 * @param s name of the vertex
 * @param p neighbor
 * @return distance between them
 */
	private double getDistance(String s, Point3D p) {
		Point3D p2 = calc.getPoint(s);
		Map map = Map.map();

		double dest = map.distanceGpsPixles(p, p2, width, height);
		return dest;
	}

}
