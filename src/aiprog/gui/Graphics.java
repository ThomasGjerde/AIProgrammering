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

public class Graphics implements ActionListener {
	Grid grid;
	public Graphics(){
		try
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//Board
        grid = new Grid();
        JFrame window = new JFrame();
        window.setSize(840, 560);
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
        for(int i = 0; i < board.sizeX; i++)
        {
        	for(int j = board.sizeY; j > 0; j--)
        	{
        		if(board.boardArray[i][board.sizeY - j].status == Node.Status.Obstacle){
        			grid.fillCellBlack(i, j);
        		}
        		else if(board.boardArray[i][board.sizeY - j].status == Node.Status.Visiting){
        			grid.fillCellRed(i, j);
        		}
        	}
        }
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println(((JComboBox)e.getSource()).getSelectedItem().toString());
	}
}
