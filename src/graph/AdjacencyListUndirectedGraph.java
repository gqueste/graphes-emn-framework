package graph;

import java.util.ArrayList;

public class AdjacencyListUndirectedGraph implements IUndirectedGraph {

	private ArrayList<Integer> node;
	private ArrayList<Integer> succ;

	public AdjacencyListUndirectedGraph(int[][] matrice) {

		this.node = new ArrayList<Integer>();
		this.succ = new ArrayList<Integer>();

		int indiceSucc = 0;
		for (int i = 0; i < matrice.length; i++) {
			node.add(indiceSucc);
			for (int j = 0; j < matrice.length; j++) {
				if (matrice[i][j] == 1) {
					succ.add(j);
					indiceSucc ++;
				}
			}
		}
		this.node.add(indiceSucc);
		this.succ.add(null);
	}
	
	public AdjacencyListUndirectedGraph(IUndirectedGraph graph) {
		this.node = new ArrayList<Integer>();
		this.succ = new ArrayList<Integer>();
		
		int[][] mat = graph.toAdjacencyMatrix();
		AdjacencyListUndirectedGraph conv = new AdjacencyListUndirectedGraph(mat);
		
		this.node = conv.getNode();
		this.succ = conv.getSucc();
	}

	public ArrayList<Integer> getNode() {
		return node;
	}

	public void setNode(ArrayList<Integer> node) {
		this.node = node;
	}

	public ArrayList<Integer> getSucc() {
		return succ;
	}

	public void setSucc(ArrayList<Integer> succ) {
		this.succ = succ;
	}

	@Override
	public int getNbNodes() {
		return this.node.size() - 1;
	}

	@Override
	public int[][] toAdjacencyMatrix() {
		int[][] ret = new int[getNbNodes()][getNbNodes()];
		for (int i = 0; i < getNbNodes(); i++) {
			int[] neigbors = getNeighbors(i);
			for (int j = 0; j < neigbors.length; j++) {
				ret[i][neigbors[j]] = 1;
			}			
		}
		return ret;
	}

	@Override
	public int getNbEdges() {
		return (this.succ.size() - 1) / 2;
	}

	@Override
	public boolean isEdge(int x, int y) {
		boolean ret = false;
		int indiceSuccStart = this.node.get(x);
		int indiceSuccStop = this.node.get(x+1);
		for (int i = 0; i < indiceSuccStop - indiceSuccStart; i++) {
			if (this.succ.get(indiceSuccStart + i) == y) {
				ret = true;
				break;
			}
		}
		return ret;
	}

	@Override
	public void removeEdge(int x, int y) {
		if(isEdge(x, y)) {
			for (int i = 0; i < node.get(x + 1) - node.get(x); i++) {
				if (succ.get(node.get(x) + i) == y) {
					succ.remove(node.get(x) + i);
					break;
				}
			}
			
			for (int i = 1; i < node.size() - x; i++) {
				node.set(x + i, node.get(i + x) - 1);
			}
			removeEdge(y, x);
		}
	}

	@Override
	public void addEdge(int x, int y) {
		if(!isEdge(x, y)) {
			for (int i = 1; i < node.size() - x; i++) {
				node.set(i + x, node.get(x+i) + 1);
			}
			
			Integer save = null;
			Integer toPut = y;
			for (int i = 0; i < succ.size() - node.get(x); i++) {
				save = succ.get(node.get(x) + i);
				succ.set(node.get(x) + i, toPut);
				toPut = save;
			}
			succ.add(null);
			addEdge(y, x);
		}
	}

	@Override
	public int[] getNeighbors(int x) {
		ArrayList<Integer> neighbors = new ArrayList<Integer>();
		for (int i = 0; i < node.get(x+1) - node.get(x); i++) {
			neighbors.add(succ.get(node.get(x) + i));
		}
		
		int[] ret = new int[neighbors.size()];
		for (int i = 0; i < ret.length; i++) {
			ret[i] = neighbors.get(i);
		}
		return ret;
	}
}
