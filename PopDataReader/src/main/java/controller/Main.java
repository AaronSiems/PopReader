package controller;

import popClasses.Population;
import utils.PopReader;

public class Main {

	public static void main(String[] args) {
		
		String defaultFilePath = "/valve_population/";
		
		String[] popFileNames = {
				"mvm_bigrock_advanced1.pop",
				"mvm_bigrock_advanced2.pop",
				"mvm_bigrock.pop",
				"mvm_coaltown_advanced.pop",
				"mvm_coaltown_advanced2.pop",
				"mvm_coaltown_expert1.pop",
				"mvm_coaltown_intermediate.pop",
				"mvm_coaltown_intermediate2.pop",
				"mvm_coaltown.pop",
				"mvm_decoy_advanced.pop",
				"mvm_decoy_advanced2.pop",
				"mvm_decoy_advanced3.pop",
				"mvm_decoy_expert1.pop",
				"mvm_decoy_intermediate.pop",
				"mvm_decoy_intermediate2.pop",
				"mvm_decoy.pop",
				"mvm_ghost_town_666.pop",
				"mvm_ghost_town.pop",
				"mvm_mannhattan_advanced1.pop",
				"mvm_mannhattan_advanced2.pop",
				"mvm_mannhattan.pop",
				"mvm_mannworks_advanced.pop",
				"mvm_mannworks_expert1.pop",
				"mvm_mannworks_intermediate.pop",
				"mvm_mannworks_intermediate2.pop",
				"mvm_mannworks_ironman.pop",
				"mvm_mannworks.pop",
				"mvm_rottenburg_advanced1.pop",
				"mvm_rottenburg_advanced2.pop",
				"mvm_rottenburg.pop"};
		PopReader pr = new PopReader();
		
		for(String s : popFileNames) {
			System.out.println("Pop file: " + s);
			String fileName = (defaultFilePath + s);
			Population p = pr.read(fileName);
			//System.out.println(p.toString());
			System.out.println(p.getStats());
		} 
		
		//pr.read(defaultFilePath + "mvm_rottenburg_advanced1.pop");
	}
}


