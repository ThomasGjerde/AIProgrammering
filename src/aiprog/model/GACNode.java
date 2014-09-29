package aiprog.model;

import java.util.ArrayList;

public class GACNode extends Node {
	protected ArrayList<CSPNode> changes;
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
	@Override
	public ArrayList<Node> getUnoccupiedChildren() {
		// TODO Auto-generated method stub
		return null;
	}
	public void setChanges(ArrayList<CSPNode> changes){
		this.changes = new ArrayList<CSPNode>(changes);
	}
	public ArrayList<CSPNode> getChanges(){
		return this.changes;
	}

}
