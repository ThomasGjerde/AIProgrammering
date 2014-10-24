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
		graphic = graphics;
		initModifications(currentState);
		counter = 0;
		reduction(currentState);
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
		
		//Adder alle noder med farge til en liste
		for(int i=0; i<state.getNodes().size(); i++){
			if(((FFNode)state.getNodes().get(i)).getColor() != null){
				colorList.add(((FFNode)state.getNodes().get(i))); //Alle med farge satt
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
				midList.get(0).setColor(colorList.get(j).getColor(), colorList.get(j));
			}
			midList.clear();
		}
		graphic.setState(currentState);
		//Ferdig med det "garanterte"
		
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
		//IT WORKS MOTTHHAAAFUCCKAAS
		
		
		
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
					//DETTE FUNKER PGA JESUS
					pathLengthRight++;
					for(int x=0; x<pathLengthRight; x++){
						if(full.get(o+x).getColor() == null){
							full.get(o+x).setColor(colorStart.getColor(),full.get(o+x-1));
						}
					}
					FFStateNode genState = currentState.generateStateNode(cloneArray);
					//funker, men viser ikke i graphics
					//genState.applyChanges();
					//graphic.setState(genState);
				}
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
		//Blanco it is
	}
	
	//kan evt være deduction
	//skal fikse det åpenbare
	public void reduction(FFStateNode state){
		//boolean check = true;
		//int counter = 1;
		FFStateNode redState = state;
		ArrayList<FFNode> endArray = redState.getAllEndOfPathNodes();
		//almost works
		while(!endArray.isEmpty()){
			for(int i=0; i<endArray.size(); i++){
				ArrayList<FFNode> midList = new ArrayList<FFNode>();
				for(int j=0; j<endArray.get(i).getChildren().size(); j++){
					if(((FFNode)endArray.get(i).getChildren().get(j)).getColor() == null){
						midList.add((FFNode)endArray.get(i).getChildren().get(j));
					}
				}
				if(midList.size() == 1){
					midList.get(0).setColor(endArray.get(i).getColor(), endArray.get(i));
					endArray.add(midList.get(0));
				}else{
					endArray.remove(i);
				}
				midList.clear();
			}
		}
		//redState.applyChanges();
		graphic.setState(redState);
		
	}

	@Override
	protected void processCurrentNode() {
		
		
	}

}
