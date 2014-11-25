package aiprog.main;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import aiprog.flowfree.FFBoard;
import aiprog.flowfree.FlowFree;
import aiprog.gui.FFGraphics;
import aiprog.gui.NNGraphics;
import aiprog.gui.TFEGraphics;
import aiprog.model.Direction;
import aiprog.model.NNStateNode;
import aiprog.navigation.Board;
import aiprog.navigation.GridBestFirstSearch;
import aiprog.navigation.GridBreadthFirstSearch;
import aiprog.navigation.GridDepthFirstSearch;
import aiprog.nonogram.NNBoard;
import aiprog.nonogram.NonoGram1;
import aiprog.vcp.VCP;
import aiprog.vcp.VCPGraph;
import aiprog.twentyfortyeigth.TFE;
import aiprog.twentyfortyeigth.TfeBoard;


public class Main {

	/**
	 * @param args
	 */
	@SuppressWarnings("unused")
	public static void main(String[] args) {

		
		if(args.length >= 1){
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
				if(args[1].toLowerCase().equals("best")){
					try {
						Board board = new Board(args[2]);
						GridBestFirstSearch gbfs = new GridBestFirstSearch(board.boardArray[board.startPos.x][board.startPos.y], board.endPos, board);
						gbfs.search();
						System.out.println("Path: " + gbfs.getPathLength());
						System.out.println("Steps: " + gbfs.getSteps());
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}else if(args[1].toLowerCase().equals("breadth")){
					try {
						Board board = new Board(args[2]);
						GridBreadthFirstSearch gbfs = new GridBreadthFirstSearch(board.boardArray[board.startPos.x][board.startPos.y], board.endPos, board);
						gbfs.search();
						System.out.println("Path: " + gbfs.getPathLength());
						System.out.println("Steps: " + gbfs.getSteps());
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}else if(args[1].toLowerCase().equals("depth")){
					try {
						Board board = new Board(args[2]);
						GridDepthFirstSearch gdfs = new GridDepthFirstSearch(board.boardArray[board.startPos.x][board.startPos.y], board.endPos, board);
						gdfs.search();
						System.out.println("Path: " + gdfs.getPathLength());
						System.out.println("Steps: " + gdfs.getSteps());
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}else if(args[0].toLowerCase().equals("2048")){
				try
				{
					TFE tfe = new TFE(Integer.parseInt(args[1]));
					if(args.length == 3 && args[2].toLowerCase().equals("noanimation")){
						tfe.runTFE(false);
					}else if(args.length == 4 && args[2].toLowerCase().equals("test")){
						tfe.runMultiThreadedTest(Integer.parseInt(args[3]));
					}else{
						tfe.runTFE(true);
					}
				} catch (InterruptedException | ExecutionException e){
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}else{
			System.out.println("Wrong number of arguments");
		}		
	}

}
