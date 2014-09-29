package aiprog.main;

import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import bsh.EvalError;

import aiprog.gui.GraphGraphics;
import aiprog.model.GACNode;
import aiprog.model.Point;
import aiprog.model.ToDoRevise;
import aiprog.model.VCPNode;
import aiprog.utility.IOUtils;

public class VCPGraph {
	int numNodes = 0;
	int numEdges = 0;
	int k;
	//ArrayList<ColorNode> nodes = new ArrayList<ColorNode>();
	Map<Integer,VCPNode> nodeMap = new HashMap<Integer, VCPNode>();
	public VCPGraph(String path, int k) throws IOException{
		this.k = k;
		ArrayList<String> input = IOUtils.getInputFromFile(path);
		setNums(parseLine(input.get(0)));
		generateNodes(input);
		generateEdges(input);
		//GraphGraphics gg = new GraphGraphics((int)(Math.ceil(Math.sqrt(numNodes))), (int)(Math.ceil(Math.sqrt(numNodes))));
		//StateNode initStateNode = generateInitialStateNode();
		//ToDoRevise tdr = new ToDoRevise(initStateNode);
		//gg.setGraph(initStateNode);
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private GACNode generateInitialStateNode(){
		ArrayList<VCPNode> vcpNodes = new ArrayList<VCPNode>();
		Iterator it = nodeMap.entrySet().iterator();
		while(it.hasNext()){
			Map.Entry<Integer,VCPNode> pairs = (Map.Entry<Integer,VCPNode>)it.next();
			vcpNodes.add(pairs.getValue());	
		}
		GACNode gacNode = new GACNode(new Point(0,0));
		ArrayList<VCPNode> changesList = new ArrayList<VCPNode>();
		for(int i = 0; i < gacNode.getCSPList().size(); i++){
			VCPNode oldNode = (VCPNode)gacNode.getCSPList().get(i);
			VCPNode newNode = new VCPNode(new Point(),k);
			newNode.setId(oldNode.getId());
			newNode.setColor(oldNode.getColor());
			newNode.setDomain(new ArrayList<Integer>(oldNode.getDomain()));
			changesList.add(newNode);
		}
		sn.changes = changesList;
		return sn;
	}
	private void generateNodes(ArrayList<String> input){
		for(int i = 1; i < numNodes+1; i++){
			ArrayList<Double> tempList = parseLine(input.get(i));
			OldColorNode cn = new OldColorNode(new Point());
				cn.id = tempList.get(0).intValue();
				cn.pos.setDoubleX(tempList.get(1));
				cn.pos.setDoubleY(tempList.get(2));
				for(int j = 0; j < k; j++){
					cn.addDomain();
				}
				//<remove before production testing>
				/*
				cn.addDomain();
				cn.addDomain();
				cn.addDomain();
				if(i % 2 == 0){
					cn.setColor(Color.BLUE);
				}else{
					cn.setColor(Color.DARK_GRAY);
				}
				*/
				//</remove before production testing>
				
				nodeMap.put(cn.id, cn);
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
