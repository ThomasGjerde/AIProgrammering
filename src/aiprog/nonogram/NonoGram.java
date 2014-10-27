package aiprog.nonogram;

import aiprog.model.NNColRow;
import aiprog.model.Node;
import aiprog.search.AStar;

public class NonoGram extends AStar{

	public NonoGram(Node startNode) {
		super(startNode);
		// TODO Auto-generated constructor stub
	}
	
	//Skal fylle alle domenene i starten
	public void fillDomains(){
		
	}
	
	//Skal redusere domenene til vær rowcol
	//Skal kanskje ta inn 2 rowcol objecter, ala en row og en col
	public void reduction(NNColRow obj, int pos, boolean value){
		for(int i=0; i<obj.getDomain().size(); i++){
			if(obj.getDomain().get(i).get(pos) != value){
				obj.deleteFromDomain(obj.getDomain().get(i));
			}
		}
	}

	@Override
	protected boolean checkVictory() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected void processCurrentNode() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void setHeuristic(Node node) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void updateGui() {
		// TODO Auto-generated method stub
		
	}

}
