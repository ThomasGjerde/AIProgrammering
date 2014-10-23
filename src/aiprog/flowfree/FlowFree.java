package aiprog.flowfree;

import java.util.ArrayList;

import aiprog.model.FFNode;
import aiprog.model.FFStateNode;
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
	
	//Skal bare kjøre på S0
	//Kan kanskje kjøre i starten på alle states, er ikke sikker
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
				edgeList.add(midNode);	//Alle noder på en kant
				if(((FFNode)state.getNodes().get(i)).getColor() != null){
					edgeColorList.add(midNode); //Alle noder med farger på kant, disse er også i edgeList but hey
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
		//Dette her burde egentlig bli standard staten, pga det er ikke mulig at dette kan være feil, eller koden kan være feil men om den funker så er dette garantert riktig
		//Hem, how to do this..... jeg har lyst på vin, men så har jeg ikke vin, DILEMMA!
		//Dette her burde bli en egen state, for dette er ikke "garantert" riktig
		
		//Hem den her er faktisk litt tricky
		//Veien langs kanten burde være den korteste tilgjengelig
		//Men før det må jeg vite at veien er "clear"
		//Hem, kanskje jeg skal lage 4 arrays for vær kant? Virker unnødvendig
		//Jeg kunne kjørt a* fra nodene i edgeColorList, virker but does it work????
		//I såfall må vi modifisere a* og nodene ganske heftig, ikke verdt det imo
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
	
	//Ja, det har gått så langt at her lager man support metoder, som kun skal brukes på den første staten
	//faen da, den er fremdeles 11...... WHY!?! Den skal være minst 13, nei den skal være 18
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
	
	//kan evt være deduction
	//skal fikse det åpenbare
	public void reduction(){
		
	}

	@Override
	protected void processCurrentNode() {
		// TODO Auto-generated method stub
		
	}

}
