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
	
	public void findFarthestPosition(int[][] currentBoard, int startX, int startY, Direction dir){
		if(dir == Direction.UP){//up
			if(startY == 0){
				
			}else{
				int midY = startY;
				while(midY >= 0){
					if(currentBoard[startX][midY] != 0){
						//return node
					}
					midY--;
				}
			}
		}else if(dir == Direction.RIGHT){//right
			if(startX == 3){
				
			}else{
				int midX = startX;
				while(midX >= 0){
					if(currentBoard[midX][startY] != 0){
						//return node
					}
					midX--;
				}
			}
		}else if(dir == Direction.DOWN){//down
			if(startY == 3){
				
			}else{
				int midY = startY;
				while(midY <= 3){
					if(currentBoard[startX][midY] != 0){
						//return node
					}
					midY++;
				}
			}
		}else{//left
			if(startX == 0){
				
			}else{
				int midX = startX;
				while(midX <= 3){
					if(currentBoard[midX][startY] != 0){
						//return node
					}
					midX++;
				}
			}
		}
	}
	
	public int heuristic(){
		return 0;
	}
	
	public int monotonicity(){
		return 0;
	}
	
	public int smoothness(int[][] currentBoard){
		int smoothness = 0;
		for(int i=0; i<4; i++){
			for(int j=0; j<4; j++){
				if(checkOccupied(currentBoard, i, j)){
					double value = Math.log(getNodeValue(currentBoard, i, j)) / Math.log(2);
					for(int k=0; k<2; k++){
						int vector;
						Point target = new Point();
						
						/*
						var vector = this.getVector(direction);
				          var targetCell = this.findFarthestPosition(this.indexes[x][y], vector).next;

				          if (this.cellOccupied(targetCell)) {
				            var target = this.cellContent(targetCell);
				            var targetValue = Math.log(target.value) / Math.log(2);
				            smoothness -= Math.abs(value - targetValue);
				          }
				          */
					}
				}
			}
		}
		
		
		
		return 0;
	}
	
	public int islands(){
		return 0;
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