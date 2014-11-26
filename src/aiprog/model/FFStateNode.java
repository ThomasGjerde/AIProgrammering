package aiprog.model;

import java.awt.Color;
import java.util.ArrayList;

public class FFStateNode extends Node{
	
	public ArrayList<FFNode> availAssumptions;
	public ArrayList<FFNode> nodes = new ArrayList<FFNode>();
	public ArrayList<FFNode> changes = new ArrayList<FFNode>();
	public ArrayList<FFNode> endPoints;
	
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
					head.setHead(true);
				}
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
		if(getAllEndOfPathNodes().size() > 0){
			return false;
		}
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
		return false;
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
	@SuppressWarnings("unused")
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
