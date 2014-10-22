package aiprog.model;

import java.awt.Color;
import java.util.ArrayList;

public class FFNode extends NavNode {
	
	ArrayList<Color> domain = new ArrayList<Color>();
	Color nodeColor;
	boolean endPoint;
	int heuristic;
	
	public FFNode (Point position, boolean end, Color color){
		super(position);
		endPoint = end;
		setColor(color);
		heuristic = 0;
	}
	
	public FFNode (Point position, int k){
		super(position);
		standardColors(k);
		heuristic = 0;
	}
	
	public void setColor(Color color){
		nodeColor = color;
		domain.clear();
		domain.add(color);
		heuristic = 0;
	}
	
	public Color getColor(){
		return nodeColor;
	}
	
	public void setHeuristic(){
		
	}
	
	public int getHeuristic(){
		return heuristic;
	}
	
	public boolean isEndPoint(){
		return endPoint;
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
	
	public void calcHeuristic(){
		//Utregning for heuristic for denne noden
		//Dette er unikt for vær state, så vi kan evt nullstille heuristc/dvs ikke ta det med over når vi lager en ny state
		//Har ikke funnet ut om det går og ta med openlist nedover, tviler egentlig på det... kanskje hvis vi har parents, kan være tricky
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
