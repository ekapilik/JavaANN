/*
COMP3190 - Introduction to Artificial Intelligence
@purpose: Assignment 4
@author: Eric Kapilik

 */
package ann;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Eric Kapilik
 */
public class ANN {
	static Data data; 
	static Network network;
	/**
	 * Driver for ANN (running, training)
	 */
	public static void main(String[] args) {
		init();

		
		Scanner reader = new Scanner(System.in); //read from System.in
		while(true){
			System.out.println("\n\nWould you like to:"
				+ "\n[1] Get Predictions from ANN"
				+ "\n[2] Train ANN"
				+ "\n[3] Reset ANN"
				+ "\n[4] Tune ANN");

			int opt = reader.nextInt(); //scan for next int

			switch(opt){
				case 1:
					System.out.println("Getting predictions from model...");
					network.getResults(data);
					break;
				case 2:
					System.out.println("Training the model...");
					network.train(data);
					break;
				case 3:
					System.out.println("Destroying previous model and starting fresh...");
					init();
					break;
				case 4:
					System.out.println("Enter learning rate as decimal: ");
					double newLearningRate = reader.nextDouble();
					System.out.println("Enter maximum training iterations");
					int newMaxIterations = reader.nextInt();
					System.out.println("Enter number of hidden perceptrons");
					int newHidden = reader.nextInt();

					network = new Network(newLearningRate, newMaxIterations, new int[] {2, newHidden, 1});
			}
		}
	}

	private static void init() {
		//simple logical AND truth table
		network = new Network(1.00, 1000, new int[] {2,3,1});
		System.out.println("Initial Structure of ANN");
		System.out.println(network + "\n");

		data = new Data();
		data.add(new Row(new double[] {0.0,0.0}, new double[] {0.0}));
		data.add(new Row(new double[] {0.0,1.0}, new double[] {1.0}));
		data.add(new Row(new double[] {1.0,0.0}, new double[] {1.0}));
		data.add(new Row(new double[] {1.0,1.0}, new double[] {0.0}));
	}
}
