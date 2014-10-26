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
	public boolean stateFail;
	public FlowFree(Node startNode, FFGraphics graphics) {
		super(startNode);
		currentState = (FFStateNode) startNode;
		graphic = graphics;
		stateFail = false;
		initModifications(currentState);
		counter = 0;
		reduction(currentState, true);
		currentState.checkAllConstraints();
		if(currentState.checkAllConstraints()){
			System.out.println("Victory");
		}else{
			assumptions(currentState);
			this.search();
		}
		
	}
	
	@Override
	protected boolean checkVictory() {
		// TODO Auto-generated method stub
		return ((FFStateNode)currentNode).checkAllConstraints();
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
		
		//alle tomme gjorne noder blir satt til en farge hvis det bare er 1 mulighet
		FFNode venstreTop = null;
		FFNode venstreBot = null;
		FFNode hoyreBot = null;
		FFNode hoyreTop = null;
		
		for(int ll=0; ll<state.getNodes().size(); ll++){
			if(state.getNodes().get(ll).pos.x == 0 && state.getNodes().get(ll).pos.y == 0){
				venstreTop = state.getNodes().get(ll);
			}
			if(state.getNodes().get(ll).pos.x == edge && state.getNodes().get(ll).pos.y == edge){
				hoyreBot = state.getNodes().get(ll);
			}
			if(state.getNodes().get(ll).pos.x == 0 && state.getNodes().get(ll).pos.y == edge){
				venstreBot = state.getNodes().get(ll);
			}
			if(state.getNodes().get(ll).pos.x == edge && state.getNodes().get(ll).pos.y == 0){
				hoyreTop = state.getNodes().get(ll);
			}
		}
		ArrayList<FFNode> hjorneArray = new ArrayList<FFNode>();
		hjorneArray.add(venstreTop);
		hjorneArray.add(venstreBot);
		hjorneArray.add(hoyreTop);
		hjorneArray.add(hoyreBot);
		
		for(int cc = 0; cc<hjorneArray.size(); cc++){
			ArrayList<FFNode> midList = new ArrayList<FFNode>();
			for(int vv=0; vv<hjorneArray.get(cc).getChildren().size(); vv++){
				if(((FFNode)hjorneArray.get(cc).getChildren().get(vv)).getColor() != null){
					midList.add((FFNode)hjorneArray.get(cc).getChildren().get(vv));
				}
			}
			if(midList.size() == 1){
				hjorneArray.get(cc).setColor(midList.get(0).getColor(), midList.get(0));
				midList.clear();
			}
			midList.clear();
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
	public boolean reduceSingle(FFNode node){
		ArrayList<FFNode> midList = new ArrayList<FFNode>();
		for(int i=0; i<node.getChildren().size(); i++){
			FFNode midNode = (FFNode)node.getChildren().get(i);
			if(midNode.isHead() && midNode.getColor() == node.getColor() && !midNode.isEndPoint()){
				return false;
			}
			if(midNode.getColor() == null){
				midList.add(midNode);
			}
		}
		if(midList.size() == 0){
			stateFail = true;
			return false;
		}
		if(midList.size() == 1){
			stateFail = false;
			midList.get(0).setColor(node.getColor(), node);
			try {
				
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return true;
		}
		return false;
	}
	
	public void reduction(FFStateNode state, boolean check){
		if(check){
			check = false;
			ArrayList<FFNode> endArray = state.getAllEndOfPathNodes();
			for(int i=0; i<endArray.size(); i++){
				boolean result = reduceSingle(endArray.get(i));
				/*if(result){
					check = true;
					graphic.setState(state);
				}*/
				check = result;
				graphic.setState(state);
			}
		}
		if(check){
			reduction(state, true);
		}
	}

	@Override
	protected void processCurrentNode() {
		FFStateNode current = (FFStateNode)currentNode;
		current.applyChanges();
		reduction(current,true);
		if(!stateFail){
			assumptions(current);
		}else{
			stateFail = false;
		}
	}
	
	public void pathAssumption(){
		//Kanskje vi faktisk skal bruke domener her
		//Vi burde velge ut en farge/og en av nodene i dette paret
		//så må vi lage en state per mulige path, en path er mulig hvis den overholder alle "pathreglene" og at den ender opp hos endnoden den ikke starta fra
		//kanskje vi må lage et path object
		//For det minste mappet så får vi ikke så mange mulige paths, feks, og hvis en path ikke er mulig og lage, så skal staten legges til closed
	}
	
	
	public void assumptionsSupp(FFStateNode state, FFNode node){
		ArrayList<FFNode> cloneArray = new ArrayList<FFNode>();
		FFNode midParent = null;
		FFNode midChild = null;
		for(int c=0; c<state.getNodes().size(); c++){
			cloneArray.add(state.getNodes().get(c).cloneNode());
			//System.out.println("cloneArray" + cloneArray.get(c).getColor());
			
			if(cloneArray.get(c).pos.x == node.getParentPos().x && cloneArray.get(c).pos.y == node.getParentPos().y){
				midParent = cloneArray.get(c);
			}
			if(cloneArray.get(c).pos.x == node.pos.x && cloneArray.get(c).pos.y == node.pos.y){
				midChild = cloneArray.get(c);
			}
		}
		midChild.setColor(node.getColor(), midParent);
		state.generateStateNode(cloneArray);
		
	}
	
	public void assumptions(FFStateNode state){
		
		ArrayList<FFNode> headList = state.getAllEndOfPathNodes();
		//int counter = 0;
		
		for(int i=0; i<headList.size(); i++){
			FFNode midHead = headList.get(i);
			for(int j=0; j<midHead.getChildren().size(); j++){
				FFNode midChild = ((FFNode)midHead.getChildren().get(j)).cloneNode();
				if(midChild.getColor() == null){
					midChild.setColor(midHead.getColor(), midHead);
					assumptionsSupp(state, midChild);
					//counter++;
					//System.out.println("midchild x: " + midChild.pos.x + " y: " + midChild.pos.y + " counter " + counter);
				}
				//state.generateStateNode(cloneArray);
			}
		}
	}
	
	//public ArrayList<FFNode> revert(ArrayList<FFNode> )

}
