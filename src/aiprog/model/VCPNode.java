package aiprog.model;

import java.awt.Color;
import java.util.ArrayList;

import bsh.EvalError;
import bsh.Interpreter;

public class VCPNode extends CSPNode{
	
	public VCPNode(Point position, ArrayList<String> constraintVars, String constraintExpression, int k) {
		super(position, constraintVars, constraintExpression);
		standardColors(k);
	}

	@Override
	public ArrayList<Node> getUnoccupiedChildren() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Color getColor(){
		if(nodeValue == -1){
			return null;
		}
		Color color = new Color(nodeValue);
		return color;
	}
	
	public void setColor(Color color){
		if(color == null){
			nodeValue = -1;
		}else{
		nodeValue = color.getRGB();
		}
	}
	
	public void standardColors(int k){
		ArrayList<Color> standardColors = new ArrayList<Color>();
		standardColors.add(Color.RED);
		standardColors.add(Color.BLUE);
		standardColors.add(Color.CYAN);
		standardColors.add(Color.DARK_GRAY);
		standardColors.add(Color.YELLOW);
		standardColors.add(Color.LIGHT_GRAY);
		standardColors.add(Color.GREEN);
		standardColors.add(Color.PINK);
		standardColors.add(Color.MAGENTA);
		standardColors.add(Color.ORANGE);
		
		for(int i=0; i<k; i++){
			domain.add(standardColors.get(i).getRGB());
		}
		
		
	}
}
