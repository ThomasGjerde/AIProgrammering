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
		//System.out.println("Heuristic: " + node.heuristic);
	}
	
	private void reduction(Node node){
		ArrayList<CSPNode> cSPList = ((GACNode)node).getCSPList();
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
					}
					if(primeChild.getNodeValue() == -1 && primeChild.domain.size() == 1){
						primeChild.setNodeValue(primeChild.domain.get(0));
					}
				}
			}
		}
		
		/*
		for(int i=0; i<cSPList.size(); i++){
			VCPNode prime = (VCPNode)cSPList.get(i);
			for(int j=0; j<prime.getChildren().size(); j++){
				VCPNode primeChild = (VCPNode)prime.getChildren().get(j);
				if(prime.domain.contains(primeChild.getNodeValue())){
					prime.reduceDomain(primeChild.getNodeValue());
				}else if(primeChild.domain.contains(prime.getNodeValue())){
					primeChild.reduceDomain(prime.getNodeValue());
				}
			}
		}*/
		
		/*
		for(int i=0; i<cSPList.size(); i++){
			for(int k=0; k<cSPList.size(); k++){
			VCPNode parentVCPNode = (VCPNode)cSPList.get(k);
				for(int j=0; j<parentVCPNode.getChildren().size(); j++){
					VCPNode childVCPNode = (VCPNode)cSPList.get(j);
					if(childVCPNode.domain.contains(parentVCPNode.getNodeValue())){
						childVCPNode.reduceDomain(parentVCPNode.getNodeValue());
					}
				}
			}
		}
		*/
		/*
		for(int i=0; i<cSPList.size(); i++){
			VCPNode tempVCPNode = (VCPNode)cSPList.get(i);
			if(tempVCPNode.getNodeValue() != -1){
				for(int j=0; j<tempVCPNode.getChildren().size(); j++){
					VCPNode tempVCPChild = (VCPNode) tempVCPNode.getChildren().get(j);
					if(tempVCPChild.domain.contains(tempVCPNode.getNodeValue())){
						tempVCPChild.reduceDomain(tempVCPNode.getNodeValue());
						if(tempVCPChild.domain.size() == 1){
							moreReduse.add(tempVCPChild);
						}
					}
				}
			}
		}
		if(!moreReduse.isEmpty()){
			//reductionCycle(moreReduse.get(0));
			//if(moreReduse.size() > 1){
				for(int k=0; k<moreReduse.size(); k++){
					reductionCycle(moreReduse.get(k));
				}
			//}
		}*/
	}
	/*
	private void reductionCycle(VCPNode node){
		node.setNodeValue(node.domain.get(0));
		for(int i=0; i<node.getChildren().size(); i++){
			VCPNode tempVCPChild = (VCPNode)node.getChildren().get(i);
			if(tempVCPChild.domain.contains(node.getNodeValue())){
				tempVCPChild.reduceDomain(node.getNodeValue());
			}
			if(tempVCPChild.domain.size() == 1){
				reductionCycle(tempVCPChild);
			}
		}
	}*/
}
