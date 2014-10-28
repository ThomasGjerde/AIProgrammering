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
		ArrayList<NNColRow> colList = new ArrayList<NNColRow>(state.colDomains);
		for(int i = 0; i < colList.size(); i++){
			ArrayList<Integer> changeStatus = findCommon(colList.get(i));
			for(int j = 0; j < changeStatus.size(); j++){
				if(changeStatus.get(j) != 3){
					NNColRow row = state.rowDomains.get(j);
					for(int k = 0; k < row.getDomain().size(); k++){
						boolean value;
						if(changeStatus.get(j) == 1){
							value = true;
						}else{
							value = false;
						}
						if(row.getDomain().get(k).get(i) != value){
							row.deleteFromDomain(row.getDomain().get(k));
						}
					}
				}
			}
		}
		
		ArrayList<NNColRow> rowList = new ArrayList<NNColRow>(state.rowDomains);
		for(int i = 0; i < rowList.size(); i++){
			ArrayList<Integer> changeStatus = findCommon(rowList.get(i));
			for(int j = 0; j < changeStatus.size(); j++){
				if(changeStatus.get(j) != 3){
					NNColRow col = state.colDomains.get(j);
					for(int k = 0; k < col.getDomain().size(); k++){
						boolean value;
						if(changeStatus.get(j) == 1){
							value = true;
						}else{
							value = false;
						}
						if(col.getDomain().get(k).get(i) != value){
							col.deleteFromDomain(col.getDomain().get(k));
						}
					}
				}
			}
		}
		
	}
	public void generalReduction(){
		ArrayList<NNColRow> colList = new ArrayList<NNColRow>(currentState.colDomains);
		for(int i = 0; i < colList.size(); i++){
			NNColRow col = colList.get(i);
			if(col.getValue() != null){
				for(int j = 0; j < col.getValue().size(); j++){
					NNColRow row = currentState.rowDomains.get(j);
					for(int k = 0; k < row.getDomain().size(); k++){
						if(row.getDomain().get(k).get(i) != col.getValue().get(j)){
							row.deleteFromDomain(row.getDomain().get(k));
						}
					}
				}
			}
			
		}
		
		ArrayList<NNColRow> rowList = new ArrayList<NNColRow>(currentState.rowDomains);
		for(int i = 0; i < rowList.size(); i++){
			NNColRow row = rowList.get(i);
			if(row.getValue() != null){
				for(int j = 0; j < row.getValue().size(); j++){
					NNColRow col = currentState.colDomains.get(j);
					for(int k = 0; k < col.getDomain().size(); k++){
						if(col.getDomain().get(k).get(i) != row.getValue().get(j)){
							col.deleteFromDomain(col.getDomain().get(k));
						}
					}
				}
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
