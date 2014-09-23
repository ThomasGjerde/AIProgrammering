package aiprog.gui;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import aiprog.gui.MyCanvas.Grid;
import aiprog.model.Board;
import aiprog.model.Node;

public class Graphics {
	Grid grid;
	Board board;
	public Graphics(Board board){
		this.board = board;
		try{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e){
			e.printStackTrace();
		}
		//Board
        grid = new Grid();
        JFrame window = new JFrame();
        window.setSize(1024, 730);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.add(grid);
        window.setVisible(true);
	}
	public void setBoard(Board board){
		grid.clearRed(); //Delete old tail
        for(int i = 0; i < board.size.x; i++)
        {
        	for(int j = board.size.y; j > 0; j--)
        	{
        		if(board.boardArray[i][board.size.y - j].status == Node.Status.Obstacle){
        			grid.fillCellBlack(i, j);
        		}
        		else if(board.boardArray[i][board.size.y - j].status == Node.Status.Visiting){
        			grid.fillCellRed(i, j);
        		}else if(board.boardArray[i][board.size.y - j].pos.x == board.endPos.x && board.boardArray[i][board.size.y - j].pos.y == board.endPos.y){
        			grid.fillCellWhite(i, j);
        		}
        	}
        }
	}
}
