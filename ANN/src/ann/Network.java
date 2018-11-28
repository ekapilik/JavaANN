package ann;


import ann.Layer;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Eric Kapilik
 */
public class Network {
	private double learningRate;
	private int numIterations;

	private Layer input;
	private Layer hidden;
	private Layer output;

	/**
	 * constructor 
	 * @param learningRate size of step to take
	 * @param maxIterations maximum number of iterations to run in training
	 * @param configuration network configuration (left to right) includes input, output, does not include bias terms
	 */
	public Network(double learningRate, int maxIterations, int[] configuration){
		this.learningRate = learningRate;
		this.numIterations = maxIterations;

		input = new Layer(configuration[0]);

		hidden = new Layer(configuration[1], input, true);

		output = new Layer(configuration[2], hidden, false);
	}

	public String toString(){
		String result = "[learningRate: " + learningRate + ", maxIterations: " + numIterations + "]";
		result += "\nINPUT:  " + input.toString();
		result += "\nHIDDEN: " + hidden.toString();
		result += "\nOUTPUT: " + output.toString();
		return result;
	}

	void train(double[][][] trainingData) {
		//ArrayList<Double> errorPerIteration = new ArrayList<Double>();
		//for (int i = 0; i < maxIterations; i++){	
			//shuffle data rows
			//foreach training row
				//forwardPropogation()
				//errorPerIteration += backPropogation();
		//}
		//graph errorPerIteration
		//http://www.jfree.org/jfreechart/
	}

	public void forwardPropogation(double[] inputs){
		input.inputFeed(inputs);
		hidden.forwardFeed();
		output.forwardFeed();
	}

	public double backPropogation(double[] outputValues) {
		//ERROR
		//calculate total error
			//foreach output perceptron
		//will return total error for analysis
		double totalError = 0.0;


		//OUTPUT LAYER
		//calculate delta foreach output perceptron
		this.output.calculateDeltaTerm(outputValues);

		//calculate and STORE (do not update) weights for each perceptron
		this.output.calculateWeightUpdates();

		//HIDDEN LAYER
		//calculate delta foreach hidden layer perceptron
			//calculate ...
			//calculate ...
		//calculate and STORE (do not update) weights for each perceptron
		
		//update output weights
		//update hidden weights
		return totalError;
	}
}
