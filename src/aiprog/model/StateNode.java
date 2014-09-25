package aiprog.model;

import java.util.ArrayList;

public class StateNode extends Node{
	public ArrayList<ColorNode> nodes;
	public boolean victoryState;
	
	public StateNode(Point position, ArrayList<ColorNode> nodeList){
		super(position);
		this.nodes = nodeList;
		victoryState = checkVictory(this.nodes);
	}
	
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
}
