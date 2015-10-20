package model;

import java.util.*;

public class GA {
	
	private double[][] values = new double[Constants.GA_POP_SIZE][5];
	private double[][] nextGen = new double[Constants.GA_POP_SIZE][5];
	private int[] outcomes;
	int total;
	
	public GA(int total) {
		this.total = total;
		int place = 0;
		/*values[0][0] = 1.0;
		values[0][1] = 1.0;
		values[0][2] = 1.0;
		values[0][3] = 0.0;
		values[0][4] = 0.0;*/
		for(int i = 0; i < values.length; i++) {
			for(int j = 0; j < 5; j++) {
				values[i][j] = Math.random();
			}
			while (sum(values[i]) > total) {
				place = (int)(5*Math.random());
				if (values[i][place] > 0.001) {
					values[i][place] -= 0.001;
				} else if (2*Math.random() < 1) {
					values[i][place] = 0;	
				}
			}
			while (sum(values[i]) < total - 1) {
				place = (int)(5*Math.random());
				if (values[i][place] < 0.999) {
					values[i][place] += 0.001;
				} else if (100*Math.random() < 1) {
					values[i][place] = 1.0;	
				}
			}
			/*for(int j = 0; j < 5; j++) {
				System.out.print(values[i][j] + " ");
			}
			System.out.println(sum(values[i]));*/
		}
	}
	
	public int sum(double[] toSum) {
		int sum = 0;
		for(int i = 0; i < toSum.length; i++) {
			sum += toSum[i]*Constants.AGE_GROUP_SIZE[i];
		}
		return sum;
	}
	
	public double[] getValues(int pos) {
		return values[pos];
	}
	
	public void setOutcomes(int[] outcomes) {
		this.outcomes = outcomes;
	}
	
	public double[] tournament(ArrayList<Integer> alreadyChosen) {
		double[][] current = new double[10][5];
		int[] currentOutcomes = new int[10];
		int rand;
		for (int i = 0; i < 10; i++) {
			rand = (int)(Constants.GA_POP_SIZE*Math.random());
			if (alreadyChosen.contains(rand)) {
				i--;
				continue;
			}
			current[i] = values[rand];
			currentOutcomes[i] = outcomes[rand];
		}
		int temp = currentOutcomes[0];
		int pos = 0;
		for (int i = 1; i < 10; i++) {
			if (currentOutcomes[i] < temp) {
				temp = currentOutcomes[i];
				pos = i;
			}
		}
		return current[pos];
	}
	
	public double[] crossover(double[] first, double[] second) {
		double[] result =  new double[5];
		for (int i = 0; i < 5; i++) {
			result[i] = Constants.CROSSOVER*first[i] + (1-Constants.CROSSOVER)*second[i];
		}
		return result;
	}
	
	public void repair(double[] toRepair) {
		int rand = (int)(5*Math.random());
		while (sum(toRepair) > total) {
			if (toRepair[rand] < 0.001) {
				toRepair[rand] = 0;
				rand = (int)(5*Math.random());
				continue;
			}
			toRepair[rand] -= 0.001;
		}
		while (sum(toRepair) < total - 5) {
			if (toRepair[rand] > 1) {
				toRepair[rand] = 1;
				rand = (int)(5*Math.random());
				continue;
			}
			toRepair[rand] += 0.001;
		}
	}
	
	public void mutation(double[] toMute) {
		int rand = (int)(5*Math.random());
		toMute[rand] = Math.random();
		repair(toMute);
	}
	
	public void getNextGeneration() {
		nextGen = new double[Constants.GA_POP_SIZE][5];
		ArrayList<Integer> alreadyChosen = new ArrayList<Integer>();
		int top = GA.findMin(outcomes)[0];
		nextGen[0] = values[top];
		alreadyChosen.add(top);
		double[][] temp = new double[25][5];
		for (int i = 0; i < 25; i++) {
			temp[i] = tournament(alreadyChosen);
		}
		for (int i = 0; i < Constants.GA_POP_SIZE - 1; i++) {
			nextGen[i+1] = crossover(temp[(int)(25*Math.random())], temp[(int)(25*Math.random())]);
			if (Math.random() < 0.2) {
				mutation(nextGen[i+1]);
			}
		}
		values = nextGen;
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