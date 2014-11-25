package aiprog.twentyfortyeigth;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import aiprog.gui.TFEGraphics;
import aiprog.model.Move;

public class TFE {
	private final int targetValue;
	private static double initAlpha = -100000;
	private static double initBeta = 100000;
	public TFE(int targetValue) throws InterruptedException, ExecutionException{
		this.targetValue = targetValue;
	}
	public void runTFE(boolean animate){
		TfeBoard board = new TfeBoard(true,this.targetValue);
		int depth = 2;
		TFEGraphics graphics = new TFEGraphics();
		graphics.setBoard(board);
		while(!board.hasFailed() && !board.victoryCheck()) {
			depth = 2;
			MinMax minMax = new MinMax(board);
			Move bestMove = minMax.search(initAlpha, initBeta, depth);
			while(bestMove.getDirection() == null && depth > 0){
				depth--;
				bestMove = minMax.search(initAlpha, initBeta, depth);
			}
			board.move(bestMove.getDirection());
			board.generateRandomNumber();
			System.out.println(bestMove.getDirection());
			if(animate){
				graphics.animateSetBoard(board, bestMove.getDirection());
			}else{
				graphics.setBoard(board);
			}
			
		}	
		if(board.hasFailed()){
			System.out.println("Failed");
			graphics.setBoard(board);
		}else{
			System.out.println("Victory");
			graphics.setBoard(board);
		}
	}
	@SuppressWarnings("rawtypes")
	public void runMultiThreadedTest(int iterations) throws InterruptedException, ExecutionException{
		int wins = 0;
		int fails = 0;
		ExecutorService executorService = Executors.newFixedThreadPool(6);
		List<Future> futures = new ArrayList<>();
		for(int i = 0; i < iterations; i++){
			Future<Boolean> future = executorService.submit(new Callable<Boolean>(){
                @Override
                public Boolean call() throws Exception {
                	
                    return runTFETest();
                 }
                 });
			futures.add(future);
		}
		
		for(Future future : futures) {
		    if((boolean)future.get() == true){
		    	wins++;
				System.out.println("Wins: " + wins);
				System.out.println("Fails: " + fails);
		    }else{
		    	fails++;
				System.out.println("Wins: " + wins);
				System.out.println("Fails: " + fails);
		    }
		}
		System.out.println("----------------");
		System.out.println("Wins: " + wins);
		System.out.println("Fails: " + fails);
		System.out.println(((double)wins/(double)iterations)*100 + "% Win");
	}
	private boolean runTFETest(){
		int depth = 2;
		TfeBoard board = new TfeBoard(true,this.targetValue);
		while(!board.hasFailed() && !board.victoryCheck()) {
			depth = 2;
			MinMax minMax = new MinMax(board);
			Move bestMove = minMax.search(initAlpha, initBeta, 2);
			while(bestMove.getDirection() == null && depth > 0){
				depth--;
				bestMove = minMax.search(initAlpha, initBeta, depth);
			}
			board.move(bestMove.getDirection());
			board.generateRandomNumber();
		}	
		if(board.hasFailed()){
			System.out.println("Failed at " + Math.pow(2, board.maxValue()));
			return false;
		}else{
			System.out.println("Victory");
			return true;
		}
	}
}
