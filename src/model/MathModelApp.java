package model;

import java.util.*;

public class MathModelApp {
	
	public static void main(String args[]) {
		
		int[] vaccineStrategy = {0, 0, 0, 0, 0};
		new Thread(new MathModel(vaccineStrategy)).start();
		
	}
}