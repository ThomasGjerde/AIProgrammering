package aiprog.model;

public class Constraint {
	public OldColorNode x;
	public OldColorNode y;
	public Constraint(OldColorNode xNode, OldColorNode yNode){
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
	public OldColorNode getConstraintX(){
		return x;
	}
	public OldColorNode getConstraintY(){
		return y;
	}
}
