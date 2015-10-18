package model;

import java.util.*;

public class MixingGroup {
	
	protected int type;
	protected double prob;
	protected int numberOfInfectedChildren = 0;
	protected int numberOfInfectedAdults = 0;
	protected int numberOfChildren = 0;
	protected int numberOfAdults = 0;
	protected int sizeLeft = 0;
	protected ArrayList<Person> members;
	
	public MixingGroup(int type, double prob) {
		this.type = type;
		this.prob = prob;
		members = new ArrayList<Person>();
		if (type == Constants.PG) {
			sizeLeft = 5;
		} else if (type == Constants.DC) {
			sizeLeft = 14;
		} else if (type == Constants.WRKGRP) {
			sizeLeft = 25;
		}
	}
	
	public int getType() {
		return type;
	}
	
	public double getProb() {
		return prob;
	}

	public void assignMember(Person member) {
		if (member.getAge() < 2) {
			numberOfChildren++;
			if (member.getStatus() == Constants.INFECTED) {
				numberOfInfectedChildren++;
			}
		} else {
			numberOfAdults++;
			if (member.getStatus() == Constants.INFECTED) {
				numberOfInfectedAdults++;
			}
		}
		members.add(member);
	}
	
	public void removeMember(Person member) {
		if (member.getAge() < 2) {
			numberOfChildren--;
		} else {
			numberOfAdults--;
		}
		members.remove(member);
	}
	
	public void incrementInfected(Person member) {
		if (member.getAge() < 2) {
			numberOfInfectedChildren++;
		} else {
			numberOfInfectedAdults++;
		}
	}
	
	public void decrementInfected(Person member) {
		if (member.getAge() < 2) {
			numberOfInfectedChildren--;
		} else {
			numberOfInfectedAdults--;
		}
	}
	
	public ArrayList<Person> getMembers() {
		return members;
	}
	
	public int getInfected() {
		return numberOfInfectedChildren + numberOfInfectedAdults;
	}
	
	public int getNumberOfChildren() {
		return numberOfChildren;
	}
	
	public int getNumberOfAdults() {
		return numberOfAdults;
	}
	
	public int getInfectedChildren() {
		return numberOfInfectedChildren;
	}
	
	public int getInfectedAdults() {
		return numberOfInfectedAdults;
	}
	
	public void setSizeLeft(int size) {
		sizeLeft = size;
	}
	
	public int getSizeLeft() {
		return sizeLeft;
	}
	
	public String toString() {
		return "Type: " + type + " Number of members: " + members.size() + " Number of adults: " + numberOfAdults + " Number of children: " + numberOfChildren + " Number of infected: " + (numberOfInfectedChildren + numberOfInfectedAdults) + " Number of infected children: " + numberOfInfectedChildren + " Number of infected adults: " + numberOfInfectedAdults;
	}
	
}