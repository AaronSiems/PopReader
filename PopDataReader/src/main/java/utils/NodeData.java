package utils;

public class NodeData {
	
	private String name;
	private Float time;
	
	
	public NodeData(String name, Float time) {
		super();
		this.name = name;
		this.time = time;
	}
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Float getTime() {
		return time;
	}
	public void setTime(Float time) {
		this.time = time;
	}


	@Override
	public String toString() {
		return "NodeData [name=" + name + ", time=" + time + "]";
	}
	
	

}
