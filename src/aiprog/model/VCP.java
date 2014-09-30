package aiprog.model;

import java.util.ArrayList;

import aiprog.gui.GraphGraphics;

public class VCP extends CSP{
	GraphGraphics graphics;
	public VCP(GACNode initialNode) {
		super(initialNode);
		 graphics = new GraphGraphics(initialNode,(int)(Math.ceil(Math.sqrt(initialNode.getCSPList().size()))), (int)(Math.ceil(Math.sqrt(initialNode.getCSPList().size()))));
		 graphics.setGraph((GACNode)currentNode);
		 ((GACNode)currentNode).generateChildren();
		 this.search();
		// TODO Auto-generated constructor stub
	}

	@Override
	protected boolean checkVictory() {
		for(int i = 0; i < ((GACNode)currentNode).cspList.size(); i++){
			if(((GACNode)currentNode).cspList.get(i).getNodeValue() == -1){
				return false;
			}
		}
		return true;
	}

	@Override
	protected void updateGui() {
		try {
			Thread.sleep(0);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		graphics.setGraph((GACNode)currentNode);
	}

}
