package aiprog.flowfree;

import aiprog.model.FFStateNode;
import aiprog.model.Node;
import aiprog.search.AStar;


//Hopper over et niv� med arving her
//Bare s�nn at vi slipper bittelitt casting:)
//Ogs� er det litt mer oversiktlig med shit i samme klasse
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
	
	//Skal bare kj�re p� S0
	//Kan kanskje kj�re i starten p� alle states, er ikke sikker
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
	
	//kan evt v�re deduction
	//skal fikse det �penbare
	public void reduction(){
		
	}

	@Override
	protected void processCurrentNode() {
		// TODO Auto-generated method stub
		
	}

}
