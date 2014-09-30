package aiprog.gac;

import java.util.ArrayList;

import bsh.EvalError;

import aiprog.model.GACCSPNode;
import aiprog.model.GACNode;
import aiprog.model.Node;
import aiprog.search.AStar;

public abstract class AStarCSPGAC extends AStar{
	
	public AStarCSPGAC(Node startNode) {
		super(startNode);
		
		// TODO Auto-generated constructor stub
	}
	@Override
	protected void processCurrentNode() {
		// TODO Auto-generated method stub
		GACNode newGacNode = (GACNode)currentNode;
		newGacNode.applyChanges();
		try {
			if(newGacNode.checkConsistency()){
				newGacNode.generateChildren();
			}
		} catch (EvalError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
