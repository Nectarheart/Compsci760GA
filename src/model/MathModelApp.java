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
	//Family
	public static final int FAM = 5;
	//Neighbourhood
	public static final int NH = 6;
	//Community
	public static final int COM = 7;
	//Work group
	public static final int WRKGRP = 8;
	
	public static void main(String args[]) {
		
		Person[] pop = new Person[10000];
		for(int i = 0; i < 10000; i++) {
			MixingGroup[] group = new MixingGroup[6];
			pop[i] = new Person(i, Person.SUSCEPTIBLE, 0, group, false);
		}
		for(int i = 0; i < 10000; i++) {
			System.out.println(pop[i].getStatus());
		}
	}
}