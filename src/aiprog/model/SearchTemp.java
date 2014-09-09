package aiprog.model;

import java.util.LinkedList;
import java.util.Queue;
import aiprog.model.Board;
import aiprog.model.Node.Status;

public class SearchTemp {
	
	Board board;
	public SearchTemp(){
		
	}
	
	public void dfs(Node node){
		if(node == null){
			return;
		}
		node.setStatus(Status.Visited);
		
		if(node.getNextChild(node) != null){
			if(board.endX == node.positionX && board.endY == node.positionY){
				
			}else{
				dfs(node.getNextChild(node));
			}
		}
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
			//if(r.getNextChild(r) != null){
				while(r.getNextChild(r) != null){
					queue.add(r.getNextChild(r));
					r.getNextChild(r).setStatus(Status.Visited);
					if(board.endX == r.positionX && board.endY == r.positionY){
						break;
					}
				}
			//}
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
	
	public void aStar(){
		
	}
	
}
