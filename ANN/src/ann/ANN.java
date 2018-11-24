/*
COMP3190 - Introduction to Artificial Intelligence
@purpose: Assignment 4
@author: Eric Kapilik

 */
package ann;
import java.util.Scanner;

/**
 *
 * @author Eric Kapilik
 */
public class ANN {
	static int[][][] trainingData;
	static Network network;
	/**
	 * Driver for ANN (running, training)
	 */
	public static void main(String[] args) {
		init();

		System.out.println("Would you like to:"
			+ "\n[1] get predictions"
			+ "\n[2] train the ANN");

		Scanner reader = new Scanner(System.in); //read from System.in
		int opt = reader.nextInt(); //scan for next int
		reader.close();

		switch(opt){
			case 1:
				System.out.println("Getting predictions from model...");
				break;
			case 2:
				System.out.println("Training the model...");
				network.train();
				break;
		}
	}

	private static void init() {
		//simple logical AND truth table
		trainingData = new int[][][] {	{{0,0},{0}}, 
						{{0,1},{0}},
						{{1,0},{0}}, 
						{{1,1},{1}} };
		network = new Network(0.07, 1000000, new int[] {2,3,3,1});
		System.out.println(network + "\n");
		double[] test_inputs = new double[] {0.0, 1.0};
		double[] test_output = new double[] {1.0};

		network.forwardPropogation(test_inputs);
		System.out.println(network + "\n");
		network.backPropogation(test_output);
	}
	
}
