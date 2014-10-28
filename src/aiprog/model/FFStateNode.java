package aiprog.model;

import java.awt.Color;
import java.util.ArrayList;

import aiprog.gui.FFGraphics;

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
		//System.out.println("h " + h);
		return newNode;
	}
	public void applyChanges(){
		for(int i = 0; i < changes.size(); i++){
			nodes.get(i).nodeColor = changes.get(i).nodeColor;
			nodes.get(i).setDomain(changes.get(i).domain);
			nodes.get(i).setHead(changes.get(i).isHead());
			nodes.get(i).setParentPos(changes.get(i).parentPos);
			nodes.get(i).originPos = changes.get(i).originPos;
		}
	}
	public ArrayList<FFNode> getAllEndOfPathNodes(){
		ArrayList<FFNode> retArray = new ArrayList<FFNode>();
		for(int i = 0; i < endPoints.size(); i++){
			if(endPoints.get(i).checkConstraint() == false){
				FFNode head = endPoints.get(i).getEndOfPath();
				if(head.isHead() == false){
					//System.out.println("Not Head");
					head.setHead(true);
				}
				//retArray.add(head);
				//System.out.println(endPoints.get(i).getEndOfPath().pos.x + "," + endPoints.get(i).getEndOfPath().pos.y);
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
		//System.out.println("AllHeadsValidated");
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
			boolean failed = true;
			FFNode head = (FFNode)heads.get(i);
			for(int j = 0; j < head.children.size(); j++){
				if(((FFNode)head.children.get(j)).getColor() == null){ //Might put in domain check here
					failed = false;
				}
			}
			if(failed == true){
				return true;
			}
		}
		for(int i = 0; i < heads.size(); i++){
			for(int j = 0; j < heads.size(); j++){
				if(heads.get(i).getColor() == heads.get(j).getColor() && i != j){
					if(!heads.get(i).canReach(heads.get(j))){
						return true;
					}
				}
			}
		}
		for(int i = 0; i < nodes.size(); i++){
			FFNode node = (FFNode)nodes.get(i);
			if(node.getDomain().size() == 0 && node.getColor() == null){
				return true;
			}
		}
		/*
		if(hasTrappedBlock()){
			return true;
		}
		*/
		return false;
	}
	private boolean hasPossibleRoute(FFNode node){
		return true;
	}
	public ArrayList<Color> getCompletedColors(){
		ArrayList<Color> completedColors = new ArrayList<Color>();
		for(int i = 0; i < endPoints.size(); i++){
			if (!completedColors.contains(endPoints.get(i).getColor())){
				completedColors.add(endPoints.get(i).getColor());
			}
		}
		ArrayList<FFNode> heads = getAllEndOfPathNodes();
		for(int i = 0; i < endPoints.size(); i++){
			for(int j = 0; j < heads.size(); j++){
				if(endPoints.get(i).getColor() == heads.get(j).getColor()){
					completedColors.remove(endPoints.get(i).getColor());
				}
			}
		}
		return completedColors;
	}
	public void removeColorFromAllDomains(Color color){
		for(int i = 0; i < nodes.size(); i++){
			nodes.get(i).removeFromDomain(color);
		}
	}
	private boolean hasTrappedBlock(){
		
		for(int i = 0; i < nodes.size(); i++){
			ArrayList<FFNode> freeBlock = getFreeBlock(nodes.get(i));
			if(blockIsTrapped(freeBlock)){
				return true;
			}
		}
		return false;
	}
	private boolean blockIsTrapped(ArrayList<FFNode> freeBlock){
		for(int j = 0; j < freeBlock.size(); j++){
			for(int k = 0; k < freeBlock.get(j).children.size(); k++){
				FFNode child = (FFNode)freeBlock.get(j).children.get(k);
				if(child.getDomain().size() > 0 && child.getColor() != null){
					return false;
				}
			}
		}
		return true;
	}
	private ArrayList<FFNode> getFreeBlock(FFNode startNode){
		ArrayList<FFNode> retList = new ArrayList<FFNode>();
		retList.add(startNode);
		searchFreeBlocks(startNode,retList);
		return retList;
	}
	private void searchFreeBlocks(FFNode startNode, ArrayList<FFNode> result){
		for(int i = 0; i < startNode.children.size(); i++){
			FFNode child = (FFNode)startNode.children.get(i);
			if(child.getColor() == null){
				if(!result.contains(child)){
					result.add(child);
					searchFreeBlocks(child, result);
				}
				
			}
		}
	}
	
}
