package aiprog.search;

import aiprog.model.Node;
import aiprog.model.Point;

public abstract class BreadthFirstSearch extends AStarWithEndPoint{

	public BreadthFirstSearch(Node startNode, Point endPoint) {
		super(startNode, endPoint);
	}
	protected void setHeuristic(Node node) {
		node.heuristic = 0;
	}
}
