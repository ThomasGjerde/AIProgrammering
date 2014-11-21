package aiprog.gui;

import java.awt.Color;

import aiprog.model.Direction;
import aiprog.model.Point;
import aiprog.twentyfortyeigth.TfeBoard;

public class TFEGraphics extends Graphics {
	TfeBoard board;
	Color[] colors;
	public TFEGraphics() {
		super(4,4);
		grid.setScale(100);
		colors = new Color[] {new Color(0xEEE4DA),
				new Color(0xEAE0C8),
				new Color(0xF59563),
				new Color(0x3399ff),
				new Color(0xffa333),
				new Color(0xcef030),
				new Color(0xE8D8CE),
				new Color(0x990303),
				new Color(0x6BA5DE),
				new Color(0xDCAD60),
				new Color(0xB60022),
				new Color(0xEEE4DA)};
		grid.setShowGrid(true);
		grid.repaint();
		// TODO Auto-generated constructor stub
	}
	public void setBoard(TfeBoard board){
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
		for(int i = 0; i < grid.scale; i++){
			grid.transMoveTexts(dir);
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		setBoard(board);
	}
	
}
