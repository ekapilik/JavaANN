package ann;


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
public class Perceptron {


	public enum roles {INPUT, HIDDEN, OUTPUT};

	private roles role;
	private ArrayList<Double> weights;
	private ArrayList<Double> inputs;
	private ArrayList<Double> deltaWeights; //weights not updated by deltaWeights until .updateWeights() is run
	private double delta;
	private Double activation;
	private Double bias;
	private double net;

	/**
	 * Constructor
	 * 
	 * @param numInputs  number of inputs (not including bias term)
	 */
	public Perceptron(int numInputs, roles role){
		this.role = role;

		weights = new ArrayList<Double>();
		inputs = new ArrayList<Double>();
		deltaWeights = new ArrayList<Double>();
		activation = 0.0;
		bias = Math.random();

		//initialize random weight values
		for(int i = 0; i < numInputs; i++){//for each input
			weights.add(Math.random());
		}//end for

	}

	public void forwardFeed(double[] inputs) throws Exception{
		if(inputs.length != weights.size()){
			throw new Exception("Incorrect number of inputs. Expected: " + weights.size());
		}

		this.inputs = new ArrayList<Double>();

		//sum of weights times activations
		double sum = 0;
		for (int i = 0; i < inputs.length; i++){
			this.inputs.add(inputs[i]);
			sum += weights.get(i) * inputs[i];
		}

		this.net = sum + bias;
		this.activation = activationFunction(this.net);
	}//end forwardFeed

	public double activationFunction(double z){
		//sigmoid function
		return 1/(1 + (Math.pow(Math.E, -z)));
	}

	public double getActivation(){
		return activation;
	}

	public void setActivation(double d) {
		if(role == roles.INPUT){
			activation = d;
		}
	}

	/**
	 * calculate delta term for output term
	 * @param targetOutput 
	 */
	public void calculateDeltaTerm(double targetOutput) {
		if(role == roles.OUTPUT){
			this.delta = (targetOutput - activation) * (activation) * (1 - activation);
		}
	}

	/**
	 * calculate the delta term for a hidden layer which depends upon layer closer to output 
	 * @param perceptron 
	 */
	public void calculateDeltaTerm(Perceptron perceptron, int num){
		if(role == roles.HIDDEN){
			this.delta = perceptron.getDeltaTerm() * perceptron.getWeight(num) * activation * (1 - activation);
		}
	}

	public void calculateDeltaWeights(){
		if(role == roles.OUTPUT){
			deltaWeights.clear();	
			for(int i = 0; i < weights.size(); i++){
				deltaWeights.add(delta * inputs.get(i));
			}
		}
	}

	public void updateWeights(){
		for(int i = 0; i < weights.size(); i++){
			weights.set(i, weights.get(i) - deltaWeights.get(i));
		}
	}

	private double getWeight(int num) {
		return weights.get(num);
	}

	public double getDeltaTerm(){
		return this.delta;
	}
}
