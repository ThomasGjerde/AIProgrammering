package aiprog.gui;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class GridCanvas extends Canvas
{
	Color[][] cells;
	public GridCanvas(int sizeX, int sizeY) {
		super();
		cells = new Color[sizeX][sizeY];
	}
	public void clearColor(Color color){
		for(int i = 0; i < cells.length; i++){
			for(int j = 0; j < cells[0].length; j++){
				if(cells[i][j] != null){
					if(cells[i][j].toString().equals(color.toString())){
						cells[i][j] = null;
					}
				}

			}
		}
	}
	public void setCellColor(int x, int y, Color color){
		cells[x][y] = color;
		repaint();
	}
	protected void renderGraphics(Graphics g) {
		for(int i = 0; i < cells.length; i++){
			for(int j = 0; j < cells[0].length; j++){
				if(cells[i][j] != null){
					int cellX = scale + (i*scale);
					int cellY = scale + (j*scale);
					g.setColor(cells[i][j]);
					g.fillRect(cellX, cellY, scale, scale);
				}
			}
		}
		
	}
	public void drawLine(Point startPoint, Point endPoint){
		
	}
}
