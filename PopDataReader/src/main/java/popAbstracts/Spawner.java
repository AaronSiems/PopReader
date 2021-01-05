package popAbstracts;

//Squad, Tank, TFBot
public abstract class Spawner {
	
	protected String Name;
	protected int Health;
	
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public int getHealth() {
		return Health;
	}
	public void setHealth(int health) {
		Health = health;
	}

}
