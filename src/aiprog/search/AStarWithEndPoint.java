package aiprog.search;

import aiprog.model.Node;
import aiprog.model.Point;

public abstract class AStarWithEndPoint extends AStar{

	Point endPoint = new Point();
	public AStarWithEndPoint(Node startNode, Point endPoint) {
		super(startNode);
		this.endPoint = endPoint;
	}

	@Override
	protected boolean checkVictory() {
		if(currentNode.pos.x == endPoint.x && currentNode.pos.y == endPoint.y){
			return true;
		}else{
			return false;
		}
	}
}
