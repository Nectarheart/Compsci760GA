package model;

public class GA {
	
	private int[][] values = new int[Constants.GA_POP_SIZE][5];
	
	public GA(int total) {
		int place = 0;
		for(int i = 0; i < values.length; i++) {
			for(int j = 0; j < 5; j++) {
				values[i][j] = (int)((total+1)*Math.random());
			}
			while (sum(values[i]) > total) {
				place = (int)(5*Math.random());
				if (values[i][place] > 30) {
					values[i][place] -= 30;
				} else if (100*Math.random() < 1) {
					values[i][place] = 0;	
				}
			}
			/*for(int j = 0; j < 5; j++) {
				System.out.print(values[i][j] + " ");
			}
			System.out.println();*/
		}
	}
	
	public int sum(int[] toSum) {
		int sum = 0;
		for(int i = 0; i < toSum.length; i++) {
			sum += toSum[i];
		}
		return sum;
	}
	
	public int[] getValues(int pos) {
		return values[pos];
	}
	
	public static int[] findMin(int[] list) {
		int[] currentMin = {0, list[0]};
		for (int i = 1; i < list.length; i++) {
			if (list[i] < currentMin[1]) {
				currentMin[0] = i;
				currentMin[1] = list[i];
			}
		}
		return currentMin;
	}
	
	
}