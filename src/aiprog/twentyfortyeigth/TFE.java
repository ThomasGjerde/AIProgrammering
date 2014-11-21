package aiprog.twentyfortyeigth;

import aiprog.gui.TFEGraphics;
import aiprog.model.Move;

public class TFE {
	public TFE(){
		TfeBoard board = new TfeBoard(true,2048);
		TFEGraphics graphics = new TFEGraphics();
		graphics.setBoard(board);
		while(!board.hasFailed() && !board.victoryCheck()) {//!board.isWin && board.hasFailed
			//System.out.println("While loop");
			MinMax minMax = new MinMax(board);
			Move bestMove = minMax.search(-10000, 10000, 2);
			board.move(bestMove.getDirection());
			board.generateRandomNumber();
			System.out.println(bestMove.getDirection());
			graphics.animateSetBoard(board, bestMove.getDirection());
		}
		if(board.hasFailed()){
			System.out.println("Failed");
		}else{
			System.out.println("Victory");
		}
	}
}
