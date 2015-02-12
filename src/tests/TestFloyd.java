package tests;

import static org.junit.Assert.*;
import graph.Floyd;
import graph.GraphTools;
import graph.ValuatedAdjacencyMatrixUndirectedGraph;

import org.junit.Test;

public class TestFloyd {

	@Test
	public void test() {
		int[][] matrice = {
				{Integer.MAX_VALUE	,Integer.MAX_VALUE	,Integer.MAX_VALUE	,Integer.MAX_VALUE	,4	},
				{Integer.MAX_VALUE	,Integer.MAX_VALUE	,Integer.MAX_VALUE	,10					,2},
				{Integer.MAX_VALUE	,Integer.MAX_VALUE	,Integer.MAX_VALUE	,8					,2},
				{Integer.MAX_VALUE	,10					,8					,Integer.MAX_VALUE	,6},
				{4					,2					,2					,6					,Integer.MAX_VALUE}
		};
		
		int[][] expected = {
				{0,6,6,10,4},
				{6,0,4,8,2},
				{6,4,0,8,2},
				{10,8,8,0,6},
				{4,2,2,6,0}
		};
		
		int[][] floyd = Floyd.floyd(new ValuatedAdjacencyMatrixUndirectedGraph(matrice));
		assertEquals(expected, floyd);
	}

}
