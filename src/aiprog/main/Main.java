package aiprog.main;

import java.io.IOException;
import aiprog.model.Board;


public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Board board = new Board("/home/board.txt");
			GridDepthFirstSearch gbfs = new GridDepthFirstSearch(board.boardArray[board.startPos.x][board.startPos.y], board.endPos, board);
			gbfs.search();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
