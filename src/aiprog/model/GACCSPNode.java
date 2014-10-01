package aiprog.model;

import java.awt.Color;
import java.util.ArrayList;

import aiprog.model.Node.Status;
import bsh.EvalError;

public class GACCSPNode extends GACNode{
	protected ArrayList<CSPNode> changes = new ArrayList<CSPNode>();
	public ArrayList<CSPNode> cspList;
	public GACCSPNode() {
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
				CSPNode tempNode = new CSPNode();
				CSPNode oldNode = this.getCSPList().get(i);
				tempNode.id = oldNode.id;
				tempNode.setNodeValue(oldNode.getNodeValue());
				tempNode.setDomain(new ArrayList<Integer>(oldNode.getDomain()));
				availNodes.add(tempNode);
			}
		}
		System.out.println("availNodes.size " + availNodes.size());
		/*for(int j=0; j<availNodes.size(); j++){
			System.out.println("availNodes" + availNodes.get(j).domain.size());
		}*/
		
		if(!availNodes.isEmpty()){
			for(int j=0; j<availNodes.size(); j++){
				for(int k=0; k<availNodes.get(j).domain.size(); k++){
					CSPNode sendNode = availNodes.get(j);
					sendNode.setNodeValue(availNodes.get(j).domain.get(k));
					GACCSPNode newNode = generateNewState(sendNode);
					this.addChild(newNode);
					//sendNode.setNodeValue(-1);
				}
			}
		}
		
		/*
		for(int i = 0; i < this.children.size(); i++){
			GACNode gacNode = (GACNode)this.children.get(i);
			gacNode.applyChanges();
			for(int j = 0; j < gacNode.getCSPList().size(); j++){
			}
		}
		*/
	}
	
	private GACCSPNode generateNewState(CSPNode node){
		GACCSPNode newState = new GACCSPNode();
		newState.setCSPList(this.getCSPList());
		ArrayList<CSPNode> changesList = new ArrayList<CSPNode>();
		//Prevent reference passing
		for(int i = 0; i < this.getCSPList().size(); i++){
			CSPNode oldNode = this.getCSPList().get(i);
			CSPNode newNode = new CSPNode();
			newNode.id = oldNode.id;
			newNode.setDomain(new ArrayList<Integer>(oldNode.getDomain()));
			newNode.setNodeValue(oldNode.getNodeValue());
			changesList.add(newNode);
		}
		//Add assumtion
		for(int i = 0; i < changesList.size(); i++){

			CSPNode tempNode = changesList.get(i);
			if(tempNode.id == node.id){
				tempNode.setDomain(new ArrayList<Integer>(node.getDomain()));
				tempNode.setNodeValue(node.getNodeValue());
				ArrayList<Integer> singletonDomain = new ArrayList<Integer>();
				singletonDomain.add(node.getNodeValue());
				tempNode.setDomain(singletonDomain);
			}
		}
		/*
		CSPNode assumtionNode = new CSPNode();
		assumtionNode.setId(node.getId());
		assumtionNode.setDomain(new ArrayList<Integer>(node.getDomain()));
		assumtionNode.setNodeValue(node.getNodeValue());
		changesList.add(assumtionNode);
		newState.setChanges(changesList);
		*/
		newState.setChanges(changesList);
		return newState;
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
				CSPNode node = cspList.get(j);
				CSPNode change = changes.get(i);
				if(node.id == change.id){
					node.setDomain(change.getDomain());
					node.setNodeValue(change.getNodeValue());
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
