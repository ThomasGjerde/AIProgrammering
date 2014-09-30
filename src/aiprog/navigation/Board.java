package aiprog.navigation;

import java.io.*;
import java.util.ArrayList;

import aiprog.model.NavNode;
import aiprog.model.Node;
import aiprog.model.Point;
import aiprog.model.Node.Status;
import aiprog.utility.IOUtils;

public class Board {
	public Point size = new Point();
	public Point startPos = new Point();
	public Point endPos = new Point();
	public int steps = 0;
	public NavNode[][] boardArray;
	public boolean complete = false;
	
	public Board(String path) throws IOException{
		ArrayList<String> input = IOUtils.getInputFromFile(path);
		if(input.size() >= 2){
			setBoardSize(parseLine(input.get(0)));
			setStartAndEnd(parseLine(input.get(1)));
			generateBoard();
			for(int i = 2; i < input.size(); i++){
				generateObstacle(parseLine(input.get(i)));
			}
			setChildrenForAllNodes();
		}
	}
	public ArrayList<Integer> parseLine(String input){
		String tempArray[] = input.split(",");
		ArrayList<Integer> returnArray = new ArrayList<Integer>();
		for(int i = 0; i < tempArray.length; i++){
			returnArray.add(Integer.parseInt(tempArray[i]));
		}
		return returnArray;
	}
	public boolean isEndNode(NavNode node){
		if(node.pos.x == endPos.x && node.pos.y == endPos.y){
			return true;
		}else{
			return false;
		}
	}
	public void resetBoard(){
		complete = false;
		steps = 0;
		for(int i = 0; i < size.x; i++){
			for(int j = 0; j < size.y; j++){
				Node currentNode = boardArray[i][j];
				if(currentNode.status != Status.Obstacle){
					currentNode.status = Status.Unvisited;
				}
			}
		}
	}
	private void setChildrenForAllNodes(){
		for(int i = 0; i < size.x; i++){
			for(int j = 0; j < size.y; j++){
				setChildren(boardArray[i][j]);
			}
		}
	}
	private void setChildren(NavNode node){
		if(node.pos.x > 0){
			node.addChild(boardArray[node.pos.x -1][node.pos.y]);
		}
		if(node.pos.y < size.y - 1){
			node.addChild(boardArray[node.pos.x][node.pos.y + 1]);
		}
		if(node.pos.x < size.x - 1){
			node.addChild(boardArray[node.pos.x + 1][node.pos.y]);
		}
		if(node.pos.y > 0){
			node.addChild(boardArray[node.pos.x][node.pos.y - 1]);
		}
	}
	private void generateObstacle(ArrayList<Integer> input){
		int x = input.get(0);
		int y = input.get(1);
		int width = input.get(2);
		int height = input.get(3);
		for(int i = x; i < (x + width); i++){
			for(int j = y; j < (y + height); j++){
				boardArray[i][j].status = Node.Status.Obstacle;
			}
		}
	}
	private void generateBoard(){
		boardArray = new NavNode[size.x][size.y];
		for(int i = 0; i < size.x; i++){
			for(int j = 0; j < size.y; j++){
				boardArray[i][j] = new NavNode(new Point(i,j));
			}
		}
	}
	private void setBoardSize(ArrayList<Integer> input){
		size.x = input.get(0);
		size.y = input.get(1);
	}
	private void setStartAndEnd(ArrayList<Integer> input){
		startPos.x = input.get(0);
		startPos.y = input.get(1);
		endPos.x = input.get(2);
		endPos.y = input.get(3);
	}
}
