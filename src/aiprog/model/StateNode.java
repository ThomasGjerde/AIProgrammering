package aiprog.model;

import java.util.ArrayList;

public class StateNode extends Node{
	private ArrayList<ColorNode> nodes;
	public boolean victoryState;
	public boolean consistency;
	public StateNode stateParent;
	public ColorNode assumption;
	
	public StateNode(Point position, ArrayList<ColorNode> nodeList){
		super(position);
		this.nodes = nodeList;
		//victoryState = checkVictory();
	}
	
	//dette må skrives om, vekk med constraints
	/*
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
	*/
	public boolean checkVictory(){
		for(int i=0; i<this.getNodeList().size(); i++){
			ColorNode midNode = this.getNodeList().get(i);
			if(midNode.getColor() == null){
				this.victoryState = false;
				return false;
			}
			for(int j=0; j<midNode.getChildren().size(); j++){
				ColorNode midChild = (ColorNode)midNode.getChildren().get(i);
				if(midChild.getColor() == null){
					this.victoryState = false;
					return false;
				}
				if(midChild.getColor() == midNode.getColor()){
					this.victoryState = false;
					return false;
				}
			}
		}
		this.victoryState = true;
		return true;
	}
	public boolean isVictory(){
		return victoryState;
	}
	
	
	//Øker domenet i alle nodene med 1 farge
	public void increaseNodeDomain(){
		for(int i=0; i<getNodeList().size(); i++){
			getNodeList().get(i).addDomain();
		}
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
