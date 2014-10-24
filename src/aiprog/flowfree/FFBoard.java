package aiprog.flowfree;

import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;
import aiprog.model.FFNode;
import aiprog.model.FFStateNode;
import aiprog.model.NavNode;
import aiprog.model.Point;
import aiprog.utility.IOUtils;

public class FFBoard {
	int numEndpoints;
	public FFNode[][] boardArray;
	ArrayList<FFNode> endPoints = new ArrayList<FFNode>();
	public FFBoard(String path) throws IOException{
		ArrayList<String> input = IOUtils.getInputFromFile(path);
		ArrayList<Integer> paramLine = parseLine(input.get(0));
		boardArray = new FFNode[paramLine.get(0)][paramLine.get(0)];
		numEndpoints = paramLine.get(1);
		
		
		for(int i = 0; i < boardArray.length; i++){
			for(int j = 0; j < boardArray[0].length; j++){
				boardArray[i][j] = new FFNode(new Point(i,j),numEndpoints);
			}
		}
		
		ArrayList<Color> availColors = new ArrayList<Color>(boardArray[0][0].getDomain());
		System.out.println(availColors.size());
		for(int i = 1; i < input.size(); i++){
			ArrayList<Integer> lineArray = parseLine(input.get(i));
			boardArray[lineArray.get(1)][lineArray.get(2)].setColor(availColors.get(lineArray.get(0)),null);
			boardArray[lineArray.get(1)][lineArray.get(2)].setEndPointStatus(true);
			endPoints.add(boardArray[lineArray.get(1)][lineArray.get(2)]);
			
			boardArray[lineArray.get(3)][lineArray.get(4)].setColor(availColors.get(lineArray.get(0)),null);
			boardArray[lineArray.get(3)][lineArray.get(4)].setEndPointStatus(true);
			endPoints.add(boardArray[lineArray.get(3)][lineArray.get(4)]);
		}
		setChildrenForAllNodes();
		
	}
	private void setChildrenForAllNodes(){
		for(int i = 0; i < boardArray.length; i++){
			for(int j = 0; j < boardArray[0].length; j++){
				setChildren(boardArray[i][j]);
			}
		}
	}
	private void setChildren(NavNode node){
		if(node.pos.x > 0){
			node.addChild(boardArray[node.pos.x -1][node.pos.y]);
		}
		if(node.pos.y < boardArray[0].length - 1){
			node.addChild(boardArray[node.pos.x][node.pos.y + 1]);
		}
		if(node.pos.x < boardArray.length - 1){
			node.addChild(boardArray[node.pos.x + 1][node.pos.y]);
		}
		if(node.pos.y > 0){
			node.addChild(boardArray[node.pos.x][node.pos.y - 1]);
		}
	}
	
	public FFStateNode createInitState(){
		ArrayList<FFNode> nodeArray = new ArrayList<FFNode>();
		for(int i=0; i<boardArray.length; i++){
			for(int j=0; j<boardArray[0].length; j++){
				nodeArray.add(boardArray[i][j]);
			}
		}
		FFStateNode firstState = new FFStateNode(nodeArray,endPoints);
		return firstState;
	}
	
	private ArrayList<Integer> parseLine(String line){
		String[] splitArray = line.split(" ");
		ArrayList<Integer> retArray = new ArrayList<Integer>();
		for(int i = 0; i < splitArray.length; i++){
			retArray.add(Integer.parseInt(splitArray[i]));
		}
		return retArray;
	}
}
