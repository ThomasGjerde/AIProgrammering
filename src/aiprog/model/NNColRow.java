package aiprog.model;

import java.util.ArrayList;

public class NNColRow {
	ArrayList<ArrayList<Boolean>> domain;
	public NNColRow(ArrayList<ArrayList<Boolean>> domain){
		setDomain(domain);
	}
	public ArrayList<ArrayList<Boolean>> getDomain() {
		return domain;
	}
	public void setDomain(ArrayList<ArrayList<Boolean>> domain) {
		this.domain = new ArrayList<ArrayList<Boolean>>();
		for(int i = 0; i < domain.size(); i++){
			this.domain.add((ArrayList<Boolean>) domain.get(i).clone()); //Might not work
		}
	}
}
