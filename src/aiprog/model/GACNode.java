package aiprog.model;

import java.util.ArrayList;

public class GACNode extends Node {
	protected ArrayList<CSPNode> cspList;
	public GACNode(Point position) {
		super(position);
		cspList = new ArrayList<CSPNode>();
		// TODO Auto-generated constructor stub
	}
	public void setCSPList(ArrayList<CSPNode> cspList){
		this.cspList = cspList;
	}
	public ArrayList<CSPNode> getCSPList(){
		return this.cspList;
	}

}
