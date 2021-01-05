package utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import controller.Main;
import popClasses.Mission;
import popClasses.Population;
import popClasses.Squad;
import popClasses.TFBot;
import popClasses.Tank;
import popClasses.Wave;
import popClasses.WaveSpawn;

public class PopReader {
	
	private String fileName;
	private TemplateReader TR;
	private String mapName;
	
	/*Debug list for bel
	private ArrayList<String> classNames;
	private ArrayList<String> robotNames; */
	
	public PopReader() { 
		TR = new TemplateReader();
		TR.getTemplates();
		/* Bel Debug
		classNames = new ArrayList<String>();
		classNames.add("Spy");
		classNames.add("Sniper");
		classNames.add("Scout");
		classNames.add("Soldier");
		classNames.add("Pyro");
		classNames.add("Heavyweapons");
		classNames.add("Demoman");
		classNames.add("Medic");
		robotNames = new ArrayList<String>();
		robotNames.add("Spy");
		robotNames.add("Sniper");
		robotNames.add("Scout");
		robotNames.add("Soldier");
		robotNames.add("Pyro");
		robotNames.add("Heavyweapons");
		robotNames.add("Demoman");
		robotNames.add("Medic");
		robotNames.add("Crit Scout");
		robotNames.add("Crit Soldier");
		robotNames.add("Crit Pyro");
		robotNames.add("Crit Heavy");
		robotNames.add("Crit Demo"); */
		//System.out.println(TR.allTemplates());
	}
	
	/*Debug method for bel
	private ArrayList<String> inList(ArrayList<String> list, String input, String type, int line){
		if(list.contains(input)) {
			return list;
		} else {
			System.out.println("Found different than expected " + type + " on line " + line + ": \"" + input + "\"");
			return list;
		}
	} */
	
