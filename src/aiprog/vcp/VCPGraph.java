package aiprog.vcp;

import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import bsh.EvalError;

import aiprog.gui.GraphGraphics;
import aiprog.model.CSPNode;
import aiprog.model.GACCSPNode;
import aiprog.model.Point;
import aiprog.model.VCPNode;
import aiprog.utility.IOUtils;

public class VCPGraph {
	int numNodes = 0;
	int numEdges = 0;
	int k;
	ArrayList<String> constraintVars = new ArrayList<String>();
	String constraintExpression;
	Map<Integer,VCPNode> nodeMap = new HashMap<Integer, VCPNode>();
	public VCPGraph(String path, int k) throws IOException{
		this.k = k;
		//Constraints
		constraintVars.add("a");
		constraintVars.add("b");
		constraintExpression = "a.getNodeValue() != b.getNodeValue()";
		
		ArrayList<String> input = IOUtils.getInputFromFile(path);
		setNums(parseLine(input.get(0)));
		generateNodes(input);
		generateEdges(input);
		GACCSPNode initStateNode = generateInitialStateNode();
		VCP vcp = new VCP(initStateNode);
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private GACCSPNode generateInitialStateNode(){
		ArrayList<CSPNode> vcpNodes = new ArrayList<CSPNode>();
		Iterator it = nodeMap.entrySet().iterator();
		while(it.hasNext()){
			Map.Entry<Integer,VCPNode> pairs = (Map.Entry<Integer,VCPNode>)it.next();
			vcpNodes.add((CSPNode)pairs.getValue());	
		}
		GACCSPNode gacNode = new GACCSPNode();
		gacNode.setCSPList(vcpNodes);
		for(int i = 0; i < gacNode.getCSPList().size(); i++){
			VCPNode oldNode = (VCPNode)gacNode.getCSPList().get(i);
			VCPNode newNode = new VCPNode(constraintVars,constraintExpression,k);
			newNode.setId(oldNode.getId());
			newNode.setColor(oldNode.getColor());
			newNode.setDomain(new ArrayList<Integer>(oldNode.getDomain()));
			gacNode.addChange((CSPNode)newNode);
		}
		return gacNode;
	}
	private void generateNodes(ArrayList<String> input){
		for(int i = 1; i < numNodes+1; i++){
			ArrayList<Double> tempList = parseLine(input.get(i));
			VCPNode vcpNode = new VCPNode(constraintVars,constraintExpression,k);
				vcpNode.setId(tempList.get(0).intValue());
				vcpNode.getPos().setX(tempList.get(1));
				vcpNode.getPos().setY(tempList.get(2));			
				nodeMap.put(vcpNode.getId(), vcpNode);
		}
	}
	private void generateEdges(ArrayList<String> input){
		
		for(int i = (numNodes+1); i < numNodes + 2 + numEdges - 1; i++){
			ArrayList<Double> edges = parseLine(input.get(i));
			int startEdge = edges.get(0).intValue();
			int endEdge = edges.get(1).intValue();
			nodeMap.get(startEdge).addChild(nodeMap.get(endEdge));
			nodeMap.get(endEdge).addChild(nodeMap.get(startEdge));
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
