package aiprog.model;

public class DoublePoint {
	private double x;
	private double y;
	public DoublePoint(double x, double y){
		this.x = x;
		this.y = y;
	}
	public DoublePoint(){
		
	}
	public void setX(double x){
		this.x = x;
	}
	public double getX(){
		return this.x;
	}
	public void setY(double y){
		this.y = y;
	}
	public double getY(){
		return this.y;
	}
	public Point toPoint(){
		Point retPoint = new Point((int)this.x,(int)this.y);
		return retPoint;
	}
}
