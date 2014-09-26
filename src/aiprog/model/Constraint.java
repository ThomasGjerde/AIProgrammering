package aiprog.model;

public class Constraint {
	public ColorNode x;
	public ColorNode y;
	public Constraint(ColorNode xNode, ColorNode yNode){
		this.x = xNode;
		this.y = yNode;
	}
	
	public boolean checkConstrain(){
		if(this.x.getColor() != null && this.y.getColor() != null){
			if(this.x.getColor() != this.y.getColor()){
				return true;
			}
		}
		return false;
	}
	public ColorNode getConstraintX(){
		return x;
	}
	public ColorNode getConstraintY(){
		return y;
	}
}
