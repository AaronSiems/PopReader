package utils;

import java.util.ArrayList;

import popAbstracts.Spawner;
import popClasses.Squad;
import popClasses.TFBot;
import popClasses.WaveSpawn;

public class TimeCalculator {
	
	//Spawn names
	private ArrayList<String> bigrockSpawns = new ArrayList<String>();
	private ArrayList<String> coaltownSpawns = new ArrayList<String>();
	private ArrayList<String> ghosttownSpawns = new ArrayList<String>();
	private ArrayList<String> decoySpawns = new ArrayList<String>();
	private ArrayList<String> mannhattanSpawns = new ArrayList<String>();
	private ArrayList<String> mannworksSpawns = new ArrayList<String>();
	private ArrayList<String> rottenburgSpawns = new ArrayList<String>();
	//Time to hit ledge
	private ArrayList<Double> bigrockTimes = new ArrayList<Double>();
	private ArrayList<Double> coaltownTimes = new ArrayList<Double>();
	private ArrayList<Double> ghosttownTimes = new ArrayList<Double>();
	private ArrayList<Double> decoyTimes = new ArrayList<Double>();
	private ArrayList<Double> mannhattanTimes = new ArrayList<Double>();
	private ArrayList<Double> mannworksTimes = new ArrayList<Double>();
	private ArrayList<Double> rottenburgTimes = new ArrayList<Double>();
	
	public TimeCalculator() {
		//Spy spawning is special since they teleport, will use a guess of 1 second before they appear.
		double spyTime = 1.0;
		//Some maps have ledges that are either large or small, times taken from rottenburg.
		double smallFall = 0.55;
		double bigFall = 0.78;
		
		//mvm_bigrock
		bigrockSpawns.add("spawnbot_giant");
		bigrockTimes.add(1.77);
		bigrockSpawns.add("spawnbot");
		bigrockTimes.add(1.77);
		bigrockSpawns.add("spawnbot_mission_sniper");
		bigrockTimes.add(1.77);
		bigrockSpawns.add("spawnbot_mission_spy");
		bigrockTimes.add(spyTime);
		//mvm_coaltown
		coaltownSpawns.add("spawnbot_giant");
		coaltownTimes.add((2.76+1.3)/2 + smallFall);
		coaltownSpawns.add("spawnbot_invasion");
		coaltownTimes.add((7.17+2.76+1.3)/3 + smallFall);
		coaltownSpawns.add("spawnbot_mission_sniper");
		coaltownTimes.add((2.76+1.3)/2 + smallFall);
		coaltownSpawns.add("spawnbot");
		coaltownTimes.add(7.17 + smallFall);
		coaltownSpawns.add("spawnbot_mission_spy");
		coaltownTimes.add(spyTime);
		//mvm_decoy
		decoySpawns.add("spawnbot_single_flag");
		decoyTimes.add(4.68 + bigFall);
		decoySpawns.add("spawnbot");
		decoyTimes.add(4.68 + bigFall);
		decoySpawns.add("spawnbot_invasion");
		decoyTimes.add((4.68+ bigFall +1.39+1.18)/3);
		decoySpawns.add("spawnbot_mission_sniper");
		decoyTimes.add((1.39+1.18)/2);
		decoySpawns.add("spawnbot_right");
		decoyTimes.add(1.39);
		decoySpawns.add("spawnbot_left");
		decoyTimes.add(1.18);
		decoySpawns.add("spawnbot_mission_spy");
		decoyTimes.add(spyTime);
		//mvm_ghost_town - same times as coaltown
		ghosttownSpawns.add("spawnbot_giant");
		ghosttownTimes.add((2.76+1.3)/2 + smallFall);
		ghosttownSpawns.add("spawnbot_invasion");
		ghosttownTimes.add((7.17+2.76+1.3)/3 + smallFall);
		ghosttownSpawns.add("spawnbot_scattered");
		ghosttownTimes.add((7.17+2.76+1.3)/3 + smallFall);
		ghosttownSpawns.add("spawnbot_mission_sniper");
		ghosttownTimes.add((2.76+1.3)/2 + smallFall);
		ghosttownSpawns.add("spawnbot");
		ghosttownTimes.add(7.17 + smallFall);
		ghosttownSpawns.add("spawnbot_mission_spy");
		ghosttownTimes.add(spyTime);
		//mvm_mannhattan
		mannhattanSpawns.add("spawnbot_main0");
		mannhattanTimes.add(6.9 + bigFall);
		mannhattanSpawns.add("spawnbot");
		mannhattanTimes.add(6.9 + bigFall);
		mannhattanSpawns.add("spawnbot_main0_squad");
		mannhattanTimes.add(6.9 + bigFall);
		mannhattanSpawns.add("spawnbot_mission_spy");
		mannhattanTimes.add(spyTime);
		mannhattanSpawns.add("spawnbot_upper0");
		mannhattanTimes.add(4.63 + smallFall);
		mannhattanSpawns.add("spawnbot_mission_sniper0");
		mannhattanTimes.add(4.63 + smallFall);
		//mvm_mannworks
		mannworksSpawns.add("spawnbot_mission_sniper");
		mannworksTimes.add((6+ smallFall +2.13)/2);
		mannworksSpawns.add("spawnbot_mission_spy");
		mannworksTimes.add(spyTime);
		mannworksSpawns.add("spawnbot_lower");
		mannworksTimes.add((6+ smallFall +2.13)/2);
		mannworksSpawns.add("spawnbot");
		mannworksTimes.add((3.82+3.52)/2 + bigFall);
		mannworksSpawns.add("spawnbot_left");
		mannworksTimes.add(3.82 + bigFall);
		mannworksSpawns.add("spawnbot_right");
		mannworksTimes.add(3.52 + bigFall);
		//mvm_rottenburg
		rottenburgSpawns.add("spawnbot_mission_sentry_buster");
		rottenburgTimes.add(3.42 + bigFall);
		rottenburgSpawns.add("spawnbot_mission_spy");
		rottenburgTimes.add(spyTime);
		rottenburgSpawns.add("spawnbot_chief");
		rottenburgTimes.add(4.92 + smallFall);
		rottenburgSpawns.add("spawnbot");
		rottenburgTimes.add(3.42 + bigFall);
		rottenburgSpawns.add("flankers");
		rottenburgTimes.add(4.92 + smallFall);
		rottenburgSpawns.add("spawnbot_mission_sniper");
		rottenburgTimes.add(4.92 + smallFall);
		
	}
	
