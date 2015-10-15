package model;

import java.util.*;

public class MixingGroup {
	
	private int type;
	private double prob;
	private int infected;
	private ArrayList<Person> constituents;
	
	public MixingGroup(int type, double prob) {
		this.type = type;
		this.prob = prob;
	}
	
	public int getType() {
		return type;
	}
	
	public double getProb() {
		return prob;
	}
	
	public int getInfected () {
		return infected;
	}
	
	public void assignMember(Person member) {
		constituents.add(member);
	}
	
	public void addInfection () {
		infected++;
	}
	
	public void removeInfection () {
		infected--;
	}
	
}