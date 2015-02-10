package graph;

import java.util.ArrayList;

//creation du minimum spanning tree
public class Prim {

	public static IUndirectedGraph minimumSpanningTree(IValuatedUndirectGraph g) {
		// Version 1 : g est forcément connexe
		// version 2 : g pas forcément connexe ; 
		// 		on retourne lors l'arbre minimal
		//		couvrant la composante connexe contenant le sommet initial (ici 0)
		boolean encoreUnVoisinNonAtteint = true; // version 2 : g pas nécessairement connexe
		int n = g.getNbNodes();
		IValuatedUndirectGraph st = new ValuatedAdjacencyMatrixUndirectedGraph(n); // st pour spanning tree
		ArrayList<Integer> sommetsAtteints = new ArrayList<Integer>();
		int xMin=0;
		int yMin=0;
		sommetsAtteints.add(0); // premier sommet
		//	while (sommetsAtteints.size() < n) { // Version 1 
		while (encoreUnVoisinNonAtteint) { // version 2
			encoreUnVoisinNonAtteint = false; // version 2
			int valeur = Integer.MAX_VALUE;
			for (int x : sommetsAtteints) {
				for (int y : g.getNeighbors(x)) {
					//		    if (!sommetsAtteints.contains(y) && g.getValue(x, y) < valeur) { // version 1
					if (!sommetsAtteints.contains(y)) { // version 2
						encoreUnVoisinNonAtteint = true; // il reste au moins un voisin non encore atteint (y) ; version 2
						if (g.weightOfEdge(x, y) < valeur) { // version 2
							valeur = g.weightOfEdge(x, y);
							xMin = x;
							yMin = y;
						} 
					}
				}
			}
			if (encoreUnVoisinNonAtteint) { // version 2
				sommetsAtteints.add(yMin);
				st.addEdge(xMin, yMin, g.weightOfEdge(xMin, yMin));
			}

		}
		return st;

	}

	public static void main(String[] args) {
		int[][] data = GraphTools.generateValuatedGraphData(5, 8, true, 1, 10);
		IValuatedUndirectGraph g = new ValuatedAdjacencyMatrixUndirectedGraph(data);

		GraphTools.show(g.toAdjacencyMatrix());
		
		for (int x = 0 ; x < g.getNbNodes() ; x++) {
			System.out.println(x + ":" + GraphTools.arrayToString(g.getNeighbors(x)));
		}

		GraphTools.show(minimumSpanningTree(g).toAdjacencyMatrix());
	}
}