	public Population read(String filePath) {
		
		Population p = new Population();
		Mission m = new Mission();
		TFBot b = new TFBot();
		Wave w = new Wave();
		WaveSpawn ws = new WaveSpawn();
		ws.setMapName(mapName);
		Squad sq = new Squad();
		Tank t = new Tank();
		
		fileName = filePath;
		
		//CHANGE IF CHANGED IN MAIN
		String defaultFilePath = "/valve_population/";
		mapName = filePath.substring(defaultFilePath.length());
		
		InputStream is = Main.class.getResourceAsStream(fileName);
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));
		String line;
		int lineNum = 0;
		
		//Tracking bools
		Boolean inPopulation = false;
		Boolean inMission = false;
		Boolean inBot = false;
		Boolean inWave = false;
		Boolean inWaveSpawn = false;
		Boolean inSquad = false; //Can be Squad or RandomChoice
		Boolean inTank = false;
		int brackets = 0; //Foolish me thinking I could get away with just 1 bracket, should rename to popBrackets
		int missionBrackets = -1;
		int botBrackets = -1;
		int waveBrackets = -1;
		int waveSpawnBrackets = -1;
		int squadBrackets = -1;
		int tankBrackets = -1;
		
		//Keyword lists
		ArrayList<String> popKeys = p.getKeyWords();
		ArrayList<String> missionKeys = m.getKeyWords();
		ArrayList<String> botKeys = b.getKeyWords();
		ArrayList<String> waveKeys = w.getKeyWords();
		ArrayList<String> waveSpawnKeys = ws.getKeyWords();
		ArrayList<String> squadKeys = sq.getKeyWords();
		ArrayList<String> tankKeys = t.getKeyWords();
		
		
		
	    try {
			while( (line = bufferedReader.readLine()) != null )
			{
				lineNum++;
				String tempLine = "";
				Boolean slash = false;
				Boolean inQuotes = false;
				
				//First clear line of comments
				for(int i = 0; i < line.length(); i++) {
					char c = line.charAt(i);
					if(c == '/') { //Char is a slash
						if(slash) { //Previous char was a slash, do nothing for the rest of the line
							break; 
						} else { //Previous char was not a slash, mark slash and continue
							slash = true; 
						}
					} else if (slash) { //We had a slash followed by not a slash, mark slash false and re-add slash to line
						slash = false;
						tempLine += "/" + c;
					} else if (c == '#' && i == 0) { //NewLine include comment also ignore
						break;
					} else { //No slash logic, add the char
						tempLine += c;
					}
				}
				if(tempLine.length() <= 0) { continue; } //Blank line or only comment, go to next line
				
				if(!inPopulation) {
					if(tempLine.contains("WaveSchedule")) {
						inPopulation = true;
						if(tempLine.contains("{")) { brackets++; }
					} else {
						System.out.println("Error: invalid top of pop file, must start with WaveSchedule");
					}
				} else { //Inside population
					if(!inMission && !inWave && !inWaveSpawn && !inSquad && !inBot) { 
						//We aren't in anything else so check for pop flags
						for(String s : popKeys) {
							if(tempLine.contains(s) && !tempLine.contains("FixedRespawnWaveTime")) {
								int loc = tempLine.indexOf(s) + s.length();
								String value = "";
								for(int i = 0; i < tempLine.substring(loc).length(); i++) {
									char c = tempLine.substring(loc).charAt(i);
									if(c == ' ' && !inQuotes) { 
										continue;
									}
									if(c == '\t' && !inQuotes) { 
										continue;
									}
									if(c == '"') {
										if(inQuotes) {
											inQuotes = false;
										} else {
											inQuotes = true;
										}
										continue;
									}
									value += c;
								}
								inQuotes = false;
								//System.out.println("Key: " + s + " Value: " + value);
								p.setKeyWord(s, value);
							}
						}
					}
					//Manual if logic for classes
					if(tempLine.contains("Mission")) {
						//System.out.println("Entering mission");
						inMission = true;
						if(tempLine.contains("{")) { 
							brackets++;
							missionBrackets = brackets;
						}
					} else if(tempLine.contains("TFBot")) {
						//System.out.println("Entering bot");
						inBot = true;
						if(tempLine.contains("{")) { 
							brackets++;
							botBrackets = brackets;
						}
					} else if(tempLine.endsWith("Wave")) {
						//System.out.println("Entering wave Line: " + lineNum);
						inWave = true;
						if(tempLine.contains("{")) { 
							brackets++;
							waveBrackets = brackets;
						}
					} else if(tempLine.contains("WaveSpawn")) {
						//System.out.println("Entering waveSpawn Line: " + lineNum);
						inWaveSpawn = true;
						if(tempLine.contains("{")) { 
							brackets++;
							waveSpawnBrackets = brackets;
						}
						ws.setMapName(mapName);
					} else if(tempLine.contains("Squad") || tempLine.contains("RandomChoice")) {
						//System.out.println("Entering squad Line: " + lineNum);
						inSquad = true;
						if(tempLine.contains("{")) { 
							brackets++;
							squadBrackets = brackets;
						}
					} else if(tempLine.endsWith("Tank")) {
						//System.out.println("Entering tank Line: " + lineNum);
						inTank = true;
						if(tempLine.contains("{")) { 
							brackets++;
							tankBrackets = brackets;
						}
					}
					
					//==========================
					//Logic for in[Class]
					//==========================
					
					//in mission
					if(inMission) {
						//in bot in mission
						if(inBot) {
							if(tempLine.contains("{")) {
								if(botBrackets != -1) {
									brackets++;
								} else {
									brackets++;
									botBrackets = brackets;
								}
							}
							if(tempLine.contains("}")) {
								if(botBrackets == brackets) {
									//System.out.println("Leaving bot: " + b.toString());
									inBot = false;
									m.setSpawner(b.clone());
									b = new TFBot();
									brackets--;
									botBrackets = -1;
								} else {
									brackets--;
								}
							}
							
							for(String s : botKeys) {
								//Exclude item names for now... TODO
								if(tempLine.contains(s) && !tempLine.contains("ItemName")) {
									int loc = tempLine.indexOf(s) + s.length();
									String value = "";
									for(int i = 0; i < tempLine.substring(loc).length(); i++) {
										char c = tempLine.substring(loc).charAt(i);
										if(c == ' ' && !inQuotes) { 
											continue;
										}
										if(c == '\t' && !inQuotes) {
											continue;
										}
										if(c == '"') {
											if(inQuotes) {
												inQuotes = false;
											} else {
												inQuotes = true;
											}
											continue;
										}
										value += c;
									}
									inQuotes = false;
									if(s.equals("Template")) {
										b = TR.getTemplateByName(value);
									} else {
										b.setKeyWord(s, value);
									}
									
									/*Debug for bel
									if(s.equalsIgnoreCase("Class ")) {
										classNames = inList(classNames, value, "Class Name (bot mission)", lineNum);
									} else if(s.equalsIgnoreCase("Name ")) {
										robotNames = inList(robotNames, value, "Bot Name (bot mission)", lineNum);
									} */
									//System.out.println("Key: " + s + " Value: " + value);
								}
							}
						} else { //Exit bot in mission
							//Back in mission only
							if(tempLine.contains("{")) {
								if(missionBrackets != -1) {
									brackets++;
								} else {
									brackets++;
									missionBrackets = brackets;
								}
							}
							if(tempLine.contains("}")) {
								if(missionBrackets == brackets) {
									//System.out.println("Leaving mission");
									inMission = false;
									p.addPopulator(m.clone());
									m = new Mission();
									brackets--;
									missionBrackets = -1;
								} else {
									brackets--;
								}
							}
							
							for(String s : missionKeys) {
								if(tempLine.contains(s)) {
									int loc = tempLine.indexOf(s) + s.length();
									String value = "";
									for(int i = 0; i < tempLine.substring(loc).length(); i++) {
										char c = tempLine.substring(loc).charAt(i);
										if(c == ' ' && !inQuotes) { 
											continue;
										}
										if(c == '\t' && !inQuotes) {
											continue;
										}
										if(c == '"') {
											if(inQuotes) {
												inQuotes = false;
											} else {
												inQuotes = true;
											}
											continue;
										}
										value += c;
									}
									inQuotes = false;
									m.setKeyWord(s, value);
									//System.out.println("Key: " + s + " Value: " + value);
								}
							}
						}
					} else if(inWave) {//Exit mission: enter wave
						//in waveSpawn in wave
						if(inWaveSpawn) {
							//in bot in waveSpawn or waveSpawn.squad
							if(inBot) {
								if(tempLine.contains("{")) {
									if(botBrackets != -1) {
										brackets++;
									} else {
										brackets++;
										botBrackets = brackets;
									}
								}
								if(tempLine.contains("}")) {
									if(botBrackets == brackets) {
										if(b.getHealth() == 0) {
											System.out.println("Leaving bot: " + b.toString());
										}
										inBot = false;
										if(inSquad) {
											sq.addSpawner(b.clone());
											b = new TFBot();
										} else {
											ws.addSpawner(b.clone());
											b = new TFBot();
										}
										brackets--;
										botBrackets = -1;
									} else {
										brackets--;
									}
								}
								
								for(String s : botKeys) {
									//Exclude item names for now... TODO
									if(tempLine.contains(s) && !tempLine.contains("ItemName")) {
										int loc = tempLine.indexOf(s) + s.length();
										String value = "";
										for(int i = 0; i < tempLine.substring(loc).length(); i++) {
											char c = tempLine.substring(loc).charAt(i);
											if(c == ' ' && !inQuotes) { 
												continue;
											}
											if(c == '\t' && !inQuotes) {
												continue;
											}
											if(c == '"') {
												if(inQuotes) {
													inQuotes = false;
												} else {
													inQuotes = true;
												}
												continue;
											}
											value += c;
										}
										inQuotes = false;
										if(s.equals("Template")) {
											b = TR.getTemplateByName(value);
										} else {
											b.setKeyWord(s, value);
										}
										
										/*Debug for bel
										if(s.equalsIgnoreCase("Class ")) {
											classNames = inList(classNames, value, "Class Name (bot wave)", lineNum);
										} else if(s.equalsIgnoreCase("Name ")) {
											robotNames = inList(robotNames, value, "Bot Name (bot wave)", lineNum);
										}*/
										//System.out.println("Key: " + s + " Value: " + value);
									}
								}
							} else if(inTank) { //Exit bot in waveSpawn
								//Enter tank in waveSpawn
								if(tempLine.contains("{")) {
									if(tankBrackets != -1) {
										brackets++;
									} else {
										brackets++;
										tankBrackets = brackets;
									}
								}
								if(tempLine.contains("}")) {
									if(tankBrackets == brackets) {
										//System.out.println("Leaving tank: " + t.toString());
										inTank = false;
										ws.addSpawner(t.clone());
										t = new Tank();
										brackets--;
										tankBrackets = -1;
									} else {
										brackets--;
									}
								}
								
								for(String s : tankKeys) {
									if(tempLine.contains(s)) {
										int loc = tempLine.indexOf(s) + s.length();
										String value = "";
										for(int i = 0; i < tempLine.substring(loc).length(); i++) {
											char c = tempLine.substring(loc).charAt(i);
											if(c == ' ' && !inQuotes) { 
												continue;
											}
											if(c == '\t' && !inQuotes) {
												continue;
											}
											if(c == '"') {
												if(inQuotes) {
													inQuotes = false;
												} else {
													inQuotes = true;
												}
												continue;
											}
											value += c;
										}
										inQuotes = false;
										t.setKeyWord(s, value);
										//System.out.println("Key: " + s + " Value: " + value);
									}
								}
							} else if(inSquad) { //Exit tank in waveSpawn
								//Enter squad in waveSpawn
								if(tempLine.contains("{")) {
									if(squadBrackets != -1) {
										brackets++;
									} else {
										brackets++;
										squadBrackets = brackets;
									}
								}
								if(tempLine.contains("}")) {
									if(squadBrackets == brackets) {
										//System.out.println("Leaving squad: " + sq.toString());
										inSquad = false;
										ws.addSpawner(sq.clone());
										sq = new Squad();
										brackets--;
										squadBrackets = -1;
									} else {
										brackets--;
									}
								}
								
								for(String s : squadKeys) {
									if(tempLine.contains(s)) {
										int loc = tempLine.indexOf(s) + s.length();
										String value = "";
										for(int i = 0; i < tempLine.substring(loc).length(); i++) {
											char c = tempLine.substring(loc).charAt(i);
											if(c == ' ' && !inQuotes) { 
												continue;
											}
											if(c == '\t' && !inQuotes) {
												continue;
											}
											if(c == '"') {
												if(inQuotes) {
													inQuotes = false;
												} else {
													inQuotes = true;
												}
												continue;
											}
											value += c;
										}
										inQuotes = false;
										sq.setKeyWord(s, value);
										
										/*Debug for bel
										if(s.equalsIgnoreCase("Class ")) {
											classNames = inList(classNames, value, "Class Name (s)", lineNum);
										} else if(s.equalsIgnoreCase("Name ")) {
											robotNames = inList(robotNames, value, "Bot Name (s)", lineNum);
										}*/
										//System.out.println("Key: " + s + " Value: " + value);
									}
								}
							} else { //Exit squad in waveSpawn
								//waveSpawn in wave
								if(tempLine.contains("{")) {
									if(waveSpawnBrackets != -1) {
										brackets++;
									} else {
										brackets++;
										waveSpawnBrackets = brackets;
									}
								}
								if(tempLine.contains("}")) {
									if(waveSpawnBrackets == brackets) {
										//System.out.println("(" + lineNum + ") Leaving waveSpawn: " + ws.toString());
										inWaveSpawn = false;
										w.addWaveSpawn(ws.clone());
										ws = new WaveSpawn();
										ws.setMapName(mapName);
										brackets--;
										waveSpawnBrackets = -1;
									} else {
										brackets--;
									}
								}
								
								for(String s : waveSpawnKeys) {
									if(tempLine.contains(s)) {
										if(s.equals("WaitBetweenSpawns") && tempLine.contains("WaitBetweenSpawnsAfterDeath")) { continue; }
										int loc = tempLine.indexOf(s) + s.length();
										String value = "";
										for(int i = 0; i < tempLine.substring(loc).length(); i++) {
											char c = tempLine.substring(loc).charAt(i);
											if(c == ' ' && !inQuotes) {
												continue;
											}
											if(c == '\t' && !inQuotes) {
												continue;
											}
											if(c == '"') {
												if(inQuotes) {
													inQuotes = false;
												} else {
													inQuotes = true;
												}
												continue;
											}
											value += c;
										}
										inQuotes = false;
										ws.setKeyWord(s, value);
										//System.out.println("Key: " + s + " Value: " + value);
									}
								}
							}
						} else { //Exit waveSpawn in wave
							//Back in wave only
							if(tempLine.contains("{")) {
								if(waveBrackets != -1) {
									brackets++;
								} else {
									brackets++;
									waveBrackets = brackets;
								}
							}
							if(tempLine.contains("}")) {
								if(waveBrackets == brackets) {
									//System.out.println("(" + lineNum + ") Leaving wave");
									inWave = false;
									p.addPopulator(w.clone());
									w = new Wave();
									brackets--;
									waveBrackets = -1;
								} else {
									brackets--;
								}
							}
							
							for(String s : waveKeys) {
								if(tempLine.contains(s)) {
									int loc = tempLine.indexOf(s) + s.length();
									String value = "";
									for(int i = 0; i < tempLine.substring(loc).length(); i++) {
										char c = tempLine.substring(loc).charAt(i);
										if(c == ' ' && !inQuotes) { 
											continue;
										}
										if(c == '\t' && !inQuotes) {
											continue;
										}
										if(c == '"') {
											if(inQuotes) {
												inQuotes = false;
											} else {
												inQuotes = true;
											}
											continue;
										}
										value += c;
									}
									inQuotes = false;
									w.setKeyWord(s, value);
									//System.out.println("Key: " + s + " Value: " + value);
								}
							}
						}
					}//Exit wave
					
					
					//Last line for inside population
					//if(tempLine.contains("{")) { brackets++; }
				}
			}
			//System.out.println(p.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	    return p;
	}
}
