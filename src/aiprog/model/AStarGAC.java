package aiprog.model;

import java.util.ArrayList;

import aiprog.search.AStar;

public abstract class AStarGAC extends AStar{
	
	public AStarGAC(Node startNode) {
		super(startNode);
		
		// TODO Auto-generated constructor stub
	}
	@Override
	protected void processCurrentNode() {
		// TODO Auto-generated method stub
		GACNode newGacNode = (GACNode)currentNode;
		newGacNode.applyChanges();
		newGacNode.generateChildren();
	}

}
