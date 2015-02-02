package graph;

import java.util.ArrayList;

public class Main {
	
	public static void main(String[] args) {
		//int[][] mat = GraphTools.generateGraphData(200, 19900, true);
		int[][] mat = GraphTools.generateGraphData(5, 4, true);
		GraphTools.show(mat);
		
		int[][] mat2 = GraphTools.generateGraphData(5, 4, false);
		IDirectedGraph graph = new AdjacencyListDirectedGraph(mat2);
		ArrayList<Integer> liste0 =  GraphTools.explorerProfondeurRec(graph);
		IDirectedGraph inverse = graph.computeInverse();
		System.out.println("*** Explore en profondeur l'inverse ***");
		ArrayList<Integer> liste = GraphTools.explorerProfondeurRec(inverse);
		System.out.println(liste0);
		System.out.println(liste);
	}

}
