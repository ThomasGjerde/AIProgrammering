package aiprog.main;

import java.io.IOException;
import java.util.ArrayList;

import aiprog.model.ColorNode;
import aiprog.model.Point;
import aiprog.utility.IOUtils;

public class VCPGraph {
	int numNodes = 0;
	int numEdges = 0;
	ArrayList<ColorNode> nodes = new ArrayList<ColorNode>();
	public VCPGraph(String path) throws IOException{
		ArrayList<String> input = IOUtils.getInputFromFile(path);
		setNums(parseLine(input.get(0)));
		generateNodes(input);
	}
	private void generateNodes(ArrayList<String> input){
		for(int i = 1; i < numNodes+1; i++){
			ArrayList<Double> tempList = parseLine(input.get(i));
			ColorNode cn = new ColorNode(new Point());
				cn.id = tempList.get(0).intValue();
				cn.pos.setDoubleX(tempList.get(1));
				cn.pos.setDoubleY(tempList.get(2));
				System.out.println("ID: " + cn.id);
				System.out.println("PosX: " + cn.pos.getDoubleX());
				System.out.println("PosY: " + cn.pos.getDoubleY());
		}
	}
	private void setNums(ArrayList<Double> input){
		numNodes = input.get(0).intValue();
		numEdges = input.get(1).intValue();

	}
	private ArrayList<Double> parseLine(String input){
		String tempArray[] = input.split(" ");
		ArrayList<Double> returnArray = new ArrayList<Double>();
		for(int i = 0; i < tempArray.length; i++)
		{
			if(tempArray[i] != ""){
				returnArray.add(Double.parseDouble(tempArray[i]));
			}
			
		}
		return returnArray;
	}
}
