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
				network.train(data);
				break;
		}
	}

	private static void init() {
		//simple logical AND truth table
		network = new Network(0.07, 1000, new int[] {2,3,1});
		System.out.println("Initial Structure of ANN");
		System.out.println(network + "\n");

		data = new Data();
		data.add(new Row(new double[] {0.0,0.0}, new double[] {0.0}));
		data.add(new Row(new double[] {0.0,1.0}, new double[] {0.0}));
		data.add(new Row(new double[] {1.0,0.0}, new double[] {0.0}));
		data.add(new Row(new double[] {1.0,1.0}, new double[] {1.0}));
	}
}
