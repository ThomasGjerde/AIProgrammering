package aiprog.model;

import bsh.EvalError;

public abstract class GACNode extends Node {
	public abstract void applyChanges();
	public abstract boolean checkConsistency()throws EvalError;
	public abstract void generateChildren();
}
