package model;

import java.io.PrintWriter;
import java.util.*;

public class MathModelApp {
	
	public static void main(String args[]) {
		try {
			PrintWriter pw = new PrintWriter(Constants.FILENAME);
			int amountOfVacc = 7000;
			int[] total = new int[5];
			int[] totalInfectedArray = new int[Constants.GA_POP_SIZE];
			MathModel model = new MathModel();
			GA ga = new GA(amountOfVacc);
			for (int k=0; k < 10; k++) {	
				for(int j = 0; j < Constants.GA_POP_SIZE; j++) {
					//for (int m = 0; m < 5; m++) {
						/*for (int o = 0; o < 5; o++) {
							System.out.print(ga.getValues(j)[o] + " ");
						}*/
						total = model.run(ga.getValues(j));
						for (int i = 0; i < 5; i++) {
							totalInfectedArray[j] += total[i]/1;
						}	
					//}
					//System.out.println("\n" + GA.findMin(totalInfectedArray)[0] + " " + GA.findMin(totalInfectedArray)[1]);
					/*for (int e = 0; e < j+1; e++) {
						System.out.print(totalInfectedArray[e] + " ");
					}
					System.out.println();*/
				}
				pw.println(GA.findMin(totalInfectedArray)[1]);
				/*System.out.println("\n" + GA.findMin(totalInfectedArray)[0] + " " + GA.findMin(totalInfectedArray)[1]);
				for (int j = 0; j < 5; j++) {
					System.out.print(ga.getValues(GA.findMin(totalInfectedArray)[0])[j] + " ");
				}
				System.out.println();*/
				ga.setOutcomes(totalInfectedArray);
				ga.getNextGeneration();
				totalInfectedArray = new int[Constants.GA_POP_SIZE];
			}
			for (int j = 0; j < 5; j++) {
				pw.print(ga.getValues(0)[j] + " ");
			}
			for (int i = 0; i < Constants.GA_POP_SIZE; i++) {
				for (int j = 0; j < 5; j++) {
					System.out.print(ga.getValues(i)[j] + " ");
				}
				System.out.println();
			}
			pw.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}