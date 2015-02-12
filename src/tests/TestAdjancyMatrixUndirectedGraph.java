package tests;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import graph.AdjacencyMatrixUndirectedGraph;
import graph.GraphTools;

import org.junit.Test;

public class TestAdjancyMatrixUndirectedGraph {

	@Test
	public void testUndirectedMatrix() {
		int[][] matrice = {
				{0, 1, 1, 1},
				{1, 0, 0, 0},
				{1, 0, 0, 1},
				{1, 0, 1, 0}
		};
		
		AdjacencyMatrixUndirectedGraph graph = new AdjacencyMatrixUndirectedGraph(matrice);
		assertTrue("nbNodes pas correct", graph.getNbNodes() == matrice.length);
		assertTrue("nbEdges pas correct", graph.getNbEdges() == 4);
		
		int[] verifNeighbors = {1, 2, 3};
		assertArrayEquals(verifNeighbors, graph.getNeighbors(0));
		
		int[][] verifArray = graph.toAdjacencyMatrix();
		assertTrue("conversion en matrice incorrecte", GraphTools.equals(verifArray, matrice));
		
		//isEdge
		assertTrue("edge n existe pas", graph.isEdge(0, 1));
		assertFalse("edge existe", graph.isEdge(1, 2));
		
		//removeEdge
		graph.removeEdge(0, 1);
		assertFalse("Remove : edge non supprimé",  graph.isEdge(0, 1));
		
		//addEdge
		graph.addEdge(0, 1);
		assertTrue("Add : edge non ajouté",  graph.isEdge(0, 1));
	}

}
