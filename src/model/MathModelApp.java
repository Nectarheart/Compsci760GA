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
	
	public static void main(String args[]) {
		
		Person[] pop = new Person[10000];
		Community[] communities = new Community[5];
		for(int i = 0; i < 5; i++) {
			Neighbourhood[] neighbourhoods = new Neighbourhood[4];
			for(int j = 0; j < 4; j++) {
				ArrayList<MixingGroup> playGroups = new ArrayList<MixingGroup>();
				ArrayList<MixingGroup> daycares = new ArrayList<MixingGroup>();
				ArrayList<MixingGroup> families = new ArrayList<MixingGroup>();
				for(int k = 0; k < 20; k++) {
					families.add(new MixingGroup(FAC, 0.2));
					if (k<2) {
						daycares.add(new MixingGroup(DC, 0.2));
					}
					if (k<4) {
						playGroups.add(new MixingGroup(PG, 0.2));
					}
				}
				neighbourhoods[j] = new Neighbourhood(NH, 0.2, playGroups, daycares, families);
			}
			MixingGroup[] elementarySchools = {new MixingGroup(ES, 0.2), new MixingGroup(ES, 0.2)};
			communities[i] = new Community(COM, 0.2, new MixingGroup(HS,0.2), new MixingGroup(MS, 0.2), elementarySchools, neighbourhoods);
		}
		for(int i = 0; i < 10000; i++) {
			int rand = (int)(5*Math.random() + 1);
			pop[i] = new Person(i, Person.SUSCEPTIBLE, rand, false, new ArrayList<MixingGroup>(), communities[i % 5], communities[i % 5].getNeighbourhoods()[i % 4]);
		}
		
		for(int i = 0; i < 10000; i++) {
			System.out.println(pop[i].getStatus());
		}
	}
}