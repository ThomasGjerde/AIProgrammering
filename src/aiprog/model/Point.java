package aiprog.model;

public class Point {
	//Clean up this shit
	public int x;
	public int y;
	private double dX;
	private double dY;
	public Point(int x, int y){
		this.x = x;
		this.y = y;
	}
	public Point(){
		
	}
	public Point(double x,double y){
		this.dX = x;
		this.dY = y;
	}
	public double getDoubleX(){
		return dX;
	}
	public void setDoubleX(double x){
		this.dX = x;
	}
	public double getDoubleY(){
		return dY;
	}
	public void setDoubleY(double y){
		this.dY = y;
	}
	public int getManhattanDistance(Point endPoint){
		int xDist = Math.abs(this.x - endPoint.x);
		int yDist = Math.abs(this.y - endPoint.y);
		return (xDist + yDist);
	}
}
