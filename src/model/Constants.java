package model;

public class Constants {
	
	//Small playgroup
	public static final int PG = 0;
	public static final double PG_PROB = 0.04;
	//Large day-care center
	public static final int DC = 1;
	public static final double DC_PROB = 0.015;
	//Elementary school
	public static final int ES = 2;
	public static final double ES_PROB = 0.0145;
	//Middle school
	public static final int MS = 3;
	public static final double MS_PROB = 0.0125;
	//High school
	public static final int HS = 4;
	public static final double HS_PROB = 0.0105;
	//Family child to child
	public static final int FAM = 5;
	public static final double FAM_PROB = 0;
	//Neighbourhood
	public static final int NH = 8;
	public static final double NH_PROB = 0.00001;
	//Community
	public static final int COM = 9;
	public static final double COM_PROB = 0.00001;
	//Work group
	public static final double WRKGRP_PROB = 0.0105;
	public static final int WRKGRP = 10;
	
	public static final int POP_SIZE = 10000;
	
	public static final int GA_POP_SIZE = 50;
	
	public static final int NUMBER_OF_PG = 4;
	
	public static final int WORKPLACE_SIZE = 25;
	
	public static final double CROSSOVER = 0.9;
	
	public static final int[] AGE_GROUP_SIZE = {680, 2040, 4624, 1409, 1247};
	
	public static final double EFFICACY = 0.9;
	public static final int MAX_ILL_DAYS = 4;
	
	//public static final double[] DEATH_PROB = {1, 1, 1, 1, 1};
	//public static final double[] DEATH_PROB = {0, 0, 0, 0, 0};
	public static final double[] DEATH_PROB = {0.0000263, 0.000021, 0.0002942, 0.0002942, 0.01998};
	public static final int SUSCEPTIBLE = 0;
	public static final int INFECTED = 1;
	public static final int RECOVERED = 2;
	public static final int DEAD = 3;
	//Messing around
	public static final double ADULT_TO_CHILD = 0.03*0;
	public static final double CHILD_TO_CHILD = 0.08;
	public static final double ADULT_TO_ADULT = 0.04*0.1;
	
}