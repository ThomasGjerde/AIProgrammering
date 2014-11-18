package aiprog.twentyfortyeigth;

public class TfeBoard {
	private int[][] board;
	
	public TfeBoard(){
		board = new int[4][4];
		printBoard();
	}
	
	public void moveLeft(){
		
	}
	
	public void moveRight(){
		
	}
	
	public void moveUp(){
		
	}
	
	public void moveDown(){
		
	}
	
	public void generateRandomNumber(){
		
	}
	
	public void getUnoccupiedTiles(){
		
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
