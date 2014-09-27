package aiprog.model;

import java.awt.Color;
import java.util.ArrayList;





//Det meste i denne klassen er gjort på mer håp en forventning!
//Dvs her er det mange ting som forehåpentligvis går, basert på at andre ting forhåpentligvis går.... lotto hele greia
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
			//ToDoRevise next = New ToDoRevise(newState);
			//ToDoRevise(newState);
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
	
	public void createState(StateNode parentState){
		//skal lage en ny state
		//dvs, denne skal kopiere currentState, og lage et nytt todorevise, og kalle assign på denne nye staten
		//denne nye staten skal ha currentstate som parent.
		//consistency skal være true
	}
	
	public void backTrack(){
		//denne skal gå til parent staten, og gjøre en annen antagelse enn forige gang
		//dvs, den skal lage en ny todorevise på parenten som parameter, og kalle assign
		//consistency på den staten som er feil skal være false
	}
	
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
				if(assignedNode == null){
					assignedNode = midNode;
				}else if(assignedNode.getDomain().size() > midNode.getDomain().size()){
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
	
	
	/*
	//ArrayList<Constraint> queue; //liste med constraints som skal sjekkes
	ArrayList<ColorNode> checkedQueue; //leste med constraints som har blitt sjekka
	ArrayList<ColorNode> queue;
	
	public ToDoRevise(){
		checkedQueue = new ArrayList<ColorNode>();
		queue = new ArrayList<ColorNode>();
	}
	
	public void addToQueue(ColorNode node){ //dah
		queue.add(node);
	}
	
	public ColorNode popFromQueue(){ //henter første elem i lista, og fjerner dette elementet
		if(!queue.isEmpty()){
			return queue.get(0);
		}
		return null;
	}
	
	*/
	
	/*
	 * Etter at en farge har blitt satt i en node, skal denne fargen fjernes fra domene til alle den har constrainter til dvs alle barna
	 * Fjerner også alle fargene i domene til noden der fargen har blitt satt.
	 */
	/*
	public void reduseDomainWithLinks(ColorNode node, Color reduseColor){
		//node.reduseDomain(reduseColor);
		if(node.getColor() != null){ //idiot check
			node.domain.clear(); //fjerner alle domener
		}
		for(int i=0; i<node.constraints.size(); i++){ //går igjennom alle constraints, for så og redusere domenene der dette er mulig i barna
			if(node == node.constraints.get(i).x){
				node.constraints.get(i).getConstraintY().reduseDomain(reduseColor);
			}else if(node == node.constraints.get(i).y){
				node.constraints.get(i).getConstraintX().reduseDomain(reduseColor);
			}
		}
	}
	
	public void mid(ColorNode node){
		if(node.getColor() == null){
			
		}
		for(int i=0; i<node.constraints.size(); i++){
			Constraint cons = node.constraints.get(i);
			if(cons.getConstraintX().getDomain() != null || cons.getConstraintX().getDomain().size() > 0){
				if(cons.getConstraintX().getDomain().contains(node.getColor())){
					cons.getConstraintX().reduseDomain(node.getColor());
				}
			}
			if(cons.getConstraintY().getDomain() != null || cons.getConstraintY().getDomain().size() > 0){
				if(cons.getConstraintY().getDomain().contains(node.getColor())){
					cons.getConstraintY().reduseDomain(node.getColor());
				}
			}
		}
	}
	*/
	
	/*
	 * Denne skal hente neste elem fra queue til lista er tom.
	 * Så skal den sjekke om noen av nodene forbundet med constraintene i lista har 1 elem i domene
	 * hvis den har det skal denne noden sette til denne fargen.
	 * Da burde constraintsene til denne noden legges i lista?
	 * 
	 */
	/*
	public void checkConstraints(){
		if(!queue.isEmpty()){
			ColorNode midCons = popFromQueue(); //henter constraint fra queue
			checkedQueue.add(midCons); //legger constrainten til checkedQueue
			if(midCons.getDomain().size() == 1){
				for(int i=0; i<midCons.getChildren().size(); i++){
					ColorNode midNode = (ColorNode) midCons.getChildren().get(i);
					if(midNode.getColor() != null){
						if(midNode.getColor() != midCons.getDomain().get(i)){
							midCons.setColor(midCons.getDomain().get(0));
							reduseDomainWithLinks(midCons, midCons.getColor());
						}
					}
				}	
			}
		}
	}
	
	/*
	 * Skal kalles ved setting av farge i en node, da skal noden som bli satt, sendes til denne.
	 */
	/*
	public void Revise(ColorNode node){
		for(int i=0; i<node.getChildren().size(); i++){
			ColorNode midChild = (ColorNode)node.getChildren().get(i);
			if(midChild.getColor() == null){
				
			}
		}
	}
	*/
	/*
	public boolean checkClose(ColorNode node){
		Color color = node.getDomain().get(0);
		for(int i=0; i<node.getChildren().size(); i++){
			ColorNode midNode = (ColorNode)node.getChildren().get(i);
			if(color == midNode.getColor()){
				return false;
			}
		}
		return true;
	}
	*/
	/*
	 * Når en node blir endret skal denne redusere domenet i alle naboene
	 * hvis domenet blir redusert til 1 skal den først teste om det er lov og sette den fargen
	 * hvis det er lov skal denne settes, og den skal kalle seg selv på akkurat den noden.
	 */
	/*
	public void revise(ColorNode node){
		for(int i=0; i<node.getChildren().size(); i++){
			ColorNode midNode = (ColorNode)node.getChildren().get(i);
			if(midNode.getDomain().contains(node.getColor())){
				midNode.reduseDomain(node.getColor());
				if(midNode.getDomain() != null){
					if(midNode.getDomain().size() == 1 && checkClose(midNode)){
						midNode.setColor(midNode.getDomain().get(0));
						revise(midNode);
					}
				}
			}
			
		}
	}
	*/
	
	
}
