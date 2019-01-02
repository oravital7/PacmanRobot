package AutoAlgo;

import java.util.ArrayList;
import java.util.Iterator;

import Gameboard.Blocks;
import Geom.Point3D;
import graph.Graph;
import graph.Node;

public class GraphBuilder {
	private Vertex source, target;
	private	ArrayList<Blocks> blockArr;
	private	Graph G;
	private ArrayList<Vertex> VetrexList;
	private CalcNeighbor calc;
	public GraphBuilder(Vertex source, Vertex target, ArrayList<Blocks> blockArr) {
		G = new Graph();
		this.source = source;
		this.target = target;
		this.blockArr = blockArr;
	}

	public Node getGraph() {
		buildGraph();
		return G.getNodeByName(""+target);

	}

	private void buildGraph() {
		G.add(new Node(""+source.getName()));
		calc = new CalcNeighbor(blockArr, source, target);
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

	private double getDistance(String s, Point3D p) {
		Point3D p2 = calc.getPoint(s);
		return p.distance2D(p2);
	}



}
