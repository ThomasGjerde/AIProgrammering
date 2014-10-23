package aiprog.flowfree;

import java.util.ArrayList;

import aiprog.model.FFNode;
import aiprog.model.FFStateNode;
import aiprog.model.Node;
import aiprog.search.AStar;


//Hopper over et niv� med arving her
//Bare s�nn at vi slipper bittelitt casting:)
//Ogs� er det litt mer oversiktlig med shit i samme klasse
public class FlowFree extends AStar {

	FFStateNode currentState;
	public FlowFree(Node startNode) {
		super(startNode);
		currentState = (FFStateNode) startNode;
		initModifications(currentState);
		
	}
	
	@Override
	protected boolean checkVictory() {
		// TODO Auto-generated method stub
		return false;
	}
	
	//Skal bare kj�re p� S0
	//Kan kanskje kj�re i starten p� alle states, er ikke sikker
	//Mulig det er mer effektivt og putte noe av dette inn i reduction()
	private void initModifications(FFStateNode state){
		
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
			if(state.getNodes().get(i).pos.x == 0 || state.getNodes().get(i).pos.y == 0){
				edgeList.add((FFNode)state.getNodes().get(i));	//Alle noder p� en kant
				if(((FFNode)state.getNodes().get(i)).getColor() != null){
					edgeColorList.add((FFNode)state.getNodes().get(i)); //Alle noder med farger p� kant, disse er ogs� i edgeList but hey
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
		//Dette her burde egentlig bli standard staten, pga det er ikke mulig at dette kan v�re feil, eller koden kan v�re feil men om den funker s� er dette garantert riktig
		//Hem, how to do this..... jeg har lyst p� vin, men s� har jeg ikke vin, DILEMMA!
		//Dette her burde bli en egen state, for dette er ikke "garantert" riktig
		
		
		
		//Hvis en hel path er ledig langs kanten, fyll den med den "respektive fargen"
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
	
	//kan evt v�re deduction
	//skal fikse det �penbare
	public void reduction(){
		
	}

	@Override
	protected void processCurrentNode() {
		// TODO Auto-generated method stub
		
	}

}
