package tests;

import static org.junit.Assert.*;
import graph.Prim;
import graph.ValuatedAdjacencyMatrixUndirectedGraph;

import org.junit.Test;

public class TestPrim {

	@Test
	public void test() {
		int[][] matrice = {
				{ Integer.MAX_VALUE, 2, 6, 1, 4 },
				{ 2, Integer.MAX_VALUE, 5, 3, 2 },
				{ 6, 5, Integer.MAX_VALUE, 3, 2 },
				{ 1, 3, 3, Integer.MAX_VALUE, Integer.MAX_VALUE },
				{ 4, 2, 2, Integer.MAX_VALUE, Integer.MAX_VALUE }
		};

		int[][] expected = {
				{ Integer.MAX_VALUE, 2, Integer.MAX_VALUE, 1, Integer.MAX_VALUE },
				{ 2, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 2 },
				{ Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 2 },
				{ 1, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE },
				{ Integer.MAX_VALUE, 2, 2, Integer.MAX_VALUE, Integer.MAX_VALUE }
		};
		
		int[][] obtained = Prim.minimumSpanningTree(new ValuatedAdjacencyMatrixUndirectedGraph(matrice)).toAdjacencyMatrix();
		assertEquals(expected, obtained);
	}

}
