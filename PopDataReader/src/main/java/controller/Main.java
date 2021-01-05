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
		//*
		int[] types = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		for(String s : popFileNames) {
			System.out.println("Pop file: " + s);
			String fileName = (defaultFilePath + s);
			Population p = pr.read(fileName);
			int[] temp = p.getBotTypes();
			for(int i = 0; i < temp.length; i++) {
				types[i] += temp[i];
			}
			//System.out.println(p.toString());
			System.out.println(p.getStats());
		}  /*/
		double totalBots = 0;
		for(int i = 1; i < types.length; i++) {
			totalBots += types[i];
		}
		System.out.println("Total bot counts: (% excludes tanks)");
		System.out.println("Unknown/Tanks: " + types[0]);
		System.out.println("Scouts: " + types[1] + " | " + (double)Math.round((double) types[1] / totalBots * 10000)/100 + "%");
		System.out.println("Soldiers: " + types[2] + " | " + (double)Math.round((double) types[2] / totalBots * 10000)/100 + "%");
		System.out.println("Pyros: " + types[3] + " | " + (double)Math.round((double) types[3] / totalBots * 10000)/100 + "%");
		System.out.println("Demos: " + types[4] + " | " + (double)Math.round((double) types[4] / totalBots * 10000)/100 + "%");
		System.out.println("\tDemo Knights: " + types[10] + " | " + (double)Math.round((double) types[10] / totalBots * 10000)/100 + "%");
		System.out.println("Heavies: " + types[5] + " | " + (double)Math.round((double) types[5] / totalBots * 10000)/100 + "%");
		System.out.println("Engineers: " + types[6] + " | " + (double)Math.round((double) types[6] / totalBots * 10000)/100 + "%");
		System.out.println("Medics: " + types[7] + " | " + (double)Math.round((double) types[7] / totalBots * 10000)/100 + "%");
		System.out.println("Snipers: " + types[8] + " | " + (double)Math.round((double) types[8] / totalBots * 10000)/100 + "%");
		System.out.println("Spys: " + types[9] + " | " + (double)Math.round((double) types[9] / totalBots * 10000)/100 + "%");
		
		/*
		Population p = pr.read(defaultFilePath + "mvm_ghost_town_666.pop");
		System.out.println(p.getStats());
		*/
	}
}


