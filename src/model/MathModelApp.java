package model;

public class MathModelApp {
	
	public static void main(String args[]) {
		int quant = 100;
		int[] vaccineStrategy = {quant, quant, quant, quant, quant};
		(new MathModel(vaccineStrategy)).run();
		
	}
}