package utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import controller.Main;
import popClasses.TFBot;

public class TemplateReader {
	
	private ArrayList<TFBot> bots;
	private String[] fileNames = { "robot_gatebot.pop", "robot_giant.pop", "robot_standard.pop", "mvm_mannworks_expert1.pop" };
	
	public TemplateReader() {
		bots = new ArrayList<TFBot>();
	}
	
	public TFBot getTemplateByName(String name) {
		//System.out.println("Finding template: \"" + name + "\"");
		int loc = 0;
		for(TFBot b : bots) {
			if(b.getTemplate().equalsIgnoreCase(name)) {
				break;
			}
			loc++;
		}
		return bots.get(loc).clone();
	}
	
	public void getTemplates() {
		//int botNumber = 0;
		for(String fileName : fileNames) {
			fileName = "/valve_population/templates/" + fileName;
			//System.out.println("Loading file: \"" + fileName + "\" into array list");
			InputStream is = Main.class.getResourceAsStream(fileName);
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));
			String line;
			
			Boolean inBot = false;
			Boolean inRevert = false;
			int brackets = 0;
			int tempBrackets = -1;
			int revertBrackets = -1;
			TFBot tempBot = new TFBot();
			ArrayList<String> botKeys = tempBot.getKeyWords();
			
			//int lineNumber = 1;
			//botNumber = 0;
			
			try {
				while( (line = bufferedReader.readLine()) != null ) {
					//lineNumber++;
					String tempLine = "";
					Boolean quotes = false;
					Boolean slash = false;
					
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
					
					if(inBot && !inRevert) {
						//This ignores things that happen after all gate bots are lost.
						if(tempLine.contains("RevertGateBotsBehavior")) {
							if(tempLine.contains("{")) {
								brackets++;
							}
							inRevert = true;
						} else {
							if(tempLine.contains("{")) {
								if(tempBrackets != -1) {
									brackets++;
								} else {
									brackets++;
									tempBrackets = brackets;
								}
							}
							if(tempLine.contains("}")) {
								if(tempBrackets == brackets) {
									inBot = false;
									bots.add(tempBot.clone());
									tempBot = new TFBot();
									brackets--; 
								} else {
									brackets--;
								}
							}
							for(String s : botKeys) {
								if(tempLine.contains(s)) {
									int loc = tempLine.indexOf(s) + s.length();
									String value = "";
									for(int i = 0; i < tempLine.substring(loc).length(); i++) {
										char c = tempLine.substring(loc).charAt(i);
										if(c == ' ' && !quotes) { 
											continue;
										}
										if(c == '\t' && !quotes) { 
											continue;
										}
										if(c == '"') {
											if(quotes) {
												quotes = false;
											} else {
												quotes = true;
											}
											continue;
										}
										value += c;
									}
									quotes = false;
									tempBot.setKeyWord(s, value);
								}
							}
						}
						
					} else if(inRevert) {
						if(tempLine.contains("{")) {
							if(revertBrackets != -1) {
								brackets++;
							} else {
								brackets++;
								revertBrackets = brackets;
							}
						}
						if(tempLine.contains("}")) {
							if(revertBrackets == brackets) {
								inRevert = false;
								brackets--; 
							} else {
								brackets--;
							}
						}
					} else {
						if(tempLine.contains("T_TF")) {
							int loc = tempLine.indexOf("T_TF");
							String templateName;
							if(tempLine.contains("{")) {
								brackets++;
								tempBrackets = brackets;
								int loc2 = tempLine.indexOf("{");
								templateName = tempLine.substring(loc, loc2);
							} else {
								templateName = tempLine.substring(loc);
							}
							templateName = templateName.trim();
							tempBot.setTemplate(templateName);
							//System.out.println(templateName);
							inBot = true;
							//botNumber++;
						}
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			//System.out.println("Found: " + botNumber + " bots.");	
		}
		//System.out.println("Total templates found: " + bots.size());
	}
	
	public String allTemplates() {
		String out = "";
		for(TFBot bot : bots) {
			out += bot.toString();
		}
		
		return out;
	}

}
