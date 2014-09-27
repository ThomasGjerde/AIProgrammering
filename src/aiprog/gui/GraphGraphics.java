package aiprog.gui;

import java.util.ArrayList;

import aiprog.model.ColorNode;
import aiprog.model.GridText;
import aiprog.model.Line;
import aiprog.model.Point;
import aiprog.model.StateNode;

public class GraphGraphics extends Graphics{
	private int sizeX;
	private int sizeY;
	public GraphGraphics(int sizeX, int sizeY) {
		super(sizeX, sizeY);
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		grid.setScale(80);
		grid.setSpacing(40);
		// TODO Auto-generated constructor stub
	}
	public void setGraph(StateNode node){
		int currentX = 0;
		int currentY = 0;
		ArrayList<ColorNode> nodeList = node.getNodeList();
		for(int i = 0; i < nodeList.size(); i++){
			if(nodeList.get(i).getColor() != null){
				grid.setCellColorWithoutRepaint(currentX, currentY, nodeList.get(i).getColor());
				nodeList.get(i).pos.x = currentX;
				nodeList.get(i).pos.y = currentY;
				grid.addText(new GridText(nodeList.get(i).pos,Integer.toString(nodeList.get(i).id)));
				if(currentX == sizeX - 1){
					currentY++;
					currentX = 0;
				}else{
					currentX++;
				}
			}
			for(int j = 0; j < nodeList.get(i).getChildren().size(); j++){
				grid.addLine(new Line(nodeList.get(i).pos,nodeList.get(i).getChildren().get(j).pos));
			}
		}
		grid.repaint();
	}

}
