package aiprog.model;

import java.awt.Color;
import java.util.ArrayList;

public class ReallyOldColorNode extends Node {
	public ArrayList<Color> standardColors;
	public ArrayList<Color> domain;
	public ArrayList<Constraint> constraints;
	public Color nodeColor;
	public int id = -1;
	public boolean affected;
	
	public ReallyOldColorNode(Point position){
		super(position);
		standardColors = new ArrayList<Color>();
		fillStandardColors();
		domain = new ArrayList<Color>();
		constraints = new ArrayList<Constraint>();
		affected = false;
	}
	
	public void fillStandardColors(){
		standardColors.add(Color.BLUE);
		standardColors.add(Color.CYAN);
		standardColors.add(Color.DARK_GRAY);
		standardColors.add(Color.GRAY);
		standardColors.add(Color.GREEN);
		standardColors.add(Color.LIGHT_GRAY);
		standardColors.add(Color.MAGENTA);
		standardColors.add(Color.ORANGE);
		standardColors.add(Color.PINK);
		standardColors.add(Color.RED);
		standardColors.add(Color.YELLOW);	
	}
	
	public ArrayList<Color> getDomain(){
		if(domain == null){
			return null;
		}
		return domain;
	}
	/*
	public boolean checkConstrain(){
		if(this.getColor() != null && this.y.getColor() != null){
			if(this.getColor() != this.y.getColor()){
				return true;
			}
		}
		return false;
	}*/
	
	public boolean checkConstrain(){
		for(int i=0; i<this.getChildren().size(); i++){
			OldColorNode midChild = (OldColorNode) this.getChildren().get(i);
			if(this.getColor() != null && midChild.getColor() != null){
				if(this.getColor() != midChild.getColor()){
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean reduseDomain(Color removeColor){
		if(!this.getDomain().isEmpty()){
			if(this.getDomain().contains(removeColor)){
				getDomain().remove(removeColor);
				return true;
			}
		}
		return false;
	}
	
	public void addDomain(){
		domain.add(standardColors.get(0));
		standardColors.remove(0);
	}
	
	public Color getColor(){
		if(nodeColor == null){
			return null;
		}
		return nodeColor;
	}
	
	
	//HOHEY HER M� TING FIKSES! BECAUSE THIS SHIT IS GONNA FUCK US UP YO
	public boolean setColor(Color color){
		for(int i=0;i<domain.size();i++){
			if(color == domain.get(i)){
				nodeColor = color;
				return true;
			}
		}
		return false;
	}
	
	public boolean checkColor(Color color){
		if(domain.contains(color)){
			return true;
		}
		return false;
	}
	
	public void fillConstraints(){
		for(int i=0; i<super.getChildren().size(); i++){
			constraints.add(new Constraint(this, (OldColorNode) super.getChildren().get(i)));
		}
	}

	@Override
	public ArrayList<Node> getUnoccupiedChildren() {
		// TODO Auto-generated method stub
		return null;
	}
}
