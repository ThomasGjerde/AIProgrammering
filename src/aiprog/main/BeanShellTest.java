package aiprog.main;

import aiprog.model.StateNode;
import bsh.EvalError;
import bsh.Interpreter;

public class BeanShellTest {
	public BeanShellTest(StateNode node) throws EvalError{
		Interpreter i = new Interpreter();  // Construct an interpreter
		i.set("node", node);
		// Set variables
		// Eval a statement and get the result
		i.eval("bar = node.getNodeList().get(0).getColor().toString()");             
	}

}
