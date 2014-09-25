package aiprog.model;

import java.awt.Color;
import java.util.ArrayList;

public class ColorNode extends Node {
	public ArrayList<Color> standardColors;
	public ArrayList<Color> domain;
	public ArrayList<Constraint> constraints;
	public Color nodeColor;
	
	public ColorNode(Point position){
		super(position);
		standardColors = new ArrayList<Color>();
		fillStandardColors();
		domain = new ArrayList<Color>();
		constraints = new ArrayList<Constraint>();
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
	
	public boolean setColor(Color color){
		for(int i=0;i<domain.size();i++){
			if(color == domain.get(i)){
				nodeColor = color;
				return true;
			}
		}
		return false;
	}
	
	public void fillConstraints(){
		for(int i=0; i<super.getChildren().size(); i++){
			constraints.add(new Constraint(this, (ColorNode) super.getChildren().get(i)));
		}
	}
}
