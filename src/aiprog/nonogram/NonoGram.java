package aiprog.nonogram;

import java.util.ArrayList;

import aiprog.model.NNColRow;
import aiprog.model.NNStateNode;
import aiprog.model.Node;
import aiprog.search.AStar;

public class NonoGram extends AStar{
	int counter = 0;
	NNStateNode currentState;
	public NonoGram(Node startNode) {
		super(startNode);
		currentState = (NNStateNode)startNode;
		System.out.println("domainsum: " + currentState.getDomainSum());
		initColReduction(currentState);
		initRowReduction(currentState);
		initModifications(currentState);
		System.out.println("ferdig med reductions");
		System.out.println("domainsum: " + currentState.getDomainSum());
		System.out.println("counter: " + counter);
		// TODO Auto-generated constructor stub
	}
	
	//Skal fylle alle domenene i starten
	public void fillDomains(){
		
	}
	
	public void initModifications(NNStateNode state){
		for(int i=0; i<state.colDomains.size(); i++){
			if(state.colDomains.get(i).getDomain().size() == 1){
				state.colDomains.get(i).setValue(state.colDomains.get(i).getDomain().get(0));
				System.out.println("colFilled");
			}
		}
		for(int j=0; j<state.rowDomains.size(); j++){
			if(state.rowDomains.get(j).getDomain().size() == 1){
				state.rowDomains.get(j).setValue(state.rowDomains.get(j).getDomain().get(0));
				System.out.println("rowfilled");
			}
		}
	}
	
	public void initColReduction(NNStateNode state){
		for(int i=0; i<state.colDomains.size(); i++){
			ArrayList<Integer> intArray = findCommon(state.colDomains.get(i).getDomain());
			if(checkCommon(intArray)){
				for(int j=0; j<intArray.size(); j++){
					if(intArray.get(j) != 3){
						boolean check;
						if(intArray.get(j) == 1){
							check = true;
						}else{
							check = false;
						}
						reduction(state.rowDomains.get(j), i, check);
					}
				}
			}
		}
	}
	
	public void initRowReduction(NNStateNode state){
		for(int i=0; i<state.rowDomains.size(); i++){
			ArrayList<Integer> intArray = findCommon(state.rowDomains.get(i).getDomain());
			if(checkCommon(intArray)){
				for(int j=0; j<intArray.size(); j++){
					if(intArray.get(j) != 3){
						boolean check;
						if(intArray.get(j) == 1){
							check = true;
						}else{
							check = false;
						}
						reduction(state.colDomains.get(j), i, check);
					}
				}	
			}
		}
	}
	
	public ArrayList<Integer> findCommon(ArrayList<ArrayList<Boolean>> domain){
		
		ArrayList<Integer> intArray = new ArrayList<Integer>();
		for(int i=0; i<domain.get(0).size(); i++){
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
	
	public boolean checkCommon(ArrayList<Integer> intArray){
		for(int i=0; i<intArray.size(); i++){
			if(intArray.get(i) == 1 || intArray.get(i) == 0){
				return true;
			}
		}
		return false;
	}
	
	//Skal redusere domenene til vær rowcol
	//Skal kanskje ta inn ett rowcol object og redusere domenet til denne basert på en value
	public void reduction(NNColRow obj, int pos, boolean value){
		for(int i=0; i<obj.getDomain().size(); i++){
			if(obj.getDomain().get(i).get(pos) != value){
				obj.deleteFromDomain(obj.getDomain().get(i));
				counter++;
				//System.out.println("reduction");
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
