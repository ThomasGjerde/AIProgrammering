package aiprog.twentyfortyeigth;

import java.util.ArrayList;

import aiprog.model.Direction;
import aiprog.model.Move;
import aiprog.model.Point;

public class MinMax {
	TfeBoard board;
	public MinMax(TfeBoard board){
		this.board = board;
	}
	public Move search(double alpha, double beta, int depth){
		//System.out.println("Search called, depth: " + depth + " Player: " + board.getPlayer());
		Move bestMove = new Move(null, -10001);
		if(board.getPlayer()){
			bestMove.setHeuristic(alpha);
			Move currentMove = new Move(null, -10001);
			for(Direction direction : Direction.values()){
				if(board.isLegalMove(direction)){
					TfeBoard newBoard = this.board.cloneBoard();
					newBoard.move(direction);
					//Put win check here
					newBoard.setPlayer(false);
					MinMax newMinMax = new MinMax(newBoard);
					if(depth == 0){
						currentMove.setDirection(direction);
						currentMove.setHeuristic(newBoard.heuristic());
					}else{
						currentMove = newMinMax.search(bestMove.getHeuristic(), beta, depth - 1);
					}
					if(currentMove.getHeuristic() > bestMove.getHeuristic()){
						bestMove.setHeuristic(currentMove.getHeuristic());
						bestMove.setDirection(currentMove.getDirection());
					}
					if(bestMove.getHeuristic() > beta){
						return new Move(bestMove.getDirection(),beta);
					}
				}
			}
		}else{ //Player == false
			bestMove.setHeuristic(beta);
			ArrayList<Point> availableTiles = this.board.getUnoccupiedTiles();
			int[] values = { 2 , 4 };
			for(Point tilePos : availableTiles){
				for(int value : values){
					TfeBoard newBoard = this.board.cloneBoard();
					newBoard.setPlayer(true);
					newBoard.getBoard()[tilePos.y][tilePos.x] = value;
					MinMax newMinMax = new MinMax(newBoard);
					Move currentMove = newMinMax.search(alpha, bestMove.getHeuristic(), depth);
					if(currentMove.getHeuristic() < bestMove.getHeuristic()){
						bestMove.setHeuristic(currentMove.getHeuristic());
					}
					if(bestMove.getHeuristic() < alpha){
						return new Move(null, alpha);
					}
				}
			}			
			
		}
		return bestMove;
	}
}
