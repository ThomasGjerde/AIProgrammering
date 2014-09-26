package aiprog.gui;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

import aiprog.model.Line;

public class GridCanvas extends Canvas
{
	Color[][] cells;
	ArrayList<Line> lines = new ArrayList<Line>();
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
	public void setCellColorWithoutRepaint(int x, int y, Color color){
		cells[x][y] = color;
	}
	protected void renderGraphics(Graphics g) {
		for(int i = 0; i < cells.length; i++){
			for(int j = 0; j < cells[0].length; j++){
				if(cells[i][j] != null){
					int cellX = scale + (i*scale);
					int cellY = scale + (j*scale);
					g.setColor(cells[i][j]);
					g.fillRect(cellX, cellY, scale - spacing, scale - spacing);
				}
			}
		}
		drawLines(g);
		
	}
	private void drawLines(Graphics g){
		int variance = 0;
		for(int i = 0; i < lines.size(); i++){
			Line line = lines.get(i);
			int startX = (line.startPoint.x*scale) + scale + ((scale-spacing)/2);
			int startY = (line.startPoint.y*scale) + scale + ((scale-spacing)/2);
			int endX = (line.endPoint.x*scale) + scale + ((scale-spacing)/2);
			int endY = (line.endPoint.y*scale) + scale + ((scale-spacing)/2);
			/*
			if(variance < scale/4){
				startX += variance;
				startY += variance;
				variance += 1;
			}else
			{
				variance = 0;
			}
			*/
			g.drawLine(startX, startY, endX, endY);
		}
	}
	public void addLine(Line line){
		lines.add(line);
	}
}
