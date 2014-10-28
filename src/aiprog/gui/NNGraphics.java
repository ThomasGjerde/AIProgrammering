package aiprog.gui;
import java.awt.Color;

import aiprog.model.NNStateNode;
import aiprog.nonogram.NNBoard;

public class NNGraphics extends Graphics{

	public NNGraphics(NNBoard board) {
		super(board.boardArray[0].length, board.boardArray.length);
		//setBoard(board);
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
	public void setState(NNStateNode state){
		for(int i = 0; i < state.rowDomains.size(); i++){
			if(state.rowDomains.get(i).getValue() != null){
				for(int j = 0; j < state.rowDomains.get(i).getValue().size(); j++){
					if(state.rowDomains.get(i).getValue().get(j) == true){
						grid.setCellColorWithoutRepaint(j, i, Color.BLACK);
					}else{
						grid.setCellColorWithoutRepaint(j, i, Color.WHITE);
					}
				}	
			}
			
		}
		
		for(int i = 0; i < state.colDomains.size(); i++){
			if(state.colDomains.get(i).getValue() != null){
				for(int j = 0; j < state.colDomains.get(i).getValue().size(); j++){
					if(state.colDomains.get(i).getValue().get(j) == true){
						grid.setCellColorWithoutRepaint(i, j, Color.BLACK);
					}else{
						grid.setCellColorWithoutRepaint(i, j, Color.WHITE);
					}
				}
			}
		}
		
		grid.repaint();
	}

}
