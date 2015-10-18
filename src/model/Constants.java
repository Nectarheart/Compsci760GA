package model;

public class Constants {
	
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
	
	public static final int NUMBER_OF_PG = 4;
	
	public static final double EFFICACY = 0.8;
	public static final int MAX_ILL_DAYS = 6;
	public static final int MAX_INCU_DAYS = 2;
	
	//public static final double[] DEATH_PROB = {1, 1, 1, 1, 1};
	//public static final double[] DEATH_PROB = {0, 0, 0, 0, 0};
	public static final double[] DEATH_PROB = {0.0000263, 0.000021, 0.0002942, 0.0002942, 0.01998};
	public static final int SUSCEPTIBLE = 0;
	public static final int INFECTED = 1;
	public static final int RECOVERED = 2;
	public static final int DEAD = 3;
	public static final double ADULT_TO_CHILD = 0.03;
	public static final double CHILD_TO_CHILD = 0.08;
	public static final double ADULT_TO_ADULT = 0.04;
	
}