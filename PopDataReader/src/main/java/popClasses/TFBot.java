package popClasses;

import java.util.ArrayList;

import popAbstracts.Spawner;
import popEnums.Skill;

public class TFBot extends Spawner {
	
	//Health (D:-1) and name (D:class name) from spawner
	protected String Class;
	protected float Scale; //Default -1.0 TODO should default to class hp
	protected Skill Skill; //Default Easy
	protected String Template;
	
	public TFBot() {
		this.Health = 0;
		//this.Class = "Scout";
		//this.Name = this.Class;
		this.Scale = -1.0f;
		this.Skill = popEnums.Skill.Easy;
	}
	
	public ArrayList<String> getKeyWords() {
		ArrayList<String> keys = new ArrayList<String>();
		keys.add("Health ");
		keys.add("Name ");
		keys.add("Class ");
		keys.add("Scale");
		//Begin skill
		keys.add("Easy");
		keys.add("Normal");
		keys.add("Hard");
		keys.add("Expert");
		//End skill
		keys.add("Template");
		return keys;
	}
	
	public void setKeyWord(String key, String value) {
		if(key.equals("Health ")) {
			int v = Integer.parseInt(value);
			this.Health = v;
		} else if(key.equals("Name ")) {
			this.Name = value;
		} else if(key.equalsIgnoreCase("Class ")) {
			if(this.Health == 0) {
				if(value.equalsIgnoreCase("Scout")) {
					this.Health = 125;
				} else if(value.equalsIgnoreCase("Soldier")) {
					this.Health = 200;
				} else if(value.equalsIgnoreCase("Pyro")) {
					this.Health = 175;
				} else if(value.equalsIgnoreCase("Demoman")) {
					this.Health = 175;
				} else if(value.equalsIgnoreCase("HeavyWeapons") || value.equalsIgnoreCase("Heavy")) {
					this.Health = 300;
				} else if(value.equalsIgnoreCase("Engineer")) {
					this.Health = 125;
				} else if(value.equalsIgnoreCase("Medic")) {
					this.Health = 150;
				} else if(value.equalsIgnoreCase("Sniper")) {
					this.Health = 125;
				} else if(value.equalsIgnoreCase("Spy")) {
					this.Health = 125;
				}
			}
			this.Class = value;
		} else if(key.equals("Scale")) {
			float v = Float.parseFloat(value);
			this.Scale = v;
		} else if(key.equals("Easy")) {
			this.Skill = popEnums.Skill.Easy;
		} else if(key.equals("Normal")) {
			this.Skill = popEnums.Skill.Normal;
		} else if(key.equals("Hard")) {
			this.Skill = popEnums.Skill.Hard;
		} else if(key.equals("Expert")) {
			this.Skill = popEnums.Skill.Expert;
		} else if(key.equals("Template")) {
			//TODO maybe
			this.Template = value;
		}
	}
	
	public TFBot clone() {
		TFBot bot = new TFBot();
		bot.setClass(this.Class);
		bot.setHealth(this.Health);
		bot.setName(this.Name);
		bot.setScale(this.Scale);
		bot.setSkill(this.Skill);
		bot.setTemplate(this.Template);
		
		return bot;
	}
	
	public String getClassName() {
		return Class;
	}
	public void setClass(String class1) {
		Class = class1;
	}
	public float getScale() {
		return Scale;
	}
	public void setScale(float scale) {
		Scale = scale;
	}
	public Skill getSkill() {
		return Skill;
	}
	public void setSkill(Skill skill) {
		Skill = skill;
	}
	public String getTemplate() {
		return Template;
	}
	public void setTemplate(String template) {
		Template = template;
	}

	@Override
	public String toString() {
		return "\n\t\t\t TFBot [Name=" + Name + ", Health=" + Health + ", Class=" + Class + ", Scale=" + Scale + ", Skill=" + Skill + ", Template=" + Template + "]";
	}
	

}