package aiprog.main;

import java.io.IOException;

import aiprog.flowfree.FFBoard;
import aiprog.flowfree.FlowFree;
import aiprog.gui.FFGraphics;
import aiprog.gui.NNGraphics;
import aiprog.model.NNStateNode;
import aiprog.navigation.Board;
import aiprog.navigation.GridBestFirstSearch;
import aiprog.navigation.GridBreadthFirstSearch;
import aiprog.navigation.GridDepthFirstSearch;
import aiprog.nonogram.NNBoard;
import aiprog.vcp.VCPGraph;


public class Main {

	/**
	 * @param args
	 */
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		/*
		try {
			NNBoard board = new NNBoard("input/nono-rabbit.txt");
			//NNGraphics graphics = new NNGraphics(board);
			NNStateNode sn = new NNStateNode(board);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		
		try {
			FFBoard board = new FFBoard("input/flowspec-5.txt");
			FFGraphics ffgraph = new FFGraphics(board);
			//�ja, s� derfor m� jeg arve fra node i statenodesa....
			//Det er mye dritt i denne arverekka som vi ikke trenger i dette.
			//Egentlig trenger vi kun aStar og csp delene tror jeg, og ca halvparten av Node
			FlowFree ff = new FlowFree(board.createInitState(), ffgraph);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
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
			Board board = new Board("C:\\Users\\Lefdal\\Desktop\\AIProg\\GRID\\board8.txt");
			GridBestFirstSearch gbfs = new GridBestFirstSearch(board.boardArray[board.startPos.x][board.startPos.y], board.endPos, board);
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
			Board board = new Board("C:\\Users\\Lefdal\\Desktop\\AIProg\\GRID\\board3.txt");
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
