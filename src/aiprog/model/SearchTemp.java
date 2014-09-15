package aiprog.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;

import aiprog.gui.Graphics;
import aiprog.model.Board;
import aiprog.model.Node.Status;

public class SearchTemp {
	
	Board board;
	Graphics graph;
	ArrayList<Node> drawArray;
	ArrayList<Node> openList; //til aStar
	
	public SearchTemp(Board board, Graphics graph){
		this.board = board;
		this.graph = graph;
		drawArray = new ArrayList<Node>();
		openList = new ArrayList<Node>();
	}
	
	public void dfs(Node node){
		board.steps++;
		if(node == null || board.complete){
			return;
		}
		if(board.isEndNode(node)){
			System.out.println("Goal");
			System.out.println("Steps: " + board.steps);
			board.complete = true;
			pathLength();
		}
		node.setStatus(Status.Visiting);
		graph.setBoard(board);
		
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		for(Node n: node.getChildren()){
			if(n.status == Status.Unvisited){
				dfs(n);
			}
		}
		node.setStatus(Status.Visited);
	}
	
	public void bfs(Node node){
		Queue<Node> queue = new LinkedList<Node>();
		
		if(node == null || board.complete){
			return;
		}
		node.setStatus(Status.Visited);
		queue.add(node);
		
		while(!queue.isEmpty()){
			Node r = queue.remove();
			if(board.isEndNode(r)){
				System.out.println("Goal");
				System.out.println("Steps: " + board.steps);
				pathLength();
				break;
			}
			
				while(r.getNextChild() != null){
					board.steps++;
					Node r2 = r.getNextChild();
					r2.setStatus(Status.Visiting);
					queue.add(r2);
					
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					for(int i = 0; i < board.sizeX; i++){
						for(int j = 0; j < board.sizeY; j++){
							if(board.boardArray[i][j].status == Status.Visiting){
								board.boardArray[i][j].status = Status.Visited;
							}
						}
					}
					while(r2.parent != null)
					{
						r2.setStatus(Status.Visiting);
						r2 = r2.parent;
					}
					graph.setBoard(board);
				}
				r.setStatus(Status.Visited);
		}
	}
	
	public void aStar(Node node){
		ArrayList<Node> closedList = new ArrayList<Node>(); //Visited nodes
		boolean victory = false;
		heuristic(node);
		closedList.add(node); //ingen vits og ha startnoden i openlist....
		while(!victory){
			for(int i = 0; i<node.getChildren().size(); i++){
				Node midNode = node.getChildren().get(i);
				if(!openList.contains(midNode) && !closedList.contains(midNode) && midNode.status != Status.Visited && midNode.status != Status.Obstacle){
					midNode.parent = node;
					heuristic(midNode);
	
					addToOpenList(midNode);
					board.steps++;
				}
			}
			openList.remove(node);
			closedList.add(node);
			node.setStatus(Status.Visited);
			node = getBestOpenList();
			node.setStatus(Status.Visiting);
			
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			for(int i = 0; i < board.sizeX; i++){
				for(int j = 0; j < board.sizeY; j++){
					if(board.boardArray[i][j].status == Status.Visiting){
						board.boardArray[i][j].status = Status.Visited;
					}
				}
			}			
			Node r2 = node;
			while(r2.parent != null)
			{
				r2.setStatus(Status.Visiting);
				r2 = r2.parent;
			}
			graph.setBoard(board);
			if(node.positionX == board.endX && node.positionY == board.endY){
				victory = true;
				System.out.println("Goal");
				System.out.println("Steps: " + board.steps);
				pathLength();
			}
		}
	}
	
	public void addToOpenList(Node node){
		openList.add(node);
		Collections.sort(openList, new Comparator<Node>(){
		     public int compare(Node o1, Node o2){
		    	 return o1.h - o2.h;
		     }
		});
	}
	
	public Node getBestOpenList(){

		if(openList.isEmpty()){
			return null;
		}else{
			return openList.get(0);
		}
	}
	
	public void pathLength(){
		int pathLength = 0;
		for(int x=0; x< board.sizeX; x++){
			for(int y=0; y<board.sizeY; y++)
				if(board.boardArray[x][y].status == Status.Visiting){
					pathLength++;
				}
		}
		System.out.println("PathLength: " + pathLength);
	}
	
	public void heuristic(Node node){
		//DistToFinish
		int nodeX = node.positionX;
		int nodeY = node.positionY;
		int endX = board.endX;
		int endY = board.endY;
		int distToFinish;
		int midX;
		int midY;
		
		//DistToFinish
		midX = Math.abs(nodeX - endX);
		midY = Math.abs(nodeY - endY);
		distToFinish = midY + midX;
		
		//DistFromStart
		node.h = distToFinish + node.getCostFromStart();
	}
	
}
