package aiprog.nonogram;

import java.util.ArrayList;

import aiprog.model.NNColRow;
import aiprog.model.NNStateNode;
import aiprog.model.Node;
import aiprog.search.AStar;

public class NonoGram extends AStar{

	NNStateNode currentState;
	public NonoGram(Node startNode) {
		super(startNode);
		currentState = (NNStateNode)startNode;
		// TODO Auto-generated constructor stub
	}
	
	//Skal fylle alle domenene i starten
	public void fillDomains(){
		
	}
	
	public void initReduction(NNStateNode state){
		
	}
	
	public ArrayList<Integer> findCommon(ArrayList<ArrayList<Boolean>> domain){
		
		ArrayList<Integer> intArray = new ArrayList<Integer>();
		for(int i=0; i<domain.size(); i++){
			if(domain.get(0).get(0) == true){
				intArray.add(1);
			}else{
				intArray.add(0);
			}
		}
		
		for(int j=0; j<domain.size(); j++){
			for(int k=0; k<domain.get(j).size(); k++){
				if(intArray.get(k) == 1 && domain.get(j).get(k) != true){
					intArray.set(k, 3);
				}
				if(intArray.get(k) == 0 && domain.get(j).get(k) != false){
					intArray.set(k, 3);
				}
			}
		}
		return intArray;
		
	}
	
	//Skal redusere domenene til vær rowcol
	//Skal kanskje ta inn ett rowcol object og redusere domenet til denne basert på en value
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
