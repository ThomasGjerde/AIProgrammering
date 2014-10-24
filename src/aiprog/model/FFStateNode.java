package aiprog.model;

import java.util.ArrayList;

public class FFStateNode extends Node{
	
	public ArrayList<FFNode> availAssumptions;
	public ArrayList<FFNode> nodes = new ArrayList<FFNode>();
	public ArrayList<FFNode> changes = new ArrayList<FFNode>();
	
	//Er ikke sikker p� om vi burde ha changes her... mulig det genererer mer minnebruk pga mange states?
	//Kan ha det i freeflow i stede kanskje, idk
	//public ArrayList<FFNode> changes = new ArrayList<FFNode>();
	
	public FFStateNode(ArrayList<FFNode> nodesInState){
		nodes = new ArrayList<FFNode>(nodesInState);
		availAssumptions = new ArrayList<FFNode>();
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
		FFStateNode newNode = new FFStateNode(this.nodes);
		ArrayList<FFNode> newChanges = new ArrayList<FFNode>();
		for(int i = 0; i < changes.size(); i++){
			newChanges.add(changes.get(i).cloneNode());
		}
		newNode.changes = newChanges;
		this.addChild(newNode);
		return newNode;
	}
	public void applyChanges(){
		for(int i = 0; i < changes.size(); i++){
			nodes.get(i).nodeColor = changes.get(i).nodeColor;
			nodes.get(i).setDomain(changes.get(i).domain);
		}
	}
	
	
}
