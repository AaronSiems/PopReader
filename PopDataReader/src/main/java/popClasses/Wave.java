package popClasses;

import java.util.ArrayList;

import popAbstracts.Populators;

public class Wave extends Populators {
	
	private ArrayList<WaveSpawn> WaveSpawn;
	private String Description;
	private float WaitWhenDone; //Default 0.0
	
	public Wave() {
		this.WaitWhenDone = 0;
		this.WaveSpawn = new ArrayList<WaveSpawn>();
	}
	
	public ArrayList<String> getKeyWords() {
		ArrayList<String> keys = new ArrayList<String>();
		keys.add("Description");
		keys.add("WaitWhenDone");
		return keys;
	}
	
	public void setKeyWord(String key, String value) {
		if(key.equals("Description")) {
			this.Description = value;
		} else if(key.equals("WaitWhenDone")) {
			float v = Float.parseFloat(value);
			this.WaitWhenDone = v;
		}
	}
	
	public Wave clone() {
		Wave w = new Wave();
		w.setDescription(this.Description);
		w.setWaitWhenDone(this.WaitWhenDone);
		w.setWaveSpawn(this.WaveSpawn);
		return w;
	}
	
	public int getTotalBotHealth() {
		int hp = 0;
		for(WaveSpawn s : this.WaveSpawn) {
			hp += s.getTotalBotHealth();
		}
		return hp;
	}
	
	public int getTotalBotHealthNoSupport() {
		int hp = 0;
		for(WaveSpawn s : this.WaveSpawn) {
			if(s.getSupport() == "") {
				hp += s.getTotalBotHealth();
			}
		}
		return hp;
	}
	
	public int getTotalTankHealth() {
		int hp = 0;
		for(WaveSpawn s : this.WaveSpawn) {
			hp += s.getTotalTankHealth();
		}
		return hp;
	}
	
	public int getTotalBotsNoSupport() {
		int count = 0;
		for(WaveSpawn w : this.WaveSpawn) {
			if(!w.hasTank()) {
				if(w.getSupport() == "") {
					count += w.getTotalCount();
				}
			}
		}
		return count;
	}
	
	public int getTotalBots() {
		int count = 0;
		for(WaveSpawn w : this.WaveSpawn) {
			if(!w.hasTank()) {
				count += w.getTotalCount();
			}
		}
		return count;
	}
	
	public ArrayList<WaveSpawn> getWaveSpawn() {
		return WaveSpawn;
	}
	public void addWaveSpawn(WaveSpawn ws) {
		WaveSpawn.add(ws);
	}
	public void setWaveSpawn(ArrayList<WaveSpawn> waveSpawn) {
		WaveSpawn = waveSpawn;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public float getWaitWhenDone() {
		return WaitWhenDone;
	}
	public void setWaitWhenDone(float waitWhenDone) {
		WaitWhenDone = waitWhenDone;
	}

	@Override
	public String toString() {
		return "\n Wave [WaveSpawn=" + WaveSpawn + ", Description=" + Description + ", WaitWhenDone=" + WaitWhenDone + "]";
	}
	
	
	

}
