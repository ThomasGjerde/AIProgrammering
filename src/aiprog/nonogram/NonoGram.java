package aiprog.nonogram;

import java.util.ArrayList;

import aiprog.gui.NNGraphics;
import aiprog.model.NNColRow;
import aiprog.model.NNStateNode;
import aiprog.model.Node;
import aiprog.search.AStar;

public class NonoGram extends AStar{
	int counter = 0;
	NNStateNode currentState;
	NNGraphics graphics;
	public NonoGram(Node startNode, NNGraphics graph) {
		super(startNode);
		graphics = graph;
		currentState = (NNStateNode)startNode;
		System.out.println("domainsum: " + currentState.getDomainSum());
		boolean dom=true;
		while(dom){
			int oldCount = currentState.getDomainSum();
			initColReduction(currentState);
			initRowReduction(currentState);
			int newCount = currentState.getDomainSum();
			if(oldCount == newCount){
				break;
			}
		}
		initModifications(currentState);
		checkVictory();
		graphics.setState(currentState);
		//assumption(currentState);
		System.out.println("ferdig med reductions");
		System.out.println("domainsum: " + currentState.getDomainSum());
		System.out.println("counter: " + counter);
		search();
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
	
	//Mulig jeg må modifisere denne litt
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
	
	//Denne funker
	public boolean checkCommon(ArrayList<Integer> intArray){
		for(int i=0; i<intArray.size(); i++){
			if(intArray.get(i) == 1 || intArray.get(i) == 0){
				return true;
			}
		}
		return false;
	}
	
	//Det ser ut til at denne fungerer
	public void reduction(NNColRow obj, int pos, boolean value){
		for(int i=0; i<obj.getDomain().size(); i++){
			if(obj.getDomain().get(i).get(pos) != value){
				obj.deleteFromDomain(obj.getDomain().get(i));
				//counter++;
				//System.out.println("reduction");
			}
		}
		counter++;
	}
	
	public void stateReduction(){
		
	}

	@Override
	protected boolean checkVictory() {
		// TODO Auto-generated method stub
		NNStateNode state = (NNStateNode)currentNode;
		for(int i=0; i<state.rowDomains.size(); i++){
			if(state.rowDomains.get(i) == null){
				return false;
			}
		}
		return true;
	}

	@Override
	protected void processCurrentNode() {
		System.out.println("processcurrentNode");
		if(!checkVictory()){
			NNStateNode state = (NNStateNode)currentNode;
			//assumption(state);
		}else{
			System.out.println("Seier");
		}
		
		//ArrayList<Boolean> assumption = assumption(state);
		
		//stateReduction();
		
	}
	
	//Funker tror jeg
	//Skal finne raden eller col med minst domene og sende videre til enten
	//assumtionCol (for col) eller assumptionRow for row
	public void assumption(NNStateNode state){
		NNColRow smallestRow = state.getSmallestRowDomain();
		//System.out.println("smallestRow :" + smallestRow.getDomain().size());
		NNColRow smallestCol = state.getSmallestColDomain();
		//System.out.println("smallestCol :" + smallestCol.getDomain().size());
		if(smallestRow.getDomain().size() > smallestCol.getDomain().size()){
			assumptionCol(smallestCol, state);
		}else{
			assumptionRow(smallestCol, state);
		}
	}
	
	//Funker ikke
	//Skal genere en state for vært element i domain
	//Ettersom de er generert skal endringen bli satt og reduction skal bli gjort
	//Så skal den legges til i openlista
	public void assumptionCol(NNColRow col, NNStateNode state){
		for(int i=0; i<col.getDomain().size(); i++){
			NNStateNode childState = state.generateStateNode(state.colDomains, state.rowDomains);
			//currentNode = childState;
			for(int j=0; j<col.getDomain().get(i).size(); j++){
				reduction(col, j,col.getDomain().get(i).get(j));
			}
			setHeuristic(childState);
			currentNode.children.add(childState);
			System.out.println("assumptionCol");
		}
		
	}
	
	//Funker ikke
	//Skal genere en state for vært element  domain
	//Ettersom de er generert skal endringen bli satt og reduction skal bli gjort
	//Så skal den legges til i openlista
	public void assumptionRow(NNColRow row, NNStateNode state){
		for(int i=0; i<row.getDomain().size(); i++){
			NNStateNode childState = state.generateStateNode(state.colDomains, state.rowDomains);
			//currentNode = childState;
			for(int j=0; j<row.getDomain().get(i).size(); j++){
				reduction(row, j,row.getDomain().get(i).get(j));
			}
			setHeuristic(childState);
			currentNode.children.add(childState);
			System.out.println("assumptionRow");
		}
	}

	@Override
	protected void setHeuristic(Node node) {
		// TODO Auto-generated method stub
		NNStateNode state = (NNStateNode)node;
		state.heuristic = state.getDomainSum();
		
	}

	@Override
	protected void updateGui() {
		// TODO Auto-generated method stub
		
	}

}
