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

	Layer(int numberOfPerceptrons) {
		perceptrons = new ArrayList<Perceptron>();
		for(int i = 0; i < numberOfPerceptrons; i++){
			perceptrons.add(new Perceptron(1));
		}

	}

	Layer(int numberOfPerceptrons, Layer previousLayer){
		perceptrons = new ArrayList<Perceptron>();
		for(int i = 0; i < numberOfPerceptrons; i++){
			perceptrons.add(new Perceptron(previousLayer.getNumPerceptrons()));
		}

		this.previousLayer = previousLayer;

	}
	
	public int getNumPerceptrons(){ return perceptrons.size(); }


	public String toString(){
		String result = "";
		for(Perceptron p : perceptrons){
			result += (p.isActivated()?1:0) + " ";
		}
		return result;
	}

	void inputFeed(Boolean[] input){
		for(int i = 0; i < perceptrons.size(); i++){
			((Perceptron)perceptrons.get(i)).setActivation(input[i]);
		}
	}


	void forwardFeed() {
		Boolean[] inputs = previousLayer.getActivations();
		for(Perceptron p : perceptrons){
			try {
				p.forwardFeed(inputs);
			} catch (Exception ex) {
				Logger.getLogger(Layer.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
	}

	public Boolean[] getActivations() {
		int n = perceptrons.size();
		Boolean[] activations = new Boolean[n];
		for(int i = 0; i < n; i++){
			activations[i] = perceptrons.get(i).isActivated();
		}
		return activations;
	}
}
