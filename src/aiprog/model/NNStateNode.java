package aiprog.model;

import java.util.ArrayList;
import java.util.Arrays;

import aiprog.nonogram.NNBoard;

public class NNStateNode extends Node{
	public ArrayList<NNColRow> colDomains = new ArrayList<NNColRow>();
	public ArrayList<NNColRow> rowDomains = new ArrayList<NNColRow>();
	ArrayList<int[]> tempDomain;
	public int smallestPos;
	public NNStateNode(NNBoard board){
		ArrayList<ArrayList<Integer>> colConstraints = board.colConstraints;
		ArrayList<ArrayList<Integer>> rowConstraints = board.rowConstraints;
		for(int i = 0; i < colConstraints.size(); i++){
			colDomains.add(generateColRow(board.boardArray.length, colConstraints.get(i)));
		}
		for(int i = 0; i < rowConstraints.size(); i++){
			rowDomains.add(generateColRow(board.boardArray[0].length, rowConstraints.get(i)));
		}
	}
	private NNColRow generateColRow(int size, ArrayList<Integer> constraints){
		NNColRow newColRow = new NNColRow(constraints);
		tempDomain = new ArrayList<int[]>();
		int listSize = 0;
		for(int i = 0; i < constraints.size(); i++){
			listSize += constraints.get(i);
		}
		int numZeros = size - listSize;
		int[] list = new int[numZeros + constraints.size()];
		Arrays.fill(list, 0);
		for(int i = 0; i < constraints.size(); i++){
			list[i*2] = constraints.get(i);
		}
		makePermutation(list, 0);
		ArrayList<ArrayList<Boolean>> newDomainList = new ArrayList<ArrayList<Boolean>>();
		for(int i = 0; i < tempDomain.size(); i++){
			ArrayList<Boolean> newDomain = new ArrayList<Boolean>();
			for(int j = 0; j < tempDomain.get(i).length; j++){
				int num = tempDomain.get(i)[j];
				if(num > 0){
					for(int k = 0; k < num; k++){
						newDomain.add(true);
					}
				}else{
					newDomain.add(false);
				}
				
			}
			newDomainList.add(newDomain);
		}
		newColRow.setDomain(newDomainList);
		return newColRow;
		
	}
	private void printArrayList(ArrayList<Boolean> list){
		for(int i = 0; i < list.size(); i++){
			System.out.print((list.get(i) ? "1" : "0") + " ");
		}
		System.out.println("");
	}
	private void makePermutation(int[] array, int startIndex){
		//int[] array = a.clone();
		saveArray(array);
		
		if(startIndex < array.length && array[startIndex] != 0){
			makePermutation(array, startIndex + 2);
			moveElement(array,startIndex);
		}
	}
	private void moveElement(int[] a, int startIndex){
		int[] array = a.clone();
		while(array[array.length-1] == 0){
			for(int i = array.length-1; i > startIndex; i--){
				array[i] = array[i-1];
			}
			array[startIndex] = 0;
			makePermutation(array, startIndex + 3);
			makePermutation(array, startIndex + 1);
		}
	}
	private void printArray(int[] a, String prefix){
		System.out.println("----------------");
		System.out.println(prefix);
		for (int i = 0; i < a.length; i++) {
			System.out.print(a[i]+" ");

		}
		System.out.println("");
	}

	private void saveArray(int[] a) {
		int[] array = a.clone();
		for(int i = 0; i < tempDomain.size(); i++){
			if(Arrays.equals(tempDomain.get(i),array)){
				return;
			}
		}
		tempDomain.add(array);
	}

	public int getDomainSum(){
		int sum = 0;
		for(int i = 0; i < colDomains.size(); i++){
			sum += colDomains.get(i).domain.size();
		}
		for(int i = 0; i < rowDomains.size(); i++){
			sum += rowDomains.get(i).domain.size();
		}
		return sum;		
	}
	private NNStateNode(ArrayList<NNColRow> colChanges, ArrayList<NNColRow> rowChanges){
		for(int i = 0; i < colChanges.size(); i++){
			colDomains.add(colChanges.get(i).cloneColRow());
		}
		for(int i = 0; i < rowChanges.size(); i++){
			rowDomains.add(rowChanges.get(i).cloneColRow());
		}
	}
	public NNStateNode generateStateNode(ArrayList<NNColRow> colChanges, ArrayList<NNColRow> rowChanges){
		NNStateNode newNode = new NNStateNode(colChanges,rowChanges);
		if(newNode.validateConstraints()){
			this.addChild(newNode);
		}
		else{
			System.out.println("Not valid");
		}
		return newNode;
	}
	public boolean domainCheck(){
		for(int i=0; i<this.colDomains.size(); i++){
			if(this.colDomains.get(i).getDomain().size() == 0){
				return false;
			}
		}
		for(int j=0; j<this.rowDomains.size(); j++){
			if(this.rowDomains.get(j).getDomain().size() == 0){
				return false;
			}
		}
		return true;
	}
	
	public int getSmallestRowDomainIndex(){
		NNColRow smallest = null;
		int h=100;
		int midPos = 0;
		for(int i=0; i<this.rowDomains.size(); i++){
			if(h>this.rowDomains.get(i).getDomain().size() && this.rowDomains.get(i).getDomain().size() >= 2){
				h=this.rowDomains.get(i).getDomain().size();
				smallest = this.rowDomains.get(i);
				midPos = i;
			}
		}
		smallestPos = midPos;
		return midPos;
	}
	public int getSmallestColDomainIndex(){
		NNColRow smallest = null;
		int h=100;
		int midPos = 0;
		for(int i=0; i<this.colDomains.size(); i++){
			if(h>this.colDomains.get(i).getDomain().size() && this.colDomains.get(i).getDomain().size() >= 2){
				h = this.colDomains.get(i).getDomain().size();
				smallest = this.colDomains.get(i);
				midPos = i;
			}
		}
		smallestPos = midPos;
		return midPos;
	}
	public boolean validateConstraints(){
		for(int i = 0; i < colDomains.size(); i++){
			if(!colDomains.get(i).validateConstraint()){
				return false;
			}
			if(!colDomains.get(i).crossValidate(i, rowDomains)){
				return false;
			}
		}
		for(int i = 0; i < rowDomains.size(); i++){
			if(!rowDomains.get(i).validateConstraint()){
				return false;
			}
			if(!rowDomains.get(i).crossValidate(i, colDomains)){
				return false;
			}
		}
		return true;
	}
	public Boolean[][] buildArray(){
		Boolean[][] resultArray = new Boolean[rowDomains.size()][colDomains.size()];
		for(int i = 0; i < rowDomains.size(); i++){
			if(rowDomains.get(i).getValue() != null){
				for(int j = 0; j < rowDomains.get(i).getValue().size(); j++){
					if(rowDomains.get(i).getValue().get(j) != null){
						resultArray[i][j] = rowDomains.get(i).getValue().get(j);
					}
				}
			}

		}
		for(int i = 0; i < colDomains.size(); i++){
			if(colDomains.get(i).getValue() != null){
				for(int j = 0; j < colDomains.get(i).getValue().size(); j++){
					if(colDomains.get(i).getValue().get(j) != null){
						resultArray[j][i] = colDomains.get(i).getValue().get(j);
					}
				}
			}

		}
		return resultArray;
	}
}
