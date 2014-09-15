package aiprog.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import aiprog.gui.MyCanvas.Grid;
import aiprog.model.Board;
import aiprog.model.Node;
import aiprog.model.SearchTemp;

public class Graphics implements ActionListener {
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
        
        //Combobox
        String[] algorithmStrings = {"", "A*", "BFS", "DFS" };
        JComboBox algorithmList = new JComboBox(algorithmStrings);
        algorithmList.setSelectedIndex(0);
        algorithmList.setSize(100, 30);
        algorithmList.addActionListener(this);
        grid.add(algorithmList);
	}
	public void setBoard(Board board){
		grid.clearRed(); //Delete old tail
        for(int i = 0; i < board.sizeX; i++)
        {
        	for(int j = board.sizeY; j > 0; j--)
        	{
        		if(board.boardArray[i][board.sizeY - j].status == Node.Status.Obstacle){
        			grid.fillCellBlack(i, j);
        		}
        		else if(board.boardArray[i][board.sizeY - j].status == Node.Status.Visiting){
        			grid.fillCellRed(i, j);
        		}else if(board.boardArray[i][board.sizeY - j].positionX == board.endX && board.boardArray[i][board.sizeY - j].positionY == board.endY){
        			grid.fillCellWhite(i, j);
        		}
        	}
        }
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		final SearchTemp temp = new SearchTemp(board, this);
		if(((JComboBox)e.getSource()).getSelectedIndex() == 1){
			Thread worker1 = new Thread(){
				public void run(){
					board.resetBoard();
				temp.aStar(board.boardArray[board.startX][board.startY]);
				}
				};
			worker1.start();
		}
		if(((JComboBox)e.getSource()).getSelectedIndex() == 2){
			Thread worker2 = new Thread(){
				public void run(){
					board.resetBoard();
				temp.bfs(board.boardArray[board.startX][board.startY]);
				}
				};
			worker2.start();
		}
		if(((JComboBox)e.getSource()).getSelectedIndex() == 3){
			Thread worker3 = new Thread(){
				public void run(){
					board.resetBoard();
				temp.dfs(board.boardArray[board.startX][board.startY]);
				}
			};
			worker3.start();
		}
	}
}
