/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ann;

import java.util.ArrayList;

/**
 *
 * @author Eric Kapilik
 */
public class Row {
	private ArrayList<Double> input;
	private ArrayList<Double> output;

	//assumes ins/outs will not mismatch
	public Row(double[] ins, double[] outs) {
		input = new ArrayList<Double>();
		for(double d : ins){
			input.add(d);
		}

		output = new ArrayList<Double>();
		for(double d : outs){
			output.add(d);
		}
	}

	public double[] getInputs(){ 
		double[] ins = new double[input.size()];
		for(int i = 0; i < input.size(); i++){
			ins[i] = input.get(i);
		}
		return ins;
	}
	public double[] getOutputs(){ 
		double[] outs = new double[output.size()];
		for(int i = 0; i < output.size(); i++){
			outs[i] = output.get(i);
		}
		return outs;
	}
}
