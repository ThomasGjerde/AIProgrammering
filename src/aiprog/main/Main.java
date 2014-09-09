package aiprog.main;

import java.io.IOException;
import aiprog.gui.Graphics;
import aiprog.model.Board;


public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Board board = new Board("D:\\board.txt");
			Graphics graphics = new Graphics(board);
			graphics.setBoard(board);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
