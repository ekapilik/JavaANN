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
public class Layer {
	private ArrayList<Perceptron> perceptrons;
	private double bias;
	private Layer previousLayer;

	Layer(int numberOfPerceptrons) {
		perceptrons = new ArrayList<Perceptron>();
		for(int i = 0; i < numberOfPerceptrons; i++){
			perceptrons.add(new Perceptron(1));
		}

		bias = Math.random();
	}

	Layer(int numberOfPerceptrons, Layer previousLayer){
		perceptrons = new ArrayList<Perceptron>();
		for(int i = 0; i < numberOfPerceptrons; i++){
			perceptrons.add(new Perceptron(previousLayer.getNumPerceptrons()));
		}

		this.previousLayer = previousLayer;

		bias = Math.random();
	}
	
	public int getNumPerceptrons(){ return perceptrons.size(); }


	public String toString(){
		String result = "";
		for(Perceptron p : perceptrons){
			result += p.getActivation() + "\t";
		}
		return result;
	}
}
