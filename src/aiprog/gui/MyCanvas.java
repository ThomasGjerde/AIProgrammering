package aiprog.gui;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;

public class MyCanvas
{
	public static class Grid extends JPanel {
		private static int scale = 30;
        private List<Point> blackCells;
        private List<Point> redCells;
        private List<Point> whiteCells;

        public Grid() {
            blackCells = new ArrayList<>(50);
            redCells = new ArrayList<>(50);
            whiteCells = new ArrayList<>(50);
        }

        @Override
        protected synchronized void paintComponent(Graphics g) {
            super.paintComponent(g);
            for (Point blackCell : blackCells) {
                int cellX = scale + (blackCell.x * scale);
                int cellY = scale + (blackCell.y * scale);
                g.setColor(Color.BLACK);
                g.fillRect(cellX, cellY, scale, scale);
            }
            for (Point redCell : redCells) {
            	int cellX = scale + (redCell.x * scale);
            	int cellY = scale + (redCell.y * scale);
            	g.setColor(Color.RED);
            	g.fillRect(cellX, cellY, scale, scale);
            }
            for (Point whiteCell : whiteCells){
            	int cellX = scale + (whiteCell.x * scale);
            	int cellY = scale + (whiteCell.y * scale);
            	g.setColor(Color.WHITE);
            	g.fillRect(cellX, cellY, scale, scale);
            	//g.drawChars("End".toCharArray(),0,3,cellX,cellY);
            }
            //g.setColor(Color.BLACK);
        }

        public void fillCellBlack(int x, int y) {
            blackCells.add(new Point(x, y));
            repaint();
        }
        public void fillCellRed(int x, int y){
        	redCells.add(new Point(x, y));
        	repaint();
        }
        public void clearRed(){
        	redCells.clear();
        }
        public void fillCellWhite(int x, int y){
        	whiteCells.add(new Point(x, y));
        	repaint();
        }

    }

}
