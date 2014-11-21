package aiprog.gui;

import aiprog.model.Point;

public class GridText {
	public Point position;
	public int transX = 0;
	public int transY = 0;
	public String text;
	public int sizeMod = 0;
	public GridText(Point position, String text){
		this.position = position;
		this.text = text;
	}
	public GridText(Point position, String text, int sizeMod){
		this.position = position;
		this.text = text;
		this.sizeMod = sizeMod;
	} 
	public GridText(){
		
	}

}
