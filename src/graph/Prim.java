package graph;

import java.util.HashSet;
import java.util.Set;

public class Prim {

	class Edge {
		public final int x;
		public final int y;
		public final int weight;

		public Edge(int x, int y, int weight) {
			this.x = x;
			this.y = y;
			this.weight = weight;
		}
	}

	private Edge getSmallestEdge(IValuatedUndirectGraph graph, IValuatedUndirectGraph tree, Set<Integer> treeNodes) {
		int x = -1, y = -1;
		int minWeight = Integer.MAX_VALUE;
		for (int i = 0 ; i < graph.getNbNodes() ; ++i)
			if (!treeNodes.contains(i))
				for (int neighbor : graph.getNeighbors(i))
					if (treeNodes.contains(neighbor) &&
							graph.weightOfEdge(i, neighbor) <= minWeight) {
						x = neighbor;
						y = i;
						minWeight = graph.weightOfEdge(i, neighbor);
					}

		return new Edge(x, y, minWeight);
	}

	public IValuatedUndirectGraph apply(IValuatedUndirectGraph graph) {
		IValuatedUndirectGraph tree = new ValuatedAdjacencyMatrixUndirectedGraph(new int[graph.getNbNodes()][graph.getNbNodes()]);
		Set<Integer> treeNodes = new HashSet<Integer>();
		treeNodes.add(0);
		for (int i = 0 ; i < tree.getNbNodes() - 1 ; ++i) {
			Edge edge = getSmallestEdge(graph, tree, treeNodes);
			treeNodes.add(edge.y);
			tree.addEdge(edge.x, edge.y, edge.weight);
		}
		return tree;
	}
}
