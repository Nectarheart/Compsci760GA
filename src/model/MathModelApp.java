package model;

public class MathModelApp {
	
	public static void main(String args[]) {
		int total = 0;
		int[] totalInfectedArray = new int[Constants.GA_POP_SIZE];
		GA ga = new GA(10000);
		for(int j = 0; j < Constants.GA_POP_SIZE; j++) {
			total = 0;
			//for (int i = 0; i < 5; i++) {
				total += (new MathModel(ga.getValues(j))).run();
			//}
			totalInfectedArray[j] = total;
			//System.out.println(total/5);
		}
		System.out.println(GA.findMin(totalInfectedArray)[0] + " " + GA.findMin(totalInfectedArray)[1]);
	}
}