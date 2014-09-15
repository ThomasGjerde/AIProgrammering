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
		if(node == null || board.complete){
			return;
		}
		if(board.isEndNode(node)){
			System.out.println("Goal");
			board.complete = true;
		}
		drawArray.add(node);
		
		if(drawArray.size() > 10){
			drawArray.get(drawArray.size()-1).setStatus(Status.Visited);
			if(!drawArray.isEmpty()){
				drawArray.remove(0);
			}
		}
		node.setStatus(Status.Visiting);
		graph.setBoard(board);
		
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
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
				break;
			}
			
				while(r.getNextChild() != null){
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
		System.out.println("NodeH: " + node.h);
		closedList.add(node); //ingen vits og ha startnoden i openlist....
		while(!victory){
			for(int i = 0; i<node.getChildren().size(); i++){
				Node midNode = node.getChildren().get(i);
				if(!openList.contains(midNode) && !closedList.contains(midNode) && midNode.status != Status.Visited && midNode.status != Status.Obstacle){
					heuristic(midNode);
					midNode.parent = node;
					addToOpenList(midNode);
				}
			}
			
			openList.remove(node);
			System.out.println("OpenListSize: " + openList.size());
			closedList.add(node);
			node.setStatus(Status.Visited);
			node = getBestOpenList();
			node.setStatus(Status.Visiting);
			
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//System.out.println("openList: " + openList.size());
			for(int i = 0; i < board.sizeX; i++){
				for(int j = 0; j < board.sizeY; j++){
					if(board.boardArray[i][j].status == Status.Visiting){
						System.out.println("(" + i + "," + j + ") Visiting");
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
	
	public void heuristic(Node node){
		//vet ikke om dette funker
		//utesta
		
		
		//DistToFinish
		int nodeX = node.positionX;
		int nodeY = node.positionY;
		int endX = board.endX;
		int endY = board.endY;
		int startX = board.startX;
		int startY = board.startY;
		int distToFinish;
		int distFromStart;
		int midX;
		int midY;
		
		//DistToFinish
		if(nodeX > endX){
			midX = nodeX - endX;
		}else{
			midX = endX - nodeX;
		}
		if(nodeY > endY){
			midY = nodeY - endY;
		}else{
			midY = endY - nodeY;
		}
		distToFinish = midY + midX;
		
		//DistFromStart
		/*
		if(startX > nodeX){
			midX = startX - nodeX;
		}else{
			midX = nodeX - startX;
		}
		if(startY > nodeY){
			midY = startY - nodeY;
		}else{
			midY = nodeY - startY;
		}
		distFromStart = midY + midX;
		*/
		//node.h = distFromStart + distToFinish;
		node.h = distToFinish;
	}
	
}
