package aiprog.model;

public class Move {
	private Direction direction;
	private int heuristic;
	public Move(Direction direction, int heuristic){
		this.direction = direction;
		this.heuristic = heuristic;
	}
	public Direction getDirection() {
		return direction;
	}
	public void setDirection(Direction direction) {
		this.direction = direction;
	}
	public int getHeuristic() {
		return heuristic;
	}
	public void setHeuristic(int heuristic) {
		this.heuristic = heuristic;
	}
	
}
