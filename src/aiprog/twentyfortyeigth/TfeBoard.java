package aiprog.twentyfortyeigth;

import java.util.ArrayList;

import aiprog.model.Direction;
import aiprog.model.Point;

public class TfeBoard {
	private int[][] board;
	private boolean player;
	
	public TfeBoard(boolean init){
		if(init){
			board = new int[4][4];
			generateRandomNumber();
			generateRandomNumber();
			player = true;
			
		}else{
			
		}
		
		/*
		printBoard();
		//moveLeft();
		//moveRight();
		//moveUp();
		System.out.println(isLegalRightMove());
		moveRight();
		//generateRandomNumber();
		System.out.println();
		printBoard();
		//System.out.println();
		//moveLeft();
		//moveRight();
		System.out.println();
		System.out.println(isLegalRightMove());
		//printBoard();*/
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
					Point point = new Point(j,i);
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
	
	//enkel return av spesifik node value
	public int getNodeValue(int[][] currentBoard, int posX, int posY){
		int[][] mid = currentBoard;
		if(posX >=0 && posX < mid.length && posY >= 0 && mid[0].length > posY){
			return mid[posX][posY];
		}else{
			return 0;
		}
		//return this.getBoard()[posX][posY];
	}
	
	//True hvis noden er tatt, false hvis den er 0
	public boolean checkOccupied(int[][] currentBoard, int posX, int posY){
		if(getNodeValue(currentBoard, posX, posY) != 0){
			return true;
		}else{
			return false;
		}
	}
	
	public int findFarthestPosition(int[][] currentBoard, int startX, int startY, Direction dir){
		if(dir == Direction.UP){//up
			if(startY == 0){
				return 0;
			}else{
				int midY = startY;
				while(midY >= 0){
					if(currentBoard[startX][midY] != 0){
						return currentBoard[startX][midY];
					}
					midY--;
				}
				return 0;
			}
		}else if(dir == Direction.RIGHT){//right
			if(startX == 3){
				return 0;
			}else{
				int midX = startX;
				while(midX >= 0){
					if(currentBoard[midX][startY] != 0){
						return currentBoard[midX][startY];
					}
					midX--;
				}
				return 0;
			}
		}else if(dir == Direction.DOWN){//down
			if(startY == 3){
				return 0;
			}else{
				int midY = startY;
				while(midY <= 3){
					if(currentBoard[startX][midY] != 0){
						return currentBoard[startX][midY];
					}
					midY++;
				}
				return 0;
			}
		}else{//left
			if(startX == 0){
				return 0;
			}else{
				int midX = startX;
				while(midX <= 3){
					if(currentBoard[midX][startY] != 0){
						return currentBoard[midX][startY];
					}
					midX++;
				}
				return 0;
			}
		}
		//return 0;
	}
	
	public int heuristic(){
		return 0;
	}
	
	public double monotonicity(int[][] currentBoard){
		double[] totals = new double[4];
		totals[0]=0;totals[1]=0;totals[2]=0;totals[3]=0;
		
		//up/down
		for(int i=0; i<4; i++){
			int current = 0;
			int next = current+1;
			while(next<4){
				while(next<4 && !checkOccupied(currentBoard, i, next)){
					next++;
				}
				if(next>= 4){
					next--;
				}
				double currentValue = 0;
				double nextValue = 0;
				
				if(checkOccupied(currentBoard, i, current)){
					currentValue = Math.log(getNodeValue(currentBoard, i, current) / Math.log(2));
				}
				if(checkOccupied(currentBoard, i ,next)){
					nextValue = Math.log(getNodeValue(currentBoard, i, next) / Math.log(2));
				}
				
				if(currentValue > nextValue){
					totals[0] += nextValue - currentValue;
				}else if(nextValue > currentValue){
					totals[1] += currentValue - nextValue;
				}
				
				current = next;
				next++;
			}
		}
		
		//left/right
		for(int j=0; j<4; j++){
			int current = 0;
			int next = current + 1;
			while(next < 4){
				while(next < 4 && !checkOccupied(currentBoard, j, next)){
					next++;
				}
				if(next >= 4){
					next--;
				}
				double currentValue = 0;
				double nextValue = 0;
				
				if(checkOccupied(currentBoard, current, j)){
					currentValue = Math.log(getNodeValue(currentBoard, current, j)/Math.log(2));
				}
				if(checkOccupied(currentBoard, next, j)){
					nextValue = Math.log(getNodeValue(currentBoard, next, j)/Math.log(2));
				}
				
				if(currentValue > nextValue){
					totals[2] += nextValue - currentValue;
				}else if(nextValue > currentValue){
					totals[3] += currentValue - nextValue;
				}
				current = next;
				next++;
			}
		}
		return Math.max(totals[0], totals[1]) + Math.max(totals[2], totals[3]);
	}
	
	//needs testing
	public double smoothness(int[][] currentBoard){
		double smoothness = 0;
		for(int i=0; i<4; i++){
			for(int j=0; j<4; j++){
				if(checkOccupied(currentBoard, i, j)){
					double value = Math.log(getNodeValue(currentBoard, i, j)) / Math.log(2);
					ArrayList<Direction> dirArray = new ArrayList<Direction>();
					dirArray.add(Direction.UP);
					dirArray.add(Direction.DOWN);
					dirArray.add(Direction.LEFT); 
					dirArray.add(Direction.RIGHT);
					while(!dirArray.isEmpty()){
						Direction dir = dirArray.get(0);
						dirArray.remove(0);
						int targetValue = findFarthestPosition(currentBoard, i, j, dir);
						if(targetValue != 0){
							double realTarget = Math.log(targetValue)/Math.log(2);
							smoothness -= Math.abs(value - realTarget);
						}
					}
				}
			}
		}
		return smoothness;
	}
	
	public int islands(){
		return 0;
	}
	
	public double maxValue(int[][] currentBoard){
		double max = 0;
		for(int i=0; i<4; i++){
			for(int j=0; j<4; j++){
				double value = getNodeValue(currentBoard, i, j);
				if(value != 0 && value > max){
					max = value;
				}
			}
		}
		return Math.log(max) / Math.log(2);
	}
	
	public void setPlayer(boolean value){
		player = value;
	}
	
	public boolean getPlayer(){
		return player;
	}
	
	public TfeBoard cloneBoard(){
		return null;
	}
	
	public void move(Direction dir){
		if(dir == Direction.UP){
			moveUp();
		}else if(dir == Direction.LEFT){
			moveLeft();
		}else if(dir == Direction.RIGHT){
			moveRight();
		}else{
			moveDown();
		}
	}
	
	public boolean isLegalMove(Direction dir){
		if(dir == Direction.UP){
			return isLegalUpMove();
		}else if(dir == Direction.LEFT){
			return isLegalLeftMove();
		}else if(dir == Direction.RIGHT){
			return isLegalRightMove();
		}else{
			return isLegalDownMove();
		}
	}
}