	public double getWalkingTimeFromWaveSpawn(String mapName, WaveSpawn ws) {
		double total = 0.0;
		//Next wavespawn if current one has tank
		if(ws.hasTank()) { return total; }
		
		String where = "spawnbot"; //spawnbot is default location
		//Mannhattan can have multiple where's per wavespawn but we only care about the first one
		if(ws.getWhere().size() != 0) {
			where = ws.getWhere().get(0);
		}
		
		double tempSpeed = 0.0;
		for(Spawner s : ws.getSpawner()) {
			//if squad get the slowest bot's speed
			if(s.getClass() == Squad.class) {
				double squadSpeed = 20;
				Squad squad = (Squad) s;
				for(Spawner s2 : squad.getSpawner()) {
					if(s2.getClass() == TFBot.class) {
						TFBot bot = (TFBot) s2;
						if(bot.getSpeed() < squadSpeed) {
							squadSpeed = bot.getSpeed();
						}
					}
				}
				tempSpeed = squadSpeed;
			} else {
				if(s.getClass() == TFBot.class) {
					TFBot bot = (TFBot) s;
					tempSpeed = bot.getSpeed();
				}
			}
		}
		
		double tempTime = 0.0;
		//System.out.println(mapName + " : " + where);
		if(mapName.startsWith("mvm_bigrock")) {
			tempTime = tempSpeed * bigrockTimes.get(bigrockSpawns.indexOf(where));
		} else if(mapName.startsWith("mvm_coaltown")) {
			tempTime = tempSpeed * coaltownTimes.get(coaltownSpawns.indexOf(where));
		} else if(mapName.startsWith("mvm_decoy")) {
			tempTime = tempSpeed * decoyTimes.get(decoySpawns.indexOf(where));
		} else if(mapName.startsWith("mvm_ghost_town")) {
			tempTime = tempSpeed * ghosttownTimes.get(ghosttownSpawns.indexOf(where));
		} else if(mapName.startsWith("mvm_mannhattan")) {
			tempTime = tempSpeed * mannhattanTimes.get(mannhattanSpawns.indexOf(where));
		} else if(mapName.startsWith("mvm_mannworks")) {
			tempTime = tempSpeed * mannworksTimes.get(mannworksSpawns.indexOf(where));
		} else if(mapName.startsWith("mvm_rottenburg")) {
			tempTime = tempSpeed * rottenburgTimes.get(rottenburgSpawns.indexOf(where));
		}
		
		//If we can have all them spawned w/o them dying we can just add tempTime to the existing getTime method
		//Because we only have to wait for the very last spawn to walk off
		if(ws.getTotalCount() <= ws.getMaxActive()) {
			total += ws.getNoWalkTime() + tempTime;
		} else {
			//TODO else we cry
			total += ws.getNoWalkTime() + tempTime;
		}
		//System.out.println(ws.getNoWalkTime() + " : " + tempTime);
		return total;
	}
}
