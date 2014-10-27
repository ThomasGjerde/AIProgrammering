package aiprog.model;

import java.util.ArrayList;
import java.util.Arrays;

import aiprog.nonogram.NNBoard;

public class NNStateNode extends Node{
	public ArrayList<NNColRow> colDomains = new ArrayList<NNColRow>();
	public ArrayList<NNColRow> rowDomains = new ArrayList<NNColRow>();
	ArrayList<int[]> tempDomain;
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
		NNColRow newColRow = new NNColRow();
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
}
