package tests;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import graph.AdjacencyListDirectedGraph;
import graph.AdjacencyMatrixDirectedGraph;
import graph.GraphTools;

import org.junit.Test;

public class TestAdjancyMatrixDirectedGraph {

	@Test
	public void testDirectedMatrix() {
		int[][] matrice = {
				{0, 1, 0, 1},
				{0, 0, 1, 0},
				{1, 0, 0, 0},
				{0, 0, 1, 0}
		};
		
		AdjacencyMatrixDirectedGraph graph = new AdjacencyMatrixDirectedGraph(matrice);
		assertTrue("nbNodes pas correct", graph.getNbNodes() == matrice.length);
		assertTrue("nbEdges pas correct", graph.getNbArcs() == 5);

		int[] successors = {1, 3};
		assertArrayEquals("successors non correct",successors, graph.getSuccessors(0));

		int[] verifPredecessors = {2};
		assertArrayEquals("predecessors non correct",verifPredecessors, graph.getPredecessors(0));

		int[][] verifArray = graph.toAdjacencyMatrix();
		assertTrue("conversion en matrice incorrecte", GraphTools.equals(verifArray, matrice));

		//isArc
		assertTrue("arc n existe pas", graph.isArc(0, 1));
		assertFalse("arc existe", graph.isArc(0, 2));

		//removeArc
		graph.removeArc(0, 1);
		assertFalse("Remove : arc non supprimé",  graph.isArc(0, 1));
		
		//addEdge
		graph.addArc(0, 1);
		assertTrue("Add : arc ajouté", graph.isArc(0, 1));
		
		//invert
		AdjacencyListDirectedGraph graphVerif = new AdjacencyListDirectedGraph(matrice);
		int[][] matriceVerifInvert = graphVerif.computeInverse().toAdjacencyMatrix();
		assertEquals("invert : inversion échouée", matriceVerifInvert, graph.computeInverse().toAdjacencyMatrix());;
	}
}
