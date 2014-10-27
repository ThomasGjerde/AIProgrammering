package aiprog.model;

import java.util.ArrayList;

public class NNColRow {
	ArrayList<ArrayList<Boolean>> domain;
	public NNColRow(ArrayList<ArrayList<Boolean>> domain){
		setDomain(domain);
	}
	public NNColRow(){
		domain = new ArrayList<ArrayList<Boolean>>();
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
	public void addToDomain(ArrayList<Boolean> list){
		domain.add(list);
	}
	//vel, for se om dette her funker
	public void deleteFromDomain(ArrayList<Boolean> removeArray){
		boolean check = false;
		for(int i=0; i<domain.size(); i++){
			for(int j=0; j<domain.get(i).size(); j++){
				if(domain.get(i).get(j) != removeArray.get(j)){
					check = true;
					break;
				}
			}
			if(check != true){
				domain.remove(domain.get(i));
			}
		}
	}
	public NNColRow cloneColRow(){
		ArrayList<ArrayList<Boolean>> newDomain = new ArrayList<ArrayList<Boolean>>();
		for(int i = 0; i < domain.size(); i++){
			ArrayList<Boolean> temp = new ArrayList<Boolean>();
			for(int j = 0; j < domain.get(i).size(); j++){
				temp.add(domain.get(i).get(j));
			}
			newDomain.add(temp);
		}
		NNColRow ret = new NNColRow();
		ret.setDomain(newDomain);
		return ret;
	}
}
