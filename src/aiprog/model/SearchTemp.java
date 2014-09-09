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
			
			for(Node n: r.getChildren()){
				
			}
		}
		
	}
	
}
