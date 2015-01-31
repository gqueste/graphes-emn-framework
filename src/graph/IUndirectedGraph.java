package graph;

public interface IUndirectedGraph extends IGraph {
	//Returns the number of edges in the graph
	public int getNbEdges();
	//Returns true if there is an edge between x and y
	public boolean isEdge(int x, int y);
	//removes edge if exists
	public void removeEdge(int x, int y);
	//add edge if not already present. requires x != y
	public void addEdge(int x, int y);
	//returns a new int array representing neighbors of node x
	public int[] getNeighbors(int x);
}
