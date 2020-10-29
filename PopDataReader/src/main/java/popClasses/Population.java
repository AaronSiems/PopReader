package popClasses;

import java.util.ArrayList;

import popAbstracts.Populators;

public class Population {
	
	private int StartingCurrency; //default 0
	private int RespawnWaveTime; //Default 10;
	private String EventPopFile;
	private int AddSentryBusterWhenDamageDealtExceeds; //Default 3000
	private int AddSentryBusterWhenKillCountExceeds; //Default = 15
	private Boolean CanBotsAttackWhileInSpawnRoom; //No || False = 0, else 1
	private ArrayList<Populators> Populators;
	
	public Population() {
		this.StartingCurrency = 0;
		this.RespawnWaveTime = 10;
		this.AddSentryBusterWhenDamageDealtExceeds = 3000;
		this.AddSentryBusterWhenKillCountExceeds = 15;
		this.CanBotsAttackWhileInSpawnRoom = true;
		this.Populators = new ArrayList<Populators>();
	}
	
	public ArrayList<String> getKeyWords() {
		ArrayList<String> keys = new ArrayList<String>();
		keys.add("StartingCurrency");
		keys.add("RespawnWaveTime");
		keys.add("EventPopFile");
		keys.add("AddSentryBusterWhenDamageDealtExceeds");
		keys.add("AddSentryBusterWhenKillCountExceeds");
		keys.add("CanBotsAttackWhileInSpawnRoom");
		
		return keys;
	}
	
	public void setKeyWord(String key, String value) {
		if(key.equals("StartingCurrency")) {
			int v = Integer.parseInt(value);
			this.StartingCurrency = v;
		} else if(key.equals("RespawnWaveTime")) {
			int v = Integer.parseInt(value);
			this.RespawnWaveTime = v;
		} else if(key.equals("EventPopFile")) {
			this.EventPopFile = value;
		} else if(key.equals("AddSentryBusterWhenDamageDealtExceeds")) {
			int v = Integer.parseInt(value);
			this.AddSentryBusterWhenDamageDealtExceeds = v;
		} else if(key.equals("AddSentryBusterWhenKillCountExceeds")) {
			int v = Integer.parseInt(value);
			this.AddSentryBusterWhenKillCountExceeds = v;
		} else if(key.equals("CanBotsAttackWhileInSpawnRoom")) {
			if(value.equals("0") || value.equalsIgnoreCase("no") || value.equalsIgnoreCase("false")) {
				this.CanBotsAttackWhileInSpawnRoom = false;
			} else {
				this.CanBotsAttackWhileInSpawnRoom = true;
			}
		}
	}
	
	public int getWaveBotHealth() {
		int hp = 0;
		for(Populators p : this.Populators) {
			if(p.getClass() == Wave.class) {
				Wave w = (Wave) p;
				hp += w.getTotalBotHealth();
			}
		}
		return hp;
	}
	
	public int getWaveBotHealthNoSupport() {
		int hp = 0;
		for(Populators p : this.Populators) {
			if(p.getClass() == Wave.class) {
				Wave w = (Wave) p;
				hp += w.getTotalBotHealthNoSupport();
			}
		}
		return hp;
	}
	
	public int getWaveTankHealth() {
		int hp = 0;
		for(Populators p : this.Populators) {
			if(p.getClass() == Wave.class) {
				Wave w = (Wave) p;
				hp += w.getTotalTankHealth();
			}
		}
		return hp;
	}
	
	public int getWaveBotCountNoSupport() {
		int count = 0;
		for(Populators p : this.Populators) {
			if(p.getClass() == Wave.class) {
				Wave w = (Wave) p;
				count += w.getTotalBotsNoSupport();
			}
		}
		return count;
	}
	
	public int getWaveBotCount() {
		int count = 0;
		for(Populators p : this.Populators) {
			if(p.getClass() == Wave.class) {
				Wave w = (Wave) p;
				count += w.getTotalBots();
			}
		}
		return count;
	}
	
