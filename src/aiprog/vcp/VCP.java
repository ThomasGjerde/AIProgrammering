package aiprog.vcp;

import java.util.ArrayList;

import bsh.EvalError;

import aiprog.csp.CSP;
import aiprog.gui.GraphGraphics;
import aiprog.model.CSPNode;
import aiprog.model.GACCSPNode;

public class VCP extends CSP{
	GraphGraphics graphics;
	public VCP(GACCSPNode initialNode) {
		super(initialNode);
		 graphics = new GraphGraphics(initialNode,(int)(Math.ceil(Math.sqrt(initialNode.getCSPList().size()))), (int)(Math.ceil(Math.sqrt(initialNode.getCSPList().size()))));
		 graphics.setGraph((GACCSPNode)currentNode);
		 ((GACCSPNode)currentNode).generateChildren();
		 this.search();
		// TODO Auto-generated constructor stub
	}

	@Override
	protected boolean checkVictory() {
		for(int i = 0; i < ((GACCSPNode)currentNode).cspList.size(); i++){
			if(((GACCSPNode)currentNode).cspList.get(i).getNodeValue() == -1){
				return false;
			}
		}
		calculatePathLenght();
		System.out.println("Size of tree: " + numNodes);
		System.out.println("Expanded nodes: " + steps);
		System.out.println("Length of path: " + pathLength);
		System.out.println("Number of inconsistencies: " + numInconsistencies());
		System.out.println("Number of nodes without color: " + numUncolored());
		return true;
	}
	public int numInconsistencies(){
		int errors = 0;
		GACCSPNode tempNode = (GACCSPNode)currentNode;
		for(int i = 0; i < tempNode.cspList.size(); i++){
			for(int j = 0; j < tempNode.cspList.get(i).children.size(); j++){
				try {
					if(tempNode.cspList.get(i).validateConstraint((CSPNode)tempNode.cspList.get(i).children.get(j)) == false){
						errors++;
					}
				} catch (EvalError e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return errors;
	}
	public int numUncolored(){
		int unColored = 0;
		GACCSPNode tempNode = (GACCSPNode)currentNode;
		for(int i = 0; i < tempNode.cspList.size(); i++){
			if(tempNode.cspList.get(i).getNodeValue() == -1){
				unColored++;
			}
		}
		return unColored;
	}

	@Override
	protected void updateGui() {
		try {
			Thread.sleep(0);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		graphics.setGraph((GACCSPNode)currentNode);
	}

}
