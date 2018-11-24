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

	private ArrayList<Double> weights;
	private Double activation = 0.0;
	private Double bias;
	private double net;
	private roles role;

	/**
	 * Constructor
	 * 
	 * @param numInputs  number of inputs (not including bias term)
	 */
	public Perceptron(int numInputs, roles role){
		this.role = role;
		weights = new ArrayList<Double>();
		//initialize random weight values
		for(int i = 0; i < numInputs; i++){//for each input
			weights.add(Math.random());
		}//end for
		bias = Math.random();
	}

	public void forwardFeed(double[] inputs) throws Exception{
		if(inputs.length != weights.size()){
			throw new Exception("Incorrect number of inputs. Expected: " + weights.size());
		}
		//sum of weights times activations
		double sum = 0;
		for (int i = 0; i < inputs.length; i++){
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

	void setActivation(double d) {
		if(role == roles.INPUT){
			activation = d;
		}
	}
}
