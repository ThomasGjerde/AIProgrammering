package aiprog.model;

import java.util.ArrayList;
import bsh.EvalError;

public class GACCSPNode extends GACNode{
	protected ArrayList<CSPNode> changes = new ArrayList<CSPNode>();
	public ArrayList<CSPNode> cspList;
	public CSPNode assumption;
	public GACCSPNode() {
		super();
		cspList = new ArrayList<CSPNode>();
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
		CSPNode smallestNode = getNodeWithSmallestDomain();
		if(smallestNode != null){
			for(int i = 0; i < smallestNode.getDomain().size(); i++){
				CSPNode sendNode = new CSPNode();	
				sendNode.id = smallestNode.id;
				sendNode.setDomain(new ArrayList<Integer>(smallestNode.getDomain()));
				sendNode.setNodeValue(smallestNode.getDomain().get(i));

				GACCSPNode newNode = generateNewState(sendNode);
				this.addChild(newNode);

			}
		}
	}
	private CSPNode getNodeWithSmallestDomain(){
		int smallest = 10000;
		CSPNode retNode;
		CSPNode smallestNode = null;
		for(int i = 0; i < getCSPList().size(); i++){
			CSPNode tempNode = getCSPList().get(i);
			if(tempNode.getDomain().size() > 1 && tempNode.getDomain().size() < smallest){
				smallest = tempNode.getDomain().size();
				smallestNode = tempNode;
			}
		}
		retNode = new CSPNode();
		if(smallestNode == null){
			return null;
		}
		CSPNode oldNode = smallestNode;
		retNode.id = oldNode.id;
		retNode.setNodeValue(oldNode.getNodeValue());
		retNode.setDomain(new ArrayList<Integer>(oldNode.getDomain()));
		return retNode;

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
				this.assumption = tempNode;
			}
		}
		newState.setChanges(changesList);
		return newState;
	}
	public void applyChanges(){
		//Can be optimized
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
