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
		
		initModifications(currentState);
		checkVictory();
		graphics.setState(currentState);
		//assumption(currentState);
		System.out.println("ferdig med reductions");
		System.out.println("domainsum: " + currentState.getDomainSum());
		System.out.println("counter: " + counter);
		//search();
	}
	
	public void initModifications(NNStateNode state){
		for(int i=0; i<state.colDomains.size(); i++){
			if(state.colDomains.get(i).getDomain().size() == 1){
				state.colDomains.get(i).setValue(state.colDomains.get(i).getDomain().get(0));
				reduseByCol(i, state);
				//System.out.println("colFilled");
			}
		}
		for(int j=0; j<state.rowDomains.size(); j++){
			if(state.rowDomains.get(j).getDomain().size() == 1){
				state.rowDomains.get(j).setValue(state.rowDomains.get(j).getDomain().get(0));
				reduseByRow(j, state);
				//System.out.println("rowfilled");
			}
		}
		
		
		//int oldCount = currentState.getDomainSum();
		initColReduction(currentState);
		initRowReduction(currentState);
		//initColReduction(currentState);
		//initRowReduction(currentState);
		//int newCount = currentState.getDomainSum();
		/*
		for(int x=0; x<state.colDomains.size(); x++){
			System.out.println("colDomains: " + state.colDomains.get(x).getDomain().size());
		}
		for(int c=0; c<state.rowDomains.size(); c++){
			System.out.println("rowdomains: " + state.rowDomains.get(c).getDomain().size());
		}*/
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
						state.rowDomains.get(j).catogory = true;
						reduction(state.rowDomains.get(j), i, check, state, j);
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
						state.colDomains.get(j).catogory = false;
						reduction(state.colDomains.get(j), i, check, state, j);
					}
				}	
			}
		}
	}

	//Mulig jeg må modifisere denne litt
	public ArrayList<Integer> findCommon(ArrayList<ArrayList<Boolean>> domain){
		//System.out.println("domainsize " + domain.get(0).size());
		ArrayList<Integer> intArray = new ArrayList<Integer>();
		for(int i=0; i<domain.get(0).size(); i++){
			if(domain.get(0).get(i) == true){
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
	//Vet ikke om dette funker....
	public void reduction(NNColRow obj, int pos, boolean value, NNStateNode state, int objPos){
		for(int i=0; i<obj.getDomain().size(); i++){
			if(obj.getDomain().get(i).get(pos) != value){
				obj.deleteFromDomain(obj.getDomain().get(i));
				if(obj.getDomain().size() == 1){
					obj.setValue(obj.getDomain().get(0));
					if(obj.catogory == true){
						reduseByRow(objPos, state);
					}else{
						reduseByCol(objPos, state);
					}
				}
				//counter++;
				//System.out.println("reduction");
			}
		}
		//counter++;
	}
	
	public void stateReduction(NNStateNode state){
		for(int i=0; i<state.colDomains.size(); i++){
			NNColRow col = state.colDomains.get(i);
			if(col.getValue() != null){
				reduseByCol(i, state);
			}
		}
		for(int j=0; j<state.rowDomains.size(); j++){
			NNColRow row = state.rowDomains.get(j);
			if(row.getValue() != null){
				reduseByRow(j, state);
			}
		}
	}
	
	//Denne ser ut til og fungere
	public void reduseByCol(int pos, NNStateNode state){
		NNColRow col = state.colDomains.get(pos);
		for(int i=0; i<col.getValue().size(); i++){
			NNColRow row = state.rowDomains.get(i);
			if(row.getValue() == null){
				for(int j=0; j<row.getDomain().size(); j++){
					ArrayList<Boolean> rowDomain = row.getDomain().get(j);
					if(rowDomain.get(pos) != col.getValue().get(i)){
						row.getDomain().remove(j);
						if(row.getDomain().size() == 1){
							row.setValue(row.getDomain().get(0));
							reduseByRow(i, state);
						}
					}
				}
			}
		}
	}
	
	//Denne ser ut til og fungere
	public void reduseByRow(int pos, NNStateNode state){
		NNColRow row = state.rowDomains.get(pos);
		for(int i=0; i<row.getValue().size(); i++){
			NNColRow col = state.colDomains.get(i);
			if(col.getValue() == null){
				for(int j=0; j<col.getDomain().size(); j++){
					ArrayList<Boolean> colDomain = col.getDomain().get(j);
					if(colDomain.get(pos) != row.getValue().get(i)){
						col.getDomain().remove(j);
						if(col.getDomain().size() == 1){
							col.setValue(col.getDomain().get(0));
							reduseByCol(i, state);
						}
					}
				}
			}
		}
	}

	@Override
	protected boolean checkVictory() {
		NNStateNode state = (NNStateNode)currentNode;
		int rows=0;
		int cols=0;
		for(int i=0; i<state.rowDomains.size(); i++){
			if(state.rowDomains.get(i).getValue() != null && state.rowDomains.get(i).getDomain().size() == 1){
				rows++;
			}
		}
		for(int j=0; j<state.colDomains.size(); j++){
			if(state.colDomains.get(j).getValue() != null && state.colDomains.get(j).getDomain().size() == 1){
				cols++;
			}
		}
		if(state.rowDomains.size() == rows && state.colDomains.size() == cols){
			return true;
		}else{
			return false;
		}
	}

	@Override
	protected void processCurrentNode() {
		System.out.println("processcurrentNode");
		if(!checkVictory()){
			NNStateNode state = (NNStateNode)currentNode;
			graphics.setState(state);
			assumption(state);
		}else{
			System.out.println("Seier");
			graphics.setState((NNStateNode)currentNode);
		}
		
	}
	
	//Funker tror jeg
	//Skal finne raden eller col med minst domene og sende videre til enten
	//assumtionCol (for col) eller assumptionRow for row
	public void assumption(NNStateNode state){
		NNColRow smallestRow = state.getSmallestRowDomain();
		NNColRow smallestCol = state.getSmallestColDomain();
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
			childState.colDomains.get(state.smallestPos).setValue(col.getDomain().get(i));
			stateReduction(childState);
			
			setHeuristic(childState);
			state.children.add(childState);
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
			childState.rowDomains.get(state.smallestPos).setValue(row.getDomain().get(i));
			stateReduction(childState);
			
			setHeuristic(childState);
			state.children.add(childState);
			System.out.println("assumptionRow");
		}
	}

	protected void setHeuristic(Node node) {
		NNStateNode state = (NNStateNode)node;
		state.heuristic = state.getDomainSum();
		
	}

	protected void updateGui() {
		
	}

}
