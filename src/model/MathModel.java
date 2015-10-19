package model;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class MathModel {
	
	private Person[] pop; 
	private Community[] communities;
	//private double[] vaccineStrategy;
	private int[] infectedTotal;
	private boolean runOnce = false;
	
	public MathModel() {
		
	}
	
	public int[] run(double[] vaccineStrategy) {
		//this.vaccineStrategy = vaccineStrategy;
		infectedTotal = new int[5];
		initialise(vaccineStrategy);
		int infectedCounter = 0;
		int deadCounter = 0;
		int recoveredCounter = 0;
		int[] temp = new int[Constants.POP_SIZE];
		for (int j = 0; j < 140; j++) {
			for(int i = 0; i < pop.length; i++) {
				temp[i] = pop[i].infect();
				if (temp[i] == Constants.INFECTED && pop[i].getStatus() == Constants.SUSCEPTIBLE) {
					infectedCounter++;
					infectedTotal[pop[i].getAge()]++;
					
				} else if (temp[i] == Constants.RECOVERED && pop[i].getStatus() == Constants.INFECTED) {
					recoveredCounter++;
				}
			}
			for (int i = 0; i < pop.length; i++) {
				if (temp[i] == Constants.DEAD && pop[i].getStatus() == Constants.INFECTED) {
					deadCounter++;
				}
				pop[i].update(temp[i]);
			}
		}
		/*System.out.println(infectedCounter);
		System.out.println(recoveredCounter);
		System.out.println(deadCounter);*/
		return infectedTotal;
	}
	
	private void initialise(double[] vaccineStrategy) {
		
		if (!runOnce) {
			runOnce = true;
			pop = new Person[Constants.POP_SIZE];
			communities = new Community[5];
			MixingGroup currentWP = new MixingGroup(Constants.WRKGRP, Constants.WRKGRP_PROB);
	
			for(int i = 0; i < 5; i++) {
				Neighbourhood[] neighbourhoods = new Neighbourhood[4];
				for(int j = 0; j < 4; j++) {
					ArrayList<MixingGroup> playGroups = new ArrayList<MixingGroup>();
					ArrayList<MixingGroup> daycares = new ArrayList<MixingGroup>();
					ArrayList<MixingGroup> families = new ArrayList<MixingGroup>();
					for(int k = 0; k < Constants.NUMBER_OF_PG; k++) {
						//Messing around
						playGroups.add(new MixingGroup(Constants.PG, Constants.PG_PROB));
					}
					neighbourhoods[j] = new Neighbourhood(Constants.NH, Constants.NH_PROB, playGroups, daycares, families);
				}
				MixingGroup[] elementarySchools = {new MixingGroup(Constants.ES, Constants.ES_PROB), new MixingGroup(Constants.ES, Constants.ES_PROB)};
				communities[i] = new Community(Constants.COM, Constants.COM_PROB, new MixingGroup(Constants.HS, Constants.HS_PROB), new MixingGroup(Constants.MS, Constants.MS_PROB), elementarySchools, neighbourhoods);
			}
			
			for(int i = 0; i < Constants.POP_SIZE; i++) {
				int randCommun = (int)(5*Math.random());
				if (communities[randCommun].getMembers().size() == 2000) {
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
				if ((int)(2001*Math.random()) >= Constants.INITIAL_INFECT) {
					pop[i] = new Person(i, Constants.SUSCEPTIBLE, 0, false, groups, communities[randCommun], communities[randCommun].getNeighbourhoods()[randNeighbour]);
				} else {
					pop[i] = new Person(i, Constants.INFECTED, 0, false, groups, communities[randCommun], communities[randCommun].getNeighbourhoods()[randNeighbour]);
				}
				pop[i].getNeighbourhood().assignMember(pop[i]);
				pop[i].getCommunity().assignMember(pop[i]);
			}
			
			shuffleArray(pop);
			for (int i = 0; i < pop.length; i++) {
				int age;
				if (i <= 680) {
					age = 0;
				} else if (680 < i && i <= 2720) {
					age = 1;
				} else if (2720 < i && i <= 7344) {
					age = 2;
				} else if (7344 < i && i <= 8753) {
					age = 3;
				} else {
					age = 4;
				}
				boolean toVacc = false;
				pop[i].setAge(age);
				if (pop[i].getStatus() == Constants.INFECTED) {
					infectedTotal[age]++;
				}
				if (age == 0 && i <= 680*vaccineStrategy[age]) {
					toVacc = true;
				} else if (age == 1 && i <= (2040*vaccineStrategy[age] + 680)) {
					toVacc = true;
				} else if (age == 2 && i <= (4624*vaccineStrategy[age] + 2720)) {
					toVacc = true;
				} else if (age == 3 && i <= (1409*vaccineStrategy[age] + 7344)) {
					toVacc = true;
				} else if (age == 4 && i <= (1247*vaccineStrategy[age] + 8753)) {
					toVacc = true;
				}
				pop[i].setVaccine(toVacc);
			}
			
			shuffleArray(pop);
			
			for (int i = 0; i < pop.length; i++) {
				if (pop[i].getAge() > 1 && pop[i].getAge() < 4) {
					if (currentWP.getMembers().size() < 25) {
						pop[i].getGroups().add(currentWP);
						currentWP.assignMember(pop[i]);
					} else {
						currentWP = new MixingGroup(Constants.WRKGRP, Constants.ADULT_TO_ADULT);
						pop[i].getGroups().add(currentWP);
						currentWP.assignMember(pop[i]);
					}
				}
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
							MixingGroup hh = new MixingGroup(Constants.FAM, Constants.FAM_PROB);
							communities[i].getNeighbourhoods()[j].getFamilies().add(hh);
							if (first.getAge() < 4 || 10*Math.random() < 1) {
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
										if (first.getAge() == 0) {
											MixingGroup candidatePG = communities[i].getNeighbourhoods()[j].getEmptyPG();
											if (candidatePG != null) {
												first.getGroups().add(candidatePG);
												candidatePG.assignMember(first);
											} else {
												MixingGroup candidateDC = communities[i].getNeighbourhoods()[j].getEmptyDC();
												first.getGroups().add(candidateDC);
												candidateDC.assignMember(first);
											}
										} else if (first.getAge() == 1) {
											int school = (int)(101*Math.random());
											if (0 <= school && school <= 36) {
												if ((int)(2*Math.random()) == 0) {
													first.getGroups().add(communities[i].getElementaryschools()[0]);
													communities[i].getElementaryschools()[0].assignMember(first);
												} else {
													first.getGroups().add(communities[i].getElementaryschools()[1]);
													communities[i].getElementaryschools()[1].assignMember(first);
												}
											} else if (36 < school && school <= 65) {
												first.getGroups().add(communities[i].getMiddleschool());
												communities[i].getMiddleschool().assignMember(first);
											} else {
												first.getGroups().add(communities[i].getHighschool());
												communities[i].getHighschool().assignMember(first);
											}
										}
									}
								}
							}
						} else {
							while(temp.size() > 0) {
								first = temp.remove(0);
								int randHH = (int)(communities[i].getNeighbourhoods()[j].getFamilies().size()*Math.random());
								if (communities[i].getNeighbourhoods()[j].getFamilies().get(randHH).getMembers().size() < 7) {
									communities[i].getNeighbourhoods()[j].getFamilies().get(randHH).assignMember(first);
									first.getGroups().add(communities[i].getNeighbourhoods()[j].getFamilies().get(randHH));
									if (first.getAge() == 0) {
										MixingGroup candidatePG = communities[i].getNeighbourhoods()[j].getEmptyPG();
										if (candidatePG != null) {
											first.getGroups().add(candidatePG);
											candidatePG.assignMember(first);
										} else {
											MixingGroup candidateDC = communities[i].getNeighbourhoods()[j].getEmptyDC();
											first.getGroups().add(candidateDC);
											candidateDC.assignMember(first);
										}
									} else if (first.getAge() == 1) {
										int school = (int)(101*Math.random());
										if (0 <= school && school <= 36) {
											if ((int)(2*Math.random()) == 0) {
												first.getGroups().add(communities[i].getElementaryschools()[0]);
												communities[i].getElementaryschools()[0].assignMember(first);
											} else {
												first.getGroups().add(communities[i].getElementaryschools()[1]);
												communities[i].getElementaryschools()[1].assignMember(first);
											}
										} else if (36 < school && school <= 65) {
											first.getGroups().add(communities[i].getMiddleschool());
											communities[i].getMiddleschool().assignMember(first);
										} else {
											first.getGroups().add(communities[i].getHighschool());
											communities[i].getHighschool().assignMember(first);
										}
									}
								}
							}
						}
					}
				}	
			}
		} else {
			int age;
			boolean toVacc;
			shuffleArray(pop);
			int[] counters = new int[5];
			for (int i = 0; i < Constants.POP_SIZE; i++) {
				pop[i].reset();
				if ((int)(2001*Math.random()) >= Constants.INITIAL_INFECT) {
					pop[i].setStatus(Constants.SUSCEPTIBLE);
				} else {
					pop[i].setStatus(Constants.INFECTED);
					infectedTotal[pop[i].getAge()]++;
				}
				age = pop[i].getAge();
				toVacc = false;
				if (age == 0 && counters[age] <= 680*vaccineStrategy[age]) {
					toVacc = true;
				} else if (age == 1 && counters[age] <= 2040*vaccineStrategy[age]) {
					toVacc = true;
					counters[age]++;
				} else if (age == 2 && counters[age] <= 4624*vaccineStrategy[age]) {
					toVacc = true;
					counters[age]++;
				} else if (age == 3 && counters[age] <= 1409*vaccineStrategy[age]) {
					toVacc = true;
					counters[age]++;
				} else if (age == 4 && counters[age] <= 1247*vaccineStrategy[age]) {
					toVacc = true;
					counters[age]++;
				}
				pop[i].setVaccine(toVacc);
			}
		}
	}
	
	private void shuffleArray(Person[] ar) {
	    Random rnd = ThreadLocalRandom.current();
	    for (int i = ar.length - 1; i > 0; i--) {
	    	int index = rnd.nextInt(i + 1);
	    	// Simple swap
	    	Person a = ar[index];
	    	ar[index] = ar[i];
	    	ar[i] = a;
	    }
	}
	
}