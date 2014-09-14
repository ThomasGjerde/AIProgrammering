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
	ArrayList<Node> drawArray;
	ArrayList<Node> openList; //til aStar
	
	public SearchTemp(Board board, Graphics graph){
		board1 = board;
		graph1 = graph;
		drawArray = new ArrayList<Node>();
		openList = new ArrayList<Node>();
	}
	
	public void dfs(Node node){
		if(node == null || board1.complete){
			return;
		}
		if(board1.isEndNode(node)){
			System.out.println("Goal");
			board1.complete = true;
		}
		drawArray.add(node);
		
		if(drawArray.size() > 10){
			drawArray.get(drawArray.size()-1).setStatus(Status.Visited);
			if(!drawArray.isEmpty()){
				drawArray.remove(0);
			}
		}
		
		
		
		node.setStatus(Status.Visiting);
		//drawArray.add(node);
		graph1.setBoard(board1);
		
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
		/*
		if(node.getNextChild(node) != null){
			if(board1.endX == node.positionX && board1.endY == node.positionY){
				
			}else{
				dfs(node.getNextChild(node));
			}
		}*/
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
		
		if(node == null || board1.complete){
			return;
		}
		node.setStatus(Status.Visited);
		queue.add(node);
		
		while(!queue.isEmpty()){
			Node r = queue.remove();
			if(board1.isEndNode(r)){
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
					for(int i = 0; i < board1.sizeX; i++){
						for(int j = 0; j < board1.sizeY; j++){
							if(board1.boardArray[i][j].status == Status.Visiting){
								board1.boardArray[i][j].status = Status.Visited;
							}
						}
					}
					while(r2.parent != null)
					{
						r2.setStatus(Status.Visiting);
						r2 = r2.parent;
					}
					graph1.setBoard(board1);
					System.out.println("rposX " + r.positionX + " rposY " + r.positionY);
					//r.getNextChild(r).setStatus(Status.Visited);
					if(board1.endX == r.positionX && board1.endY == r.positionY){
						System.out.println("ferdig vistnok");
					}
				}
				r.setStatus(Status.Visited);
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
		
		/*
		Startnode: Legg til alle barna til openlist, og gi dem h i samme slengen
		sorter h slik at laveste h er først
		gå til laveste h, og legg til barna rundt denne nye noden i openlist (sorteres ved input). Legg startnode i closedlist.
		parent burde lagres på en eller annen måte (ikke helt sikker på akkurat det her atm)
		
		*/
		ArrayList<Node> closedList = new ArrayList<Node>(); //noder vi har vært i
		boolean victory = false; //ikke sikker på om denne skal brukes
		heuristic(node); //håper denne kan greie 0 + distance to end.
		System.out.println("NodeH: " + node.h);
		openList.add(node); //ingen vits og ha startnoden i openlist....
		
		//en for/while der jeg legger til barn?
		//og jeg må legge node.h fær sortereinga blir gjort, siden h ikke er en del a constructor i node.
		
		
		//Har ikke tenkt det her skikkelig igjennom
		//skal legge til barna her, mulig det blir 2 for loops:(
		
		//her er det et eller annet alvorlig galt....
		//faen, kanskje det her er en uendelig loop?
		//hem, nei alt skal jo ende opp i closedList tilslutt, og da blir de jo ikke adda 
		//må være denne
		//while(!openList.isEmpty()){
		while(!victory){
			for(int i = 0; i<node.getChildren().size(); i++){
				Node midNode = node.getChildren().get(i);
				System.out.println("NodeCX: " + midNode.positionX + " NodeCY: " + midNode.positionY);
				if(!openList.contains(midNode) || !closedList.contains(midNode)){
					heuristic(midNode);
					addToOpenList(midNode);
					System.out.println("NodeX: " + openList.get(i).positionX + " NodeY: " + openList.get(i).positionY);
					System.out.println("H" + midNode.h);
				}
			}
			openList.remove(node);
			closedList.add(node);
			node.setStatus(Status.Visited);
			getBestOpenList().parent = node; //det her ser skada ut, men vel, sparer en mid node da:P
			node = getBestOpenList();
			node.setStatus(Status.Visiting);
			/*
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
			graph1.setBoard(board1);
			if(node.positionX == board1.endX && node.positionY == board1.endY){
				victory = true;
			}
		}
		
		//tror kanskje det her er greia, men føler at jeg mangler et eller annet
		//mangler gui styr
		//og status setting, bruker vel egentlig open og closedList som statuser, kanskje ikke openList
		//fK! har glemt victory state
		
		
		/*
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
		*/
		for(int j = 0;j<openList.size(); j++){
			System.out.println("Node " + j + ": X: " + openList.get(j).positionX + " Y:" + openList.get(j).positionY);
		}
		//while(node.positionX != board.endX && node.positionY != board.endY){		
		//}
	}
	
	//ikke testet, freestyle forsøk på noe ala insert sort, tror egentlig det er bubblesort med iterative adding da....
	public void addToOpenList(Node node){
		if(openList.isEmpty()){
			openList.add(node);
		}else if(openList.size() < 2){ //ikke sikker på om det skal være <1 eller <2 her
			if(openList.get(0).h < node.h){
				openList.add(node);
			}else{
				openList.add(openList.size()-1, node); //ho hey får håpe dette går
			}
		}else{
			//okey wtf, det er denne som suger ja!
			for(int i=0; i<openList.size(); i++){
				//System.out.println(openList.size() + " i: " + i);
				int a = openList.get(i).h;
				int b = openList.get(i+1).h;
				//I HAVE SPOTTET THE CULPRIT!
				if(node.h == a || node.h == b){
					openList.add(i, node);
					System.out.println("hei her er jeg");
				}else if(node.h > a && node.h < b){
					openList.add(i, node);
					System.out.println("hei her er jeg1");
					System.out.println("nodeX: " + node.positionX + " nodeY: " + node.positionY);
				}else{
					openList.add(openList.size(), node);
				}
			}
			System.out.println("Jeg er ute");
		}
	}
	
	public Node getBestOpenList(){
		if(openList.isEmpty()){
			return null;
		}else{
			return openList.get(0);
		}
	}
	/*
	public void removeFromOpenList(Node node){
		openList.remove(node);
	}
	*/
	
	public void heuristic(Node node){
		//vet ikke om dette funker
		//utesta
		
		
		//DistToFinish
		int nodeX = node.positionX;
		int nodeY = node.positionY;
		int endX = board1.endX;
		int endY = board1.endY;
		int startX = board1.startX;
		int startY = board1.startY;
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
		node.h = distFromStart + distToFinish;
		
	}
	
}
