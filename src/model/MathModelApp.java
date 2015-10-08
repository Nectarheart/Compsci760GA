package model;

public class MathModelApp {
	
	public static void main(String args[]) {
		Person[] pop = new Person[10000];
		for(int i = 0; i < 10000; i++) {
			pop[i] = new Person(i, Person.SUSCEPTIBLE, 0, false);
		}
		
		for(int i = 0; i < 10000; i++) {
			System.out.println(pop[i].getStatus());
		}
	}
}