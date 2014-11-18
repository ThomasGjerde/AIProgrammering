package aiprog.twentyfortyeigth;

import java.util.ArrayList;

import aiprog.model.Point;

public class TfeBoard {
	private int[][] board;
	
	public TfeBoard(){
		board = new int[4][4];
		generateRandomNumber();
		generateRandomNumber();
		generateRandomNumber();
		generateRandomNumber();
		printBoard();
		moveLeft();
		System.out.println();
		printBoard();
	}
	
	public void moveLeft(){
		for(int i=0; i<4; i++){
			//int value = 1;
			Point leftPoint = new Point(i,0);
			for(int j=0; j<4; j++){
				int value = this.getBoard()[i][j];
				Point midPoint = new Point(i,j);
				int leftValue = this.getBoard()[leftPoint.x][leftPoint.y];
				if(value != 0){
					
					this.getBoard()[midPoint.x][midPoint.y] = 0;
					this.getBoard()[leftPoint.x][leftPoint.y] = value;
					//leftValue = value;
				}else{
					
				}
				leftPoint.y = leftPoint.y + 1;
				
				/*
				int midValue = this.getBoard()[i][j];
				Point midPoint = new Point(10,10);
				if(midValue != 0 && value == 1){
					midPoint = new Point(i,j);
					value = midValue;
				}
				
				if(midValue == value && midPoint.x != 10){
					this.getBoard()[midPoint.x][midPoint.y] = value + midValue;
					System.out.println("her");
					this.getBoard()[i][j] = 0;
				}*/
			}
		}
	}
	
	public void moveRight(){
		
	}
	
	public void moveUp(){
		
	}
	
	public void moveDown(){
		
	}
	
	public void generateRandomNumber(){
		ArrayList<Point> pointList = getUnoccupiedTiles();
		
		if(!pointList.isEmpty()){
			double randomTile = Math.random();
			int tileValue;
			if(randomTile < 0.1){
				tileValue = 4;
			}else{
				tileValue = 2;
			}
			
			double randomPlace = Math.random() * pointList.size();
			Point placePoint = pointList.get((int)randomPlace);
			this.getBoard()[placePoint.x][placePoint.y] = tileValue;
		}else{
			System.out.println("lost");
		}
	}
	
	public ArrayList<Point> getUnoccupiedTiles(){
		ArrayList<Point> pointList = new ArrayList<Point>();
		for(int i=0; i<4; i++){
			for(int j=0; j<4; j++){
				if(board[i][j] == 0){
					Point point = new Point(i,j);
					pointList.add(point);
				}
			}
		}
		return pointList;
	}
	
	public int[][] getBoard(){
		return board;
	}
	
	public void printBoard(){
		for(int i=0; i<4; i++){
			for(int j=0; j<4; j++){
				System.out.print(board[i][j]);
			}
			System.out.println();
		}
	}
	
	
}
