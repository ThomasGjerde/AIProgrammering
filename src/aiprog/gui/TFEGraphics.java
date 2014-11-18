package aiprog.gui;

import aiprog.model.Direction;
import aiprog.model.Point;
import aiprog.twentyfortyeigth.TfeBoard;

public class TFEGraphics extends Graphics {
	TfeBoard board;
	public TFEGraphics() {
		super(4,4);
		grid.setShowGrid(true);
		grid.repaint();
		// TODO Auto-generated constructor stub
	}
	public void setBoard(TfeBoard board){
		grid.texts.clear();
		for(int i = 0; i < board.getBoard().length; i++){
			for(int j = 0; j < board.getBoard()[0].length; j++){
				if(board.getBoard()[i][j] != 0){
					grid.addText(new GridText(new Point(j,i),Integer.toString(board.getBoard()[i][j])));
				}
				
			}
		}
		grid.repaint();
	}
	public void animateSetBoard(TfeBoard board, Direction dir){
		for(int i = 0; i < grid.scale; i++){
			grid.transMoveTexts(dir);
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		setBoard(board);
	}
	
}
