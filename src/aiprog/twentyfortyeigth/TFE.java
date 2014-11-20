package aiprog.twentyfortyeigth;

import aiprog.gui.TFEGraphics;
import aiprog.model.Move;

public class TFE {
	public TFE(){
		TfeBoard board = new TfeBoard(true);
		TFEGraphics graphics = new TFEGraphics();
		graphics.setBoard(board);
		while(true) {//!board.isWin && board.hasFailed
			MinMax minMax = new MinMax(board);
			Move bestMove = minMax.search(-10000, 10000, 3);
			board.move(bestMove.getDirection());
			board.generateRandomNumber();
			graphics.animateSetBoard(board, bestMove.getDirection());
		}
		//Put Win || Fail check here
	}
}
