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
			heuristic -= tempCSPList.get(i).domain.size() - 1;
		}
		node.heuristic = heuristic;
	}
		
	private void reduction(Node node){
		GACCSPNode tempNode = (GACCSPNode)node;
		CSPNode midNode = null;
		for(int j=0; j<tempNode.getChanges().size(); j++){
			for(int k=0; k<tempNode.getCSPList().size(); k++){
				if(((CSPNode)tempNode.getChanges().get(j)).getId() == ((CSPNode)tempNode.getCSPList().get(k)).getId()){
					midNode = tempNode.getCSPList().get(k);
				}
			}
		}
		for(int i=0; i<midNode.getChildren().size(); i++){
			if(((CSPNode)midNode.getChildren().get(i)).domain.contains(midNode.getNodeValue())){
				((CSPNode)midNode.getChildren().get(i)).reduceDomain(midNode.getNodeValue());
			}
			if(((CSPNode)midNode.getChildren().get(i)).domain.size() == 1 && ((CSPNode)midNode.getChildren().get(i)).getNodeValue() == -1){
				reductionCycle((CSPNode)midNode.getChildren().get(i));
			}
		}
	}
	
	private void reductionCycle(CSPNode node){
		node.setNodeValue(node.domain.get(0));
		for(int i=0; i<node.getChildren().size(); i++){
			if(((CSPNode)node.getChildren().get(i)).domain.contains(node.getNodeValue())){
				((CSPNode)node.getChildren().get(i)).reduceDomain(node.getNodeValue());
			}
			if(((CSPNode)node.getChildren().get(i)).domain.size() == 1 && ((CSPNode)node.getChildren().get(i)).getNodeValue() == -1){
				reductionCycle((CSPNode)node.getChildren().get(i));
			}
		}
	}
	
	
	private void reduction2(Node node){
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
