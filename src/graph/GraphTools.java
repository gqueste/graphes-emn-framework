package graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;


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
		
		//ou créez tous les arcs dans 1 tableau
		//puis shuffle et on prend les n premiers
		//tirage sans remise à faire
		/*
		while (m != 0){
			int x = (int) Math.floor(Math.random()*n);
			int y = (int) Math.floor(Math.random()*n);
			if(ret[x][y] == 0 && x != y){
				ret[x][y] = 1;
				if (s){
					ret[y][x] = 1;
				}
				m--;
			}
		}*/
		return ret;
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
