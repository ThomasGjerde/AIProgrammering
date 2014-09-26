package aiprog.model;

import java.util.ArrayList;

public class StateNode extends Node{
	private ArrayList<ColorNode> nodes;
	public boolean victoryState;
	public StateNode stateParent;
	
	public StateNode(Point position, ArrayList<ColorNode> nodeList){
		super(position);
		this.nodes = nodeList;
		victoryState = checkVictory(this.nodes);
	}
	
	//dette må skrives om, vekk med constraints
	public boolean checkVictory(ArrayList<ColorNode> completeList){
		boolean check = true;
		for(int i=0; i<completeList.size(); i++){
			for(int j=0; j < completeList.get(i).constraints.size(); j++){
				Constraint midCon = completeList.get(i).constraints.get(j);
				if(!midCon.checkConstrain()){
					check = false;
					break;
				}
			}
		}
		return check;
	}
	
	public boolean isVictory(){
		return victoryState;
	}
	
	public void setParent(StateNode parentNode){
		this.stateParent = parentNode;
	}
	
	public ArrayList<ColorNode> getNodeList(){
		return nodes;
	}
	
	public void setNodeList(ArrayList<ColorNode> newList){
		nodes = newList;
	}
	
	public void addToNodeList(ColorNode newNode){
		nodes.add(newNode);
	}
}
