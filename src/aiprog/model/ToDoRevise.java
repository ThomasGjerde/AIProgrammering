package aiprog.model;

import java.awt.Color;
import java.util.ArrayList;





//Det meste i denne klassen er gjort p� mer h�p en forventning!
//Dvs her er det mange ting som foreh�pentligvis g�r, basert p� at andre ting forh�pentligvis g�r.... lotto hele greia
public class ToDoRevise {
	StateNode currentState;
	
	public ToDoRevise(StateNode state){
		currentState = state;
	}
	
	public void check(){
		for(int i=0; i<currentState.getNodeList().size(); i++){
			ColorNode midNode = currentState.getNodeList().get(i);
			if(midNode.getDomain() != null){
				if(midNode.getDomain().size() != 0){
					if(midNode.getColor() == null){
						if(midNode.getDomain().size() == 1){
							midNode.setColor(midNode.getDomain().get(0));
						}
					}
				}
			}
		}
		//consistency();
		if(consistency() && !victoryCheck()){
			Point newPoint = new Point(currentState.pos.x, currentState.pos.y+1);
			StateNode newState = new StateNode(newPoint, currentState.getNodeList());
			newState.setParent(currentState);
			currentState = newState;
			assign();
			//lag ny state, med currentState som parent
			//assign();
		}else if(victoryCheck()){
			
		}else{
			Point newPoint = new Point(currentState.stateParent.pos.x+1, currentState.stateParent.pos.y);
			StateNode newState = new StateNode(newPoint, currentState.stateParent.getNodeList());
			newState.setParent(currentState.stateParent);
			currentState = newState;
			assign();
			//legg til et felt i state, som heter noe ala consistency
			//sett dette til false
			//false er da deadend, ingen vits og fortsette med antagelser i den retningen.
			//en eller annen form for backtracking
		}
	}
	
	public boolean victoryCheck(){
		if(currentState.checkVictory()){
			return true;
		}else{
			return false;
		}
	}
	/*
	public void createState(StateNode parentState){
		//skal lage en ny state
		//dvs, denne skal kopiere currentState, og lage et nytt todorevise, og kalle assign p� denne nye staten
		//denne nye staten skal ha currentstate som parent.
		//consistency skal v�re true
	}
	
	public void backTrack(){
		//denne skal g� til parent staten, og gj�re en annen antagelse enn forige gang
		//dvs, den skal lage en ny todorevise p� parenten som parameter, og kalle assign
		//consistency p� den staten som er feil skal v�re false
	}
	*/
	
	public boolean consistency(){
		boolean cons = true;
		for(int i=0; i<currentState.getNodeList().size(); i++){
			ColorNode midNode = currentState.getNodeList().get(i);
			for(int j=0; j<midNode.getChildren().size(); j++){
				ColorNode midChild = (ColorNode)midNode.getChildren().get(i);
				if(midChild.getColor() != null && midNode.getColor() != null){
					if(midNode.getColor() == midChild.getColor()){
						cons = false;
						currentState.consistency = false;
						return cons;
					}
				}
			}
		}
		return cons;
	}
	
	
	
	public void assign(){
		ColorNode assignedNode = null;
		for(int i=0; i<currentState.getNodeList().size(); i++){
			ColorNode midNode = currentState.getNodeList().get(i); 
			if(midNode.getDomain() != null && midNode.getDomain().size() > 0){
				if(assignedNode == null && assignedNode != currentState.stateParent.assumption){
					assignedNode = midNode;
				}else if(assignedNode.getDomain().size() > midNode.getDomain().size() && assignedNode != currentState.stateParent.assumption){
					assignedNode = midNode;
				}
			}
		}
		
		assignedNode.setColor(assignedNode.getDomain().get(0));
		currentState.assumption = assignedNode; //Setter antagelsen
		reduction(assignedNode);
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
