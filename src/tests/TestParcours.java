package tests;

import static org.junit.Assert.*;
import graph.AdjacencyListDirectedGraph;
import graph.IDirectedGraph;
import graph.Parcours;

import java.util.HashSet;
import java.util.List;

import org.junit.Test;

public class TestParcours {
	private int[][] graphMatrix = {
			{ 0, 0, 0, 0, 0, 0, 0 },
			{ 1, 0, 1, 1, 1, 0, 0 },
			{ 0, 0, 0, 1, 0, 0, 0 },
			{ 0, 1, 0, 0, 1, 0, 0 },
			{ 0, 0, 0, 0, 0, 1, 1 },
			{ 0, 0, 0, 0, 0, 0, 1 },
			{ 0, 0, 0, 0, 1, 0, 0 }
	};


	@Test
	public void testParcoursLargeur() {
		IDirectedGraph graph = new AdjacencyListDirectedGraph(graphMatrix);
		List<Integer> nodes = Parcours.parcoursLargeur(0, graph);
		assertEquals(1, nodes.size());
		assertEquals(0, (int)nodes.get(0));

		nodes = Parcours.parcoursLargeur(1, graph);
		assertEquals(7, nodes.size());
		assertEquals(1, (int)nodes.get(0));
		assertEquals(0, (int)nodes.get(1));
		assertEquals(2, (int)nodes.get(2));
		assertEquals(3, (int)nodes.get(3));
		assertEquals(4, (int)nodes.get(4));
		assertEquals(5, (int)nodes.get(5));
		assertEquals(6, (int)nodes.get(6));

		nodes = Parcours.parcoursLargeur(2, graph);
		assertEquals(7, nodes.size());
		assertEquals(2, (int)nodes.get(0));
		assertEquals(3, (int)nodes.get(1));
		assertEquals(1, (int)nodes.get(2));
		assertEquals(4, (int)nodes.get(3));
		assertEquals(0, (int)nodes.get(4));
		assertEquals(5, (int)nodes.get(5));
		assertEquals(6, (int)nodes.get(6));

		nodes = Parcours.parcoursLargeur(3, graph);
		assertEquals(7, nodes.size());
		assertEquals(3, (int)nodes.get(0));
		assertEquals(1, (int)nodes.get(1));
		assertEquals(4, (int)nodes.get(2));
		assertEquals(0, (int)nodes.get(3));
		assertEquals(2, (int)nodes.get(4));
		assertEquals(5, (int)nodes.get(5));
		assertEquals(6, (int)nodes.get(6));

		nodes = Parcours.parcoursLargeur(4, graph);
		assertEquals(3, nodes.size());
		assertEquals(4, (int)nodes.get(0));
		assertEquals(5, (int)nodes.get(1));
		assertEquals(6, (int)nodes.get(2));

		nodes = Parcours.parcoursLargeur(5, graph);
		assertEquals(3, nodes.size());
		assertEquals(5, (int)nodes.get(0));
		assertEquals(6, (int)nodes.get(1));
		assertEquals(4, (int)nodes.get(2));

		nodes = Parcours.parcoursLargeur(6, graph);
		assertEquals(3, nodes.size());
		assertEquals(6, (int)nodes.get(0));
		assertEquals(4, (int)nodes.get(1));
		assertEquals(5, (int)nodes.get(2));
	}

	@Test
	public void parcoursProfondeurTest() {
		Parcours parcours = new Parcours();
		IDirectedGraph graph = new AdjacencyListDirectedGraph(graphMatrix);
		Parcours.ResultProfondeur rp = parcours.parcoursProfondeur(graph);
		assertEquals(1 , rp.debut[0]);
		assertEquals(2 , rp.fin[0]);
		assertEquals(3 , rp.debut[1]);
		assertEquals(4 , rp.debut[2]);
		assertEquals(5 , rp.debut[3]);
		assertEquals(6 , rp.debut[4]);
		assertEquals(7 , rp.debut[5]);
		assertEquals(8 , rp.debut[6]);
		assertEquals(9 , rp.fin[6]);
		assertEquals(10, rp.fin[5]);
		assertEquals(11, rp.fin[4]);
		assertEquals(12, rp.fin[3]);
		assertEquals(13, rp.fin[2]);
		assertEquals(14, rp.fin[1]);
	}

	@Test
	public void composantesFortementConnexesTest() {
		Parcours parcours = new Parcours();
		IDirectedGraph graph = new AdjacencyListDirectedGraph(graphMatrix);
		List<HashSet<Integer>> cfc = parcours.composantesFortementConnexes(graph);
		assertEquals(3, cfc.size());

		assertEquals(3, cfc.get(0).size());
		assertTrue(cfc.get(0).contains(1));
		assertTrue(cfc.get(0).contains(2));
		assertTrue(cfc.get(0).contains(3));

		assertEquals(3, cfc.get(1).size());
		assertTrue(cfc.get(1).contains(4));
		assertTrue(cfc.get(1).contains(5));
		assertTrue(cfc.get(1).contains(6));

		assertEquals(1, cfc.get(2).size());
		assertTrue(cfc.get(2).contains(0));
	}
}
