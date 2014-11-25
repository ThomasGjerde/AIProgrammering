package aiprog.twentyfortyeigth;

import java.util.ArrayList;

import aiprog.model.Direction;
import aiprog.model.Point;

public class TfeBoard {
	private int[][] board;
	private boolean player;
	private boolean failed;
	private int victoryValue;
	private Point maxPoint;
	public TfeBoard(boolean init,int vValue){
		if(init){
			board = new int[4][4];
			generateRandomNumber();
			generateRandomNumber();
			player = true;
			victoryValue = vValue;
		}
		else{
			board = new int[4][4];
			generateRandomNumber();
			generateRandomNumber();
			player = false;
			victoryValue = vValue;
		}
		failed = false;
	}
	
	private TfeBoard(boolean clonePlayer, int[][] cloneBoard){
		board = cloneBoard;
		player = clonePlayer;
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
				Point midPoint = new Point(i,j);
				boolean merge = false;
				if(value != 0){
					
					if(prevValue == value){
						rightPoint.y = rightPoint.y + 1;
						return true;
					}
					
					if(midPoint.x != rightPoint.x || midPoint.y != rightPoint.y){
						return true;
					}
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
				Point midPoint = new Point(i,j);
				boolean merge = false;
				if(value != 0){
					
					if(prevValue == value){
						leftPoint.y = leftPoint.y - 1;
						return true;
					}
					
					if(midPoint.x != leftPoint.x || midPoint.y != leftPoint.y){
						return true;
					}
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
				Point midPoint = new Point(j,i);
				boolean merge = false;
				if(value != 0){
					if(prevValue == value){
						rightPoint.x = rightPoint.x - 1;
						return true;
						//merge = true;
					}
					
					if(midPoint.x != rightPoint.x || midPoint.y != rightPoint.y){
						return true;
					}
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
				Point midPoint = new Point(j,i);
				boolean merge = false;
				if(value != 0){
					
					if(prevValue == value){
						rightPoint.x = rightPoint.x + 1;
						return true;
						//merge = true;
					}
					
					if(midPoint.x != rightPoint.x || midPoint.y != rightPoint.y){
						return true;
					}
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
			this.getBoard()[placePoint.y][placePoint.x] = tileValue;
		}else{
			this.setFailed(true);
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
	public int getNodeValue(int posX, int posY){
		if(posX >=0 && posX < board.length && posY >= 0 && board[0].length > posY){
			return board[posX][posY];
		}else{
			return 0;
		}
		//return this.getBoard()[posX][posY];
	}
	
	//True hvis noden er tatt, false hvis den er 0
	public boolean checkOccupied(int posX, int posY){
		if(getNodeValue(posX, posY) != 0){
			return true;
		}else{
			return false;
		}
	}
	
	public int findNeighbour(int posX, int posY, Direction dir){
		if(dir==Direction.UP){
			//endre y, --
			//Nothing available over the current position
			if(posY == 0){
				return 0;
			//Possible neighbour over
			}else{
				for(int i=posY-1; i>=0; i--){
					if(board[posX][i] != 0){
						return board[posX][i];
					}
				}
			}
			return 0;
		}else if(dir==Direction.DOWN){
			//endre y, ++
			//Nothing available under the current position
			if(posY == 3){
				return 0;
			//Possible neighbour under
			}else{
				for(int i=posY+1; i <= 3; i++){
					if(board[posX][i] != 0){
						return board[posX][i];
					}
				}
			}
			return 0;
		}else if(dir==Direction.RIGHT){
			//endre x, ++
			//Nothing to the right
			if(posX == 3){
				return 0;
			//Possible neighbour to the right
			}else{
				for(int i=posX+1; i<=3; i++){
					if(board[i][posY] != 0){
						return board[i][posY];
					}
				}
			}
			return 0;
		}else if(dir==Direction.LEFT){
			//endre x, --
			//Nothing to the left
			if(posX == 0){
				return 0;
			//Possible neighbour to the left
			}else{
				for(int i=posX-1; i>=0; i--){
					if(board[i][posY] != 0){
						return board[i][posY];
					}
				}
			}
			return 0;
		}else{
			return 0;
		}
	}
	
	public double heuristic(){
		double h = 0;
		ArrayList<Double> easy = easyHeuristic();
		double smoothness = easy.get(0) * 0.2;
		double max = easy.get(1) * 1;
		double maxPlacement = easy.get(2) * 0.5;
		//double freeSize = easy.get(3) * 2.7;
		double freeSize = easy.get(3) * 1;
		double order = order() * 1;
		h = smoothness + max + freeSize + order + maxPlacement;
		
		return h;
	}
	
	public double order(){
		double[] totals = new double[4];
		totals[0]=0;totals[1]=0;totals[2]=0;totals[3]=0;
		
		//up/down
		//current = 0;
		for(int i=0; i<4; i++){
			int current = 0;
			int next = current+1;
			while(next<4){
				while(next<4 && !checkOccupied(i, next)){
					next++;
				}
				if(next>= 4){
					next--;
				}
				double currentValue = 0;
				double nextValue = 0;
				
				if(checkOccupied(i, current)){
					currentValue = Math.log(getNodeValue(i, current) / Math.log(2));
				}
				if(checkOccupied(i ,next)){
					nextValue = Math.log(getNodeValue(i, next) / Math.log(2));
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
		//current = 0;
		for(int j=0; j<4; j++){
			int current = 0;
			int next = current + 1;
			while(next < 4){
				while(next < 4 && !checkOccupied(j, next)){
					next++;
				}
				if(next >= 4){
					next--;
				}
				double currentValue = 0;
				double nextValue = 0;
				
				if(checkOccupied(current, j)){
					currentValue = Math.log(getNodeValue(current, j)/Math.log(2));
				}
				if(checkOccupied(next, j)){
					nextValue = Math.log(getNodeValue(next, j)/Math.log(2));
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
	
	public ArrayList<Double> easyHeuristic(){
		//For smoothness - done
		double smoothness = 0; //det som skal brukes i h
		//For MaxValue - done
		double max = 0; //det som skal brukes i h
		Point midPoint = new Point(0,0);
		//For freeTiles - done
		ArrayList<Point> freeArray = new ArrayList<Point>(); 
		double freeArraySize = 0;//det som skal brukes i h
		//MaxAtEdge + //MaxAtCorner - testing
		double maxPlacement = 0; //Den som skal brukes i h
		
		for(int i=0; i<4; i++){
			for(int j=0; j<4; j++){
				if(board[i][j] == 0){
					Point freePoint = new Point(i,j);
					freeArray.add(freePoint);
				}else{
					if(board[i][j] != 0 && board[i][j] > max){
						max = board[i][j];
						midPoint.x = i;
						midPoint.y = j;
					}
					double value = Math.log(board[i][j]) / Math.log(2);
					ArrayList<Direction> dirArray = new ArrayList<Direction>();
					dirArray.add(Direction.UP);
					dirArray.add(Direction.DOWN);
					dirArray.add(Direction.LEFT);
					dirArray.add(Direction.RIGHT);
					while(!dirArray.isEmpty()){
						Direction dir = dirArray.get(0);
						dirArray.remove(0);
						int targetValue = findNeighbour(i,j,dir);
						if(targetValue != 0){
							double realTarget = Math.log(targetValue)/Math.log(2);
							smoothness -= Math.abs(value - realTarget);
						}
					}
				}
			}
		}
		if(max > 2048){
			max = 20;
		}else{
			max = Math.log(max) / Math.log(2);
		}
		maxPoint = midPoint;
		//freeArraySize = Math.log(freeArray.size());
		freeArraySize = freeArray.size();
		//Testing med MaxAtEdge + MaxAtCorner //Med value 1 for edge og 2 for corner
		if(maxPoint.x == 0 || maxPoint.x == 3 || maxPoint.y == 0 || maxPoint.y == 3){
			maxPlacement = 1;
			if((maxPoint.x == 0 && maxPoint.y == 0) || (maxPoint.x == 3 && maxPoint.y == 3) || (maxPoint.x == 0 && maxPoint.y == 3) || (maxPoint.x == 3 && maxPoint.y == 0)){
				maxPlacement = 2;
			}
		}
		ArrayList<Double> returnArray = new ArrayList<Double>();
		returnArray.add(smoothness); //pos 0
		returnArray.add(max); //pos 1
		returnArray.add(maxPlacement); //pos 2 
		returnArray.add(freeArraySize); //pos 3
		return returnArray;
	}
	
	public double maxValue(){
		double max = 0;
		Point midPoint = new Point(0,0);
		for(int i=0; i<4; i++){
			for(int j=0; j<4; j++){
				double value = getNodeValue(i, j);
				if(value != 0 && value > max){
					max = value;
					midPoint.x = i;
					midPoint.y = j;
				}
			}
		}
		maxPoint = midPoint;
		return Math.log(max) / Math.log(2);
	}
	
	public void setPlayer(boolean value){
		player = value;
	}
	
	public boolean getPlayer(){
		return player;
	}
	
	public TfeBoard cloneBoard(){
		boolean clonePlayer;
		boolean cloneFailed;
		
		if(player){
			clonePlayer = true;
		}else{
			clonePlayer = false;
		}
		if(hasFailed()){
			cloneFailed = true;
		}else{
			cloneFailed = false;
		}
		
		int[][] cloneBoard = new int[4][4];
		for(int i=0; i<4; i++){
			for(int j=0; j<4; j++){
				int midValue = board[i][j];
				cloneBoard[i][j] = midValue;
			}
		}
		TfeBoard midTfe = new TfeBoard(clonePlayer, cloneBoard);
		midTfe.setFailed(cloneFailed);
		double max;
		Point mid;
		if(maxPoint != null){
			mid = new Point(maxPoint.x, maxPoint.y);
			midTfe.maxPoint = mid;
		}
		midTfe.setVictoryValue(this.getVictoryValue());
		
		return midTfe;
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
		//printBoard();
		//System.out.println();
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
	
	//Mid victoryCheck
	public boolean victoryCheck(){
		boolean check = false;
		for(int i=0; i<4; i++){
			for(int j=0; j<4; j++){
				if(board[i][j] == victoryValue){
					check = true;
				}
			}
		}
		return check;
	}
	
	public int getVictoryValue(){
		return victoryValue;
	}
	
	public void setVictoryValue(int vValue){
		victoryValue = vValue;
	}
	
	public boolean hasFailed(){
		return failed;
	}
	
	public void setFailed(boolean status){
		failed = status;
	}
}
