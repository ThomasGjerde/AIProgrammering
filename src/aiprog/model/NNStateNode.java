package aiprog.model;

import java.util.ArrayList;
import java.util.Arrays;

import aiprog.nonogram.NNBoard;

public class NNStateNode extends Node{
	public ArrayList<NNColRow> colDomains;
	public ArrayList<NNColRow> rowDomains;
	ArrayList<int[]> tempDomain;
	public NNStateNode(NNBoard board){
		ArrayList<ArrayList<Integer>> colConstraints = board.colConstraints;
		ArrayList<ArrayList<Integer>> rowConstraints = board.rowConstraints;
		for(int i = 0; i < colConstraints.size(); i++){
			//colDomains.add(
			generateColRow(board.boardArray.length, colConstraints.get(i));
		}
		for(int i = 0; i < rowConstraints.size(); i++){
			//rowDomains.add(generateColRow(board.boardArray[0].length, rowConstraints.get(i)));
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
		//permute(list, 0);
		for(int i = 0; i < tempDomain.size(); i++){
			for(int j = 0; j < tempDomain.get(i).length; j++){
				System.out.print(tempDomain.get(i)[j]);
				
			}
			System.out.println("");
		}
		return null;
		/*
		boolean[] list = new boolean[size];
		int currentIndex = 0;
		for(int i = 0; i < constraints.size(); i++){
			for(int j = 0; j < constraints.get(i); j++){
				list[currentIndex + j] = true;
			}
			currentIndex += constraints.get(i) + 1;
		}
		 */
	}
	private void makePermutation(int[] array, int startIndex){
		System.out.println("Startindex: " + startIndex);
		try {
			Thread.sleep(0);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(startIndex < array.length){
			moveElement(array,startIndex);
		}
	}
	private void moveElement(int[] a, int startIndex){
		System.out.println("Startindex " + startIndex);
		int[] array = a.clone();
		while(array[array.length-1] == 0){
			for(int i = array.length-2; i >= startIndex; i--){
				array[i+1] = array[i];
			}
			array[startIndex] = 0;
			//printArray(array, "Array");
			makePermutation(array, startIndex + 3);
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
	private boolean arraysEqual(int[] array1, int[] array2){
		if(array1.length == array2.length){
			for(int i = 0; i < array1.length; i++){
				if(array1[i] != array2[i]){
					return false;
				}
			}
			return true;
		}else{
			return false;
		}
	}
	private void saveArray(int[] a) {
		int[] array = a.clone();
		for(int i = 0; i < tempDomain.size(); i++){
			if(Arrays.equals(tempDomain.get(i),array)){
				return;
			}
		}
		tempDomain.add(array);
		/*
		for (int i = 0; i < a.length; i++) {
			System.out.print(a[i]+" ");

		}
		System.out.println("");
		*/
	}
	private void permute(int[] a,int k ) {
		if(k==a.length){
			saveArray(a);
		}else{
			for (int i = k; i < a.length; i++) {
				int temp=a[k];
				a[k]=a[i];
				a[i]=temp;
				permute(a,k+1);
				temp=a[k];
				a[k]=a[i];
				a[i]=temp;
			}
		}
			
	}
}