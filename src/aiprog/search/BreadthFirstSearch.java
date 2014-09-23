package aiprog.search;

import aiprog.model.Node;
import aiprog.model.Point;

public abstract class BreadthFirstSearch extends AStar{

	public BreadthFirstSearch(Node startNode, Point endPoint) {
		super(startNode, endPoint);
		// TODO Auto-generated constructor stub
	}
	protected void setHeuristic(Node node) {
		node.heuristic = 0;
	}
}
