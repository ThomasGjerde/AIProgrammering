package aiprog.model;

import java.awt.Color;
import java.util.ArrayList;

import aiprog.gui.GraphGraphics;

public class ToDoRevise {
	StateNode currentState;
	GraphGraphics gg;
	int counter;
	StateNode rootNode;
	
	public ToDoRevise(StateNode state){
		currentState = state;
		gg = new GraphGraphics((int)(Math.ceil(Math.sqrt(40))), (int)(Math.ceil(Math.sqrt(40))));
		counter = 0;
		rootNode = state;
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
		
		if(consistency() && !victoryCheck()){
			
			StateNode newState = generateStateNode(currentState, false);
			newState.setParent(currentState);
			currentState.addChild(newState);
			currentState = newState;
			currentState.applyChanges();
			assign();
		}else if(victoryCheck()){
			
		}else{
			backTracking();
		}
	}
	
	public boolean possibleAssumption(){
		StateNode currentState = this.currentState;
		ArrayList<OldColorNode> childList = new ArrayList<OldColorNode>();
		if(currentState.getStateParent().assumption == null){
			
		}else{
			for(int i=0; i<currentState.getStateParent().getChildren().size(); i++){
				StateNode midState = (StateNode)currentState.getStateParent().getChildren().get(i);
				if(midState.assumption != null){
				childList.add(midState.assumption);
				}
			}
		}
		
		ArrayList<Integer> idList = new ArrayList<Integer>();
		for(int l = 0; l < childList.size(); l++){
			idList.add(childList.get(l).id);
		}
		
		OldColorNode tempNode = null;
		for(int j=0; j<currentState.getNodeList().size(); j++){
			tempNode = currentState.getNodeList().get(j);
			
			
			if(tempNode.getColor() == null && !tempNode.getDomain().isEmpty() && (!idList.contains(new Integer(tempNode.id)))){
				return true;
			}
		}
		return false;
	}
	private StateNode generateStateNode(StateNode currentNode, boolean backTracked){
		StateNode newNode = new StateNode(new Point(), currentNode.getNodeList());
		if(backTracked){
			newNode.pos.x = currentNode.pos.x + 1;
			newNode.pos.y = currentNode.pos.y;
		}else{
			newNode.pos.x = currentNode.pos.x;
			newNode.pos.y = currentNode.pos.y + 1;
		}
		ArrayList<OldColorNode> changesList = new ArrayList<OldColorNode>();
		for(int i = 0; i < currentNode.getNodeList().size(); i++){
			OldColorNode oldCN = currentNode.getNodeList().get(i);
			OldColorNode newCN = new OldColorNode(oldCN.pos);
			newCN.id = oldCN.id;
			newCN.domain = new ArrayList<Color>(oldCN.domain);
			newCN.nodeColor = oldCN.nodeColor;
			changesList.add(newCN);
		}
		newNode.changes = changesList;
		return newNode;
	}
	public void startBranch(){
		StateNode midState = currentState.getStateParent();
		if(currentState.getStateParent() == null){
			midState = currentState;
		}
		
		StateNode newState = generateStateNode(midState, true);
		newState.applyChanges();
		newState.setParent(midState);
		currentState.getStateParent().addChild(newState);
		currentState = newState;
		currentState.applyChanges();
		currentState.consistency = true;
		assign();
	}
	
	public void backTracking(){
		
		boolean backTrack = false;
		while(!backTrack){
			gg.setGraph(currentState);
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(currentState.getAssumption() == null){
				
			}
			if(possibleAssumption()){
				backTrack = true;
				startBranch();
				break;
			}else{
				currentState = currentState.getStateParent();
				
				currentState.applyChanges();
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
		for(int i=0; i<currentState.getNodeList().size(); i++){
			OldColorNode midNode = currentState.getNodeList().get(i);
			if(midNode.getColor() == null && midNode.getDomain().isEmpty()){
				return false;
			}
		}
		return true;
		
	}
	
	public void assign(){
		OldColorNode assignedNode = null;
		ArrayList<OldColorNode> childList = new ArrayList<OldColorNode>();

		if(currentState.getStateParent().assumption == null){
			
		}else{
			for(int i=0; i<currentState.getStateParent().getChildren().size(); i++){
				StateNode midState = (StateNode)currentState.getStateParent().getChildren().get(i);
				if(midState.assumption != null){
					childList.add(midState.assumption);
				}
			}
		}
		OldColorNode tempNode = null;

		ArrayList<Integer> idList = new ArrayList<Integer>();
		for(int l = 0; l < childList.size(); l++){
			idList.add(childList.get(l).id);
		}
		
		for(int j=0; j<currentState.getNodeList().size(); j++){
			tempNode = currentState.getNodeList().get(j);
			
			if(tempNode.getColor() == null && !tempNode.getDomain().isEmpty() && (!idList.contains(new Integer(tempNode.id)))){
				assignedNode = tempNode;
			}

			if(childList.isEmpty() && tempNode.getColor() == null && !tempNode.getDomain().isEmpty()){
				assignedNode = tempNode;
			}
		}
		
		if(assignedNode != null){
			assignedNode.setColor(assignedNode.getDomain().get(0));
			currentState.assumption = assignedNode;
			reduction(assignedNode);
		}else{
			backTracking();
		}
	}
	
	public void reduction(OldColorNode node){
		for(int i=0; i<node.getChildren().size(); i++){
			OldColorNode midChild = (OldColorNode)node.getChildren().get(i);
			if(node.getColor() != null){
				if(midChild.getDomain().contains(node.getColor())){
					midChild.reduseDomain(node.getColor());
				}
			}
		}
		check();
	}
	
}
