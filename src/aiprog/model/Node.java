package aiprog.model;

import java.util.ArrayList;

public class Node {
	public ArrayList<Node> children;
	public Node parent;
	public Status status;
	public Point pos = new Point();
	public int heuristic;
	public int cost;
	
	public Node(Point position){
		status = Status.Unvisited;
		children = new ArrayList<Node>();
		heuristic = 0;
		cost = 1;
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
	public ArrayList<Node> getUnoccupiedChildren(){
		ArrayList<Node> returnArray = new ArrayList<Node>();
		for(int i = 0; i < children.size(); i++){
			Node tempNode = children.get(i);
			if(tempNode.status == Status.Unvisited || tempNode.status == Status.Visiting){
				returnArray.add(tempNode);
			}
		}
		return returnArray;
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
			cost += tempNode.cost;
			tempNode = tempNode.parent;
		}
		return cost;
	}
}
