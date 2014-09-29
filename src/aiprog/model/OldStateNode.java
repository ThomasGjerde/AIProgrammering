package aiprog.model;

import java.util.ArrayList;

public class OldStateNode extends Node{
	private ArrayList<OldColorNode> nodes;
	public ArrayList<OldColorNode> changes;
	public boolean victoryState;
	public boolean consistency = true;
	public StateNode stateParent;
	public OldColorNode assumption;
	//public ArrayList<ColorNode> assumptionList;
	
	public OldStateNode(Point position, ArrayList<OldColorNode> nodeList){
		super(position);
		this.nodes = nodeList;
		//assumptionList = new ArrayList<ColorNode>();
		//victoryState = checkVictory();
	}
	public void applyChanges(){
		//Can be optimized later
		if(nodes == null){
			return;
		}
		if(changes == null){
			return;
		}
		for(int i = 0; i < changes.size(); i++){
			for(int j = 0; j < nodes.size(); j++){
				OldColorNode node = nodes.get(j);
				OldColorNode change = changes.get(i);
				if(node.id == change.id){
					node.domain = change.domain;
					node.nodeColor = change.nodeColor;
				}
			}
		}
	}
	public void setAssumption(OldColorNode assNode){
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
	
	public OldColorNode getAssumption(){
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
			OldColorNode midNode = this.getNodeList().get(i);
			if(midNode.getColor() == null){
				this.victoryState = false;
				return false;
			}
			for(int j=0; j<midNode.getChildren().size(); j++){
				OldColorNode midChild = (OldColorNode)midNode.getChildren().get(j);
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
	
	public ArrayList<OldColorNode> getNodeList(){
		return nodes;
	}
	
	public void setNodeList(ArrayList<OldColorNode> newList){
		nodes = newList;
	}
	
	public void addToNodeList(OldColorNode newNode){
		nodes.add(newNode);
	}
	@Override
	public ArrayList<Node> getUnoccupiedChildren() {
		// TODO Auto-generated method stub
		return null;
	}
}
