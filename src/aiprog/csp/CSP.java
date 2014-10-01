package aiprog.csp;

import java.util.ArrayList;

import aiprog.gac.AStarCSPGAC;
import aiprog.model.CSPNode;
import aiprog.model.GACCSPNode;
import aiprog.model.Node;
import aiprog.model.VCPNode;


public abstract class CSP extends AStarCSPGAC{
	public int numNodes = 0;
	public CSP(Node startNode) {
		super(startNode);
	}
	@Override
	protected void setHeuristic(Node node) {
		((GACCSPNode)node).applyChanges();
		reduction(node);
		calcHeuristic(node);
		numNodes++;
		
	}
	
	private void calcHeuristic(Node node){
		int heuristic = 0;
		ArrayList<CSPNode> tempCSPList = ((GACCSPNode)node).getCSPList();
		
		for(int i=0; i<tempCSPList.size(); i++){
			heuristic += tempCSPList.get(i).domain.size() - 1;
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
		ArrayList<CSPNode> cSPList = ((GACCSPNode)node).getCSPList();
		//ArrayList<VCPNode> moreReduse = new ArrayList<VCPNode>();
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
