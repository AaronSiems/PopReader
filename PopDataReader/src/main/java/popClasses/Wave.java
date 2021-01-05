package popClasses;

import java.util.ArrayList;
import java.util.Collection;

import com.scalified.tree.TreeNode;
import com.scalified.tree.multinode.ArrayMultiTreeNode;

import popAbstracts.Populators;
import utils.NodeData;

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
	
	
	//These have to go outside the method so the root.forEach(stuff) doesn't complain
	private NodeData tempNode;
	private String waitFor;
	private float tempTime; //(And n.forEach(stuff)
	private float time;
	public float getMinTime() {
		 time = 0;
		TreeNode<NodeData> root = new ArrayMultiTreeNode<NodeData>(new NodeData("root", 0f));
		for(int waveSpawn = 0; waveSpawn < this.WaveSpawn.size(); waveSpawn++) {
			WaveSpawn s = this.WaveSpawn.get(waveSpawn);
			//Not a support wave
			//Min time is longer than any concurrent spawns
			if(s.getSupport() != "") {
				continue;
			}
			
			if(s.getWaitForAllSpawned() == "" && s.getWaitForAllDead() != "") {
				waitFor = s.getWaitForAllDead();
			} else {
				waitFor = s.getWaitForAllSpawned();
			}
			TreeNode<NodeData> node = new ArrayMultiTreeNode<NodeData>(new NodeData(s.getName(), s.getMinTime()));
			if(waitFor == "") { //Spawns first
				root.add(node);
			} else { //Waits for another waveSpawn to end
				float largest = -1;				
				root.forEach(nd -> {
					if(nd.data().getName().equalsIgnoreCase(waitFor)) {
						//System.out.println("\t" + nd.data().getTime() + " : " + largest);
						if(nd.data().getTime() > largest) {
							tempNode = nd.data();
						}
					}
				});
				try {
					root.find(tempNode).add(node);
				} catch (NullPointerException e) {
					System.out.println("Null error in wave.getMinTime()");
					root.add(node);
				}
			}
		}
		
		//System.out.println(root.toString());
		
		Collection<? extends TreeNode<NodeData>> topLevelNodes =  root.subtrees();
		topLevelNodes.forEach(n -> {
			n.forEach(n2 ->{
				if(n2.isLeaf()) {
					tempTime = 0;
					TreeNode<NodeData> n3 = n2;
					tempTime += n3.data().getTime();
					while (!n3.isRoot()) {
						n3 = n3.parent();
						tempTime += n3.data().getTime();
					}
					
					if(tempTime > time) {
						time = tempTime;
					}
				}
			});
		});
		
		return time;
	}
	
	public int getTotalMoneyNoBonus() {
		int m = 0;
		for(WaveSpawn s : this.WaveSpawn) {
			m += s.getTotalCurrency();
		}
		return m;
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
	
	public int[] getBotTypes() {
		int[] types = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		for(WaveSpawn w : this.WaveSpawn) {
			if(w.getSupport().length() == 0) { //Skip support
				int[] temp = w.getBotTypes();
				for(int i = 0; i < temp.length; i++) {
					types[i] += temp[i];
				}
			}
		}
		return types;
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
