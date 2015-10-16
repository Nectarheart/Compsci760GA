package model;

import java.util.*;

public class MathModelApp {
	
	//Small playgroup
	public static final int PG = 0;
	//Large day-care center
	public static final int DC = 1;
	//Elementary school
	public static final int ES = 2;
	//Middle school
	public static final int MS = 3;
	//High school
	public static final int HS = 4;
	//Family child to child
	public static final int FCC = 5;
	//Family adult to child
	public static final int FAC = 6;
	//Family adult to adult
	public static final int FAA = 7;
	//Neighbourhood
	public static final int NH = 8;
	//Community
	public static final int COM = 9;
	//Work group
	public static final int WRKGRP = 10;
	
	public static final int POP_SIZE = 10000;
	
	public static void main(String args[]) {
		
		Person[] pop = new Person[POP_SIZE];
		Community[] communities = new Community[5];
		for(int i = 0; i < 5; i++) {
			Neighbourhood[] neighbourhoods = new Neighbourhood[4];
			for(int j = 0; j < 4; j++) {
				ArrayList<MixingGroup> playGroups = new ArrayList<MixingGroup>();
				ArrayList<MixingGroup> daycares = new ArrayList<MixingGroup>();
				ArrayList<MixingGroup> families = new ArrayList<MixingGroup>();
				for(int k = 0; k < 20; k++) {
					families.add(new MixingGroup(FAC, 0.9));
					if (k<2) {
						daycares.add(new MixingGroup(DC, 0.9));
					}
					if (k<4) {
						playGroups.add(new MixingGroup(PG, 0.9));
					}
				}
				neighbourhoods[j] = new Neighbourhood(NH, 0.9, playGroups, daycares, families);
			}
			MixingGroup[] elementarySchools = {new MixingGroup(ES, 0.9), new MixingGroup(ES, 0.9)};
			communities[i] = new Community(COM, 0.9, new MixingGroup(HS,0.9), new MixingGroup(MS, 0.9), elementarySchools, neighbourhoods);
		}
		
		for(int i = 0; i < POP_SIZE; i++) {
			int rand = (int)(10001*Math.random());
			int randCommun = (int)(5*Math.random());
			if (communities[randCommun].getNumber() == 2000) {
				i--;
				continue;
			}
			int randNeighbour = (int)(4*Math.random());
			if (communities[randCommun].getNeighbourhoods()[randNeighbour].getNumber() == 500) {
				i--;
				continue;
			}
			ArrayList<MixingGroup> groups = new ArrayList<MixingGroup>();
			groups.add(communities[randCommun]);
			groups.add(communities[randCommun].getNeighbourhoods()[i%4]);
			int age;
			if (rand <= 680) {
				age = 0;
			} else if (680 < rand && rand <= 2720) {
				age = 1;
			} else if (2720 < rand && rand <= 7344) {
				age = 2;
			} else if (7344 < rand && rand <= 8753) {
				age = 3;
			} else {
				age = 4;
			}
			if (i % 3 != 0) {
				pop[i] = new Person(i, Person.SUSCEPTIBLE, age, false, groups, communities[randCommun], communities[randCommun].getNeighbourhoods()[i % 4]);
			} else {
				pop[i] = new Person(i, Person.INFECTED, age, false, groups, communities[randCommun], communities[randCommun].getNeighbourhoods()[i % 4]);
				pop[i].getCommunity().addInfection();
				pop[i].getNeighbourhood().addInfection();
			}
			communities[randCommun].setNumber(communities[randCommun].getNumber() + 1);
			communities[randCommun].getNeighbourhoods()[randNeighbour].setNumber(communities[randCommun].getNumber() + 1);
		}
		
		int counter = 0;
		for(int i = 0; i < POP_SIZE; i++) {
			System.out.println(i + ": " + pop[i].getStatus());
			pop[i].update();
			if (pop[i].getAge() == 3) {
				counter += 1;
			}
			System.out.println(i + ": " + pop[i].getStatus());
		}
		System.out.println(counter);
	}
}