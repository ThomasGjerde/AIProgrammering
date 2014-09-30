package aiprog.model;

import java.util.ArrayList;

import bsh.EvalError;
import bsh.Interpreter;

public class CSPNode extends Node{
	protected int id;
	public ArrayList<Integer> domain = new ArrayList<Integer>();
	protected ArrayList<String> constraintVars;
	protected String constraintExpression;
	protected int nodeValue = -1;
	public CSPNode(ArrayList<String> constraintVars, String constraintExpression) {
		super();
		this.constraintVars = constraintVars;
		this.constraintExpression = constraintExpression;
	}
	public CSPNode(){
		super();
	}
	public boolean validateConstraint(CSPNode node) throws EvalError{
		if(this.getNodeValue() == -1 || node.getNodeValue() == -1){
			return true;
		}
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
		domain.remove(new Integer(reduction));
	}
	public int getNodeValue(){
		return nodeValue;
	}
	public void setNodeValue(int nodeValue){
		this.nodeValue = nodeValue;
		/*if(this.nodeValue != -1){
			//this.domain.clear();
		}*/
	}
	public void setId(int id){
		this.id = id;
	}
	public int getId(){
		return this.id;
	}
	
}
