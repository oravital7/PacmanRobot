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

public class GraphBuilder {
	private Vertex source, target;
	private	Collection<Blocks> blockArr;
	private	Graph G;
	private ArrayList<Vertex> VetrexList;
	private CalcNeighbor calc;
	private Node node;
	private int height, width;

	public GraphBuilder(Vertex source, Vertex target, Collection<Blocks> blockArr,int width, int height) {
		G = new Graph();
		this.source = source;
		this.target = target;
		this.blockArr = blockArr;
		this.height = height;
		this.width = width;
	}

	public double getDistancePath() {
		buildGraph();
		Graph_Algo.dijkstra(G, ""+source.getName());

		node = G.getNodeByName(""+target);
		return node.getDist();
	}
	
	public Point3D[] getPath() {
		ArrayList<String> path = node.getPath();
		Point3D[] resultPath = new Point3D[path.size()+1];
		int i=0;
		for(String s : path) {
			resultPath[i++] = calc.getPoint(s);
		}
		return resultPath;
	}

	private void buildGraph() {
		G.add(new Node(""+source.getName()));
		calc = new CalcNeighbor(blockArr, source, target,width,height);
		VetrexList = calc.getSkeleton();
		for(Vertex v : VetrexList) {
			if(!v.isLonely()) {
				Node node = new Node(""+v.getName());
				G.add(node);
				
			}
		}
		System.out.println(G.size());
		G.add(new Node(""+target.getName()));
		addEdge();
	}

	private void addEdge() {
		for(Vertex v : VetrexList) {
			if(!v.isLonely()) {
				Iterator<String> it = v.getIterator();	
				String s;
				while(it.hasNext()) {
					s = it.next();
					G.addEdge(""+v.getName(),s ,getDistance(s,v.getPoint()));
					System.out.println("Edge: "+v.getName()+","+s+", "+v.getPoint());
				}
			}
		}

	}

	private double getDistance(String s, Point3D p) {
		Point3D p2 = calc.getPoint(s);
		Map map = Map.map();
		System.out.println("p1: "+p+"p2: "+p2);

		double dest = map.distanceGpsPixles(p, p2, width, height);
		return dest;
	}

}
