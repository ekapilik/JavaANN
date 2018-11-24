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
	private ArrayList<Layer> hidden;
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

		//layers[0] is raw data input
		input = new Layer(configuration[0]);

		//in between configuration numbers are hidden layers
		hidden = new ArrayList<Layer>();
		int i = 1;
		for(; i < configuration.length - 1; i++){
			if(i == 1){
				hidden.add(new Layer(configuration[i], input, Perceptron.roles.HIDDEN));
			}
			else{
				hidden.add(new Layer(configuration[i], hidden.get(i - 2), Perceptron.roles.HIDDEN));
			}
		}

		//layers[n] is output layer
		output = new Layer(configuration[i], hidden.get(i - 2), Perceptron.roles.OUTPUT);
	}

	public String toString(){
		String result = "[learningRate: " + learningRate + ", numIterations: " + numIterations + "]";
		result += "\nINPUT:     ";
		result += input.toString();


		for(int i = 0; i < hidden.size(); i++){
			result += "\nHIDDEN[" + i + "]: ";
			result += hidden.get(i).toString();
		}

		result += "\nOUTPUT:    ";
		result += output.toString();

		return result;
	}

	void train() {
	}

	public void forwardPropogation(double[] inputs){
		input.inputFeed(inputs);
		for(Layer l : hidden){
			l.forwardFeed();
		}
		output.forwardFeed();
	}

	void backPropogation(double[] output) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}
}
