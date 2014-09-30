package aiprog.gui;

import java.awt.Color;

import aiprog.model.Node;
import aiprog.navigation.Board;

public class BoardGraphics extends Graphics{

	Board board;
	public BoardGraphics(Board board){
		super(board.size.x,board.size.y);
		//System.out.println("Board: " + board.size.x + " BoardY: " +board.size.y);
		this.board = board;
		
	}
	public void setBoard(Board board){
		grid.clearColor(Color.RED); //Delete old tail
        for(int i = 0; i < board.size.x; i++)
        {
        	for(int j = board.size.y; j > 0; j--)
        	{
        		if(board.boardArray[i][board.size.y - j].status == Node.Status.Obstacle){
        			grid.setCellColorWithoutRepaint(i, j -1, Color.BLACK);
        		}
        		else if(board.boardArray[i][board.size.y - j].status == Node.Status.Visiting){
        			grid.setCellColorWithoutRepaint(i, j -1, Color.RED);
        		}else if(board.boardArray[i][board.size.y - j].pos.x == board.endPos.x && board.boardArray[i][board.size.y - j].pos.y == board.endPos.y){
        			grid.setCellColorWithoutRepaint(i, j -1, Color.WHITE);
        		}
        	}
        }
        grid.repaint();
	}
}
