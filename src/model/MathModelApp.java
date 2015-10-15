package model;

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
		for(int i = 0; i < 5; i++) {
			
		}
		for(int i = 0; i < 10000; i++) {
			pop[i] = new Person(i, Person.SUSCEPTIBLE, 0, false);
		}
		
		for(int i = 0; i < 10000; i++) {
			pop[i] = new Person(i, Person.SUSCEPTIBLE, 0, false);
		}
		
		for(int i = 0; i < 10000; i++) {
			System.out.println(pop[i].getStatus());
		}
	}
}