package aiprog.gui;

import aiprog.model.Board;
import aiprog.model.Node;

public class BoardGraphics extends Graphics{

	Board board;
	public BoardGraphics(Board board){
		super();
		this.board = board;
		
	}
	public void setBoard(Board board){
		((GridCanvas)grid).clearRed(); //Delete old tail
        for(int i = 0; i < board.size.x; i++)
        {
        	for(int j = board.size.y; j > 0; j--)
        	{
        		if(board.boardArray[i][board.size.y - j].status == Node.Status.Obstacle){
        			((GridCanvas)grid).fillCellBlack(i, j);
        		}
        		else if(board.boardArray[i][board.size.y - j].status == Node.Status.Visiting){
        			((GridCanvas)grid).fillCellRed(i, j);
        		}else if(board.boardArray[i][board.size.y - j].pos.x == board.endPos.x && board.boardArray[i][board.size.y - j].pos.y == board.endPos.y){
        			((GridCanvas)grid).fillCellWhite(i, j);
        		}
        	}
        }
	}
}
