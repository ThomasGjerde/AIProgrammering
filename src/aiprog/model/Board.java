package aiprog.model;

import java.io.*;
import java.util.ArrayList;

public class Board {
	public int sizeX;
	public int sizeY;
	public int startX;
	public int startY;
	public int endX;
	public int endY;
	public Node[][] boardArray;
	
	public Board(String path) throws IOException{
		ArrayList<String> input = getInputFromFile(path);
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
	
	private void setChildrenForAllNodes(){
		for(int i = 0; i < sizeX; i++){
			for(int j = 0; j < sizeY; j++){
				setChildren(boardArray[i][j]);
			}
		}
	}
	private void setChildren(Node node){
		System.out.println("Current node: (" + node.positionX + "," + node.positionY + ")");
		if(node.positionX > 0){
			node.addChild(boardArray[node.positionX -1][node.positionY]);
		}
		if(node.positionX < sizeX){
			node.addChild(boardArray[node.positionX + 1][node.positionY]);
		}
		if(node.positionY > 0)
		{
			node.addChild(boardArray[node.positionX][node.positionY - 1]);
		}
		if(node.positionY < sizeY){
			node.addChild(boardArray[node.positionX][node.positionY + 1]);
		}
	}
	private void generateObstacle(ArrayList<Integer> input){
		//System.out.println("Obstacle: " + input.get(0) + ":" + input.get(1) + ":" + input.get(2) + ":" + input.get(3));
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
		boardArray = new Node[sizeX][sizeY];
		for(int i = 0; i < sizeX; i++){
			for(int j = 0; j < sizeY; j++)
			{
				boardArray[i][j] = new Node(i,j);
			}
		}
	}
	private void setBoardSize(ArrayList<Integer> input){
		sizeX = input.get(0);
		sizeY = input.get(1);
	}
	private void setStartAndEnd(ArrayList<Integer> input){
		startX = input.get(0);
		startY = input.get(1);
		endX = input.get(2);
		endY = input.get(3);
	}
	private ArrayList<Integer> parseLine(String input){
		String tempArray[] = input.split(",");
		ArrayList<Integer> returnArray = new ArrayList<Integer>();
		for(int i = 0; i < tempArray.length; i++){
			returnArray.add(Integer.parseInt(tempArray[i]));
		}
		return returnArray;
	}
	private ArrayList<String> getInputFromFile(String path) throws IOException{
		BufferedReader br = new BufferedReader(new FileReader(new File(path)));
		ArrayList<String> returnArray = new ArrayList<String>();
		String line;
	    while ((line = br.readLine()) != null) {
	        returnArray.add(line.replace("(", "").replace(")","").replace(" ", ","));
	    }
	    br.close();
	    return returnArray;
	}

}
