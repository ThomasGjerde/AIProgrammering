package aiprog.model;

import java.util.ArrayList;

public class StateNode extends Node{
	ArrayList<ColorNode> nodes;
	public StateNode(Point position, ArrayList<ColorNode> nodeList){
		super(position);
		this.nodes = nodeList;
	}
	
	public boolean checkVictory(ArrayList<ColorNode> completeList){
		boolean check = false;
		for(int i=0; i<completeList.size(); i++){
			
		}
		
		
		
		return false;
	}
}
