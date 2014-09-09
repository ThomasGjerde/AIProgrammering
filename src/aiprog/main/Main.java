package aiprog.main;

import java.io.IOException;

import aiprog.model.Board;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Board board = new Board("C:\\board.txt");
			/*
			System.out.println("SizeX: " + board.sizeX);
			System.out.println("SizeY: " + board.sizeY);
			System.out.println("StartX: " + board.startX);
			System.out.println("StartY: " + board.startY);
			System.out.println("EndX: " + board.endX);
			System.out.println("EndY: " + board.endY);
			*/
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
