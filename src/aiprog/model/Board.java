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
				System.out.println("Obstacle i:" + i);
				generateObstacle(parseLine(input.get(i)));
			}
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
				System.out.println("Obs:" + i + j);
			}
		}
	}
	private void generateBoard(){
		boardArray = new Node[sizeX][sizeY];
		for(int i = 0; i < sizeX; i++){
			for(int j = 0; j < sizeY; j++)
			{
				boardArray[i][j] = new Node();
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
