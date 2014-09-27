package aiprog.model;

import java.awt.Color;
import java.util.ArrayList;

import aiprog.gui.GraphGraphics;





//Det meste i denne klassen er gjort p� mer h�p en forventning!
//Dvs her er det mange ting som foreh�pentligvis g�r, basert p� at andre ting forh�pentligvis g�r.... lotto hele greia
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
			Thread.sleep(400);
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
							//dette er en endring
							/*
							for(int j=0; j < midNode.getChildren().size(); j++){
								ColorNode midChild1 = (ColorNode)midNode.getChildren().get(j);
								
							}
							*/
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
			System.out.println("1" + " X: " + currentState.pos.x + " Y " + currentState.pos.y);
			assign();
			//lag ny state, med currentState som parent
			//assign();
		}else if(victoryCheck()){
			
		}else{
			/*
			Point newPoint = new Point(currentState.stateParent.pos.x+1, currentState.stateParent.pos.y);
			StateNode newState = new StateNode(newPoint, currentState.stateParent.getNodeList());
			newState.setParent(currentState.stateParent);
			currentState = newState;
			System.out.println("2" + " X: " + currentState.pos.x + " Y " + currentState.pos.y);
			*/
			backTracking();
			assign();
			//legg til et felt i state, som heter noe ala consistency
			//sett dette til false
			//false er da deadend, ingen vits og fortsette med antagelser i den retningen.
			//en eller annen form for backtracking
		}
	}
	
	public void backTracking(){
		boolean nonConsistent = false;
		StateNode midState = currentState;
		while(nonConsistent == false){
			if(midState.consistency == false){
				midState = midState.stateParent;
				System.out.println("check");
			}
			if(midState.consistency == true){
				nonConsistent = true;
			}
			//nonConsistent = midState.consistency;
		}
		Point newPoint = new Point(midState.pos.x+1, midState.pos.y);
		StateNode newState = new StateNode(newPoint, midState.getNodeList());
		newState.setParent(midState);
		currentState = newState;
		System.out.println("2" + " X: " + currentState.pos.x + " Y " + currentState.pos.y);
		assign();
		
	}
	
	public boolean victoryCheck(){
		if(currentState.checkVictory()){
			//System.out.println("3 true");
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
		/*
		for(int i=0; i<currentState.getNodeList().size(); i++){
			ColorNode midNode = currentState.getNodeList().get(i);
			for(int j=0; j<midNode.getChildren().size(); j++){
				ColorNode midChild = (ColorNode)midNode.getChildren().get(j);
				if(midChild.getColor() != null && midNode.getColor() != null){
					if(midNode.getColor() == midChild.getColor()){
						cons = false;
						currentState.consistency = false;
						//System.out.println("1 " + cons);
						return cons;
					}
				}
			}
		}
		*/
		for(int i=0; i<currentState.getNodeList().size(); i++){
			ColorNode midNode = currentState.getNodeList().get(i);
			if(midNode.getColor() == null && midNode.getDomain().isEmpty()){
				cons = false;
				currentState.consistency = false;
				System.out.println("heiigjen");
				return cons;
			}
		}
		//System.out.println("2 " + cons);
		currentState.consistency = cons;
		return cons;
	}
	
	
	
	public void assign(){

		ColorNode assignedNode = null;
		if(currentState.stateParent == null){
			assignedNode = currentState.getNodeList().get(0);
			
		}else{
			//System.out.println("Else");
			for(int i=0; i<currentState.getNodeList().size(); i++){
				ColorNode midNode = currentState.getNodeList().get(i); 
				if(midNode.getDomain() != null && midNode.getDomain().size() > 0 && midNode.getColor() == null){
					assignedNode = midNode;
					break;
					/*
					if(assignedNode == null){
						assignedNode = midNode;
						//System.out.println(""+ midNode.getDomain().size());
					}else if(assignedNode.getDomain().size() > midNode.getDomain().size() && assignedNode != currentState.stateParent.assumption){
						assignedNode = midNode;
						System.out.println("2");
					}
					*/
				}
			}
		}
		assignedNode.setColor(assignedNode.getDomain().get(0));
		currentState.assumption = assignedNode; //Setter antagelsen
		//System.out.println("X " + assignedNode.pos.x + " Y " + assignedNode.pos.y);
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
