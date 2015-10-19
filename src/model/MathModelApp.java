package model;

import java.util.*;

public class MathModelApp {
	
	public static void main(String args[]) {
		Scanner scan = new Scanner(System.in);
		int[] total = new int[5];
		int[] totalInfectedArray = new int[Constants.GA_POP_SIZE];
		MathModel model = new MathModel();
		GA ga = new GA(6000);
		for (int k=0; k < 10; k++) {	
			for(int j = 0; j < Constants.GA_POP_SIZE; j++) {
				for (int m = 0; m < 1; m++) {
					/*for (int o = 0; o < 5; o++) {
						System.out.print(ga.getValues(j)[o] + " ");
					}*/
					total = model.run(ga.getValues(j));
					for (int i = 0; i < 5; i++) {
						totalInfectedArray[j] += total[i]/1;
					}	
				}
				//System.out.println("\n" + GA.findMin(totalInfectedArray)[0] + " " + GA.findMin(totalInfectedArray)[1]);
				for (int e = 0; e < j+1; e++) {
					System.out.print(totalInfectedArray[e] + " ");
				}
				System.out.println();
			}
			System.out.println("\n" + GA.findMin(totalInfectedArray)[0] + " " + GA.findMin(totalInfectedArray)[1]);
			for (int j = 0; j < 5; j++) {
				System.out.print(ga.getValues(GA.findMin(totalInfectedArray)[0])[j] + " ");
			}
			System.out.println();
			ga.setOutcomes(totalInfectedArray);
			ga.getNextGeneration();
			totalInfectedArray = new int[Constants.GA_POP_SIZE];
		}
		for (int i = 0; i < Constants.GA_POP_SIZE; i++) {
			for (int j = 0; j < 5; j++) {
				System.out.print(ga.getValues(i)[j] + " ");
			}
			System.out.println();
		}
		/*double[] vaccine = {0.0, 0.0, 1.0, 0.0, 0.0};
		total = model.run(vaccine);
		for (int i = 0; i < 5; i++) {
			totalInfectedArray[0] += total[i]/1;
		}
		System.out.print(totalInfectedArray[0]);
		System.out.println();
		double[] vaccine2 = {0.0, 0.0, 0.0, 0.0, 0.0};
		total = model.run(vaccine2);
		for (int i = 0; i < 5; i++) {
			totalInfectedArray[1] += total[i]/1;
		}
		System.out.print(totalInfectedArray[1]);*/
	}
}