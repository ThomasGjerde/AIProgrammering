package aiprog.gui;

import aiprog.model.Point;

public class GridText {
	public Point position;
	public int transX = 0;
	public int transY = 0;
	public String text;
	public GridText(Point position, String text){
		this.position = position;
		this.text = text;
	}
	public GridText(){
		
	}

}
