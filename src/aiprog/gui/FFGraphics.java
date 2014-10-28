package aiprog.gui;

import java.awt.Color;
import java.util.ArrayList;

import aiprog.flowfree.FFBoard;
import aiprog.model.FFNode;
import aiprog.model.FFStateNode;
import aiprog.model.Point;

public class FFGraphics extends Graphics{
	
	public FFGraphics(FFBoard board) {
		super(board.boardArray[0].length, board.boardArray.length);
		setBoard(board);
	}
	public FFGraphics(int x, int y) {
		super(x,y);
	}
	public void setBoard(FFBoard board){
		for(int i = 0; i < board.boardArray[0].length; i++){
			for(int j = 0; j < board.boardArray.length; j++){
				FFNode tempNode = board.boardArray[i][j];
				if(tempNode.getColor() != null){
					grid.setCellColorWithoutRepaint(i, j, tempNode.getColor());
				}else{
					grid.setCellColorWithoutRepaint(i, j, Color.WHITE);
				}
				
			}
		}
		grid.repaint();
	}
	public void setState(FFStateNode node){
		grid.texts.clear();
		ArrayList<FFNode> nodeList = node.nodes;
		for(int i = 0; i < nodeList.size(); i++){
			FFNode tempNode = nodeList.get(i);
			if(tempNode.getColor() != null){
				grid.setCellColorWithoutRepaint(tempNode.pos.x, tempNode.pos.y, tempNode.getColor());
			}else{
				grid.setCellColorWithoutRepaint(tempNode.pos.x, tempNode.pos.y, Color.WHITE);
			}
			if(tempNode.isHead()){
				grid.addText(new GridText(tempNode.pos, "H"));
			}
			if(tempNode.isEndPoint()){
				grid.addText(new GridText(tempNode.pos, "E"));
			}
			grid.addText(new GridText(tempNode.pos, ""+tempNode.getDomain().size()));
		}
		grid.repaint();
	}

}
