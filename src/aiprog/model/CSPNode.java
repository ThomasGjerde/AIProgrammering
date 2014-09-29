package aiprog.model;

import java.util.ArrayList;

public abstract class CSPNode extends Node{
	protected ArrayList<Integer> domain;
	protected int nodeValue;
	public CSPNode(Point position) {
		super(position);
	}
	public ArrayList<Integer> getDomain(){
		return this.domain;
	}
	public void setDomain(ArrayList<Integer> domain){
		this.domain = domain;
	}
	public int getNodeValue(){
		return nodeValue;
	}
	public void setNodeValue(int nodeValue){
		this.nodeValue = nodeValue;
	}
}
