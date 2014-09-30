package aiprog.gui;

import java.awt.Color;
import java.util.ArrayList;

import aiprog.model.CSPNode;
import aiprog.model.GACCSPNode;
import aiprog.model.VCPNode;

public class GraphGraphics extends Graphics{
	private VCPNode[][] cnArray;
	public GraphGraphics(GACCSPNode node, int sizeX, int sizeY) {
		super(sizeX, sizeY);
		cnArray = new VCPNode[sizeX][sizeY];

		grid.setScale((100/(sizeX/2)) + 20);
		grid.setSpacing(grid.scale/2);
		fillcnArray(node);
		sortByColumn();
		sortByRow();
	}
	private void fillcnArray(GACCSPNode node){
		int currentX = 0;
		int currentY = 0;
		ArrayList<CSPNode> nodeList = node.getCSPList();
		for(int i = 0; i < nodeList.size(); i++){
			cnArray[currentX][currentY] = (VCPNode)nodeList.get(i);
			if(currentX == cnArray[0].length - 1){
				currentY++;
				currentX = 0;
			}else{
				currentX++;
			}
		}
	}
	public void setGraph(GACCSPNode node){
		for(int i = 0; i < cnArray.length; i++){
			for(int j = 0; j < cnArray[0].length; j++){
				VCPNode tempNode = cnArray[i][j];
				if(tempNode != null){
					if(tempNode.getColor() != null){
						//System.out.println(tempNode.getColor().getRGB());
						grid.setCellColorWithoutRepaint(i, j, tempNode.getColor());
					}else{
						grid.setCellColorWithoutRepaint(i, j, Color.WHITE);
					}
					tempNode.getPos().setX(i);
					tempNode.getPos().setY(j);
					grid.addText(new GridText(tempNode.getPos().toPoint(),Integer.toString(tempNode.getId())));

				}

			}
		}
		for(int i = 0; i < cnArray.length; i++){
			for(int j = 0; j < cnArray[0].length; j++){
				VCPNode tempNode = cnArray[i][j];
				if(tempNode != null){
					for(int k = 0; k < tempNode.children.size(); k++){
						grid.addLine(new Line(tempNode.getPos(),((VCPNode)tempNode.children.get(k)).getPos()));
					}
				}
			}
		}
		grid.repaint();
	}
	private void sortByColumn(){
		boolean flag = true;
		VCPNode temp;
		for(int i = 0; i < cnArray.length; i++){
			flag = true;
			while (flag)
			{
				flag = false;
				for(int j = 0; j < cnArray[0].length - 1; j++){
					if(cnArray[i][j+1] == null){
						break;
					}
					if(cnArray[i][j].getPos().getY() < cnArray[i][j+1].getPos().getY()){
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
		VCPNode temp;
		for(int i = 0; i < cnArray[0].length; i++){
			flag = true;
			while (flag){
				flag = false;
				for(int j = 0; j < cnArray.length - 1; j++ ){
					if(cnArray[j + 1][i] == null){
						break;
					}
					if(cnArray[j][i].getPos().getX() < cnArray[j + 1][i].getPos().getX()){
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
