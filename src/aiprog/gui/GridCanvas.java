package aiprog.gui;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;

import aiprog.model.Point;
import aiprog.utility.GraphicsHelper;

@SuppressWarnings("serial")
public class GridCanvas extends Canvas
{
	Color[][] cells;
	ArrayList<Line> lines = new ArrayList<Line>();
	ArrayList<GridText> texts = new ArrayList<GridText>();
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
		drawTexts(g);
	}
	private void drawLines(Graphics g){
		g.setColor(Color.BLACK);
		for(int i = 0; i < lines.size(); i++){
			//g.setColor(GraphicsHelper.getRandomColor()); Random colored line
			Line line = lines.get(i);
			Point startPos = calcCenterPosition(line.startPoint);
			Point endPos = calcCenterPosition(line.endPoint);
			g.drawLine(startPos.x, startPos.y, endPos.x, endPos.y);
		}
	}
	private Point calcCenterPosition(Point pos){
		int X = (pos.x*scale) + scale + ((scale-spacing)/2);
		int Y = (pos.y*scale) + scale + ((scale-spacing)/2);
		return new Point(X,Y);
	}
	private void drawTexts(Graphics g){
		for(int i = 0; i < texts.size(); i++){
			GridText gt = texts.get(i);
			Point pos = calcCenterPosition(gt.position);
			g.setColor(Color.BLACK);
			g.setFont(new Font("TimesRoman",Font.PLAIN,(scale-spacing)/2));
			g.drawString(gt.text, pos.x - ((scale-spacing)/4), pos.y + ((scale-spacing)/4));
		}
	}
	public void addLine(Line line){
		lines.add(line);
	}
	public void addText(GridText gt){
		texts.add(gt);
	}
	
}
