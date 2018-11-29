package ann;


import ann.Layer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

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
	private int maxIterations;

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
		this.maxIterations = maxIterations;

		input = new Layer(configuration[0]);

		hidden = new Layer(configuration[1], input, true);

		output = new Layer(configuration[2], hidden, false);
	}

	public String toString(){
		String result = "[learningRate: " + learningRate + ", maxIterations: " + maxIterations + "]";
		result += "\nINPUT:  " + input.toString();
		result += "\nHIDDEN: " + hidden.toString();
		result += "\nOUTPUT: " + output.toString();
		return result;
	}

	void train(Data data) {
		ArrayList<Double> errorPerIteration = new ArrayList<Double>();

		for (int i = 0; i < maxIterations; i++){	
			double error = 0.0;
			Data shuffled = data.shuffle();
			for(Row r : shuffled.getRows()){
				forwardPropogation(r.getInputs());
				try {
					error = backPropogation(r.getOutputs());
					errorPerIteration.add(error);
				} catch (Exception ex) {
					Logger.getLogger(ANN.class.getName()).log(Level.SEVERE, null, ex);
				}
				forwardPropogation(r.getInputs());
			}

			System.out.println("Error: " + error + "\n" + this + "\n");
		}

		//graph errorPerIteration
		//http://www.jfree.org/jfreechart/
	}

	public void forwardPropogation(double[] inputs){
		input.inputFeed(inputs);
		hidden.forwardFeed();
		output.forwardFeed();
	}

	public double backPropogation(double[] targetOutputs) throws Exception {
		//ERROR
		//calculate total error
			//foreach output perceptron
		//will return total error for analysis
		double totalError = 0.0;

		ArrayList<Perceptron> outputs = this.output.getPerceptrons();
		ArrayList<Perceptron> hiddens = this.hidden.getPerceptrons();

		if(targetOutputs.length != outputs.size()){
			throw new Exception(String.format("Incorrect number of target output values. "
				+ "Expected [%d] target values.", outputs.size()));
		}

		int i = 0;
		double targetOutput;
		//OUTPUT LAYER
		for(Perceptron o : this.output.getPerceptrons()){ //output perceptrons
			targetOutput = targetOutputs[i++];	

			//calculate error for perceptron
			totalError += o.getError(targetOutput);
			//calculate delta 
			o.calculateDeltaTerm(targetOutput);
			//calculate and STORE (do not update) weights for each perceptron
			o.calculateDeltaWeights();

			int j = 0; //manage which perceptron num we are looking at
			for(Perceptron h : hiddens){ //hidden perceptrons
				h.calculateDeltaTerm(o, j++);
				h.calculateDeltaWeights();
			}
		}

		for(Perceptron o : this.output.getPerceptrons()){
			o.updateWeights();
		}
		for(Perceptron h : this.hidden.getPerceptrons()){
			h.updateWeights();
		}

		return totalError;
	}

	void getResults(Data data) {
		ArrayList<Row> rows = data.getRows();

		String buffer = "";
		for(int i = 0; i < rows.get(0).getInputs().length; i++){
			buffer += String.format("|  IN[%d] ",i);
		}

		for(int i = 0; i < rows.get(0).getOutputs().length; i++){
			buffer += String.format("| OUT[%d] ",i);
		}

		for(int i = 0; i < rows.get(0).getOutputs().length; i++){
			buffer += String.format("| ANN[%d] ",i);
		}

		System.out.println(buffer + "|");

		for(Row r : rows){
			buffer = "";

			double[] inputs = r.getInputs();
			for(int i = 0; i < inputs.length; i++){
				buffer += String.format("|  %2.3f ", inputs[i]);
			}
			
			double[] outputs = r.getOutputs();
			for(int i = 0; i < outputs.length; i++){
				buffer += String.format("|  %2.3f ", outputs[i]);
			}

			this.forwardPropogation(inputs);
			double[] ANN = output.getActivations();
			for(int i = 0; i < ANN.length; i++){
				buffer += String.format("|  %2.3f ", ANN[i]);
			}

			System.out.println(buffer + "|");
		}
	}
}
