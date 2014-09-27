package aiprog.main;

import java.io.IOException;


public class Main {

	/**
	 * @param args
	 */
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		
		try {
			VCPGraph graph = new VCPGraph("/home/vcpGraph.txt",4);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/*
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
		*/
		
		
		
	}

}
