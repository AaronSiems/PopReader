package popClasses;

import java.util.ArrayList;

import popAbstracts.Populators;
import popAbstracts.Spawner;

public class WaveSpawn extends Populators {
	
	private String Template;
	private int TotalCount; //Default 0
	private int MaxActive; //Default 999
	private int SpawnCount; //Default 1
	private float WaitBeforeStarting; //Default 0.0
	private float WaitBetweenSpawns; //Default 0.0
	private float WaitBetweenSpawnsAfterDeath; //This one or previous one not both
	private int TotalCurrency; //Default -1
	private String Name;
	private String Support; //"Limited" enforces TotalCount otherwise ignored, blank disables it (default null)?
	private ArrayList<Spawner> Spawner;
	
	public WaveSpawn() {
		this.TotalCount = 0;
		this.MaxActive = 999;
		this.SpawnCount = 1;
		this.WaitBeforeStarting = 0.0f;
		this.WaitBetweenSpawns = 0.0f;
		this.TotalCurrency = -1;
		this.Support = "";
		this.Spawner = new ArrayList<Spawner>();
	}
	
	public ArrayList<String> getKeyWords() {
		ArrayList<String> keys = new ArrayList<String>();
		keys.add("Template");
		keys.add("TotalCount");
		keys.add("MaxActive");
		keys.add("SpawnCount");
		keys.add("WaitBeforeStarting");
		keys.add("WaitBetweenSpawns");
		keys.add("WaitBetweenSpawnsAfterDeath");
		keys.add("TotalCurrency");
		keys.add("Name");
		keys.add("Support");
		return keys;
	}
	
	public void setKeyWord(String key, String value) {
		if(key.equals("Template")) {
			this.Template = value;
		}else if(key.equals("TotalCount")) {
			int v = Integer.parseInt(value);
			this.TotalCount = v;
		} else if(key.equals("MaxActive")) {
			int v = Integer.parseInt(value);
			this.MaxActive = v;
		} else if(key.equals("SpawnCount")) {
			int v = Integer.parseInt(value);
			this.SpawnCount = v;
		} else if(key.equals("WaitBeforeStarting")) {
			if(value.contains("s")) {
				int loc = value.indexOf('s');
				value = value.substring(0, loc);
			}
			float v = Float.parseFloat(value);
			this.WaitBeforeStarting = v;
		} else if(key.equals("WaitBetweenSpawns")) {
			float v = Float.parseFloat(value);
			this.WaitBetweenSpawns = v;
		} else if(key.equals("WaitBetweenSpawnsAfterDeath")) {
			float v = Float.parseFloat(value);
			this.WaitBetweenSpawnsAfterDeath = v;
		} else if(key.equals("TotalCurrency")) {
			int v = Integer.parseInt(value);
			this.TotalCurrency = v;
		} else if(key.equals("Name")) {
			this.Name = value;
		} else if(key.equals("Support")) {
			this.Support = value;
		}
	}
	
	public WaveSpawn clone() {
		WaveSpawn ws = new WaveSpawn();
		ws.setMaxActive(this.MaxActive);
		ws.setName(this.Name);
		ws.setSpawnCount(this.SpawnCount);
		ws.setSpawner(this.Spawner);
		ws.setSupport(this.Support);
		ws.setTemplate(this.Template);
		ws.setTotalCount(this.TotalCount);
		ws.setTotalCurrency(this.TotalCurrency);
		ws.setWaitBeforeStarting(this.WaitBeforeStarting);
		ws.setWaitBetweenSpawns(this.WaitBetweenSpawns);
		ws.setWaitBetweenSpawnsAfterDeath(this.WaitBetweenSpawnsAfterDeath);
		return ws;
	}
	
	public int getTotalBotHealth() {
		int hp = 0;
		for(Spawner s : this.Spawner) {
			if(s.getClass() == TFBot.class) {
				hp += s.getHealth() * this.TotalCount;
			} else if(s.getClass() == Squad.class) {
				Squad sq = (Squad) s;
				hp += sq.getTotalBotHealth() * (this.TotalCount / this.SpawnCount);
			}
		}
		return hp;
	}
	public int getTotalTankHealth() {
		int hp = 0;
		for(Spawner s : this.Spawner) {
			if(s.getClass() == Tank.class) {
				hp += s.getHealth();
			}
		}
		return hp;
	}
	
	public Boolean hasTank() {
		Boolean r = false;
		for(Spawner s : this.Spawner) {
			if(s.getClass() == Tank.class) {
				r = true;
				break;
			}
		}
		return r;
	}
	
	public String getTemplate() {
		return Template;
	}
	public void setTemplate(String template) {
		Template = template;
	}
	public int getTotalCount() {
		return TotalCount;
	}
	public void setTotalCount(int totalCount) {
		TotalCount = totalCount;
	}
	public int getMaxActive() {
		return MaxActive;
	}
	public void setMaxActive(int maxActive) {
		MaxActive = maxActive;
	}
	public int getSpawnCount() {
		return SpawnCount;
	}
	public void setSpawnCount(int spawnCount) {
		SpawnCount = spawnCount;
	}
	public float getWaitBeforeStarting() {
		return WaitBeforeStarting;
	}
	public void setWaitBeforeStarting(float waitBeforeStarting) {
		WaitBeforeStarting = waitBeforeStarting;
	}
	public float getWaitBetweenSpawns() {
		return WaitBetweenSpawns;
	}
	public void setWaitBetweenSpawns(float waitBetweenSpawns) {
		WaitBetweenSpawns = waitBetweenSpawns;
	}
	public float getWaitBetweenSpawnsAfterDeath() {
		return WaitBetweenSpawnsAfterDeath;
	}
	public void setWaitBetweenSpawnsAfterDeath(float waitBetweenSpawnsAfterDeath) {
		WaitBetweenSpawnsAfterDeath = waitBetweenSpawnsAfterDeath;
	}
	public int getTotalCurrency() {
		return TotalCurrency;
	}
	public void setTotalCurrency(int totalCurrency) {
		TotalCurrency = totalCurrency;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getSupport() {
		return Support;
	}
	public void setSupport(String support) {
		Support = support;
	}
	public ArrayList<Spawner> getSpawner() {
		return Spawner;
	}
	public void addSpawner(Spawner s) {
		Spawner.add(s);
	}
	public void setSpawner(ArrayList<Spawner> spawner) {
		Spawner = spawner;
	}

	@Override
	public String toString() {
		return "\n\t WaveSpawn [Template=" + Template + ", TotalCount=" + TotalCount + ", MaxActive=" + MaxActive
				+ ", SpawnCount=" + SpawnCount + ", WaitBeforeStarting=" + WaitBeforeStarting + ", WaitBetweenSpawns="
				+ WaitBetweenSpawns + ", WaitBetweenSpawnsAfterDeath=" + WaitBetweenSpawnsAfterDeath
				+ ", TotalCurrency=" + TotalCurrency + ", Name=" + Name + ", Support=" + Support + ", Spawner="
				+ Spawner + "]";
	}
	
	

}
