package aiprog.model;

import java.util.ArrayList;

public class StateNode extends Node{
	private ArrayList<ColorNode> nodes;
	public ArrayList<ColorNode> changes;
	public boolean victoryState;
	public boolean consistency = true;
	public StateNode stateParent;
	public ColorNode assumption;
	//public ArrayList<ColorNode> assumptionList;
	
	public StateNode(Point position, ArrayList<ColorNode> nodeList){
		super(position);
		this.nodes = nodeList;
		//assumptionList = new ArrayList<ColorNode>();
		//victoryState = checkVictory();
	}
	public void applyChanges(){
		//Can be optimized later
		if(nodes == null){
			System.out.println("NO nodes");
			return;
		}
		if(changes == null){
			System.out.println("NO changes");
			return;
		}
		for(int i = 0; i < changes.size(); i++){
			for(int j = 0; j < nodes.size(); j++){
				ColorNode node = nodes.get(j);
				ColorNode change = changes.get(i);
				if(node.id == change.id){
					node.domain = change.domain;
					node.nodeColor = change.nodeColor;
				}
			}
		}
	}
	public void setAssumption(ColorNode assNode){
		assumption = assNode;
	}
	/*
	public ArrayList<ColorNode> getAssumptionList(){
		return assumptionList;
	}
	
	public void addToAssumptionList(ColorNode addNode){
		assumptionList.add(addNode);
	}
	*/
	
	public boolean getConsistency(){
		return consistency;
	}
	/*
	public void removeFromAssumptionList(ColorNode removeNode){
		for(int i=0; i<assumptionList.size(); i++){
			if(assumptionList.get(i) == removeNode){
				assumptionList.remove(removeNode);
				break;
			}
		}
	}
	*/
	
	public ColorNode getAssumption(){
		return assumption;
	}
	
	public boolean checkAssumption(){
		if(assumption != null){
			return true;
		}else{
			return false;
		}
	}
	
	//dette m� skrives om, vekk med constraints
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
				ColorNode midChild = (ColorNode)midNode.getChildren().get(j);
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
	
	
	//�ker domenet i alle nodene med 1 farge
	public void increaseNodeDomain(){
		for(int i=0; i<getNodeList().size(); i++){
			getNodeList().get(i).addDomain();
		}
	}
	
	public void setParent(StateNode parentNode){
		this.stateParent = parentNode;
	}
	
	public StateNode getStateParent(){
		return this.stateParent;
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
