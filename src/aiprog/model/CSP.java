package aiprog.model;

import java.util.ArrayList;

public abstract class CSP extends AStarGAC{

	public CSP(Node startNode) {
		super(startNode);
	}
	@Override
	protected void updateGui() {
		// TODO Auto-generated method stub
		
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
			//VCPNode tempVCPNode = (VCPNode)tempCSPList.get(k);
		}
		
		//
		/*
		public void reduction(ColorNode node){
			for(int i=0; i<node.getChildren().size(); i++){
				ColorNode midChild = (ColorNode)node.getChildren().get(i);
				if(node.getColor() != null){
					if(midChild.getDomain().contains(node.getColor())){
						midChild.reduseDomain(node.getColor());
					}
				}
			}
			check();
		}
		*/
	}
	

}
