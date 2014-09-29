package aiprog.model;

import java.util.ArrayList;

import bsh.EvalError;
import bsh.Interpreter;

public abstract class CSPNode extends Node{
	protected int id;
	protected ArrayList<Integer> domain = new ArrayList<Integer>();
	protected ArrayList<String> constraintVars;
	protected String constraintExpression;
	protected int nodeValue;
	public CSPNode(Point position, ArrayList<String> constraintVars, String constraintExpression) {
		super(position);
		this.constraintVars = constraintVars;
		this.constraintExpression = constraintExpression;
	}
	public boolean validateConstraint(CSPNode node) throws EvalError{
		Interpreter i = new Interpreter();  // Construct an interpreter
		i.set(constraintVars.get(0), this);
		i.set(constraintVars.get(1), node);
		i.eval("ret = " + constraintExpression);
		return (boolean) i.get("ret");
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
