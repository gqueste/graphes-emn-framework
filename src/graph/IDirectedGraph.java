package graph;

public interface IDirectedGraph extends IGraph {
	//returns the number of arcs in the graph
	public int getNbArcs();
	//returns true if arc figures in the graph
	public boolean isArc(int from, int to);
	//removes arc if exists
	public void removeArc(int from, int to);
	//adds the arc if it is not already present in the graph, requires from != to
	public void addArc(int from, int to);
	//returns a new array reprensenting the successors of node x
	public int[] getSuccessors(int x);
	//returns a new array representing the predecessors of node x
	public int[] getPredecessors(int x);
	//Computes the inverse graph
	public IDirectedGraph computeInverse();

}
