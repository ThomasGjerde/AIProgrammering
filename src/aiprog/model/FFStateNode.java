package aiprog.model;

import java.util.ArrayList;

public class FFStateNode extends Node{
	
	public ArrayList<FFNode> availAssumptions;
	public ArrayList<FFNode> nodes = new ArrayList<FFNode>();
	public ArrayList<FFNode> changes = new ArrayList<FFNode>();
	public ArrayList<FFNode> endPoints;
	
	//Er ikke sikker p� om vi burde ha changes her... mulig det genererer mer minnebruk pga mange states?
	//Kan ha det i freeflow i stede kanskje, idk
	//public ArrayList<FFNode> changes = new ArrayList<FFNode>();
	
	public FFStateNode(ArrayList<FFNode> nodesInState, ArrayList<FFNode> endPoints){
		nodes = new ArrayList<FFNode>(nodesInState);
		availAssumptions = new ArrayList<FFNode>();
		this.endPoints = new ArrayList<FFNode>(endPoints);
	}
	
	public void setHeuristic(int h){
		heuristic = h;
	}
	
	public int getHeuristic(){
		return heuristic;
	}
	public ArrayList<FFNode> getNodes(){
		return nodes;
	}
	
	//Denne skal finne noen noder som er "prioritert" i forhold til og gj�re assumptions
	//Hvis denne lista er tom, s� kan vi evt bare velge en random?
	//Eller kanskje ha en metode for og finne en node som ikke er i denne lista og vurdere disse og bruke en?
	//Ikke sikker enda
	public void calcAssumptions(){
		//Denne skal finne nodene i enden av alle tilgjengelige paths, og legge disse (maks 3)
		//i availAssumptions arrayet, den skal ogs� lage en eller annen heuristic for hver av disse
		//Feks hvor langt den er fra m�l i manhattan distance, eller/og hvor mange andre farger det er rundt, hvor den faktisk er p� mappet (lenger fra mideten = mindre sjanse for feil)
	}
	
	//Hvis vi finner ut at domener er shit, og bare har lyst til og kj�re astar med en eller annen prioritering
	//Da trenger vi ofc en eller annen lur hullFill metode.
	//hullfill burde v�re i flowfree
	public void performAStar(){
		
	}
	public FFStateNode generateStateNode(ArrayList<FFNode> changes){
		FFStateNode newNode = new FFStateNode(this.nodes,endPoints);
		ArrayList<FFNode> newChanges = new ArrayList<FFNode>();
		for(int i = 0; i < changes.size(); i++){
			newChanges.add(changes.get(i).cloneNode());
		}
		newNode.changes = newChanges;
		int h = 0;
		for(int i = 0; i < changes.size(); i++){
			if(changes.get(i).getColor() != null){
				h++;
			}
		}
		newNode.setHeuristic(h);
		this.addChild(newNode);
		System.out.println("h " + h);
		return newNode;
	}
	public void applyChanges(){
		for(int i = 0; i < changes.size(); i++){
			nodes.get(i).nodeColor = changes.get(i).nodeColor;
			nodes.get(i).setDomain(changes.get(i).domain);
			nodes.get(i).setHead(changes.get(i).isHead());
			nodes.get(i).setParentPos(changes.get(i).parentPos);
		}
	}
	public ArrayList<FFNode> getAllEndOfPathNodes(){
		ArrayList<FFNode> retArray = new ArrayList<FFNode>();
		
		for(int i = 0; i < endPoints.size(); i++){
			if(endPoints.get(i).checkConstraint() == false){
				FFNode head = endPoints.get(i).getEndOfPath();
				if(head.isHead() == false){
					System.out.println("Not Head");
					head.setHead(true);
				}
				//retArray.add(head);
				System.out.println(endPoints.get(i).getEndOfPath().pos.x + "," + endPoints.get(i).getEndOfPath().pos.y);
			}	
		}
		
		for(int i = 0; i < nodes.size(); i++){
			if(nodes.get(i).isHead()){
				boolean addNode = true;
				FFNode head = nodes.get(i);
				for(int j = 0; j < head.children.size(); j++){
					FFNode child = (FFNode)head.children.get(j);
					if(child.isHead() && child.getColor() == head.getColor()){
						addNode = false;
					}
				}
				if(addNode){
					retArray.add(nodes.get(i));
				}
			}
		}
		
		return retArray;
	}
	public boolean checkAllConstraints(){
		//System.out.println("Heads: " + getAllEndOfPathNodes().size());
		/*
		for(int i = 0; i < endPoints.size(); i++){
			if(endPoints.get(i).checkConstraint() == false){
				return false;
			}
		}
		*/
		if(getAllEndOfPathNodes().size() > 0){
			return false;
		}
		System.out.println("AllHeadsValidated");
		for(int i = 0; i < nodes.size(); i++){
			if(nodes.get(i).getColor() == null){
				return false;
			}
		}
		return true;
	}
	
	public boolean hasFailed(){
		ArrayList<FFNode> heads = this.getAllEndOfPathNodes();
		for(int i = 0; i < heads.size(); i++){
			FFNode head = (FFNode)heads.get(i);
			for(int j = 0; j < head.children.size(); j++){
				if(((FFNode)head.children.get(j)).getColor() == null){ //Might put in domain check here
					return false;
				}
			}
		}
		return true;
	}
}
