package aiprog.gui;

import aiprog.model.DoublePoint;
import aiprog.model.Point;

public class Line {
	public Point startPoint = new Point();
	public Point endPoint = new Point();
	public Line(Point startPoint, Point endPoint){
		this.startPoint = startPoint;
		this.endPoint = endPoint;
	}
	public Line(DoublePoint startPoint, DoublePoint endPoint){
		this.startPoint = new Point((int)startPoint.getX(), (int)startPoint.getY());
		this.endPoint = new Point((int)endPoint.getX(),(int)endPoint.getY());
	}
}
