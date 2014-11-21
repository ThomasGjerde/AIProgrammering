package aiprog.twentyfortyeigth;

import aiprog.gui.TFEGraphics;
import aiprog.model.Move;

public class TFE {
	public TFE(){
		int wins = 0;
		int fails = 0;
		for(int i = 0; i < 10; i++){
			TfeBoard board = new TfeBoard(true,2048);
			TFEGraphics graphics = new TFEGraphics();
			graphics.setBoard(board);
			while(!board.hasFailed() && !board.victoryCheck()) {
				MinMax minMax = new MinMax(board);
				Move bestMove = minMax.search(-10000, 10000, 2);
				board.move(bestMove.getDirection());
				board.generateRandomNumber();
				//System.out.println(bestMove.getDirection());
				//graphics.animateSetBoard(board, bestMove.getDirection());
			}	
			if(board.hasFailed()){
				System.out.println("Failed");
				graphics.setBoard(board);
				fails++;
			}else{
				System.out.println("Victory");
				graphics.setBoard(board);
				wins++;
			}
		}
		System.out.println("Wins: " + wins);
		System.out.println("Fails: " + fails);
		
	}
}
