package model;

public class MathModelApp {
	
	public static void main(String args[]) {
		int quant = 50;
		int total = 0;
		int[] vaccineStrategy = {quant, quant, quant, quant, quant};
		for (int i = 0; i < 500; i++) {
			total += (new MathModel(vaccineStrategy)).run();
		}
		System.out.println(total/500);
	}
}