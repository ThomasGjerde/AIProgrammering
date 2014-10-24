package aiprog.flowfree;

import java.util.ArrayList;

import aiprog.gui.FFGraphics;
import aiprog.gui.Graphics;
import aiprog.model.FFNode;
import aiprog.model.FFStateNode;
import aiprog.model.GACCSPNode;
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
	public FFGraphics graphic;
	public FlowFree(Node startNode, FFGraphics graphics) {
		super(startNode);
		currentState = (FFStateNode) startNode;
		graphic = graphics;
		initModifications(currentState);
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
		edge = (int) Math.sqrt(state.getNodes().size()) - 1;
		
		//Hvis en barne node er det eneste alternativet, skal denne fylles med den "respektive" fargen
		ArrayList<FFNode> colorList = new ArrayList<FFNode>();
		ArrayList<FFNode> midList = new ArrayList<FFNode>();
		ArrayList<FFNode> edgeList = new ArrayList<FFNode>();
		//ArrayList<FFNode> edgeColorList = new ArrayList<FFNode>();
		//Adder alle noder med farge til en liste
		for(int i=0; i<state.getNodes().size(); i++){
			if(((FFNode)state.getNodes().get(i)).getColor() != null){
				colorList.add(((FFNode)state.getNodes().get(i))); //Alle med farge satt
			}
			if(suppInit((FFNode)state.getNodes().get(i))){
				edgeList.add((FFNode)state.getNodes().get(i));
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
							full.get(o+x).setColor(colorStart.getColor(),null);
							graphic.setState(currentState);
						}
					}
				}
			}
		}
	}
	
	//Ja, det har g�tt s� langt at her lager man support metoder, som kun skal brukes p� den f�rste staten
	//faen da, den er fremdeles 11...... WHY!?! Den skal v�re minst 13, nei den skal v�re 18, nei den skal v�re 20 bah
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
	
	//kan evt v�re deduction
	//skal fikse det �penbare
	public void reduction(){
		
	}

	@Override
	protected void processCurrentNode() {
		
		
	}

}
