package graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;


public class GraphTools {

	public static int[][] generateGraphData(int n, int m, boolean s) {
		int[][] ret = new int[n][n];

		ArrayList<Arc> arcs = new ArrayList<Arc>();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if(j != i) {
					arcs.add(new Arc(i, j));
				}
			}
		}
		Collections.shuffle(arcs);
		for (int i = 0; i < m; i++) {
			Arc toInsert = arcs.get(i);
			ret[toInsert.getFirst()][toInsert.getSecond()] = 1;
			if (s) {
				ret[toInsert.getSecond()][toInsert.getFirst()] = 1;
			}
		}

		return ret;
	}

	public static int[][] generateValuatedGraphData(int n, int m, boolean s, int min, int max) {
		Random rand = new Random(0);
		int[][] ret = new int[n][n];
		
		for (int i = 0; i < ret.length; i++) {
			for (int j = 0; j < ret.length; j++) {
				ret[i][j] = Integer.MAX_VALUE;
			}
		}

		ArrayList<Arc> arcs = new ArrayList<Arc>();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if(j != i) {
					arcs.add(new Arc(i, j));
				}
			}
		}
		Collections.shuffle(arcs);
		for (int i = 0; i < m; i++) {
			Arc toInsert = arcs.get(i);
			int poids = rand.nextInt(max+1-min) + min;
			ret[toInsert.getFirst()][toInsert.getSecond()] = poids;
			if (s) {
				ret[toInsert.getSecond()][toInsert.getFirst()] = poids;
			}
		}

		return ret;
	}

	public static String arrayToString(int[] tab) {
		ArrayList<Integer> a = new ArrayList<Integer>();
		for (int i = 0 ; i < tab.length ; i++) {
			a.add(tab[i]);
		}
		return a.toString();
	}

	public static void show(int[][] matrice){
		System.out.println();
		for (int i=0 ; i<matrice.length ; i++){
			for (int j=0 ; j<matrice[i].length ; j++){
				System.out.print(matrice[i][j] + " ");
			}
			System.out.println();
		}
	}

	public static void show(int[] array) {
		System.out.println();
		for (int i = 0; i < array.length; i++) {
			System.out.println(array[i] + " ");
		}
		System.out.println();
	}

	public static boolean equals(int[][] array1, int[][] array2) {
		boolean ret = true;
		if(array1.length != array2.length) {
			ret = false;
		}
		else {
			for (int i = 0; i < array1.length; i++) {
				if (!Arrays.equals(array1[i], array2[i])) {
					ret = false;
					break;
				}
			}
		}
		return ret;
	}
}
