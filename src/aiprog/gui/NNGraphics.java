package aiprog.gui;
import java.awt.Color;

import aiprog.nonogram.NNBoard;

public class NNGraphics extends Graphics{

	public NNGraphics(NNBoard board) {
		super(board.boardArray[0].length, board.boardArray.length);
		setBoard(board);
		// TODO Auto-generated constructor stub
	}
	public void setBoard(NNBoard board){
		for(int i = 0; i < board.boardArray[0].length; i++){
			for(int j = 0; j < board.boardArray.length; j++){
				if(board.boardArray[i][j] == true){
					grid.setCellColorWithoutRepaint(i, j, Color.BLACK);
				}else{
					grid.setCellColorWithoutRepaint(i, j, Color.WHITE);
				}
				
			}
		}
		grid.repaint();
	}

}
