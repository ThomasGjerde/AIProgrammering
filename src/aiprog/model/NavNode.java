package aiprog.model;

import java.util.ArrayList;

import aiprog.model.Node.Status;

public class NavNode extends Node{
	public Point pos = new Point();
	public NavNode(Point position) {
		super();
		this.pos = position;
		// TODO Auto-generated constructor stub
	}
}
