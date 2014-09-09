package aiprog.model;

import java.util.LinkedList;
import java.util.Queue;

import aiprog.model.Node.Status;

public class SearchTemp {
	
	
	public SearchTemp(){
		
	}
	
	public void dfs(Node node){
		if(node == null){
			return;
		}
		node.setStatus(Status.Visited);
		
		if(node.getNextChild(node) != null){
			dfs(node.getNextChild(node));
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
	
}
