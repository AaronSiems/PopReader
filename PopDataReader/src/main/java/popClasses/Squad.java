package popClasses;

import java.util.ArrayList;

import popAbstracts.Spawner;

public class Squad extends Spawner{
	
	private float FormationSize; //Default -1.0
	private Boolean ShouldPreserveSquad; //Default 0
	private ArrayList<Spawner> Spawner;
	
	public Squad() {
		this.FormationSize = -1.0f;
		this.ShouldPreserveSquad = false;
		this.Spawner = new ArrayList<Spawner>();
	}
	
	public ArrayList<String> getKeyWords() {
		ArrayList<String> keys = new ArrayList<String>();
		keys.add("FormationSize");
		keys.add("ShoulPreserveSquad");
		keys.add("Name");
		return keys;
	}
	
	public void setKeyWord(String key, String value) {
		if(key.equals("FormationSize")) {
			float v = Float.parseFloat(value);
			this.FormationSize = v;
		} else if(key.equals("ShoulPreserveSquad")) {
			if(value.equalsIgnoreCase("true") || value.equals("1") || value.equalsIgnoreCase("yes")) {
				this.ShouldPreserveSquad = true;
			} else if(value.equalsIgnoreCase("false") || value.equals("0") || value.equalsIgnoreCase("no")) {
				this.ShouldPreserveSquad = false;
			}
		} else if(key.equals("Name")) {
			this.Name = value;
		}
	}
	
	public Squad clone() {
		Squad s = new Squad();
		s.setFormationSize(this.FormationSize);
		s.setName(this.Name);
		s.setShouldPreserveSquad(this.ShouldPreserveSquad);
		s.setSpawner(this.Spawner);
		return s;
	}
	
	public int getTotalBotHealth() {
		int hp = 0;
		for(Spawner s : this.Spawner) {
			if(s.getClass() == TFBot.class) {
				hp += s.getHealth();
			}
		}
		
		return hp;
	}
	
	/**
	 * @return 11 length array for all class types; 0 holding unknown/tanks 11 holding demoknights
	 */
	public int[] getBotTypes() {
		int[] types = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		for(Spawner s : this.Spawner) {
			if(s.getClass() == TFBot.class) {
				TFBot bot = (TFBot) s;
				String c = bot.getClassName();
				if(c.equalsIgnoreCase("Scout")) {
					types[1]++;
				} else if(c.equalsIgnoreCase("Soldier")) {
					types[2]++;
				} else if(c.equalsIgnoreCase("Pyro")) {
					types[3]++;
				} else if(c.equalsIgnoreCase("Demoman")) {
					if(bot.SubClass.equalsIgnoreCase("DemoKnight")) {
						types[10]++;
					}
					types[4]++;
				} else if(c.equalsIgnoreCase("HeavyWeapons") || c.equalsIgnoreCase("Heavy")) {
					types[5]++;
				} else if(c.equalsIgnoreCase("Engineer")) {
					types[6]++;
				} else if(c.equalsIgnoreCase("Medic")) {
					types[7]++;
				} else if(c.equalsIgnoreCase("Sniper")) {
					types[8]++;
				} else if(c.equalsIgnoreCase("Spy")) {
					types[9]++;
				} else {
					types[0]++;
				}
			} else {
				types[0]++;
			}
		}
		return types;
	}
	
	public float getFormationSize() {
		return FormationSize;
	}
	public void setFormationSize(float formationSize) {
		FormationSize = formationSize;
	}
	public Boolean getShouldPreserveSquad() {
		return ShouldPreserveSquad;
	}
	public void setShouldPreserveSquad(Boolean shouldPreserveSquad) {
		ShouldPreserveSquad = shouldPreserveSquad;
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
		return "\n\t\tSquad [FormationSize=" + FormationSize + ", ShouldPreserveSquad=" + ShouldPreserveSquad + ", Spawner="
				+ Spawner + "]";
	}
	
	

}
