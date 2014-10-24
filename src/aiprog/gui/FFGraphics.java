package aiprog.gui;

import java.awt.Color;
import java.util.ArrayList;

import aiprog.flowfree.FFBoard;
import aiprog.model.FFNode;
import aiprog.model.FFStateNode;

public class FFGraphics extends Graphics{
	
	public FFGraphics(FFBoard board) {
		super(board.boardArray[0].length, board.boardArray.length);
		setBoard(board);
		// TODO Auto-generated constructor stub
	}
	public void setBoard(FFBoard board){
		for(int i = 0; i < board.boardArray[0].length; i++){
			for(int j = 0; j < board.boardArray.length; j++){
				FFNode tempNode = board.boardArray[i][j];
				if(tempNode.getColor() != null){
					//System.out.println(tempNode.getColor().getRGB());
					grid.setCellColorWithoutRepaint(i, j, tempNode.getColor());
				}else{
					grid.setCellColorWithoutRepaint(i, j, Color.WHITE);
				}
				
			}
		}
	}
	public void setState(FFStateNode node){
		ArrayList<FFNode> nodeList = node.nodes;
		for(int i = 0; i < nodeList.size(); i++){
			FFNode tempNode = nodeList.get(i);
			if(tempNode.getColor() != null){
				grid.setCellColorWithoutRepaint(tempNode.pos.x, tempNode.pos.y, tempNode.getColor());
			}else{
				grid.setCellColorWithoutRepaint(tempNode.pos.x, tempNode.pos.y, Color.WHITE);
			}
		}
	}

}
