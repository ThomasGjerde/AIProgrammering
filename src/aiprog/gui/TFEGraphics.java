package aiprog.gui;

import java.awt.Color;
import java.util.ArrayList;

import aiprog.model.Direction;
import aiprog.model.Point;
import aiprog.twentyfortyeigth.TfeBoard;

public class TFEGraphics extends Graphics {
	TfeBoard board;
	Color[] colors;
	public TFEGraphics() {
		super(4,4);
		grid.setScale(100);
		colors = new Color[] {
				new Color(0xeee4da),
				new Color(0xede0c8),
				new Color(0xf2b179),
				new Color(0xf59563),
				new Color(0xf67c5f),
				new Color(0xf65e3b),
				new Color(0xedcf72),
				new Color(0xedcc61),
				new Color(0xedc850),
				new Color(0xedc53f),
				new Color(0xedc22e),
				new Color(0xEEE4DA),
				new Color(0xEEE4DA),
				new Color(0xEEE4DA)};
		grid.setShowGrid(true);
		grid.repaint();
		// TODO Auto-generated constructor stub
	}
	public void setBoard(TfeBoard board){
		this.board = board.cloneBoard();
		grid.texts.clear();
		grid.clearAllColors();
		for(int i = 0; i < board.getBoard().length; i++){
			for(int j = 0; j < board.getBoard()[0].length; j++){
				if(board.getBoard()[i][j] != 0){
					grid.setCellColorWithoutRepaint(j, i, colors[((int)(Math.log(board.getBoard()[i][j]) / Math.log(2)))-1]);
					grid.addText(new GridText(new Point(j,i),Integer.toString(board.getBoard()[i][j]),-25));
				}
				
			}
		}
		grid.repaint();
	}
	public void animateSetBoard(TfeBoard board, Direction dir){
		ArrayList<Point> movedTiles = getMovedTiles(board);
		for(int i = 0; i < grid.scale; i++){
			grid.transMoveTexts(movedTiles, dir);
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		setBoard(board);
	}
	private ArrayList<Point> getMovedTiles(TfeBoard newBoard){
		ArrayList<Point> movedTiles = new ArrayList<Point>();
		int[][] oldArray = board.getBoard();
		int[][] newArray = newBoard.getBoard();
		for(int i = 0; i < oldArray.length; i++){
			for(int j = 0; j < oldArray[0].length; j++){
				if(oldArray[i][j] != newArray[i][j] && oldArray[i][j]*2 != newArray[i][j]){
					movedTiles.add(new Point(j,i));
				}
			}
		}
		return movedTiles;
	}
	
}
