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
		//ArrayList<Node> validChildren = new ArrayList<Node>();
		for(int i=0; i<node.getChildren().size(); i++){
			if(node.getChildren().get(i).status == Status.Unvisited){
				//validChildren.add(node.getChildren().get(i));
				return node.getChildren().get(i);
			}
		}
		return null;
		/*
		if(!validChildren.isEmpty()){
			return validChildren.get(0);
		}else{
			return null;
		}*/
	}
	
	public enum Status {
		Unvisited,
		Visiting,
		Visited,
		Obstacle
	}
}
