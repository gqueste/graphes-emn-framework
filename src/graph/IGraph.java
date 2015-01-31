package graph;

public interface IGraph {
	//returns the number of nodes in the graph (referred to as the order of the graph)
	public int getNbNodes();
	//Returns the adjacency matrix reprensentation of the graph
	public int [][] toAdjacencyMatrix();
}