	public String getPerWaveBotCountNoSupport() {
		String out = "";
		int count;
		int wave = 0;
		for(Populators p : this.Populators) {
			count = 0;
			if(p.getClass() == Wave.class) {
				wave++;
				Wave w = (Wave) p;
				count += w.getTotalBotsNoSupport();
				out += "\n\t\tWave: " + wave + ", Count: " + count;
			}
			
		}
		return out;
	}
	
	public String getPerWaveBotCount() {
		String out = "";
		int count;
		int wave = 0;
		for(Populators p : this.Populators) {
			count = 0;
			if(p.getClass() == Wave.class) {
				wave++;
				Wave w = (Wave) p;
				count += w.getTotalBots();
				out += "\n\t\tWave: " + wave + ", Count: " + count;
			}
			
		}
		return out;
	}
	
	public int getStartingCurrency() {
		return StartingCurrency;
	}
	public void setStartingCurrency(int startingCurrency) {
		StartingCurrency = startingCurrency;
	}
	public int getRespawnWaveTime() {
		return RespawnWaveTime;
	}
	public void setRespawnWaveTime(int respawnWaveTime) {
		RespawnWaveTime = respawnWaveTime;
	}
	public String getEventPopFile() {
		return EventPopFile;
	}
	public void setEventPopFile(String eventPopFile) {
		EventPopFile = eventPopFile;
	}
	public int getAddSentryBusterWhenDamageDealtExceeds() {
		return AddSentryBusterWhenDamageDealtExceeds;
	}
	public void setAddSentryBusterWhenDamageDealtExceeds(int addSentryBusterWhenDamageDealtExceeds) {
		AddSentryBusterWhenDamageDealtExceeds = addSentryBusterWhenDamageDealtExceeds;
	}
	public int getAddSentryBusterWhenKillCountExceeds() {
		return AddSentryBusterWhenKillCountExceeds;
	}
	public void setAddSentryBusterWhenKillCountExceeds(int addSentryBusterWhenKillCountExceeds) {
		AddSentryBusterWhenKillCountExceeds = addSentryBusterWhenKillCountExceeds;
	}
	public Boolean getCanBotsAttackWhileInSpawnRoom() {
		return CanBotsAttackWhileInSpawnRoom;
	}
	public void setCanBotsAttackWhileInSpawnRoom(Boolean canBotsAttackWhileInSpawnRoom) {
		CanBotsAttackWhileInSpawnRoom = canBotsAttackWhileInSpawnRoom;
	}
	public ArrayList<Populators> getPopulators() {
		return Populators;
	}
	public void addPopulator(Populators p) {
		Populators.add(p);
	}
	public void setPopulators(ArrayList<Populators> populators) {
		Populators = populators;
	}

	@Override
	public String toString() {
		return "Population [StartingCurrency=" + StartingCurrency + ", RespawnWaveTime=" + RespawnWaveTime
				+ ", EventPopFile=" + EventPopFile + ", AddSentryBusterWhenDamageDealtExceeds="
				+ AddSentryBusterWhenDamageDealtExceeds + ", AddSentryBusterWhenKillCountExceeds="
				+ AddSentryBusterWhenKillCountExceeds + ", CanBotsAttackWhileInSpawnRoom="
				+ CanBotsAttackWhileInSpawnRoom + ", Populators=" + Populators.toString() + "]";
	}

	public String getStats() {
		return "\tTotal bot health from waves: " + this.getWaveBotHealth() + "\n"
		+ "\tTotal bot health from waves w/o support: " + this.getWaveBotHealthNoSupport() + "\n"
		+ "\tTotal tank health from waves: " + this.getWaveTankHealth() + "\n"
		+ "\tTotal bots from waves: " + this.getWaveBotCount() + "\n"
		+ "\tWave breakdown: " + this.getPerWaveBotCount() + "\n"
		+ "\tTotal bots from waves w/o support: " + this.getWaveBotCountNoSupport() + "\n"
		+ "\tWave breakdown: " + this.getPerWaveBotCountNoSupport() + "\n";
	}
	
}
