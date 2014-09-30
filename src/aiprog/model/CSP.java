package aiprog.model;

import java.util.ArrayList;

public abstract class CSP extends AStarGAC{

	public CSP(Node startNode) {
		super(startNode);
	}
	@Override
	protected void setHeuristic(Node node) {
		((GACNode)node).applyChanges();
		reduction(node);
		calcHeuristic(node);
		
	}
	
	private void calcHeuristic(Node node){
		int heuristic = 0;
		ArrayList<CSPNode> tempCSPList = ((GACNode)node).getCSPList();
		
		for(int i=0; i<tempCSPList.size(); i++){
			if(tempCSPList.get(i).getNodeValue() == -1){
				heuristic += tempCSPList.get(i).domain.size();
			}
		}
		node.heuristic = heuristic;
	}
	
	/*
	private void reduction(Node node){
		ArrayList<CSPNode> cspList = ((GACNode)node).getCSPList();
		for(int i=0; i<cspList.size(); i++){
			VCPNode midNode = (VCPNode)cspList.get(i);
			if(midNode.getNodeValue() != -1){
				
			}
		}
	}
	
	private void reductionCycle(VCPNode child){
		
	}*/
	
	
	private void reduction(Node node){
		ArrayList<CSPNode> cSPList = ((GACNode)node).getCSPList();
		for(int i=0; i<cSPList.size(); i++){
			for(int j=0; j<cSPList.size(); j++){
				VCPNode prime = (VCPNode)cSPList.get(j);
				for(int k=0; k<prime.getChildren().size(); k++){
					VCPNode primeChild = (VCPNode)prime.getChildren().get(k);
					if(prime.domain.contains(primeChild.getNodeValue())){
						prime.reduceDomain(primeChild.getNodeValue());
					}else if(primeChild.domain.contains(prime.getNodeValue())){
						primeChild.reduceDomain(prime.getNodeValue());
					}
					if(prime.getNodeValue() == -1 && prime.domain.size() == 1){
						prime.setNodeValue(prime.domain.get(0));
						//System.out.println("set");
					}
					if(primeChild.getNodeValue() == -1 && primeChild.domain.size() == 1){
						primeChild.setNodeValue(primeChild.domain.get(0));
						//System.out.println("set");
					}
				}
			}
		}
	}
}
