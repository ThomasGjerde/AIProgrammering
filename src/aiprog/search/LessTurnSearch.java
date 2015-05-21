package aiprog.search;

import aiprog.gui.BoardGraphics;
import aiprog.model.DirectedNavNode;
import aiprog.model.Node;
import aiprog.model.Node.Status;
import aiprog.model.Point;
import aiprog.navigation.Board;
import aiprog.navigation.DirectedBoard;

public class LessTurnSearch extends AStarWithEndPoint
{
	Board board;
	BoardGraphics graphics;
	public LessTurnSearch(Node startNode, Point endPoint, Board board)
	{
		super(startNode, endPoint);
		this.board = board;
		graphics = new BoardGraphics(board);
		graphics.setBoard(board);
	}

	@Override
	protected void processCurrentNode()
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void setHeuristic(Node node)
	{
		node.heuristic = (((DirectedNavNode)node).getNumberOfTurns()*-2) - node.getCostFromStart();
	}

	@Override
	protected void updateGui()
	{
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(int i = 0; i < board.size.x; i++){
			for(int j = 0; j < board.size.y; j++){
				if(board.boardArray[i][j].status == Status.Visiting){
					board.boardArray[i][j].status = Status.Visited;
				}
			}
		}
		Node r2 = currentNode;
		while(r2.parent != null)
		{
			r2.setStatus(Status.Visiting);
			r2 = r2.parent;
		}
		graphics.setBoard(board);
		
	}
	

}
