package model;

import java.util.*;

public class MixingGroup {
	
	protected int type;
	protected double prob;
	protected ArrayList<Person> constituents;
	
	public MixingGroup(int type, double prob) {
		this.type = type;
		this.prob = prob;
		constituents = new ArrayList<Person>();
	}
	
	public int getType() {
		return type;
	}
	
	public double getProb() {
		return prob;
	}

	public void assignMember(Person member) {
		constituents.add(member);
	}
	
	public ArrayList<Person> getMembers() {
		return constituents;
	}
	
	public int getInfected() {
		int counter = 0;
		for (int i = 0; i < constituents.size(); i++) {
			if (constituents.get(i).getStatus() == Person.INFECTED) {
				counter++;
			}
		}
		return counter;
	}
	
	public int getNumberOfChildren() {
		int counter = 0;
		for (int i = 0; i < constituents.size(); i++) {
			if (constituents.get(i).getAge() < 2) {
				counter++;
			}
		}
		return counter;
	}
	
	public int getNumberOfAdults() {
		int counter = 0;
		for (int i = 0; i < constituents.size(); i++) {
			if (constituents.get(i).getAge() > 1) {
				counter++;
			}
		}
		return counter;
	}
	
	public int getInfectedChildren() {
		int counter = 0;
		for (int i = 0; i < constituents.size(); i++) {
			if (constituents.get(i).getAge() < 2 && constituents.get(i).getStatus() == Person.INFECTED) {
				counter++;
			}
		}
		return counter;
	}
	
	public int getInfectedAdults() {
		int counter = 0;
		for (int i = 0; i < constituents.size(); i++) {
			if (constituents.get(i).getAge() > 1 && constituents.get(i).getStatus() == Person.INFECTED) {
				counter++;
			}
		}
		return counter;
	}
}