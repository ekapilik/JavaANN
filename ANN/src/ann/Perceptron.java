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
	private ArrayList<Double> weights;
	private Double activation = 0.0;
	private Double bias;

	/**
	 * Constructor
	 * 
	 * @param numInputs  number of inputs (not including bias term)
	 */
	public Perceptron(int numInputs){
		weights = new ArrayList<Double>();
		//initialize random weight values
		for(int i = 0; i < numInputs; i++){//for each input
			weights.add(Math.random());
		}//end for
		bias = Math.random();
	}

	public void forwardFeed(Boolean[] inputs) throws Exception{
		if(inputs.length != weights.size()){
			throw new Exception("Incorrect number of inputs. Expected: " + weights.size());
		}

		double sum = 0;
		for (int i = 0; i < inputs.length; i++){
			if(inputs[i]){
				sum += weights.get(i);
			}
		}

		activation = activationFunction(sum);
	}//end forwardFeed

	public void setActivation(boolean input){
		if(input){
			activation = 1.0;
		}
		else{
			activation = 0.0;
		}
	}

	public double activationFunction(double z){
		//sigmoid function
		return 1/(1 + (Math.pow(Math.E, -z)));
	}
	public boolean isActivated(){
		return activation > bias;
	}
}
