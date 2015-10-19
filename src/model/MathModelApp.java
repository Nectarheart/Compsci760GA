package model;

public class MathModelApp {
	
	public static void main(String args[]) {
		int[] total;
		int[] totalInfectedArray = new int[Constants.GA_POP_SIZE];
		GA ga = new GA(4000);
		MathModel model = new MathModel();
		for (int k=0; k < 10; k++) {	
			for(int j = 0; j < Constants.GA_POP_SIZE; j++) {
				for (int m = 0; m < 1; m++) {
					total = model.run(ga.getValues(j));
					for (int i = 0; i < 5; i++) {
						totalInfectedArray[j] += total[i]/1;
					}	
					/*for (int i = 0; i < 5; i++) {
					System.out.print(total[i] + " ");
					}*/
				}
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
	}
}