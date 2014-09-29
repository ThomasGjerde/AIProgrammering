package aiprog.model;

import java.util.ArrayList;

public abstract class CSPNode extends Node{
	protected int id;
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
	public void reduceDomain(int reduction){
		domain.remove(reduction);
	}
	public int getNodeValue(){
		return nodeValue;
	}
	public void setNodeValue(int nodeValue){
		this.nodeValue = nodeValue;
	}
	public void setId(int id){
		this.id = id;
	}
	public int getId(){
		return this.id;
	}
}
