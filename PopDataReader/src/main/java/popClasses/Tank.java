package popClasses;

import java.util.ArrayList;

import popAbstracts.Spawner;

public class Tank extends Spawner {

	//Health (D:50,000) and name (D:"Tank") from spawner
	private float Speed; //default 75
	
	public Tank() {
		this.Health = 50000;
		this.Name = "Tank";
		this.Speed = 75;
	}
	
	public ArrayList<String> getKeyWords() {
		ArrayList<String> keys = new ArrayList<String>();
		keys.add("Health");
		keys.add("Name");
		keys.add("Speed");
		return keys;
	}
	
	public void setKeyWord(String key, String value) {
		if(key.equals("Health")) {
			int v = Integer.parseInt(value);
			this.Health = v;
		} else if(key.equals("Name")) {
			this.Name = value;
		} else if(key.equals("Speed")) {
			float v = Float.parseFloat(value);
			this.Speed = v;
		}
	}
	
	public Tank clone() {
		Tank t = new Tank();
		t.setHealth(this.Health);
		t.setName(this.Name);
		t.setSpeed(this.Speed);
		return t;
	}

	public float getSpeed() {
		return Speed;
	}

	public void setSpeed(float speed) {
		Speed = speed;
	}

	@Override
	public String toString() {
		return "Tank [Speed=" + Speed + ", Name=" + Name + ", Health=" + Health + "]";
	}
	
	
	
	
}
