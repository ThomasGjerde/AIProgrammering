package aiprog.gac;


import bsh.EvalError;
import aiprog.model.GACNode;
import aiprog.model.Node;
import aiprog.search.AStar;

public abstract class AStarCSPGAC extends AStar{
	
	public AStarCSPGAC(Node startNode) {
		super(startNode);
	}
	@Override
	protected void processCurrentNode() {
		GACNode newGacNode = (GACNode)currentNode;
		newGacNode.applyChanges();
		try {
			if(newGacNode.checkConsistency()){
				newGacNode.generateChildren();
			}
		} catch (EvalError e) {
			e.printStackTrace();
		}
		
	}

}
