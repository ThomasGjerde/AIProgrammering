package aiprog.model;

public class Move {
	private Direction direction;
	private double heuristic;
	public Move(Direction direction, double heuristic){
		this.direction = direction;
		this.heuristic = heuristic;
	}
	public Direction getDirection() {
		return direction;
	}
	public void setDirection(Direction direction) {
		this.direction = direction;
	}
	public double getHeuristic() {
		return heuristic;
	}
	public void setHeuristic(double heuristic) {
		this.heuristic = heuristic;
	}
	
}
