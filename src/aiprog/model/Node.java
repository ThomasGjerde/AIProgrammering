package aiprog.model;

import java.util.ArrayList;

public class Node {
	public ArrayList<Node> children;
	public Status status;
	public int positionX;
	public int positionY;
	public int h;
	
	public Node(int positionX, int positionY){
		status = Status.Unvisited;
		children = new ArrayList<Node>();
		h = 0;
		this.positionX = positionX;
		this.positionY = positionY;
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
	
	public Node getNextChild(Node node){
		if(node.getChildren().isEmpty()){
			return null;
		}
		for(int i=0; i<node.getChildren().size(); i++){
			if(node.getChildren().get(i).status == Status.Unvisited){
				return node.getChildren().get(i);
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
}
