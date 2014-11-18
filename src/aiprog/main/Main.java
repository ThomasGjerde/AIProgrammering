package aiprog.main;

import java.io.IOException;

import aiprog.flowfree.FFBoard;
import aiprog.flowfree.FlowFree;
import aiprog.gui.FFGraphics;
import aiprog.gui.NNGraphics;
import aiprog.gui.TFEGraphics;
import aiprog.model.NNStateNode;
import aiprog.navigation.Board;
import aiprog.navigation.GridBestFirstSearch;
import aiprog.navigation.GridBreadthFirstSearch;
import aiprog.navigation.GridDepthFirstSearch;
import aiprog.nonogram.NNBoard;
import aiprog.nonogram.NonoGram1;
import aiprog.vcp.VCP;
import aiprog.vcp.VCPGraph;
import aiprog.twentyfortyeigth.TfeBoard;


public class Main {

	/**
	 * @param args
	 */
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		TfeBoard newBoard = new TfeBoard();
		TFEGraphics tfeGraphics = new TFEGraphics();
		tfeGraphics.setBoard(newBoard, false);
		/*
		if(args.length >= 2){
			if(args[0].toLowerCase().equals("flowfree")){
				try {
					FFBoard board = new FFBoard(args[1]);
					FFGraphics ffgraph = new FFGraphics(board);
					FlowFree ff;
					if(args.length == 3){
						ff = new FlowFree(board.createInitState(), ffgraph, Integer.parseInt(args[2]));
					}else{
						ff = new FlowFree(board.createInitState(), ffgraph,0);
					}
					 
					System.out.println("Path length: " + ff.getPathLength());
					System.out.println("Expanded nodes: " + ff.getSteps());
					System.out.println("Generated states: " + (ff.getGeneratedChildren()+1));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else if(args[0].toLowerCase().equals("nonogram")){
				try {
					NNBoard board = new NNBoard(args[1]);
					NNGraphics graphics = new NNGraphics(board);
					NNStateNode sn = new NNStateNode(board);
					NonoGram1 nono = new NonoGram1(sn, graphics);
					System.out.println("Path length: " + nono.getPathLength());
					System.out.println("Expanded nodes: " + nono.getSteps());
					System.out.println("Generated states: " + (nono.getGeneratedChildren()+1));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else if(args[0].toLowerCase().equals("vcp")){
				if(args.length == 3){
					try {
						VCPGraph graph = new VCPGraph(args[1], Integer.parseInt(args[2]));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}else if(args[0].toLowerCase().equals("navigation")){
				
			}
		}else{
			System.out.println("Wrong number of arguments");
		}
		/*
		
		
		/*
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
