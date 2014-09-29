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
			Thread.sleep(500);
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
			//Point newPoint = new Point(currentState.pos.x, currentState.pos.y+1);
			//StateNode newState = new StateNode(newPoint, currentState.getNodeList());
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
		ArrayList<ColorNode> childList = new ArrayList<ColorNode>();
		if(currentState.getStateParent().assumption == null){
			
		}else{
			for(int i=0; i<currentState.getStateParent().getChildren().size(); i++){
				StateNode midState = (StateNode)currentState.getStateParent().getChildren().get(i);
				if(midState.assumption != null){
				childList.add(midState.assumption);
				}
			}
		}
		ColorNode tempNode = null;
		for(int j=0; j<currentState.getNodeList().size(); j++){
			tempNode = currentState.getNodeList().get(j);
			ArrayList<Integer> idList = new ArrayList<Integer>();
			for(int l = 0; l < childList.size(); l++){
				idList.add(childList.get(l).id);
			}
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
		ArrayList<ColorNode> changesList = new ArrayList<ColorNode>();
		for(int i = 0; i < currentNode.getNodeList().size(); i++){
			ColorNode oldCN = currentNode.getNodeList().get(i);
			ColorNode newCN = new ColorNode(oldCN.pos);
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
		//Point newPoint = new Point(midState.pos.x+1, midState.pos.y);
		//StateNode newState = new StateNode(newPoint, midState.getNodeList());
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
		System.out.println("Backtracking");
		for(int i = 0; i < rootNode.changes.size(); i++){
			if(rootNode.changes.get(i).nodeColor == null){
			}else{	
			}
			
		}
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
			ColorNode midNode = currentState.getNodeList().get(i);
			if(midNode.getColor() == null && midNode.getDomain().isEmpty()){
				System.out.println("Return false");
				return false;
				
			}
		}
		return true;
		/*
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
		*/
	}
	
	//rewrite av assign
	//trengte og ordne tankene litt + og rydde opp
	public void assign(){
		ColorNode assignedNode = null;
		ArrayList<ColorNode> childList = new ArrayList<ColorNode>();
		//for loop for og legge til alle assumption nodene til barna, hvertbarn skal ha 1 om branchinga funker riktig
		if(currentState.getStateParent().assumption == null){
			
		}else{
			for(int i=0; i<currentState.getStateParent().getChildren().size(); i++){
				StateNode midState = (StateNode)currentState.getStateParent().getChildren().get(i);
				if(midState.assumption != null){
				
				childList.add(midState.assumption);
				}
			}
		}
		ColorNode tempNode = null;
		//g�r igjennom alle nodene
		for(int j=0; j<currentState.getNodeList().size(); j++){
			tempNode = currentState.getNodeList().get(j);
			//g�r igjennom alle barna for hver iterasjon
			
			//Remember remember the fifth of fix this shit
			ArrayList<Integer> idList = new ArrayList<Integer>();
			for(int l = 0; l < childList.size(); l++){
				idList.add(childList.get(l).id);
			}
			for(int k=0; k<childList.size(); k++){
				if(tempNode.getColor() == null && !tempNode.getDomain().isEmpty() && (!idList.contains(new Integer(tempNode.id)))){
					assignedNode = tempNode;
				}
			}
			//hvis childlist er tom sjekk, dette skal egentlig kun skje n�r det kun er 1 statenode, eller n�r vi er nederst i en branch
			if(childList.isEmpty() && tempNode.getColor() == null && !tempNode.getDomain().isEmpty()){
				assignedNode = tempNode;
			}
		}
		
		//childList.size er alltid 0
		//det bare m� v�re feilen, pga dette s� blir det alldri branching, og infinite loop
		
		if(assignedNode != null){
			assignedNode.setColor(assignedNode.getDomain().get(0));
			currentState.assumption = assignedNode;
			reduction(assignedNode);
		}else{
			backTracking();
		}
	}
	
	public void assign2(){

		ColorNode assignedNode = null;
		//Dette er ikke bra, hvis vi backtracker hit (til rotnoden), s� vil assignedNode altid bli den samme
		//s� hvis den f�rste antagelsen tilfeldigvis ikke er riktig kan dette hindre at vi finner en l�sning
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
					ArrayList<ColorNode> midArray = new ArrayList<ColorNode>();
					for(int j=0; j<currentState.getStateParent().getChildren().size(); j++){
						StateNode midChild = (StateNode)currentState.getStateParent().getChildren().get(j);
						if(midChild.assumption != null){
							midArray.add(midChild.assumption);
						}

					}
					
					boolean stupidCheck = true;
					for(int k=0; k<midArray.size(); k++){
						if(midArray.get(k).pos.x != midNode.pos.x && midArray.get(k).pos.y != midNode.pos.y){
							stupidCheck = true;
						}else{
							stupidCheck = false;
						}
					}
					if(stupidCheck){
						assignedNode = midNode;
						currentState.assumption = assignedNode;
					}
					/*
					if(!midArray.contains(midNode)){
						assignedNode = midNode;
						currentState.assumption = assignedNode;
						//break;
					}else{
						backTracking();
					}*/
					
					/*
					assignedNode = midNode;
					currentState.assumption = assignedNode;
					break;
					*/
				}
			}
		}
		if(!victoryCheck() && (assignedNode == null || assignedNode.getDomain() == null || assignedNode.getDomain().isEmpty())){
			if(assignedNode == null){
			}
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
