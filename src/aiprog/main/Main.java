package aiprog.main;

import java.io.IOException;

import aiprog.navigation.Board;
import aiprog.navigation.GridBestFirstSearch;
import aiprog.navigation.GridBreadthFirstSearch;
import aiprog.navigation.GridDepthFirstSearch;


public class Main {

	/**
	 * @param args
	 */
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		
		/*
		try {
			VCPGraph graph = new VCPGraph("/home/vcpGraph2.txt",4);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		/*
		try {
			Board board = new Board("/home/board.txt");
			GridBreadthFirstSearch gbfs = new GridBreadthFirstSearch(board.boardArray[board.startPos.x][board.startPos.y], board.endPos, board);
			gbfs.search();
			System.out.println("Path: " + gbfs.getPathLength());
			System.out.println("Steps: " + gbfs.getSteps());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		/*
		try {
			Board board = new Board("/home/board.txt");
			GridBestFirstSearch gbfs = new GridBestFirstSearch(board.boardArray[board.startPos.x][board.startPos.y], board.endPos, board);
			gbfs.search();
			System.out.println("Path: " + gbfs.getPathLength());
			System.out.println("Steps: " + gbfs.getSteps());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		
		try {
			Board board = new Board("/home/board.txt");
			GridDepthFirstSearch gbfs = new GridDepthFirstSearch(board.boardArray[board.startPos.x][board.startPos.y], board.endPos, board);
			gbfs.search();
			System.out.println("Path: " + gbfs.getPathLength());
			System.out.println("Steps: " + gbfs.getSteps());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
	}

}
