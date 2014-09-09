package aiprog.model;

import java.util.ArrayList;

public class Node {
	ArrayList<Node> children;
	public Status status;
	
	public Node(){
		status = Status.Unvisited;
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
		ArrayList<Node> validChildren = new ArrayList<Node>();
		for(int i=0; i<node.getChildren().size(); i++){
			if(node.getChildren().get(i).status == status.Unvisited){
				validChildren.add(node.getChildren().get(i));
			}
		}
		if(!validChildren.isEmpty()){
			return validChildren.get(0);
		}else{
			return null;
		}
	}
	
	public enum Status {
		Unvisited,
		Visiting,
		Visited
	}
}
