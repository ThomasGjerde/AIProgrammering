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
	private int wins = 0;
	private int fails = 0;
	public TFE() throws InterruptedException, ExecutionException{
		ExecutorService executorService = Executors.newFixedThreadPool(10);
		List<Future> futures = new ArrayList<>();
		for(int i = 0; i < 10; i++){
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
		    }else{
		    	fails++;
		    }
		}
		System.out.println("----------------");
		System.out.println("Wins: " + wins);
		System.out.println("Fails: " + fails);
		
	}
	private boolean runTFETest(){
		TfeBoard board = new TfeBoard(true,2048);
		//TFEGraphics graphics = new TFEGraphics();
		//graphics.setBoard(board);
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
			//graphics.setBoard(board);
			return false;
		}else{
			System.out.println("Victory");
			//graphics.setBoard(board);
			return true;
		}
	}
}
