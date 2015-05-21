package aiprog.model;

public class DirectedNavNode extends NavNode
{
	private Direction direction;
	public DirectedNavNode(Point position)
	{
		super(position);
		// TODO Auto-generated constructor stub
	}
	public int getNumberOfTurns(){
		int numTurns = 0;
		DirectedNavNode node = this;
		while(node.parent != null){
			if(node.getDirection() != ((DirectedNavNode)node.parent).getDirection()){
				numTurns++;
			}
			node = (DirectedNavNode)node.parent;
		}
		return numTurns;
	}
	public Direction getDirection(){
		return direction;
	}
	@Override
	public void setParent(Node node)
	{
		super.setParent(node);
		if(this.parent != null){
			int xDiff = this.pos.x - ((DirectedNavNode)this.parent).pos.x;
			int yDiff = this.pos.y - ((DirectedNavNode)this.parent).pos.y;
			
			if(xDiff > 0){
				direction = Direction.UP;
			}else if(xDiff < 0){
				direction = Direction.DOWN;
			}else if(yDiff > 0){
				direction = Direction.RIGHT;
			}else if(yDiff < 0){
				direction = Direction.LEFT;
			}
		}
	}

}
