package aiprog.gui;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;

import aiprog.model.Direction;
import aiprog.model.Point;
import aiprog.utility.GraphicsHelper;

@SuppressWarnings("serial")
public class GridCanvas extends Canvas
{
	Color[][] cells;
	ArrayList<Line> lines = new ArrayList<Line>();
	ArrayList<GridText> texts = new ArrayList<GridText>();
	boolean showGrid = false;
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
	public void clearAllColors(){
		int sizeX = cells.length;
		int sizeY = cells[0].length;
		cells = new Color[sizeX][sizeY];
	}
	public void setShowGrid(boolean showGrid){
		this.showGrid = showGrid;
	}
	public void setCellColor(int x, int y, Color color){
		cells[x][y] = color;
		repaint();
	}
	public void setCellColorWithoutRepaint(int x, int y, Color color){
		cells[x][y] = color;
	}
	public void transMoveTexts(ArrayList<Point> positions, Direction dir){
		ArrayList<GridText> textList = pointsToGridtexts(positions);
		for(int i = 0; i < textList.size(); i++){
			if(dir == Direction.DOWN){
				if(textList.get(i).position.y < cells[0].length -1){
					textList.get(i).transY += 1;
				}
			}else if(dir == Direction.UP){
				if(textList.get(i).position.y > 0){
					textList.get(i).transY -= 1;
				}
			}else if(dir == Direction.RIGHT){
				if(textList.get(i).position.x < cells.length -1){
					textList.get(i).transX += 1;
				}
			}else{
				if(textList.get(i).position.x > 0){
					textList.get(i).transX -= 1;
				}
			}

		}
		repaint();
		
	}
	private ArrayList<GridText> pointsToGridtexts(ArrayList<Point> positions){
		ArrayList<GridText> retArray = new ArrayList<GridText>();
		for(Point pos : positions){
			for(GridText gt : texts){
				if(gt.position.x == pos.x && gt.position.y == pos.y){
					retArray.add(gt);
				}
			}
		}
		return retArray;
	}
	protected void renderGraphics(Graphics g) {
		for(int i = 0; i < cells.length; i++){
			for(int j = 0; j < cells[0].length; j++){
				int cellX = scale + (i*scale);
				int cellY = scale + (j*scale);
				if(cells[i][j] != null){
					g.setColor(cells[i][j]);
					g.fillRect(cellX, cellY, scale - spacing, scale - spacing);
				}
				if(showGrid){
					g.setColor(Color.BLACK);
					g.drawRect(cellX, cellY, scale - spacing, scale - spacing);
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
			g.setFont(new Font("TimesRoman",Font.PLAIN,((scale-spacing)/2) + gt.sizeMod));
			g.drawString(gt.text, (pos.x + gt.transX) - ((scale-spacing)/4), (pos.y + gt.transY) + ((scale-spacing)/4));
		}
	}
	public void addLine(Line line){
		lines.add(line);
	}
	public void addText(GridText gt){
		texts.add(gt);
	}
	
}
