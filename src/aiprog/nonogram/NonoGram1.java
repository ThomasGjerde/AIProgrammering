package aiprog.nonogram;

import java.util.ArrayList;

import aiprog.gui.NNGraphics;
import aiprog.model.NNColRow;
import aiprog.model.NNStateNode;
import aiprog.model.Node;
import aiprog.search.AStar;

public class NonoGram1 extends AStar {

	NNStateNode currentState;
	NNGraphics graphics;
	public NonoGram1(Node startNode, NNGraphics graph) {
		super(startNode);
		currentState = (NNStateNode)startNode;
		graphics = graph;
		initModifications(currentState);
		graphics.setState(currentState);
		/*
		for(int i=0; i<currentState.colDomains.size(); i++){
			System.out.println("colDomains: " + currentState.colDomains.get(i).getDomain().size());
		}
		for(int j=0; j<currentState.rowDomains.size(); j++){
			System.out.println("rowDomains: " + currentState.rowDomains.get(j).getDomain().size());
		}
		*/
		// TODO Auto-generated constructor stub
	}
	
	//Funker
	public void setSingles(NNStateNode state){
		for(int i=0; i<state.colDomains.size(); i++){
			if(state.colDomains.get(i).getDomain().size() == 1){
				state.colDomains.get(i).setValue(state.colDomains.get(i).getDomain().get(0));
				reduseByCol(i,state);
			}
		}
		for(int j=0; j<state.rowDomains.size(); j++){
			if(state.rowDomains.get(j).getDomain().size() == 1){
				state.rowDomains.get(j).setValue(state.rowDomains.get(j).getDomain().get(0));
				reduseByRow(j,state);
			}
		}
	}
	//Funker
	public void reduseByRow(int posInArray, NNStateNode state){
		NNColRow row = state.rowDomains.get(posInArray);
		for(int i=0; i<row.getValue().size(); i++){
			NNColRow col = state.colDomains.get(i);
			for(int j=0; j<col.getDomain().size(); j++){
				if(col.getDomain().get(j).get(posInArray) != row.getValue().get(i)){
					col.getDomain().remove(j);
					if(col.getDomain().size() == 1){
						col.setValue(col.getDomain().get(0));
						reduseByCol(i, state);
					}
				}
			}
		}
	}
	//Funker
	public void reduseByCol(int posInArray, NNStateNode state){
		NNColRow col = state.colDomains.get(posInArray);
		for(int i=0; i<col.getValue().size(); i++){
			NNColRow row = state.rowDomains.get(i);
			for(int j=0; j<row.getDomain().size(); j++){
				if(row.getDomain().get(j).get(posInArray) != col.getValue().get(i)){
					row.getDomain().remove(j);
					if(row.getDomain().size() == 1){
						row.setValue(row.getDomain().get(0));
						reduseByRow(i, state);
					}
				}
			}
		}
	}
	//UC
	public void reduseByCommon(NNStateNode state){
		ArrayList<NNColRow> colRowList = new ArrayList<NNColRow>();
		for(int i=0; i<state.colDomains.size(); i++){
			if(state.colDomains.get(i).getDomain().size() > 1){
				colRowList.add(state.colDomains.get(i));
			}
		}
		/*
		for(int m=0; m<state.rowDomains.size(); m++){
			if(state.rowDomains.get(m).getDomain().size() > 1){
				colRowList.add(state.rowDomains.get(m));
			}
		}*/
		for(int x=0; x<colRowList.size(); x++){
			System.out.println("colRowList: " + colRowList.get(x).getDomain());
		}
		
		for(int j=0; j<colRowList.size(); j++){
			ArrayList<Integer> intArray = findCommon(colRowList.get(j));
			NNColRow midObj = colRowList.get(j);
			if(checkCommon(intArray)){
				for(int k=0; k<midObj.getDomain().size(); k++){
					for(int l=0; l<midObj.getDomain().get(k).size(); l++){
						int intCheck = intArray.get(l);
						/*
						if(midObj.getDomain().get(k).get(l) == null){
							break;
						}*/
						boolean booleanCheck = midObj.getDomain().get(k).get(l);
						if((intCheck == 1 && booleanCheck == true) || (intCheck == 0 && booleanCheck == false)){
							//colRowList.get(j).getDomain().remove(k);
						}else{
							colRowList.get(j).getDomain().remove(k);
						}
						if(midObj.getDomain().size() == 1){
							//midObj.setValue(newValue);
							break;
						}
					}
					if(midObj.getDomain().size() == 1){
						break;
					}
				}
			}
		}
		for(int z=0; z<colRowList.size(); z++){
			System.out.println("colRowListModif: " + colRowList.get(z).getDomain());
			if(colRowList.get(z).getDomain().size() == 1){
				colRowList.get(z).setValue(colRowList.get(z).getDomain().get(0));
			}
		}
		
	}
	
	public void reduseSpesific(){
		
	}
	//Funker og testa
	public ArrayList<Integer> findCommon(NNColRow obj){
		ArrayList<Integer> intArray = new ArrayList<Integer>();
		for(int c=0; c<obj.getDomain().get(0).size(); c++){
			if(obj.getDomain().get(0).get(c) == true){
				intArray.add(1);
			}else{
				intArray.add(0);
			}
		}
		for(int i=0; i<obj.getDomain().size(); i++){
			ArrayList<Boolean> midArray = obj.getDomain().get(i);
			for(int j=0; j<midArray.size(); j++){
				if(midArray.get(j) == true){
					if(intArray.get(j) == 1){
						
					}else{
						intArray.set(j, 3);
					}
				}else{
					if(intArray.get(j) == 0){
						
					}else{
						intArray.set(j, 3);
					}
				}
			}
		}
		return intArray;
	}
	
	//Funker
	public boolean checkCommon(ArrayList<Integer> intArray){
		for(int i=0; i<intArray.size(); i++){
			if(intArray.get(i) == 1 || intArray.get(i) == 0){
				return true;
			}
		}
		return false;
	}
	public void initModifications(NNStateNode state){
		int oldDomainSize = state.getDomainSum();
		//setSingles(state);
		int newDomainSize = state.getDomainSum();
		//Funker, men 1 gang for mye
		/*
		while(oldDomainSize > newDomainSize){
			oldDomainSize = state.getDomainSum();
			setSingles(state);
			newDomainSize = state.getDomainSum();
		}*/
		reduseByCommon(state);
		//setSingles(state);
		graphics.setState(state);
		
		for(int i=0; i<state.colDomains.size(); i++){
			System.out.println("ferdig: " + state.colDomains.get(i).getDomain());
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
