package aiprog.model;

import java.util.ArrayList;

import aiprog.gui.GraphGraphics;

public class VCP extends CSP{
	GraphGraphics graphics;
	public VCP(GACNode initialNode) {
		super(initialNode);
		 graphics = new GraphGraphics((int)(Math.ceil(Math.sqrt(initialNode.getCSPList().size()))), (int)(Math.ceil(Math.sqrt(initialNode.getCSPList().size()))));
		 graphics.setGraph((GACNode)currentNode);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected boolean checkVictory() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected void updateGui() {
		graphics.setGraph((GACNode)currentNode);
	}

}
