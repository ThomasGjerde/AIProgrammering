package aiprog.search;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import aiprog.model.Node;
import aiprog.model.Node.Status;
import aiprog.model.Point;

public abstract class AStar {
	protected ArrayList<Node> openList = new ArrayList<Node>();
	protected ArrayList<Node> closedList = new ArrayList<Node>();
	protected Node currentNode;
	protected Point endPoint = new Point();
	private int steps = 0;
	private int pathLength = 0;
	public AStar(Node startNode, Point endPoint){
		this.currentNode = startNode;
		this.endPoint = endPoint;
	}
	public void search(){
		boolean victory = false;
		setHeuristic(currentNode);
		closedList.add(currentNode);
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
			steps++;
			updateGui();
			
			if(currentNode.pos.x == endPoint.x && currentNode.pos.y == endPoint.y){
				victory = true;
				System.out.println("Goal");
				calculatePathLenght();
			}
		}
	}
	private void calculatePathLenght(){
		if(currentNode != null){
			Node tempNode = currentNode;
			while(tempNode != null){
				tempNode = tempNode.parent;
				pathLength++;
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
