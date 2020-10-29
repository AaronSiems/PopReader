package popClasses;

import java.util.ArrayList;

import popAbstracts.Populators;
import popAbstracts.Spawner;
import popEnums.Objective;

public class Mission extends Populators {
	
	private Objective Objective;
	private float InitialCooldown; //Default 0.0
	private float CooldownTime; //Default 0.0
	private int BeginAtWave; //Default 0
	private int RunForThisManyWaves; //Default 0
	private int DesiredCount; //Default 0;
	private Spawner Spawner;
	
	public Mission() {
		this.InitialCooldown = 0.0f;
		this.CooldownTime = 0.0f;
		this.BeginAtWave = 0;
		this.RunForThisManyWaves = 0;
		this.DesiredCount = 0;
	}
	
	public ArrayList<String> getKeyWords() {
		ArrayList<String> keys = new ArrayList<String>();
		//Begin objective types
		keys.add("DestroySentries");
		keys.add("SeekAndDestroy");
		keys.add("Sniper");
		keys.add("Spy");
		keys.add("Engineer");
		//End objective types
		keys.add("InitialCooldown");
		keys.add("CooldownTime");
		keys.add("BeginAtWave");
		keys.add("RunForThisManyWaves");
		keys.add("DesiredCount");
		return keys;
	}
	
	public void setKeyWord(String key, String value) {
		if(key.equals("DestroySentries")) {
			this.Objective = popEnums.Objective.DestroySentries;
		} else if(key.equals("SeekAndDestroy")) {
			this.Objective = popEnums.Objective.SeekAndDestroy;
		} else if(key.equals("Sniper")) {
			this.Objective = popEnums.Objective.Sniper;
		} else if(key.equals("Spy")) {
			this.Objective = popEnums.Objective.Spy;
		} else if(key.equals("Engineer")) {
			this.Objective = popEnums.Objective.Engineer;
		} else if(key.equals("InitialCooldown")) {
			float f = Float.parseFloat(value);
			this.InitialCooldown = f;
		} else if(key.equals("CooldownTime")) {
			float f = Float.parseFloat(value);
			this.CooldownTime = f;
		} else if(key.equals("BeginAtWave")) {
			int v = Integer.parseInt(value);
			this.BeginAtWave = v;
		} else if(key.equals("RunForThisManyWaves")) {
			int v = Integer.parseInt(value);
			this.RunForThisManyWaves = v;
		} else if(key.equals("DesiredCount")) {
			int v = Integer.parseInt(value);
			this.DesiredCount = v;
		}
	}
	
	public Mission clone() {
		Mission m = new Mission();
		m.BeginAtWave = this.BeginAtWave;
		m.CooldownTime = this.CooldownTime;
		m.DesiredCount = this.DesiredCount;
		m.InitialCooldown = this.InitialCooldown;
		m.Objective = this.Objective;
		m.RunForThisManyWaves = this.RunForThisManyWaves;
		m.Spawner = this.Spawner;
		return m;
	}
	
	public Objective getObjective() {
		return Objective;
	}
	public void setObjective(Objective objective) {
		Objective = objective;
	}
	public float getInitialCooldown() {
		return InitialCooldown;
	}
	public void setInitialCooldown(float initialCooldown) {
		InitialCooldown = initialCooldown;
	}
	public float getCooldownTime() {
		return CooldownTime;
	}
	public void setCooldownTime(float cooldownTime) {
		CooldownTime = cooldownTime;
	}
	public int getBeginAtWave() {
		return BeginAtWave;
	}
	public void setBeginAtWave(int beginAtWave) {
		BeginAtWave = beginAtWave;
	}
	public int getRunForThisManyWaves() {
		return RunForThisManyWaves;
	}
	public void setRunForThisManyWaves(int runForThisManyWaves) {
		RunForThisManyWaves = runForThisManyWaves;
	}
	public int getDesiredCount() {
		return DesiredCount;
	}
	public void setDesiredCount(int desiredCount) {
		DesiredCount = desiredCount;
	}
	public Spawner getSpawner() {
		return Spawner;
	}
	public void setSpawner(Spawner spawner) {
		Spawner = spawner;
	}

	@Override
	public String toString() {
		return "\n Mission [Objective=" + Objective + ", InitialCooldown=" + InitialCooldown + ", CooldownTime="
				+ CooldownTime + ", BeginAtWave=" + BeginAtWave + ", RunForThisManyWaves=" + RunForThisManyWaves
				+ ", DesiredCount=" + DesiredCount + ", Spawner=" + Spawner + "]";
	}

	
}
