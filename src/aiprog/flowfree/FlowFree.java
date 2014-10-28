package aiprog.flowfree;

import java.awt.Color;
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
		counter = 0;
		setCorners(currentState);
		reduction(currentState, true);
		initModifications(currentState);
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
	
	//Skal bare kj�re p� S0
	//Kan kanskje kj�re i starten p� alle states, er ikke sikker
	private void setCorners(FFStateNode state){
		//alle tomme gjorne noder blir satt til en farge hvis det bare er 1 mulighet
				FFNode venstreTop = null;
				FFNode venstreBot = null;
				FFNode hoyreBot = null;
				FFNode hoyreTop = null;
				
				for(int ll=0; ll<state.getNodes().size(); ll++){
					if(state.getNodes().get(ll).pos.x == 0 && state.getNodes().get(ll).pos.y == 0 && ((FFNode)state.getNodes().get(ll)).getColor() == null){
						venstreTop = state.getNodes().get(ll);
					}
					if(state.getNodes().get(ll).pos.x == edge && state.getNodes().get(ll).pos.y == edge && ((FFNode)state.getNodes().get(ll)).getColor() == null){
						hoyreBot = state.getNodes().get(ll);
					}
					if(state.getNodes().get(ll).pos.x == 0 && state.getNodes().get(ll).pos.y == edge && ((FFNode)state.getNodes().get(ll)).getColor() == null){
						venstreBot = state.getNodes().get(ll);
					}
					if(state.getNodes().get(ll).pos.x == edge && state.getNodes().get(ll).pos.y == 0 && ((FFNode)state.getNodes().get(ll)).getColor() == null){
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
					if(hjorneArray.get(cc) != null){
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
				}
	}
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
					if(z == (full.size()-1)){
						pathLengthRight = 0;
						break;
					}
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
					System.out.println(full.get(o).getColor() +" " + pathLengthRight);
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
	
	//kan evt v�re deduction
	//skal fikse det �penbare
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
			//stateFail = false;
			midList.get(0).setColor(node.getColor(), node);
			try {
				
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return true;
		}
		return false;
	}
	private boolean passThroughReduction(FFNode node){
		for(int i = 0; i < node.children.size(); i++){
			FFNode child = (FFNode)node.children.get(i);
			if(child.getColor() == null){
				for(int j = 0; j < child.getDomain().size(); j++){
					int neighbouringColors = 0;
					for(int k = 0; k < child.getChildren().size(); k++){
						FFNode grandChild = (FFNode)child.getChildren().get(k);
						for(int l = 0; l < grandChild.getDomain().size(); l++){
							if(grandChild.getDomain().get(l) == child.getDomain().get(j)){
								neighbouringColors++;
							}
						}
					}
					if(neighbouringColors < 2){
						
						child.removeFromDomain(child.getDomain().get(j));
						return true;
					}
				}
			}
			
		}
		return false;
	}
	private boolean setNeighbouringSingleton(FFNode node){
		boolean changed = false;
		for(int i = 0; i < node.children.size(); i++){
			FFNode child = (FFNode)node.children.get(i);
			if(child.getDomain().size() == 1 && child.getColor() == null){
				child.setColor(child.getDomain().get(0), node);
				changed = true;
			}
		}
		return changed;
	}
	public void reduction(FFStateNode state, boolean check){
		if(check){
			check = false;
			ArrayList<FFNode> endArray = state.getAllEndOfPathNodes();
			for(int i=0; i<endArray.size(); i++){
				boolean result = reduceSingle(endArray.get(i));
				if(!result){
					boolean tempResult = passThroughReduction(endArray.get(i));
					if(tempResult){
						result = setNeighbouringSingleton(endArray.get(i));
					}
				}
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
		completedColorReduction(); //Might move this
		if(!stateFail && !current.hasFailed()){
			assumptions(current);
		}else{
			stateFail = false;
		}
	}
	public void completedColorReduction(){
		FFStateNode current = (FFStateNode)currentNode;
		ArrayList<Color> completedColors = current.getCompletedColors();
		for(int i = 0; i < completedColors.size(); i++){
			current.removeColorFromAllDomains(completedColors.get(i));
		}
	}
	public void pathAssumption(){
		//Kanskje vi faktisk skal bruke domener her
		//Vi burde velge ut en farge/og en av nodene i dette paret
		//s� m� vi lage en state per mulige path, en path er mulig hvis den overholder alle "pathreglene" og at den ender opp hos endnoden den ikke starta fra
		//kanskje vi m� lage et path object
		//For det minste mappet s� f�r vi ikke s� mange mulige paths, feks, og hvis en path ikke er mulig og lage, s� skal staten legges til closed
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
				if(midChild.getColor() == null && midChild.getDomain().contains(midHead.getColor())){
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
