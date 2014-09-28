package aiprog.model;

import java.awt.Color;
import java.util.ArrayList;

import aiprog.gui.GraphGraphics;

public class ToDoRevise {
	StateNode currentState;
	GraphGraphics gg;
	int counter;
	
	public ToDoRevise(StateNode state){
		currentState = state;
		gg = new GraphGraphics((int)(Math.ceil(Math.sqrt(40))), (int)(Math.ceil(Math.sqrt(40))));
		counter = 0;
		check();
	}
	
	public void check(){
		try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		gg.setGraph(currentState);
		for(int i=0; i<currentState.getNodeList().size(); i++){
			ColorNode midNode = currentState.getNodeList().get(i);
			if(midNode.getDomain() != null){
				if(midNode.getDomain().size() != 0){
					if(midNode.getColor() == null){
						if(midNode.getDomain().size() == 1){
							midNode.setColor(midNode.getDomain().get(0));
							reduction(midNode);
						}
					}
				}
			}
		}
		if(consistency() && !victoryCheck()){
			Point newPoint = new Point(currentState.pos.x, currentState.pos.y+1);
			StateNode newState = new StateNode(newPoint, currentState.getNodeList());
			newState.setParent(currentState);
			currentState = newState;
			assign();
		}else if(victoryCheck()){
			
		}else{
			backTracking();
		}
	}
	
	public boolean possibleAssumption(ColorNode old){
		for(int i=0; i<currentState.getNodeList().size(); i++){
			ColorNode midNode = currentState.getNodeList().get(i); 
			if(midNode.getDomain() != null && midNode.getDomain().size() > 0 && midNode.getColor() == null && midNode != old){
				return true;
			}
		}
		return false;
	}
	
	public void startBranch(){
		StateNode midState = currentState.getStateParent();
		if(currentState.getStateParent() == null){
			midState = currentState;
		}
		Point newPoint = new Point(midState.pos.x+1, midState.pos.y);
		StateNode newState = new StateNode(newPoint, midState.getNodeList());
		newState.setParent(midState);
		currentState = newState;
		currentState.consistency = true;
		assign();
	}
	
	public void backTracking(){
		boolean backTrack = false;
		while(!backTrack){
			if(currentState.getAssumption() == null){
				
			}
			if(possibleAssumption(currentState.getAssumption()) && currentState.consistency == true){
				backTrack = true;
				startBranch();
				break;
			}else{
				currentState.consistency = false;
				currentState = currentState.getStateParent();
			}
		}
	}
	
	public boolean victoryCheck(){
		if(currentState.checkVictory()){
			return true;
		}else{
			return false;
		}
	}
	
	public boolean consistency(){
		boolean cons = true;
		for(int i=0; i<currentState.getNodeList().size(); i++){
			ColorNode midNode = currentState.getNodeList().get(i);
			if(midNode.getColor() == null && midNode.getDomain().isEmpty()){
				cons = false;
				currentState.consistency = false;
				return cons;
			}
		}
		currentState.consistency = cons;
		return cons;
	}
	
	
	
	public void assign(){

		ColorNode assignedNode = null;
		if(currentState.stateParent == null){
			assignedNode = currentState.getNodeList().get(0);
			currentState.setAssumption(assignedNode);
		
		}else{
			for(int i=0; i<currentState.getNodeList().size(); i++){
				ColorNode midNode = currentState.getNodeList().get(i); 
				if(midNode.getDomain() != null && midNode.getDomain().size() > 0 && midNode.getColor() == null && midNode != currentState.getStateParent().getAssumption()){
					/*
					for(int j=0; j<currentState.getChildren().size(); j++){
						StateNode midState = (StateNode)currentState.getChildren().get(j);
						if(midState.getAssumption() != midNode){
							
						}
					}*/
					
					assignedNode = midNode;
					currentState.assumption = assignedNode;
					break;
				}
			}
		}
		if(!victoryCheck() && (assignedNode == null || assignedNode.getDomain() == null || assignedNode.getDomain().isEmpty())){
			currentState.consistency = false;
			backTracking();
		}else{
			assignedNode.setColor(assignedNode.getDomain().get(0));
			currentState.assumption = assignedNode;
			reduction(assignedNode);
		}
	}
	
	public void reduction(ColorNode node){
		for(int i=0; i<node.getChildren().size(); i++){
			ColorNode midChild = (ColorNode)node.getChildren().get(i);
			if(node.getColor() != null){
				if(midChild.getDomain().contains(node.getColor())){
					midChild.reduseDomain(node.getColor());
				}
			}
		}
		check();
	}
	
}
