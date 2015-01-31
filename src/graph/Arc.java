package graph;

public class Arc{
	private int first;
	private int second;
	
	public Arc(int f, int s) {
		this.first = f;
		this.second = s;
	}
	
	public int getFirst() {
		return first;
	}
	public void setFirst(int first) {
		this.first = first;
	}
	public int getSecond() {
		return second;
	}
	public void setSecond(int second) {
		this.second = second;
	}
}
