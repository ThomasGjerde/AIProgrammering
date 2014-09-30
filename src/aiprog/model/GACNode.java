package aiprog.model;

import java.util.ArrayList;

import aiprog.model.Node.Status;
import bsh.EvalError;

public class GACNode extends Node {
	protected ArrayList<CSPNode> changes = new ArrayList<CSPNode>();
	protected ArrayList<CSPNode> cspList;
	public GACNode() {
		super();
		cspList = new ArrayList<CSPNode>();
		// TODO Auto-generated constructor stub
	}
	public void setCSPList(ArrayList<CSPNode> cspList){
		this.cspList = cspList;
	}
	public ArrayList<CSPNode> getCSPList(){
		return this.cspList;
	}
	
	public void generateChildren(){
		
		ArrayList<CSPNode> availNodes = new ArrayList<CSPNode>();
		for(int i=0; i<this.getCSPList().size(); i++){
			if(this.getCSPList().get(i).getNodeValue() == -1){
				availNodes.add(this.getCSPList().get(i));
			}
		}
		if(!availNodes.isEmpty()){
			for(int j=0; j<availNodes.size(); j++){
				for(int k=0; k<availNodes.get(j).domain.size(); k++){
					CSPNode sendNode = availNodes.get(j);
					sendNode.setNodeValue(availNodes.get(j).domain.get(k));
					generateNewState(sendNode);
					//sendNode.setNodeValue(-1);
				}
			}
		}
	}
	
	private GACNode generateNewState(CSPNode node){
		return null;
	}
	public void applyChanges(){
		//Can be optimized later
		if(cspList == null){
			return;
		}
		if(changes == null){
			return;
		}
		for(int i = 0; i < changes.size(); i++){
			for(int j = 0; j < cspList.size(); j++){
				VCPNode node = (VCPNode)cspList.get(j);
				VCPNode change = (VCPNode)changes.get(i);
				if(node.id == change.id){
					node.domain = change.domain;
					node.setColor(change.getColor());
				}
			}
		}
	}
	public void setChanges(ArrayList<CSPNode> changes){
		this.changes = new ArrayList<CSPNode>(changes);
	}
	public ArrayList<CSPNode> getChanges(){
		return this.changes;
	}
	public void addChange(CSPNode change){
		changes.add(change);
	}
	public boolean checkConsistency() throws EvalError{
		for(int i = 0; i < cspList.size(); i++){
			for(int j = 0; j < cspList.get(i).children.size(); j++){
				if(cspList.get(i).validateConstraint((CSPNode)cspList.get(i).children.get(j)) == false){
					return false;
				}
			}
		}
		return true;
	}

}
