package aiprog.model;

import java.util.ArrayList;

public class NNColRow {
	ArrayList<ArrayList<Boolean>> domain;
	ArrayList<Boolean> value;
	public boolean catogory;
	ArrayList<Integer> constraints = new ArrayList<Integer>();
	public NNColRow(ArrayList<ArrayList<Boolean>> domain, ArrayList<Integer> constraints){
		setDomain(domain);
		this.constraints = new ArrayList<Integer>(constraints);
		value = new ArrayList<Boolean>();
	}
	public NNColRow(ArrayList<Integer> constraints){
		this.constraints = new ArrayList<Integer>(constraints);
		domain = new ArrayList<ArrayList<Boolean>>();
	}
	public ArrayList<Boolean> getValue(){
		return value;
	}
	
	public void setValue(ArrayList<Boolean> newValue){
		value = newValue;
		this.domain.clear();
		this.domain.add(value);
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

		NNColRow ret = new NNColRow(this.constraints);
		if(value != null){
			ArrayList<Boolean> newValue = new ArrayList<Boolean>();
			for(int i = 0; i < value.size(); i++){
				newValue.add(value.get(i));
			}
			ret.setValue(newValue);
		}
		ret.setDomain(newDomain);
		
		return ret;
	}
	public boolean validateConstraint(){
		if(this.getValue() != null){
			ArrayList<Integer> checkArray = new ArrayList<Integer>();
			int counter = 0;
			for(int i = 0; i < getValue().size(); i++){
				if(getValue().get(i) == false){
					if(counter > 0){
						checkArray.add(counter);
					}
					counter = 0;
				}else{
					counter++;
				}

			}
			if(counter > 0){
				checkArray.add(counter);
			}
			System.out.println("----------------");
			for(int i = 0; i < constraints.size(); i++){
				System.out.print(constraints.get(i) + ",");
			}
			System.out.println("");
			for(int i = 0; i < checkArray.size(); i++){
				System.out.print(checkArray.get(i) + ",");
			}
			System.out.println("");
			System.out.println("----------------");
			if(constraints.size() == checkArray.size()){
				for(int i = 0; i < constraints.size(); i++){
					if(constraints.get(i) != checkArray.get(i)){
						return false;
					}
				}
				return true;
			}else{
				return false;
			}
		}else{
			return true;
		}
	}
}
