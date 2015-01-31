package tests;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import graph.AdjacencyListUndirectedGraph;
import graph.GraphTools;

import java.util.ArrayList;

import org.junit.Test;

public class TestAdjacencyListUndirectedGraph {	

	@Test
	public void testConstructeur() {
		int[][] matrice = {
				{0, 1, 1, 1},
				{1, 0, 0, 0},
				{1, 0, 0, 1},
				{1, 0, 1, 0}
		};
		ArrayList<Integer> nodeVerif = new ArrayList<Integer>();
		nodeVerif.add(0);
		nodeVerif.add(3);
		nodeVerif.add(4);
		nodeVerif.add(6);
		nodeVerif.add(8);
		
		ArrayList<Integer> succVerif = new ArrayList<Integer>();
		succVerif.add(1);
		succVerif.add(2);
		succVerif.add(3);
		succVerif.add(0);
		succVerif.add(0);
		succVerif.add(3);
		succVerif.add(0);
		succVerif.add(2);
		succVerif.add(null);
		
		AdjacencyListUndirectedGraph graph = new AdjacencyListUndirectedGraph(matrice);
		assertTrue("succ non correct à génération", graph.getSucc().equals(succVerif));
		assertTrue("node non correct à génération", graph.getNode().equals(nodeVerif));
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
		ArrayList<Integer> verifRemoveNode = new ArrayList<Integer>();
		verifRemoveNode.add(0);
		verifRemoveNode.add(2);
		verifRemoveNode.add(2);
		verifRemoveNode.add(4);
		verifRemoveNode.add(6);
		
		ArrayList<Integer> verifRemoveSucc = new ArrayList<Integer>();
		verifRemoveSucc.add(2);
		verifRemoveSucc.add(3);
		verifRemoveSucc.add(0);
		verifRemoveSucc.add(3);
		verifRemoveSucc.add(0);
		verifRemoveSucc.add(2);
		verifRemoveSucc.add(null);
		
		assertTrue("Remove : Node incorrect", graph.getNode().equals(verifRemoveNode));
		assertTrue("Remove : Succ incorrect", graph.getSucc().equals(verifRemoveSucc));
		
		//addEdge
		graph.addEdge(0, 1);
		assertTrue("Add : Node incorrect", graph.getNode().equals(nodeVerif));
		assertTrue("Add : Succ incorrect", graph.getSucc().equals(succVerif));
	}
	
	
}
