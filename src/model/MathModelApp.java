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
	public static final int FAM = 5;
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
				for(int k = 0; k < 4; k++) {
					if (k<2) {
						daycares.add(new MixingGroup(DC, 0.015));
					}
					playGroups.add(new MixingGroup(PG, 0.04));
				}
				neighbourhoods[j] = new Neighbourhood(NH, 0.00001, playGroups, daycares, families);
			}
			MixingGroup[] elementarySchools = {new MixingGroup(ES, 0.0145), new MixingGroup(ES, 0.0145)};
			communities[i] = new Community(COM, 0.00001, new MixingGroup(HS,0.0105), new MixingGroup(MS, 0.0125), elementarySchools, neighbourhoods);
		}
		
		for(int i = 0; i < POP_SIZE; i++) {
			int rand = (int)(10001*Math.random());
			int randCommun = (int)(5*Math.random());
			if (communities[randCommun].getNumber() == 2000) {
				i--;
				continue;
			}
			int randNeighbour = (int)(4*Math.random());
			if (communities[randCommun].getNeighbourhoods()[randNeighbour].getMembers().size() == 500) {
				i--;
				continue;
			}
			ArrayList<MixingGroup> groups = new ArrayList<MixingGroup>();
			groups.add(communities[randCommun]);
			groups.add(communities[randCommun].getNeighbourhoods()[randNeighbour]);
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
				pop[i] = new Person(i, Person.SUSCEPTIBLE, age, false, groups, communities[randCommun], communities[randCommun].getNeighbourhoods()[randNeighbour]);
			} else {
				pop[i] = new Person(i, Person.INFECTED, age, false, groups, communities[randCommun], communities[randCommun].getNeighbourhoods()[randNeighbour]);
			}
			pop[i].getNeighbourhood().getMembers().add(pop[i]);
			communities[randCommun].setNumber(communities[randCommun].getNumber() + 1);
		}
		
		for(int i = 0; i < 5; i++) {
			for(int j = 0; j < 4; j++) {
				ArrayList<Person> temp = new ArrayList<Person>(communities[i].getNeighbourhoods()[j].getMembers());
				long seed = System.nanoTime();
				Collections.shuffle(temp, new Random(seed));
				int kidCounter = 0;
				for(int k = 0; k < temp.size(); k++) {
					if(temp.get(k).getAge() <= 1) {
						kidCounter++;
					}
				}
				while (temp.size() > 0) {
					int rand = (int)(101*Math.random());
					int hhSize;
					if (rand <= 33) {
						hhSize = 1;
					} else if (33 < rand && rand <= 67) {
						hhSize = 2;
					} else if (67 < rand && rand <= 80) {
						hhSize = 3;
					} else if (80 < rand && rand <= 90) {
						hhSize = 4;
					} else if (90 < rand && rand <= 97){
						hhSize = 5;
					} else if (97 < rand && rand <= 99) {
						hhSize = 6;
					} else {
						hhSize = 7;
					}
					if (hhSize > temp.size()) {
						continue;
					}
					Person first = null;
					if (kidCounter != temp.size()) {
						first = temp.remove(0);
						while (first.getAge() < 2) {						
							temp.add(first);
							first = temp.remove(0);
						}
						MixingGroup hh = new MixingGroup(FAM, 0.0);
						communities[i].getNeighbourhoods()[j].getFamilies().add(hh);
						first.getGroups().add(hh);
						hh.assignMember(first);
						int adultNumber = 1;
						for (int k = 0; k < hhSize - 1 && temp.size() > 0; k++) {
							first = temp.remove(0);
							if (first.getAge() > 1) {
								adultNumber++;
							} else {
								kidCounter--;
							}
							if (adultNumber > 2 && kidCounter > 0) {
								temp.add(first);
								k--;
								adultNumber--;
							} else {
								first.getGroups().add(hh);
								hh.assignMember(first);
							}
						}
					} else {
						while(temp.size() > 0) {
							first = temp.remove(0);
							int randHH = (int)(communities[i].getNeighbourhoods()[j].getFamilies().size()*Math.random());
							if (communities[i].getNeighbourhoods()[j].getFamilies().get(randHH).getMembers().size() < 7) {
								communities[i].getNeighbourhoods()[j].getFamilies().get(randHH).getMembers().add(first);
								first.getGroups().add(communities[i].getNeighbourhoods()[j].getFamilies().get(randHH));
							}
						}
					}
				}
			}	
		}
		Person[] temp = new Person[pop.length];
		System.arraycopy(pop, 0, temp, 0, pop.length );
		for(int i = 0; i < pop.length; i++) {
			System.out.println(i + " " + pop[i].getStatus());
			temp[i].update();
			int tempStat = temp[i].getStatus();
			temp[i].setStatus(pop[i].getStatus());
			pop[i].setStatus(tempStat);
			System.out.println(i + " " + pop[i].getStatus());
		}
	}
}