package tests;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import graph.AdjacencyListDirectedGraph;
import graph.GraphTools;

import java.util.ArrayList;

import org.junit.Test;

public class TestAdjacencyListDirectedGraph {

	@Test
	public void testDirectedGraph() {
		int[][] matrice = {
				{0, 1, 0, 1},
				{0, 0, 1, 0},
				{1, 0, 0, 0},
				{0, 0, 1, 0}
		};
		ArrayList<Integer> nodeVerif = new ArrayList<Integer>();
		nodeVerif.add(0);
		nodeVerif.add(2);
		nodeVerif.add(3);
		nodeVerif.add(4);
		nodeVerif.add(5);

		ArrayList<Integer> succVerif = new ArrayList<Integer>();
		succVerif.add(1);
		succVerif.add(3);
		succVerif.add(2);
		succVerif.add(0);
		succVerif.add(2);
		succVerif.add(null);

		AdjacencyListDirectedGraph graph = new AdjacencyListDirectedGraph(matrice);
		assertTrue("succ non correct à génération", graph.getSucc().equals(succVerif));
		assertTrue("node non correct à génération", graph.getNode().equals(nodeVerif));
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
		ArrayList<Integer> verifRemoveNode = new ArrayList<Integer>();
		verifRemoveNode.add(0);
		verifRemoveNode.add(1);
		verifRemoveNode.add(2);
		verifRemoveNode.add(3);
		verifRemoveNode.add(4);

		ArrayList<Integer> verifRemoveSucc = new ArrayList<Integer>();
		verifRemoveSucc.add(3);
		verifRemoveSucc.add(2);
		verifRemoveSucc.add(0);
		verifRemoveSucc.add(2);
		verifRemoveSucc.add(null);

		assertTrue("Remove : Node incorrect", graph.getNode().equals(verifRemoveNode));
		assertTrue("Remove : Succ incorrect", graph.getSucc().equals(verifRemoveSucc));
		
		//addEdge
		graph.addArc(0, 1);
		assertTrue("Add : Node incorrect", graph.getNode().equals(nodeVerif));
		assertTrue("Add : Succ incorrect", graph.getSucc().equals(succVerif));
		
		//invert
		ArrayList<Integer> invertnodeVerif = new ArrayList<Integer>();
		invertnodeVerif.add(0);
		invertnodeVerif.add(1);
		invertnodeVerif.add(2);
		invertnodeVerif.add(4);
		invertnodeVerif.add(5);

		ArrayList<Integer> invertsuccVerif = new ArrayList<Integer>();
		invertsuccVerif.add(2);
		invertsuccVerif.add(0);
		invertsuccVerif.add(1);
		invertsuccVerif.add(3);
		invertsuccVerif.add(0);
		invertsuccVerif.add(null);
		
		AdjacencyListDirectedGraph invertedGraph = (AdjacencyListDirectedGraph) graph.computeInverse();
		assertEquals("invert : node non correct", invertnodeVerif, invertedGraph.getNode());
		assertEquals("invert : succ non correct", invertsuccVerif, invertedGraph.getSucc());
	}
}
