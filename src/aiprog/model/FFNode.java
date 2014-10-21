package aiprog.model;

import java.awt.Color;
import java.util.ArrayList;

public class FFNode extends NavNode {
	
	ArrayList<Color> domain = new ArrayList<Color>();
	Color nodeColor;
	boolean endPoint;
	
	public FFNode (Point position, boolean end, Color color){
		super(position);
		endPoint = end;
		setColor(color);
	}
	
	public FFNode (Point position, int k){
		super(position);
		standardColors(k);
	}
	
	public void setColor(Color color){
		nodeColor = color;
		domain.clear();
		domain.add(color);
	}
	
	public Color getColor(){
		return nodeColor;
	}
	
	public boolean isEndPoint(){
		if(endPoint){
			return true;
		}else{
			return false;
		}
	}
	
	public void setEndPointStatus(boolean status){
		endPoint = status;
	}
	
	public ArrayList<Color> getDomain(){
		return domain;
	}
	
	public void setDomain(ArrayList<Color> newDomain){
		//Husk at dette er pass by referanse
		//Vi kan enten clone det her, eller i metoden for nye states
		
		//Hvis clone allerede er gjort
		//domain = newDomain;
		
		//Hvis clone ikke er gjort
		domain.clear();
		for(int i=0; i<newDomain.size(); i++){
			Color midColor = null;
			midColor = newDomain.get(i);
			domain.add(midColor);
		}
	}
	
	public void standardColors(int k){
		ArrayList<Color> standardColors = new ArrayList<Color>();
		standardColors.add(Color.RED);
		standardColors.add(Color.BLUE);
		standardColors.add(Color.CYAN);
		standardColors.add(Color.DARK_GRAY);
		standardColors.add(Color.YELLOW);
		standardColors.add(Color.LIGHT_GRAY);
		standardColors.add(Color.GREEN);
		standardColors.add(Color.PINK);
		standardColors.add(Color.MAGENTA);
		standardColors.add(Color.ORANGE);
		
		for(int i=0; i<k; i++){
			domain.add(standardColors.get(i));
		}
		
		
	}
	
}
