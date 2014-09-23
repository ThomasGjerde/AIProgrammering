package aiprog.model;

import java.util.ArrayList;

public class Node {
	public ArrayList<Node> children;
	public Node parent;
	public Status status;
	public Point pos;
	public int heuristic;
	
	public Node(Point position){
		status = Status.Unvisited;
		children = new ArrayList<Node>();
		heuristic = 0;
		this.pos = position;
	}
	
	public void addChild(Node node){
		children.add(node);
	}
	
	public ArrayList<Node> getChildren(){
		return children;
	}
	
	public void setStatus(Status newStatus){
		status = newStatus;
	}
	
	public Node getNextChild(){
		if(getChildren().isEmpty()){
			return null;
		}
		for(int i = 0; i < getChildren().size(); i++){
			if(getChildren().get(i).status == Status.Unvisited){
				Node child = getChildren().get(i);
				child.parent = this;
				return child;
			}
		}
		return null;
	}
	
	public enum Status {
		Unvisited,
		Visiting,
		Visited,
		Obstacle
	}
	public int getCostFromStart(){
		Node tempNode = this.parent;
		int cost = 0;
		if(tempNode == null){
			return 0;
		}
		while(tempNode.parent != null){
			cost++;
			tempNode = tempNode.parent;
		}
		return cost;
	}
}
