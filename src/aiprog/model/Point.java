package aiprog.model;

public class Point {
	//Clean up this shit
	public int x;
	public int y;
	public Point(int x, int y){
		this.x = x;
		this.y = y;
	}
	public Point(){
		
	}
	public int getManhattanDistance(Point endPoint){
		int xDist = Math.abs(this.x - endPoint.x);
		int yDist = Math.abs(this.y - endPoint.y);
		return (xDist + yDist);
	}
}
