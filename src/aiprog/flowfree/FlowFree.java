package aiprog.flowfree;

import aiprog.model.FFStateNode;
import aiprog.model.Node;
import aiprog.search.AStar;


//Hopper over et nivå med arving her
//Bare sånn at vi slipper bittelitt casting:)
//Også er det litt mer oversiktlig med shit i samme klasse
public class FlowFree extends AStar {

	public FlowFree(Node startNode) {
		super(startNode);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected boolean checkVictory() {
		// TODO Auto-generated method stub
		return false;
	}
	
	//Skal bare kjøre på S0
	//Kan kanskje kjøre i starten på alle states, er ikke sikker
	//Mulig det er mer effektivt og putte noe av dette inn i reduction()
	private void initModifications(){
		
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
