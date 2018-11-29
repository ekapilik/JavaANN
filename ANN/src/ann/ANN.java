/*
COMP3190 - Introduction to Artificial Intelligence
@purpose: Assignment 4
@author: Eric Kapilik

 */
package ann;
import java.io.IOException;
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

	static double learningRate = 1.0;
	static int maxIterations = 1000;
	static int hidden = 3;
	/**
	 * Driver for ANN (running, training)
	 */
	public static void main(String[] args) {
		init();
		
		Scanner reader = new Scanner(System.in); //read from System.in
		while(true){
			System.out.println("\n========================================");
			System.out.println("Current ANN");
			System.out.println(network);

			System.out.println("----------------------------------------");
			System.out.println("Would you like to:"
				+ "\n\t[1] Get Predictions from ANN"
				+ "\n\t[2] Train ANN"
				+ "\n\t[3] Reset ANN's weights and biases"
				+ "\n\t[4] Tune ANN"
				+ "\n\t[5] Quit");
			System.out.println("========================================");

			int opt = reader.nextInt(); //scan for next int

			switch(opt){
				case 1:
					System.out.println("Getting predictions from model...");
					network.getResults(data);
					break;
				case 2:
					System.out.println("Training the model...");
					double start = System.nanoTime();
					network.train(data);
					double duration = System.nanoTime() - start;

					System.out.println("Training took: [" + duration / 1000000000.0 + "] s.");
					break;
				case 3:
					System.out.println("Destroying previous model and starting fresh...");
					init();
					break;
				case 4:
					System.out.println("Enter learning rate as decimal: ");
					learningRate = reader.nextDouble();
					System.out.println("Enter maximum training iterations");
					maxIterations = reader.nextInt();
					System.out.println("Enter number of hidden perceptrons");
					hidden = reader.nextInt();
					init();
					break;
				case 5:
					System.exit(1);
			}
			System.out.println("\nPress [enter] to continue...");
			try {
				System.in.read();
			} catch (IOException ex) {
				Logger.getLogger(ANN.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
	}

	private static void init() {
		//simple logical AND truth table
		network = new Network(learningRate, maxIterations, new int[] {2,hidden,1});

		data = new Data();
		data.add(new Row(new double[] {0.0,0.0}, new double[] {0.0}));
		data.add(new Row(new double[] {0.0,1.0}, new double[] {1.0}));
		data.add(new Row(new double[] {1.0,0.0}, new double[] {1.0}));
		data.add(new Row(new double[] {1.0,1.0}, new double[] {0.0}));
	}
}
