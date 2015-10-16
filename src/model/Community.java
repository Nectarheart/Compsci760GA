package model;

import java.util.*;

public class Community extends MixingGroup {
	
	private MixingGroup highschool;
	private MixingGroup middleschool;
	private MixingGroup[] elementaryschools;
	private Neighbourhood[] neighbourhoods;
	private int numberOfPeople;
	
	public Community(int type, double prob, MixingGroup highschool, MixingGroup middleschool, MixingGroup[] elementaryschools, Neighbourhood[] neighbourhoods) {
		super(type,prob);
		this.highschool = highschool;
		this.middleschool = middleschool;
		this.elementaryschools = elementaryschools;
		this.neighbourhoods = neighbourhoods;
	}
	
	public MixingGroup getHighschool() {
		return highschool;
	}
	
	public MixingGroup getMiddleschool() {
		return middleschool;
	}
	
	public MixingGroup[] getElementaryschools() {
		return elementaryschools;
	}
	
	public Neighbourhood[] getNeighbourhoods() {
		return neighbourhoods;
	}
	
	public void setNumber(int numberOfPeople) {
		this.numberOfPeople = numberOfPeople;
	}
	
	public int getNumber() {
		return numberOfPeople;
	}
	
}