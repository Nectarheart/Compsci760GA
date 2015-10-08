package model;

public class Person {
	
	public static final double EFFICACY = 0.1;
	public static final int MAX_ILL_DAYS = 4;
	public static final double[] DEATH_PROB = {0.0000263, 0.000021, 0.0002942, 0.0002942, 0.01998};
	public static final int SUSCEPTIBLE = 0;
	public static final int INFECTED = 0;
	public static final int RECOVERED = 0;
	public static final int DEAD = 0;
	
	private int pos;
	private int status;
	private int age;
	private int daysInfected = 0;
	private boolean vaccinated;
	
	public Person(int pos, int status, int age, boolean vaccinated) {
		this.pos = pos;
		this.status = status;
		this.age = age;
		this.vaccinated = vaccinated;
	}
	
	public void setStatus(int status) {
		this.status = status;
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
	
	public void infect(double[][] transmissionMatrix) {
		if (status == SUSCEPTIBLE) {
			double prob = 1;
			for (int i = 0; i < transmissionMatrix.length; i++) {
				double transmission = transmissionMatrix[pos][i];
				if (transmission != 0) {
					prob *= 1 - transmission;
				}
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
			}
		}
	}
	
}