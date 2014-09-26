package aiprog.gui;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Graphics {
	protected GridCanvas grid;
	public Graphics(int sizeX, int sizeY){
		try{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e){
			e.printStackTrace();
		}
        grid = new GridCanvas(sizeX,sizeY);
        JFrame window = new JFrame();
        window.setSize(1024, 730);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.add(grid);
        window.setVisible(true);
	}
}
