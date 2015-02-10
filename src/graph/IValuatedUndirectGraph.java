package graph;

public interface IValuatedUndirectGraph extends IUndirectedGraph {
	
	public void addEdge(int x, int y, int value);

	public int weightOfEdge(int x, int y);

}
