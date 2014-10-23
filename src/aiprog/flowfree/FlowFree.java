package aiprog.flowfree;

import java.util.ArrayList;

import aiprog.gui.FFGraphics;
import aiprog.gui.Graphics;
import aiprog.model.FFNode;
import aiprog.model.FFStateNode;
import aiprog.model.GACCSPNode;
import aiprog.model.Node;
import aiprog.search.AStar;


//Hopper over et nivå med arving her
//Bare sånn at vi slipper bittelitt casting:)
//Også er det litt mer oversiktlig med shit i samme klasse
public class FlowFree extends AStar {
	//Check
	int edge;
	int counter;
	FFStateNode currentState;
	public FFGraphics graphic;
	public FlowFree(Node startNode, FFGraphics graphics) {
		super(startNode);
		currentState = (FFStateNode) startNode;
		initModifications(currentState);
		graphic = graphics;
		counter = 0;
	}
	
	@Override
	protected boolean checkVictory() {
		// TODO Auto-generated method stub
		return false;
	}
	
	//Skal bare kjøre på S0
	//Kan kanskje kjøre i starten på alle states, er ikke sikker
	//Mulig det er mer effektivt og putte noe av dette inn i reduction()
	private void initModifications(FFStateNode state){
		edge = (int) Math.sqrt(state.getNodes().size()) - 1;
		
		//Hvis en barne node er det eneste alternativet, skal denne fylles med den "respektive" fargen
		ArrayList<FFNode> colorList = new ArrayList<FFNode>();
		ArrayList<FFNode> midList = new ArrayList<FFNode>();
		ArrayList<FFNode> edgeList = new ArrayList<FFNode>();
		ArrayList<FFNode> edgeColorList = new ArrayList<FFNode>();
		//Adder alle noder med farge til en liste
		for(int i=0; i<state.getNodes().size(); i++){
			if(((FFNode)state.getNodes().get(i)).getColor() != null){
				colorList.add(((FFNode)state.getNodes().get(i))); //Alle med farge satt
			}
			if(suppInit((FFNode)state.getNodes().get(i))){
				edgeList.add((FFNode)state.getNodes().get(i));
				if(((FFNode)state.getNodes().get(i)).getColor() != null){
					edgeColorList.add((FFNode)state.getNodes().get(i));
				}
			}
		}
		
		//Sjekker for eneste mulighet barn
		for(int j=0; j<colorList.size(); j++){
			for(int k=0; k<colorList.get(j).getChildren().size(); k++){
				if(((FFNode)colorList.get(j).getChildren().get(k)).getColor() == null){
					midList.add((FFNode)colorList.get(j).getChildren().get(k));
				}
			}
			if(midList.size() == 1){
				midList.get(0).setColor(colorList.get(j).getColor());
			}
			midList.clear();
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
		System.out.println("right: " + right.size());
		System.out.println("left " + left.size());
		
		
		//Fjerner alle singe farga noder på kantene
		ArrayList<FFNode> edgePairs = new ArrayList<FFNode>();
		while(!edgeColorList.isEmpty()){
			FFNode checkNode = edgeColorList.get(0);
			edgeColorList.remove(0);
			for(int k=0; k<edgeColorList.size(); k++){
				if(checkNode.getColor() == edgeColorList.get(k).getColor()){
					edgePairs.add(checkNode);
					edgePairs.add(edgeColorList.get(k));
					edgeColorList.remove(k);
				}
			}
		}
		System.out.println("edgeColorList aft " + edgeColorList.size());
		System.out.println("edgePairs " + edgePairs.size());
		//okey, så da har vi det her...
		//hem, kanskje jeg ikke trenger alle kant arrayene....
		//int xDist = Math.abs(this.x - endPoint.x);
		//int yDist = Math.abs(this.y - endPoint.y);
		//return (xDist + yDist);
		for(int m=0; m<edgePairs.size(); m++){
			FFNode start = edgePairs.get(m);
			FFNode end = edgePairs.get(m+1);
			m++;
			if(start.getColor() == end.getColor()){
				System.out.println("true");
			}
		}
		
	}
	
	//Ja, det har gått så langt at her lager man support metoder, som kun skal brukes på den første staten
	//faen da, den er fremdeles 11...... WHY!?! Den skal være minst 13, nei den skal være 18, nei den skal være 20 bah
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
	
	//duh
	@Override
	protected void updateGui() {
		// TODO Auto-generated method stub
	}
	
	
	//For og sette heuristic per state
	@Override
	protected void setHeuristic(Node node) {
		// TODO Auto-generated method stub
		
	}
	
	//kan evt være deduction
	//skal fikse det åpenbare
	public void reduction(){
		
	}

	@Override
	protected void processCurrentNode() {
		
		
	}

}
