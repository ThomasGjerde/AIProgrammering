package aiprog.model;

import java.util.ArrayList;

import aiprog.model.Node.Status;

public class NavNode extends Node{

	public NavNode(Point position) {
		super(position);
		// TODO Auto-generated constructor stub
	}

	@Override
	public ArrayList<Node> getUnoccupiedChildren() {
		ArrayList<Node> returnArray = new ArrayList<Node>();
		for(int i = 0; i < children.size(); i++){
			Node tempNode = children.get(i);
			if(tempNode.status == Status.Unvisited || tempNode.status == Status.Visiting){
				returnArray.add(tempNode);
			}
		}
		return returnArray;
	}

}
