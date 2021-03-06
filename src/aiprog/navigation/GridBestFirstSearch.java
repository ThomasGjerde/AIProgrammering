package aiprog.navigation;

import aiprog.gui.BoardGraphics;
import aiprog.model.Node;
import aiprog.model.Node.Status;
import aiprog.model.Point;
import aiprog.search.BestFirstSearch;

public class GridBestFirstSearch extends BestFirstSearch{
	Board board;
	BoardGraphics graphics;
	public GridBestFirstSearch(Node startNode, Point endPoint, Board board) {
		super(startNode, endPoint);
		this.board = board;
		graphics = new BoardGraphics(board);
	}

	@Override
	protected void updateGui() {
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
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

	@Override
	protected void processCurrentNode() {
		
	}

}
