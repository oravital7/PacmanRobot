package AutoAlgo;

import java.util.ArrayList;

import Gameboard.Blocks;
import graph.Graph;
import graph.Node;

public class GraphBuilder {
	private Vertex source, target;
	private	ArrayList<Blocks> blockArr;
	private	Graph G;
	public GraphBuilder(Vertex source, Vertex target, ArrayList<Blocks> blockArr) {
		G = new Graph();
		this.source = source;
		this.target = target;
		this.blockArr = blockArr;
		buildGraph();
	}

	private void buildGraph() {
		G.add(new Node(source.toString()));
		CalcNeighbor calc = new CalcNeighbor(blockArr, source, target);
		
	}
	
	
}
