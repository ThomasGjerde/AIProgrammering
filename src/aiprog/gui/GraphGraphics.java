package aiprog.gui;

import java.awt.Color;
import java.util.ArrayList;

import aiprog.model.OldColorNode;
import aiprog.model.GridText;
import aiprog.model.Line;
import aiprog.model.StateNode;

public class GraphGraphics extends Graphics{
	private OldColorNode[][] cnArray;
	public GraphGraphics(int sizeX, int sizeY) {
		super(sizeX, sizeY);
		cnArray = new OldColorNode[sizeX][sizeY];
		
		grid.setScale((100/(sizeX/2)) + 20);
		grid.setSpacing(grid.scale/2);
	}
	private void fillcnArray(StateNode node){
		int currentX = 0;
		int currentY = 0;
		ArrayList<OldColorNode> nodeList = node.getNodeList();
		for(int i = 0; i < nodeList.size(); i++){
			cnArray[currentX][currentY] = nodeList.get(i);
			if(currentX == cnArray[0].length - 1){
				currentY++;
				currentX = 0;
			}else{
				currentX++;
			}
		}
	}
	public void setGraph(StateNode node){
		fillcnArray(node);
		sortByColumn();
		sortByRow();
		for(int i = 0; i < cnArray.length; i++){
			for(int j = 0; j < cnArray[0].length; j++){
				OldColorNode tempNode = cnArray[i][j];
				if(tempNode != null){
					if(tempNode.getColor() != null){
						grid.setCellColorWithoutRepaint(i, j, tempNode.getColor());
					}else{
						grid.setCellColorWithoutRepaint(i, j, Color.WHITE);
					}
					tempNode.pos.x = i;
					tempNode.pos.y = j;
					grid.addText(new GridText(tempNode.pos,Integer.toString(tempNode.id)));
					for(int k = 0; k < tempNode.children.size(); k++){
						grid.addLine(new Line(tempNode.pos,tempNode.children.get(k).pos));
					}
				}

			}
		}
		grid.repaint();
	}
	private void sortByColumn(){
		boolean flag = true;
		OldColorNode temp;
		for(int i = 0; i < cnArray.length; i++){
			flag = true;
			while (flag)
			{
				flag = false;
				for(int j = 0; j < cnArray[0].length - 1; j++){
					if(cnArray[i][j+1] == null){
						break;
					}
					if(cnArray[i][j].pos.getDoubleY() < cnArray[i][j+1].pos.getDoubleY()){
						temp = cnArray[i][j];
						cnArray[i][j] = cnArray[i][j+1];
						cnArray[i][j+1] = temp;
						flag = true;
					} 
				} 
			} 
		}
	}
	private void sortByRow(){
		boolean flag = true;
		OldColorNode temp;
		for(int i = 0; i < cnArray[0].length; i++){
			flag = true;
			while (flag){
				flag = false;
				for(int j = 0; j < cnArray.length - 1; j++ ){
					if(cnArray[j + 1][i] == null){
						break;
					}
					if(cnArray[j][i].pos.getDoubleX() < cnArray[j + 1][i].pos.getDoubleX()){
						temp = cnArray[j][i];
						cnArray[j][i] = cnArray[j + 1][i];
						cnArray[j + 1][i] = temp;
						flag = true;
					} 
				} 
			} 
		}
	}
}
