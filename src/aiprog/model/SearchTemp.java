package aiprog.model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import aiprog.gui.Graphics;
import aiprog.model.Board;
import aiprog.model.Node.Status;

public class SearchTemp {
	
	Board board1;
	Graphics graph1;
	public SearchTemp(Board board, Graphics graph){
		board1 = board;
		graph1 = graph;
	}
	
	public void dfs(Node node){
		if(node == null){
			return;
		}
		node.setStatus(Status.Visited);
		
		if(node.getNextChild(node) != null){
			if(board1.endX == node.positionX && board1.endY == node.positionY){
				
			}else{
				dfs(node.getNextChild(node));
			}
		}
		System.out.println("rposX " + node.positionX + " rposY " + node.positionY);
		/*
		while(node.getChildren().size()>0){
			if(node.getChildren().get(0).status == Status.Unvisited){
				dfs(node.getChildren().get(0));
			}
		}*/
	}
	
	public void bfs(Node node){
		Queue<Node> queue = new LinkedList<Node>();
		
		if(node == null){
			return;
		}
		
		node.setStatus(Status.Visited);
		queue.add(node);
		
		while(!queue.isEmpty()){
			Node r = queue.remove();
			
			
				while(r.getNextChild(r) != null){
					queue.add(r.getNextChild(r));
					
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					
					
					r.setStatus(Status.Visiting);
					graph1.setBoard(board1);
					System.out.println("rposX " + r.positionX + " rposY " + r.positionY);
					r.getNextChild(r).setStatus(Status.Visited);
					if(board1.endX == r.positionX && board1.endY == r.positionY){
						System.out.println("ferdig vistnok");
					}
				}
		}
		/*
		while(!queue.isEmpty())
        {
            //removes from front of queue
            Node r = queue.remove(); 

            //Visit child first before grandchild
            for(Node n: r.getChild())
            {
                if(n.state == State.Unvisited)
                {
                    queue.add(n);
                    n.state = State.Visited;
                }
            }
        }
        */
		
	}
	
	public void aStar(Node node){
		
		ArrayList<Node> openList = new ArrayList<Node>(); //noder vi ikke har vært i
		ArrayList<Node> closedList = new ArrayList<Node>(); //noder vi har vært i
		boolean victory = false;
		openList.add(node);
		heuristic(node);
		Node midNode = node;
		while(!openList.isEmpty()){
			for(int i = 0; i<midNode.getChildren().size(); i++){
				heuristic(midNode.getChildren().get(i));
				if(midNode.getChildren().get(i).h <= midNode.h){
					openList.add(midNode.getChildren().get(i));
				}else{
					closedList.add(midNode.getChildren().get(i));
				}
			}
			openList.remove(midNode);
			midNode = openList.get(0);
		}
		
		for(int j = 0;j<openList.size(); j++){
			System.out.println("Node " + j + ": X: " + openList.get(j).positionX + " Y:" + openList.get(j).positionY);
		}
		//while(node.positionX != board.endX && node.positionY != board.endY){		
		//}
	}
	
	public int heuristic(Node node){
		
		int nodeX = Math.abs(node.positionX);
		int nodeY = Math.abs(node.positionY);
		int endX = Math.abs(board1.endX);
		int endY = Math.abs(board1.endY);
		int heuristic = 0;
		int midX = 0;
		int midY = 0;
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
		heuristic = midY + midX;
		
		node.h = heuristic;
		return heuristic;
		
	}
	
}
