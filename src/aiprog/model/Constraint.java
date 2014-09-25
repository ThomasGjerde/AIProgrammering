package aiprog.model;

public class Constraint {
	ColorNode x;
	ColorNode y;
	public Constraint(ColorNode xNode, ColorNode yNode){
		this.x = xNode;
		this.y = yNode;
	}
	
	public boolean checkConstrain(){
		if(x != y){
			return true;
		}
		return false;
	}
}
