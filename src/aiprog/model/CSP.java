package aiprog.model;

import java.util.ArrayList;

public abstract class CSP extends AStarGAC{

	public CSP(Node startNode) {
		super(startNode);
	}
	@Override
	protected void setHeuristic(Node node) {
		ArrayList<CSPNode> tempCSPList = ((GACNode)node).getCSPList();
		int heuristic = 0;
		for(int i=0; i<tempCSPList.size(); i++){
			
		}
		
		//Legger sammen alle domenene
		for(int j=0; j<tempCSPList.size(); j++){
			heuristic += tempCSPList.get(j).getDomain().size();
		}
		//abcdefghijklmnop
		//reduction
		for(int k=0; k<tempCSPList.size(); k++){
			VCPNode tempVCPNode = (VCPNode)tempCSPList.get(k);
			if(tempVCPNode.getColor() != null){
				for(int l=0; l<tempVCPNode.getChildren().size(); l++){
					VCPNode tempVCPChild = (VCPNode) tempVCPNode.getChildren().get(l);
					if (tempVCPChild.domain.contains(tempVCPNode.getColor().getRGB())){
						tempVCPChild.reduceDomain(tempVCPNode.getColor().getRGB());
					}
				}
			}
		}
	}
}
