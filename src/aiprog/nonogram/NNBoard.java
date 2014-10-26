package aiprog.nonogram;

import java.io.IOException;
import java.util.ArrayList;

import aiprog.utility.IOUtils;

public class NNBoard {
	public boolean[][] boardArray;
	public ArrayList<ArrayList<Integer>> colConstraints;
	public ArrayList<ArrayList<Integer>> rowConstraints;
	public NNBoard(String path) throws IOException{
		ArrayList<String> input = IOUtils.getInputFromFile(path);
		ArrayList<Integer> paramLine = parseLine(input.get(0));
		boardArray = new boolean[paramLine.get(1)][paramLine.get(0)];
		colConstraints = new ArrayList<ArrayList<Integer>>();
		rowConstraints = new ArrayList<ArrayList<Integer>>();
		for(int i = 1; i < boardArray.length + 1; i++){
			ArrayList<Integer> line = parseLine(input.get(i));
			ArrayList<Integer> row = new ArrayList<Integer>();
			for(int j = 0; j < line.size(); j++){
				row.add(line.get(j));
			}
			rowConstraints.add(row);
		}
		for(int i = boardArray.length + 1; i < boardArray.length + 1 + boardArray[0].length;i++){
			ArrayList<Integer> line = parseLine(input.get(i));
			ArrayList<Integer> col = new ArrayList<Integer>();
			for(int j = 0; j < line.size(); j++){
				col.add(line.get(j));
			}
			colConstraints.add(col);
		}
	}

	private ArrayList<Integer> parseLine(String line){
		String[] splitArray = line.split(" ");
		ArrayList<Integer> retArray = new ArrayList<Integer>();
		for(int i = 0; i < splitArray.length; i++){
			retArray.add(Integer.parseInt(splitArray[i]));
		}
		return retArray;
	}
}
