package ann;


import java.util.ArrayList;
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
public class Layer {
	private ArrayList<Perceptron> perceptrons;
	private Layer previousLayer;
	private Perceptron.roles role; //all perceptron roles in a single layer will be the same

	Layer(int numberOfPerceptrons) {
		role = Perceptron.roles.INPUT;
		perceptrons = new ArrayList<Perceptron>();
		for(int i = 0; i < numberOfPerceptrons; i++){
			perceptrons.add(new Perceptron(1, Perceptron.roles.INPUT));
		}

	}

	Layer(int numberOfPerceptrons, Layer previousLayer, Perceptron.roles role){
		this.role = role;
		perceptrons = new ArrayList<Perceptron>();
		for(int i = 0; i < numberOfPerceptrons; i++){
			perceptrons.add(new Perceptron(previousLayer.getNumPerceptrons(), role));
		}
		this.previousLayer = previousLayer;
	}
	
	public int getNumPerceptrons(){ return perceptrons.size(); }


	public String toString(){
		String result = "";
		for(Perceptron p : perceptrons){
			result += String.format("%1.2f ", p.getActivation());
		}
		return result;
	}

	void inputFeed(double[] input){
		for(int i = 0; i < perceptrons.size(); i++){
			((Perceptron)perceptrons.get(i)).setActivation(input[i]);
		}
	}


	void forwardFeed() {
		double[] inputs = previousLayer.getActivations();
		for(Perceptron p : perceptrons){
			try {
				p.forwardFeed(inputs);
			} catch (Exception ex) {
				Logger.getLogger(Layer.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
	}

	public double[] getActivations() {
		int n = perceptrons.size();
		double[] activations = new double[n];
		for(int i = 0; i < n; i++){
			activations[i] = perceptrons.get(i).getActivation();
		}
		return activations;
	}
}
