package model;

import java.util.*;

public class Person {
	
	public static final double EFFICACY = 0.1;
	public static final int MAX_ILL_DAYS = 4;
	
	//public static final double[][] TRANSMISSION_PROB = {{0.04, 0, 0, 0, 0, 0}, {0, 0.015, 0, 0, 0, 0}, {0, 0, 0.0145, 0, 0, 0}, {0, 0, 0, 0.0125, 0, 0}, {0, 0, 0, 0, 0.0105, 0}, {1, 1, 1, 1, 1, 1}, {0.00004, 0.00004, 0.00012, 0.00012, 0.00012, 0.00016}, {0.00001, 0.00001, 0.0003, 0.0003, 0.0003, 0.0004}, {0, 0, 0, 0, 0, 0.04}};
	public static final double[] DEATH_PROB = {0.0000263, 0.000021, 0.0002942, 0.0002942, 0.01998};
	public static final int SUSCEPTIBLE = 0;
	public static final int INFECTED = 1;
	public static final int RECOVERED = 2;
	public static final int DEAD = 3;
	
	private int pos;
	private int status;
	private int age;
	private int daysInfected = 0;
	private  ArrayList<MixingGroup> groups;
	private MixingGroup community;
	private MixingGroup neighbourhood;
	private boolean vaccinated;
	private boolean firstTime = false;
	
	public Person(int pos, int status, int age, boolean vaccinated, ArrayList<MixingGroup> groups, MixingGroup community, MixingGroup neighbourhood) {
		this.pos = pos;
		this.status = status;
		this.age = age;
		this.vaccinated = vaccinated;
		this.groups = groups;
		this.community = community;
		this.neighbourhood = neighbourhood;
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
	
	public MixingGroup getNeighbourhood() {
		return neighbourhood;
	}
	
	public void update() {
		if (status == SUSCEPTIBLE) {
			double prob = 1;
			for (int i = 0; i < groups.size(); i++) {
				prob = prob*Math.pow(1-groups.get(i).getProb(), groups.get(i).getInfected()-1);
			}
			double rand = Math.random();
			if (vaccinated) {
				if ((1-prob)*EFFICACY > rand) {
					status = INFECTED;
				}
			} else {
				if ((1-prob) > rand) {
					status = INFECTED;
				}
			}
		} else if (status == INFECTED && daysInfected < MAX_ILL_DAYS) {
			daysInfected++;
		} else {
			double rand = Math.random();
			if (DEATH_PROB[age] >= rand) {
				status = DEAD;
			} else {
				status = RECOVERED;
			}
		}
	}
}