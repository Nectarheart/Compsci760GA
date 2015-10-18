package model;

import java.util.*;

public class Person {
	
	private int pos;
	private int status;
	private int age;
	private int daysInfected = -2;
	private  ArrayList<MixingGroup> groups;
	private Community community;
	private Neighbourhood neighbourhood;
	private boolean vaccinated;
	private int vaccineDay;
	
	public Person(int pos, int status, int age, boolean vaccinated, ArrayList<MixingGroup> groups, Community community, Neighbourhood neighbourhood) {
		this.pos = pos;
		this.status = status;
		this.age = age;
		this.vaccinated = vaccinated;
		this.groups = groups;
		this.community = community;
		this.neighbourhood = neighbourhood;
		if (vaccinated) {
			vaccineDay = 1;
		} else {
			vaccineDay = 0;
		}
	}
	
	public void setStatus(int status) {
		this.status = status;
	}
	
	public void setGroups(ArrayList<MixingGroup> groups) {
		this.groups = groups;
	}
	
	public int getPos() {
		return pos;
	}
	
	public int getStatus() {
		return status;
	}
	
	public int getAge() {
		return age;
	}
	
	public ArrayList<MixingGroup> getGroups() {
		return groups;
	}
	
	public MixingGroup getCommunity() {
		return community;
	}
	
	public Neighbourhood getNeighbourhood() {
		return neighbourhood;
	}
	
	public int infect() {
		if (status == Constants.SUSCEPTIBLE) {
			double prob = 1;
			int otherInfected = 0;
			double transmissionProb = 0.0;
			for (int i = 0; i < groups.size(); i++) {
				otherInfected = groups.get(i).getInfected();
				transmissionProb = groups.get(i).getProb();
				if (groups.get(i).getType() == Constants.FAM) {
					if (age < 2) {
						prob = prob*Math.pow(1-Constants.ADULT_TO_CHILD, groups.get(i).getInfectedAdults());
						prob = prob*Math.pow(1-Constants.CHILD_TO_CHILD, groups.get(i).getInfectedChildren());
					} else {
						prob = prob*Math.pow(1-Constants.ADULT_TO_ADULT, groups.get(i).getInfectedAdults());
						prob = prob*Math.pow(1-Constants.ADULT_TO_CHILD, groups.get(i).getInfectedChildren());
					}
				} else if (groups.get(i).getType() == Constants.COM) {
					if (age == 0) {
						prob = prob*Math.pow(1-transmissionProb, otherInfected);
					} else if (age == 1) {
						prob = prob*Math.pow(1-transmissionProb*3, otherInfected);
					} else if (age > 1) {
						prob = prob*Math.pow(1-transmissionProb*4, otherInfected);
					}
				} else if (groups.get(i).getType() == Constants.NH) {
					if (age == 0) {
						prob = prob*Math.pow(1-transmissionProb*4, otherInfected);
					} else if (age == 1) {
						prob = prob*Math.pow(1-transmissionProb*12, otherInfected);
					} else if (age > 1) {
						prob = prob*Math.pow(1-transmissionProb*16, otherInfected);
					}
				} else {
					prob = prob*Math.pow(1-transmissionProb, otherInfected);
				}
			}
			double rand = Math.random();
			if (vaccinated) {
				if ((1-prob)*(1-Constants.EFFICACY) > rand) {
					return Constants.INFECTED;
				}
			} else {
				if ((1-prob) > rand) {
					return Constants.INFECTED;
				}
			}
			return Constants.SUSCEPTIBLE;
		} else if (status == Constants.INFECTED && daysInfected < (Constants.MAX_ILL_DAYS - vaccineDay)) {
			daysInfected++;
			return Constants.INFECTED;
		} else if (status == Constants.INFECTED && daysInfected == (Constants.MAX_ILL_DAYS - vaccineDay)) {
			double rand = Math.random();
			if (Constants.DEATH_PROB[age] >= rand) {
				return Constants.DEAD;
			} else {
				return Constants.RECOVERED;
			}
		} else if (status == Constants.DEAD){
			return Constants.DEAD;
		} else {
			return Constants.RECOVERED;
		}
	}
	
	public void update(int status) {
		this.status = status;
		if (status == Constants.INFECTED && daysInfected == 0) {
			for (int i = 0; i < groups.size(); i++) {
				groups.get(i).incrementInfected(this);
			}
		} else if (status == Constants.RECOVERED && daysInfected == (Constants.MAX_ILL_DAYS - vaccineDay)) {
			for (int i = 0; i < groups.size(); i++) {
				groups.get(i).decrementInfected(this);
			}
			daysInfected++;
		} else if (status == Constants.DEAD && daysInfected == (Constants.MAX_ILL_DAYS - vaccineDay)) {
			for (int i = 0; i < groups.size(); i++) {
				groups.get(i).decrementInfected(this);
				groups.get(i).removeMember(this);
			}
			daysInfected++;
		}
	}
	
	public String toString() {
		String toSend = "";
		for(int i = 0; i < groups.size(); i++) {
			toSend += (groups.get(i).toString()) + "\n";
		}
		return "Age: " + age + " Status: " + status + " " + toSend;
	}
}