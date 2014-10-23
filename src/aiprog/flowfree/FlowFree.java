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
	//Check
	int edge;
	int counter;
	FFStateNode currentState;
	public FlowFree(Node startNode) {
		super(startNode);
		currentState = (FFStateNode) startNode;
		initModifications(currentState);
		edge = (currentState.getNodes().size() / 4) -1;
		counter = 0;
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
			System.out.println("node check " + state.getNodes().get(i).pos.y);
			if(suppInit((FFNode)state.getNodes().get(i))){
				edgeList.add((FFNode)state.getNodes().get(i));
				if(((FFNode)state.getNodes().get(i)).getColor() != null){
					edgeColorList.add((FFNode)state.getNodes().get(i));
				}
			}
			/*
			if(state.getNodes().get(i).pos.x == 0 || state.getNodes().get(i).pos.y == 0 || state.getNodes().get(i).pos.x == edge || state.getNodes().get(i).pos.y == edge){
				System.out.println("node check1 " + state.getNodes().get(i).pos.y);
				FFNode midNode = new FFNode(state.getNodes().get(i).pos, ((FFNode)state.getNodes().get(i)).getDomain().size());
				edgeList.add(midNode);	//Alle noder p� en kant
				if(((FFNode)state.getNodes().get(i)).getColor() != null){
					edgeColorList.add(midNode); //Alle noder med farger p� kant, disse er ogs� i edgeList but hey
				}
			}*/
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
		
		//Hem den her er faktisk litt tricky
		//Veien langs kanten burde v�re den korteste tilgjengelig
		//Men f�r det m� jeg vite at veien er "clear"
		//Hem, kanskje jeg skal lage 4 arrays for v�r kant? Virker unn�dvendig
		//Jeg kunne kj�rt a* fra nodene i edgeColorList, virker but does it work????
		//I s�fall m� vi modifisere a* og nodene ganske heftig, ikke verdt det imo
		//Fck it!
		ArrayList<FFNode> topList = new ArrayList<FFNode>();
		ArrayList<FFNode> rightList = new ArrayList<FFNode>();
		ArrayList<FFNode> leftList = new ArrayList<FFNode>();
		ArrayList<FFNode> bottomList = new ArrayList<FFNode>();
		for(int l=0; l<edgeList.size(); l++){
			if(edgeList.get(l).pos.x == 5){
				rightList.add(edgeList.get(l));
			}
			if(edgeList.get(l).pos.y == 5){
				bottomList.add(edgeList.get(l));
			}
			if(edgeList.get(l).pos.x == 0){
				leftList.add(edgeList.get(l));
			}
			if(edgeList.get(l).pos.y == 0){
				topList.add(edgeList.get(l));
			}
			//hem, alle y verdiene er fucked....
			//og det er bare 11 noder i edgelist, wtf?
			//System.out.println("nodepos x: " + edgeList.get(l).pos.y + " y: " + edgeList.get(l));
		}
		System.out.println("edgeListSize " + edgeList.size());
		//System.out.println("topList " + topList.size());
		//System.out.println("leftList " + leftList.size());
		//System.out.println("rightList " + rightList.size());
		//System.out.println("bottomList " + bottomList.size());
		//Hvis en hel path er ledig langs kanten, fyll den med den "respektive fargen"
		System.out.println("counter " + counter);
	}
	
	//Ja, det har g�tt s� langt at her lager man support metoder, som kun skal brukes p� den f�rste staten
	//faen da, den er fremdeles 11...... WHY!?! Den skal v�re minst 13, nei den skal v�re 18
	private boolean suppInit(FFNode node){
		counter = counter + 1;
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
	
	//kan evt v�re deduction
	//skal fikse det �penbare
	public void reduction(){
		
	}

	@Override
	protected void processCurrentNode() {
		// TODO Auto-generated method stub
		
	}

}
