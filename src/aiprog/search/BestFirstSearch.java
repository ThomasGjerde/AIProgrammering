package aiprog.search;

import aiprog.model.NavNode;
import aiprog.model.Node;
import aiprog.model.Point;

public abstract class BestFirstSearch extends AStarWithEndPoint{

	public BestFirstSearch(Node startNode, Point endPoint) {
		super(startNode, endPoint);
	}
	protected void setHeuristic(Node node) {
		node.heuristic = this.endPoint.getManhattanDistance(((NavNode)node).pos) + node.getCostFromStart();
	}
}
