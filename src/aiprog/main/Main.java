package aiprog.main;

import java.io.IOException;
import aiprog.model.Board;
import aiprog.navigation.GridBestFirstSearch;


public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Board board = new Board("/home/board.txt");
			GridBestFirstSearch gbfs = new GridBestFirstSearch(board.boardArray[board.startPos.x][board.startPos.y], board.endPos, board);
			gbfs.search();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
