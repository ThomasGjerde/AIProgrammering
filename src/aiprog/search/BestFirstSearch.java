package aiprog.search;

import aiprog.model.Node;
import aiprog.model.Point;

public abstract class BestFirstSearch extends AStar{

	public BestFirstSearch(Node startNode, Point endPoint) {
		super(startNode, endPoint);
	}
	protected void setHeuristic(Node node) {
		node.heuristic = this.endPoint.getManhattanDistance(node.pos) + node.getCostFromStart();
	}
}
