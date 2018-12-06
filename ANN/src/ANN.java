/*
COMP3190 - Introduction to Artificial Intelligence
@purpose: Assignment 4
@author: Eric Kapilik

 */
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

	static int dataSet = 1;
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
				+ "\n\t[5] Change training data set"
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
					System.out.println("[1] XOR"
						+ "\n[2] sin(x)"
						+ "\n[3] Normal Distribution");
					dataSet = reader.nextInt(); //scan for next int
					init();
					break;
				case 6:
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
		loadData();
		int numInputs = data.getRow(0).getInputs().length;
		network = new Network(learningRate, maxIterations, new int[] {numInputs,hidden,1});
	}

	private static void loadData(){
		//simple logical XOR truth table
		data = new Data();
		switch(dataSet){
			case 1://XOR
				data.add(new Row(new double[] {0.0,0.0}, new double[] {0.0}));
				data.add(new Row(new double[] {0.0,1.0}, new double[] {1.0}));
				data.add(new Row(new double[] {1.0,0.0}, new double[] {1.0}));
				data.add(new Row(new double[] {1.0,1.0}, new double[] {0.0}));
				break;
			case 2://sin(x)
				double x1 = 0;
				int divisions = 18;
				for(int i = 1; i < 17; i ++){
					data.add(new Row(new double[] {x1}, new double[] {Math.sin(x1)}) );
					x1 += 2*Math.PI/divisions;
				}
				break;
			case 3://normal distribution
				for(double x = -3.0; x < 3.1; x += 0.1){
					data.add(new Row(new double[] {x}, new double[] {normalDist(x)}) );
				}
				break;
			default:
				data.add(new Row(new double[] {0.0,0.0}, new double[] {0.0}));
				data.add(new Row(new double[] {0.0,1.0}, new double[] {1.0}));
				data.add(new Row(new double[] {1.0,0.0}, new double[] {1.0}));
				data.add(new Row(new double[] {1.0,1.0}, new double[] {0.0}));
				break;
		}
	
	}

	private static double normalCoeff = (1.0/Math.sqrt(2.0 * Math.PI));
	private static double normalDist(double x){
		//simple normal distribution with mean 0 and standard deviation 1
		return normalCoeff * Math.exp(-(x * x)/(2.0));
	}
}
