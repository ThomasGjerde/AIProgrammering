package aiprog.flowfree;

import java.util.ArrayList;

import aiprog.gui.FFGraphics;
import aiprog.model.FFNode;
import aiprog.model.FFStateNode;
import aiprog.model.Node;
import aiprog.search.AStar;

public class FlowFree extends AStar {
	//Check
	int edge;
	int counter;
	FFStateNode currentState;
	public FFGraphics graphic;
	public FlowFree(Node startNode, FFGraphics graphics) {
		super(startNode);
		currentState = (FFStateNode) startNode;
		graphic = graphics;
		initModifications(currentState);
		counter = 0;
		reduction(currentState, true);
	}
	
	@Override
	protected boolean checkVictory() {
		// TODO Auto-generated method stub
		return false;
	}
	
	//Skal bare kjøre på S0
	//Kan kanskje kjøre i starten på alle states, er ikke sikker
	private void initModifications(FFStateNode state){
		edge = (int) Math.sqrt(state.getNodes().size()) - 1;
		
		//Start antagelser
		ArrayList<FFNode> cloneArray = new ArrayList<FFNode>();
		for(int ii=0; ii<state.getNodes().size(); ii++){
			cloneArray.add(state.getNodes().get(ii).cloneNode());
		}
		ArrayList<FFNode> edgeList = new ArrayList<FFNode>();
		for(int a=0; a<cloneArray.size(); a++){
			if(suppInit(cloneArray.get(a))){
				edgeList.add(cloneArray.get(a));
			}
		}
		
		
		ArrayList<FFNode> right = new ArrayList<FFNode>();
		ArrayList<FFNode> left = new ArrayList<FFNode>();
		for(int n=0; n<edgeList.size(); n++){
			if(edgeList.get(n).pos.x == 0 || edgeList.get(n).pos.y == edge){
				right.add(edgeList.get(n));
			}
			if(edgeList.get(n).pos.x == edge || edgeList.get(n).pos.y == 0){
				left.add(edgeList.get(n));
			}
		}
		
		//Alle edgenoder i et "rundt" array
		ArrayList<FFNode> full = new ArrayList<FFNode>();
		for(int l=0; l<right.size(); l++){
			full.add(right.get(l));
			
		}
		for(int m=left.size()-2; 0<m; m--){
			full.add(left.get(m));
		}
		
		//Dette gaar bare den ene veien
		for(int o=0; o<full.size(); o++){
			if(full.get(o).getColor() != null){
				FFNode colorStart = full.get(o);
				int pathLengthRight = 0;
				
				for(int z=o+1; z<full.size(); z++){
					if(full.get(z).getColor() == null){
						pathLengthRight++;
					}else if(full.get(z).getColor() != colorStart.getColor()){
						pathLengthRight = 0;
						break;
					}else if(full.get(z).getColor() == colorStart.getColor()){
						break;
					}
				}
				if(pathLengthRight != 0){
					pathLengthRight++;
					for(int x=0; x<pathLengthRight; x++){
						if(full.get(o+x).getColor() == null){
							full.get(o+x).setColor(colorStart.getColor(),full.get(o+x-1));
						}
					}
					FFStateNode genState = currentState.generateStateNode(cloneArray);
				}
			}
		}
	}
	
	private boolean suppInit(FFNode node){
		if(node.pos.y == edge){
			return true;
		}else if(node.pos.y == 0){
			return true;
		}else if(node.pos.x == edge){
			return true;
		}else if(node.pos.x == 0){
			return true;
		}
		return false;
	}
	
	@Override
	protected void updateGui() {
		// TODO Auto-generated method stub
	}
	
	
	//For og sette heuristic per state
	@Override
	protected void setHeuristic(Node node) {
		// TODO Auto-generated method stub
		//Blanco it is
	}
	
	//kan evt være deduction
	//skal fikse det åpenbare
	public void reduction(FFStateNode state, boolean check){
		
		if(check){
			check = false;
			FFStateNode redState = state;
			ArrayList<FFNode> endArray = redState.getAllEndOfPathNodes();
			for(int i=0; i<endArray.size(); i++){
				ArrayList<FFNode> midList = new ArrayList<FFNode>();
				for(int j=0; j<endArray.get(i).getChildren().size(); j++){
					FFNode midNode = (FFNode)endArray.get(i).getChildren().get(j);
					if(midNode.getColor() == null){
						midList.add(midNode);
					}
				}
				if(midList.size() == 1){
					midList.get(0).setColor(endArray.get(i).getColor(), endArray.get(i));
					graphic.setState(redState);
					check = true;
					try {
						
						Thread.sleep(500);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				midList.clear();
			}
		}
		if(check){
			reduction(state, check);
		}
	}

	@Override
	protected void processCurrentNode() {
		
		
	}

}
