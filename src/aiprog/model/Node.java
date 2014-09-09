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
	public enum Status {
		Unvisited,
		Current,
		Visited
	}
}
