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

        private List<Point> blackCells;
        private List<Point> redCells;

        public Grid() {
            blackCells = new ArrayList<>(25);
            redCells = new ArrayList<>(25);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            for (Point blackCell : blackCells) {
                int cellX = 40 + (blackCell.x * 40);
                int cellY = 40 + (blackCell.y * 40);
                g.setColor(Color.BLACK);
                g.fillRect(cellX, cellY, 40, 40);
            }
            for (Point redCell : redCells) {
            	int cellX = 40 + (redCell.x * 40);
            	int cellY = 40 + (redCell.y * 40);
            	g.setColor(Color.RED);
            	g.fillRect(cellX, cellY, 40, 40);
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

    }

}
