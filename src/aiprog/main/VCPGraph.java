package aiprog.main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import aiprog.model.ColorNode;
import aiprog.model.Node;
import aiprog.model.Point;
import aiprog.utility.IOUtils;

public class VCPGraph {
	int numNodes = 0;
	int numEdges = 0;
	//ArrayList<ColorNode> nodes = new ArrayList<ColorNode>();
	Map<Integer,ColorNode> nodeMap = new HashMap<Integer, ColorNode>();
	public VCPGraph(String path) throws IOException{
		ArrayList<String> input = IOUtils.getInputFromFile(path);
		setNums(parseLine(input.get(0)));
		generateNodes(input);
		generateEdges(input);
		
	}
	private void generateNodes(ArrayList<String> input){
		for(int i = 1; i < numNodes+1; i++){
			ArrayList<Double> tempList = parseLine(input.get(i));
			ColorNode cn = new ColorNode(new Point());
				cn.id = tempList.get(0).intValue();
				cn.pos.setDoubleX(tempList.get(1));
				cn.pos.setDoubleY(tempList.get(2));
				//nodes.add(cn);
				nodeMap.put(cn.id, cn);
				/*
				System.out.println("ID: " + cn.id);
				System.out.println("PosX: " + cn.pos.getDoubleX());
				System.out.println("PosY: " + cn.pos.getDoubleY());
				*/
		}
	}
	private void generateEdges(ArrayList<String> input){
		
		for(int i = (numNodes+2); i < numNodes + 2 + numEdges - 1; i++){
			ArrayList<Double> edges = parseLine(input.get(i));
			int startEdge = edges.get(0).intValue();
			int endEdge = edges.get(1).intValue();
			nodeMap.get(startEdge).addChild(nodeMap.get(endEdge));
			nodeMap.get(endEdge).addChild(nodeMap.get(startEdge));
		}
		Iterator it = nodeMap.entrySet().iterator();
		while(it.hasNext()){
			Map.Entry<Integer,ColorNode> pairs = (Map.Entry<Integer,ColorNode>)it.next();
			ColorNode node = pairs.getValue();
			for(Node cn : node.getChildren()){
				System.out.println("Parent: " + node.id + " Child: " + ((ColorNode)cn).id);
			}
			
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
