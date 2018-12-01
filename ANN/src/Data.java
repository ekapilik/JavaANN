/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Eric Kapilik
 */
public class Data {
	private ArrayList<Row> rows;

	public Data(){
		rows = new ArrayList<Row>();
	}

	public Data(ArrayList<Row> rows){
		this.rows = rows;
	}

	public void add(Row row){ rows.add(row); }
	
	public Row getRow(int index){
		return rows.get(index);
	}

	public ArrayList<Row> getRows(){
		return rows;
	}

	public Data shuffle(){
		List<Row> shuffleMe = (List) this.rows.clone(); //clone original data
		Collections.shuffle(shuffleMe); //shuffle
  		return(new Data((ArrayList<Row>) shuffleMe)); //return same Data type
	}
}
