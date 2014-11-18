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
		generateRandomNumber();
		generateRandomNumber();
		
		generateRandomNumber();
		generateRandomNumber();
		generateRandomNumber();
		generateRandomNumber();
		
		printBoard();
		//moveLeft();
		//moveRight();
		//moveUp();
		System.out.println(isLegalRightMove());
		moveRight();
		generateRandomNumber();
		System.out.println();
		printBoard();
		//System.out.println();
		//moveLeft();
		//moveRight();
		System.out.println();
		System.out.println(isLegalRightMove());
		//printBoard();
	}
	
	//works
	public void moveLeft(){
		for(int i=0; i<4; i++){
			Point leftPoint = new Point(i,0);
			int prevValue = 0;
			for(int j=0; j<4; j++){
				int value = this.getBoard()[i][j];
				Point midPoint = new Point(i,j);
				boolean merge = false;
				if(value != 0){
					
					if(prevValue == value){
						leftPoint.y = leftPoint.y - 1;
						merge = true;
					}
					this.getBoard()[midPoint.x][midPoint.y] = 0;
					this.getBoard()[leftPoint.x][leftPoint.y] += value;
					leftPoint.y = leftPoint.y + 1;
					
					if(merge){
						prevValue = 0;
					}else{
						prevValue = value;
					}
				}else{
					
				}
				
			}
		}
	}
	
	//Works
	public void moveRight(){
		for(int i=0; i<4; i++){
			Point rightPoint = new Point(i,3);
			int prevValue = 0;
			for(int j=3; j>=0; j--){
				int value = this.getBoard()[i][j];
				Point midPoint = new Point(i,j);
				boolean merge = false;
				if(value != 0){
					
					if(prevValue == value){
						rightPoint.y = rightPoint.y + 1;
						merge = true;
					}
					this.getBoard()[midPoint.x][midPoint.y] = 0;
					this.getBoard()[rightPoint.x][rightPoint.y] += value;
					rightPoint.y = rightPoint.y - 1;
					
					if(merge){
						prevValue = 0;
					}else{
						prevValue = value;
					}
				}else{
					
				}
				
			}
		}
	}
	
	//Works
	public void moveDown(){
		for(int i=0; i<4; i++){
			Point rightPoint = new Point(3,i);
			int prevValue = 0;
			for(int j=3; j>=0; j--){
				int value = this.getBoard()[j][i];
				Point midPoint = new Point(j,i);
				boolean merge = false;
				if(value != 0){
					
					if(prevValue == value){
						rightPoint.x = rightPoint.x + 1;
						merge = true;
					}
					this.getBoard()[midPoint.x][midPoint.y] = 0;
					this.getBoard()[rightPoint.x][rightPoint.y] += value;
					rightPoint.x = rightPoint.x - 1;
					
					if(merge){
						prevValue = 0;
					}else{
						prevValue = value;
					}
				}else{
					
				}
				
			}
		}
	}
	//Works
	public void moveUp(){
		for(int i=0; i<4; i++){
			Point rightPoint = new Point(0,i);
			int prevValue = 0;
			for(int j=0; j<4; j++){
				int value = this.getBoard()[j][i];
				Point midPoint = new Point(j,i);
				boolean merge = false;
				if(value != 0){
					if(prevValue == value){
						rightPoint.x = rightPoint.x - 1;
						merge = true;
					}
					this.getBoard()[midPoint.x][midPoint.y] = 0;
					this.getBoard()[rightPoint.x][rightPoint.y] += value;
					rightPoint.x = rightPoint.x + 1;
					
					if(merge){
						prevValue = 0;
					}else{
						prevValue = value;
					}
				}else{
					
				}
				
			}
		}
	}
	
	public boolean isLegalRightMove(){
		//return false;
		for(int i=0; i<4; i++){
			Point rightPoint = new Point(i,3);
			int prevValue = 0;
			for(int j=3; j>=0; j--){
				int value = this.getBoard()[i][j];
				//Point midPoint = new Point(i,j);
				boolean merge = false;
				if(value != 0){
					
					if(prevValue == value){
						rightPoint.y = rightPoint.y + 1;
						return true;
					}
					//this.getBoard()[midPoint.x][midPoint.y] = 0;
					//this.getBoard()[rightPoint.x][rightPoint.y] += value;
					rightPoint.y = rightPoint.y - 1;
					
					if(merge){
						prevValue = 0;
					}else{
						prevValue = value;
					}
				}else{
					
				}
				
			}
		}
		return false;
		
	}
	
	public boolean isLegalLeftMove(){
		for(int i=0; i<4; i++){
			Point leftPoint = new Point(i,0);
			int prevValue = 0;
			for(int j=0; j<4; j++){
				int value = this.getBoard()[i][j];
				//Point midPoint = new Point(i,j);
				boolean merge = false;
				if(value != 0){
					
					if(prevValue == value){
						leftPoint.y = leftPoint.y - 1;
						return true;
					}
					//this.getBoard()[midPoint.x][midPoint.y] = 0;
					//this.getBoard()[leftPoint.x][leftPoint.y] += value;
					leftPoint.y = leftPoint.y + 1;
					
					if(merge){
						prevValue = 0;
					}else{
						prevValue = value;
					}
				}else{
					
				}
				
			}
		}
		return false;
	}
	
	public boolean isLegalUpMove(){
		for(int i=0; i<4; i++){
			Point rightPoint = new Point(0,i);
			int prevValue = 0;
			for(int j=0; j<4; j++){
				int value = this.getBoard()[j][i];
				//Point midPoint = new Point(j,i);
				boolean merge = false;
				if(value != 0){
					if(prevValue == value){
						rightPoint.x = rightPoint.x - 1;
						return true;
						//merge = true;
					}
					//this.getBoard()[midPoint.x][midPoint.y] = 0;
					//this.getBoard()[rightPoint.x][rightPoint.y] += value;
					rightPoint.x = rightPoint.x + 1;
					
					if(merge){
						prevValue = 0;
					}else{
						prevValue = value;
					}
				}else{
					
				}
				
			}
		}
		return false;
	}
	
	public boolean isLegalDownMove(){
		for(int i=0; i<4; i++){
			Point rightPoint = new Point(3,i);
			int prevValue = 0;
			for(int j=3; j>=0; j--){
				int value = this.getBoard()[j][i];
				//Point midPoint = new Point(j,i);
				boolean merge = false;
				if(value != 0){
					
					if(prevValue == value){
						rightPoint.x = rightPoint.x + 1;
						return true;
						//merge = true;
					}
					//this.getBoard()[midPoint.x][midPoint.y] = 0;
					//this.getBoard()[rightPoint.x][rightPoint.y] += value;
					rightPoint.x = rightPoint.x - 1;
					
					if(merge){
						prevValue = 0;
					}else{
						prevValue = value;
					}
				}else{
					
				}
				
			}
		}
		return false;
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
