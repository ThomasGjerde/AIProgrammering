package aiprog.model;

import java.awt.Color;
import java.util.ArrayList;

public class FFNode extends NavNode {
	
	ArrayList<Color> domain = new ArrayList<Color>();
	public Color nodeColor;
	boolean endPoint;
	Point originPos;
	Point parentPos;
	
	public Point getParentPos() {
		return parentPos;
	}
	public void setParentPos(Point parentPos) {
		this.parentPos = parentPos;
	}
	public FFNode (Point position, int k){
		super(position);
		standardColors(k);
	}
	private FFNode (Point position){
		super(position);
	}
	public void setColor(Color color, FFNode parentNode){
		if(parentNode != null){
			if(parentNode.originPos != null){
				this.originPos = new Point(parentNode.originPos.x, parentNode.originPos.y);
			}else{
				this.originPos = new Point(parentNode.pos.x,parentNode.pos.y);
			}
			this.parentPos = new Point(parentNode.pos.x,parentNode.pos.y);
		}
		nodeColor = color;
		domain.clear();
		domain.add(color);
	}
	
	public Color getColor(){
		return nodeColor;
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
	
	public Point getOrigin(){
		return originPos;
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
		//Dette er unikt for v�r state, s� vi kan evt nullstille heuristc/dvs ikke ta det med over n�r vi lager en ny state
		//Har ikke funnet ut om det g�r og ta med openlist nedover, tviler egentlig p� det... kanskje hvis vi har parents, kan v�re tricky
	}
	public FFNode cloneNode(){
		FFNode newNode = new FFNode(new Point(pos.x,pos.y));
		newNode.nodeColor = this.nodeColor;
		newNode.heuristic = this.heuristic;
		newNode.parentPos = this.parentPos;
		newNode.originPos = this.originPos;
		ArrayList<Color> newDomain = new ArrayList<Color>(this.domain); //Possible reference problem?
		newNode.setDomain(newDomain);
		newNode.setEndPointStatus(endPoint);
		return newNode;
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
	public ArrayList<FFNode> getChildrenByColor(Color color){
		ArrayList<FFNode> retArray = new ArrayList<FFNode>();
		for(int i = 0; i < this.children.size(); i++){
			FFNode tempChild = (FFNode)children.get(i);
			if(tempChild.getColor() == color && tempChild.parentPos != null && tempChild.parentPos.x == this.pos.x && tempChild.parentPos.y == this.pos.y){ //Possible problem
				retArray.add(tempChild);
			}
		}
		return retArray;
	}
	public FFNode getEndOfPath(){
		FFNode tempNode = this;
		while(tempNode.getChildrenByColor(this.getColor()).size() > 0){
			ArrayList<FFNode> childList = tempNode.getChildrenByColor(this.getColor());
			for(int i = 0; i < childList.size(); i++){
				tempNode = childList.get(i);
				
			}
		}
		return tempNode;
		
	}
	public boolean checkConstraint(){
		FFNode tempNode = this;
		while(tempNode.getChildrenByColor(this.getColor()).size() > 0){
			ArrayList<FFNode> childList = tempNode.getChildrenByColor(this.getColor());
			for(int i = 0; i < childList.size(); i++){
				tempNode = childList.get(i);
				
			}
		}
		for(int i = 0; i < tempNode.children.size(); i++){
			FFNode tempNode2 = (FFNode)tempNode.children.get(i);
			if(tempNode2.getColor() == this.getColor()){
				if(tempNode2.originPos != null && (tempNode2.originPos.x != this.pos.x || tempNode2.originPos.y == this.pos.y)){
					return true;
				}
				if(tempNode2.endPoint == true && tempNode2.pos != this.pos){
					return true;
				}
			}
		}
		return false;
	}
}
