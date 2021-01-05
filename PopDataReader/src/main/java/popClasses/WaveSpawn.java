package popClasses;

import java.util.ArrayList;

import popAbstracts.Populators;
import popAbstracts.Spawner;
import utils.TimeCalculator;

public class WaveSpawn extends Populators {
	
	private String MapName;
	private String Template;
	private int TotalCount; //Default 0
	private int MaxActive; //Default 999
	private int SpawnCount; //Default 1
	private float WaitBeforeStarting; //Default 0.0
	private String WaitForAllSpawned;
	private String WaitForAllDead;
	private float WaitBetweenSpawns; //Default 0.0
	private float WaitBetweenSpawnsAfterDeath; //This one or previous one not both
	private int TotalCurrency; //Default -1
	private String Name;
	private String Support; //"Limited" enforces TotalCount otherwise ignored, blank disables it (default null)?
	private ArrayList<String> Where; //I believe game defaults this to "spawnbot" if not found
	private ArrayList<Spawner> Spawner;
	
	private TimeCalculator TC;
	
	public WaveSpawn() {
		this.TotalCount = 0;
		this.MaxActive = 999;
		this.SpawnCount = 1;
		this.WaitBeforeStarting = 0.0f;
		this.WaitForAllSpawned = "";
		this.WaitForAllDead = "";
		this.WaitBetweenSpawns = 0.0f;
		this.TotalCurrency = -1;
		this.Support = "";
		this.Name = "";
		this.Where = new ArrayList<String>();
		this.Spawner = new ArrayList<Spawner>();
		this.TC = new TimeCalculator();
	}
	
	public ArrayList<String> getKeyWords() {
		ArrayList<String> keys = new ArrayList<String>();
		keys.add("Template");
		keys.add("TotalCount");
		keys.add("MaxActive");
		keys.add("SpawnCount");
		keys.add("WaitBeforeStarting");
		keys.add("WaitForAllSpawned");
		keys.add("WaitForAllDead");
		keys.add("WaitBetweenSpawns");
		keys.add("WaitBetweenSpawnsAfterDeath");
		keys.add("TotalCurrency");
		keys.add("Name");
		keys.add("Support");
		keys.add("Where");
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
		} else if(key.equals("WaitForAllSpawned")) {
			this.WaitForAllSpawned = value;
		} else if(key.equals("WaitForAllDead")) {
			this.WaitForAllDead = value;
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
		} else if(key.equals("Where")) {
			this.Where.add(value);
		}
	}
	
	public WaveSpawn clone() {
		WaveSpawn ws = new WaveSpawn();
		ws.setMapName(this.MapName);
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
		ws.setWaitForAllSpawned(this.WaitForAllSpawned);
		ws.setWaitForAllDead(this.WaitForAllDead);
		ws.setWhere(this.Where);
		ws.setWaitBetweenSpawnsAfterDeath(this.WaitBetweenSpawnsAfterDeath);
		return ws;
	}
	
	public float getMinTime() {
		double v = TC.getWalkingTimeFromWaveSpawn(this.MapName, this);
		//System.out.println(v + " : " + (float)v);
		return (float)v;
	}
	
	public float getNoWalkTime() {
		float v = ((this.TotalCount / this.SpawnCount) * this.WaitBetweenSpawns) - this.WaitBetweenSpawns + this.WaitBeforeStarting;
		return v;
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
	
	public int[] getBotTypes() {
		int[] types = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		for(Spawner s : this.Spawner) {
			if(s.getClass() == TFBot.class) {
				TFBot bot = (TFBot) s;
				String c = bot.getClassName();
				if(c.equalsIgnoreCase("Scout")) {
					types[1] += this.TotalCount;
				} else if(c.equalsIgnoreCase("Soldier")) {
					types[2] += this.TotalCount;
				} else if(c.equalsIgnoreCase("Pyro")) {
					types[3] += this.TotalCount;
				} else if(c.equalsIgnoreCase("Demoman")) {
					if(bot.SubClass.equalsIgnoreCase("DemoKnight")) {
						types[10] += this.TotalCount;
					}
					types[4] += this.TotalCount;
				} else if(c.equalsIgnoreCase("HeavyWeapons") || c.equalsIgnoreCase("Heavy")) {
					types[5] += this.TotalCount;
				} else if(c.equalsIgnoreCase("Engineer")) {
					types[6] += this.TotalCount;
				} else if(c.equalsIgnoreCase("Medic")) {
					types[7] += this.TotalCount;
				} else if(c.equalsIgnoreCase("Sniper")) {
					types[8] += this.TotalCount;
				} else if(c.equalsIgnoreCase("Spy")) {
					types[9] += this.TotalCount;
				} else {
					types[0]++;
				}
			} else if (s.getClass() == Squad.class) {
				Squad squad = (Squad) s;
				int[] temp = squad.getBotTypes();
				int botsInSquad = 0;
				while(botsInSquad < this.TotalCount) {
					for(int i = 0; i < temp.length; i++) {
						botsInSquad += temp[i];
						if(botsInSquad > this.TotalCount) { //Check if we went over
							//Add enough to reach, no cluse how the game actually handles these
							//There was 8 that I found I think it's usually the random choice ones.
							types[i] += (temp[i] - (botsInSquad - this.TotalCount)); 
							break;
						} else {
							types[i] += temp[i];
						}
					} 
				}
			} else {
				types[0]++;
			}
		}
		return types;
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
	public String getWaitForAllSpawned() {
		return WaitForAllSpawned;
	}
	public void setWaitForAllSpawned(String waitForAllSpawned) {
		WaitForAllSpawned = waitForAllSpawned;
	}
	public String getWaitForAllDead() {
		return WaitForAllDead;
	}
	public void setWaitForAllDead(String waitForAllDead) {
		WaitForAllDead = waitForAllDead;
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
	public ArrayList<String> getWhere() {
		return Where;
	}
	public void setWhere(ArrayList<String> where) {
		Where = where;
	}
	public String getMapName() {
		return MapName;
	}
	public void setMapName(String mapName) {
		//System.out.println(mapName);
		MapName = mapName;
	}

	@Override
	public String toString() {
		return "WaveSpawn [MapName=" + MapName + ", Template=" + Template + ", TotalCount=" + TotalCount
				+ ", MaxActive=" + MaxActive + ", SpawnCount=" + SpawnCount + ", WaitBeforeStarting="
				+ WaitBeforeStarting + ", WaitForAllSpawned=" + WaitForAllSpawned + ", WaitForAllDead=" + WaitForAllDead
				+ ", WaitBetweenSpawns=" + WaitBetweenSpawns + ", WaitBetweenSpawnsAfterDeath="
				+ WaitBetweenSpawnsAfterDeath + ", TotalCurrency=" + TotalCurrency + ", Name=" + Name + ", Support="
				+ Support + ", Where=" + Where + "]";
	}

	
	
	

}
