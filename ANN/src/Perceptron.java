

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
	private ArrayList<Perceptron> inputs;
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
	public Perceptron(){
		this.role = roles.INPUT;

		activation = 0.0;
		bias = Math.random();
	}

	public Perceptron(ArrayList<Perceptron> inputs, boolean isHidden){
		this.role = (isHidden ? roles.HIDDEN : roles.OUTPUT);

		this.inputs = inputs;

		weights = new ArrayList<Double>();
		for(int i = 0; i < inputs.size(); i++){ weights.add(Math.random()); }
		deltaWeights = new ArrayList<Double>();
		activation = 0.0;
		bias = Math.random();
	}

	public void forwardFeed() {
		//sum of weights times activations
		double sum = 0;
		int i = 0;
		for(Perceptron in : inputs){
			sum += weights.get(i++) * in.getActivation();
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
			this.delta = (activation - targetOutput) * (activation) * (1 - activation);
		}
	}

	/**
	 * calculate the delta term for a hidden layer which depends upon layer closer to output 
	 * @param perceptron 
	 */
	public void calculateDeltaTerm(Perceptron perceptron, int perceptronNumInLayer){
		if(role == roles.HIDDEN){
			this.delta = perceptron.getDeltaTerm() * 
				(perceptron.getWeight(perceptronNumInLayer) *
				 activation * (1 - activation));
		}
	}

	public void calculateDeltaWeights(){
		deltaWeights.clear();	
		for(int i = 0; i < weights.size(); i++){
			deltaWeights.add(delta * inputs.get(i).getActivation());
		}
	}

	public void updateWeights(double learningRate){
		for(int i = 0; i < weights.size(); i++){
			double oldWeight = weights.get(i);
			double delta = deltaWeights.get(i);
			double newWeight = oldWeight - (learningRate * delta);
			weights.set(i, newWeight);
		}
	}

	public double getError(double targetValue){
		return Math.pow((targetValue - activation),2.0)/2.0;
	}

	private double getWeight(int num) {
		return weights.get(num);
	}

	public double getDeltaTerm(){
		return this.delta;
	}
}
