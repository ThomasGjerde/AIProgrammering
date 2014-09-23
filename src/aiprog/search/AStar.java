package aiprog.search;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import aiprog.model.Node;
import aiprog.model.Node.Status;
import aiprog.model.Point;

public abstract class AStar {
	protected ArrayList<Node> openList;
	protected ArrayList<Node> closedList;
	protected Node currentNode;
	protected Point endPoint;
	public AStar(Node startNode, Point endPoint){
		this.currentNode = startNode;
		this.endPoint = endPoint;
	}
	protected void search(){
		closedList = new ArrayList<Node>(); //Visited nodes
		boolean victory = false;
		setHeuristic(currentNode);
		closedList.add(currentNode); //ingen vits og ha startnoden i openlist....
		while(!victory){
			for(int i = 0; i < currentNode.getChildren().size(); i++){
				Node midNode = currentNode.getChildren().get(i);
				//Remember to move obstacle check to Node
				if(!openList.contains(midNode) && !closedList.contains(midNode) && midNode.status != Status.Visited && midNode.status != Status.Obstacle){
					midNode.parent = currentNode;
					setHeuristic(midNode);
	
					addToOpenList(midNode);
					//board.steps++;
				}
			}
			openList.remove(currentNode);
			closedList.add(currentNode);
			currentNode.setStatus(Status.Visited);
			currentNode = getBestOpenList();
			currentNode.setStatus(Status.Visiting);
			updateGui();
			
			if(currentNode.pos.x == endPoint.x && currentNode.pos.y == endPoint.y){
				victory = true;
				System.out.println("Goal");
				//System.out.println("Steps: " + board.steps);
				//pathLength();
			}
		}
	}
	protected void addToOpenList(Node node){
		openList.add(node);
		Collections.sort(openList, new Comparator<Node>(){
		     public int compare(Node o1, Node o2){
		    	 return o1.heuristic - o2.heuristic;
		     }
		});
	}
	protected Node getBestOpenList(){

		if(openList.isEmpty()){
			return null;
		}else{
			return openList.get(0);
		}
	}
	protected abstract void setHeuristic(Node node);
	protected abstract void updateGui();
}
