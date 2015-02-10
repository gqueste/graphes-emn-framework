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
	
	public static int explorerSommet(int s, ArrayList<Integer> atteint, IUndirectedGraph graphe, int compteur, int[] debut , int[] fin) {
		int res = compteur;
		for (int t : graphe.getNeighbors(s)) {
			if (!atteint.contains(t)) {
				atteint.add(t);
				res ++ ;
				debut[t] = res;
				System.out.println("debut de "+ t + " : " + debut[t]);
				res = explorerSommet(t,atteint, graphe,res, debut,fin);
				fin[t] = res;
				System.out.println("fin de "+ t + " : " + fin[t]);
			}
		}
		res++;
		return res;
	} 
	
	
	public static int explorerSommet(int s, ArrayList<Integer> atteint, IDirectedGraph graphe, int compteur, int[] debut , int[] fin) {
		int res = compteur;
		for (int t : graphe.getSuccessors(s)) {
			if (!atteint.contains(t)) {
				atteint.add(t);
				res ++ ;
				debut[t] = res;
				System.out.println("debut de " + t + " : " + debut[t]);
				res = explorerSommet(t,atteint, graphe,res,debut,fin);
				fin[t] = res;
				System.out.println("fin de "+ t +" : " + fin[t]);
			}
		}
		res++;
		return res;
	} 
	
	/**
	 * (Recursif) Exploration en profondeur d'un graphe non oriente
	 * @param graphe le graphe que vous voulez explorer
	 * @param n le noeud à partir duquel vous voulez commencer l'exploration
	 * @return le parcours du graphe
	 */
	public static ArrayList<Integer> explorerProfondeurRec(IUndirectedGraph graphe) {
		int[] debut = new int[graphe.getNbNodes()];
		int[] fin = new int[graphe.getNbNodes()];
		int compteur = 0 ;
		ArrayList<Integer> atteint = new ArrayList<Integer>();
		int i = 0 ;
		while(i<graphe.getNbNodes() && atteint.size()<graphe.getNbNodes()){
			if(!atteint.contains(i)){
				atteint.add(i);
				debut[i] = compteur;
				compteur++;
				System.out.println("debut de " + i + " : " + debut[i]);
				for (int s : graphe.getNeighbors(i)) {
					if (!atteint.contains(s)) {
						atteint.add(s);
						debut[s] = compteur;
						System.out.println("d�but de "+ s +" : " + debut[s]);
						compteur = explorerSommet(s, atteint, graphe,compteur,debut,fin);
						fin[s] = compteur;
						compteur++;
						System.out.println("fin de " + s + " : " + fin[s]);
					}
				}
				fin[i] = compteur;
				compteur++;
				System.out.println("fin de " + i + " : "+ fin[i]);
			}
			i++;
		}

		return atteint;
	}
	/**
	 * (Recursif) Exploration en profondeur d'un graphe oriente
	 * @param graphe le graphe que vous voulez explorer
	 * @param n le noeud à partir duquel vous voulez commencer l'exploration
	 * @return le parcours du graphe
	 */
	public static ArrayList<Integer> explorerProfondeurRec(IDirectedGraph graphe) {
		int[] debut = new int[graphe.getNbNodes()];
		int[] fin = new int[graphe.getNbNodes()];
		int compteur = 0 ;
		ArrayList<Integer> atteint = new ArrayList<Integer>();
		int i = 0;
		while(i < graphe.getNbNodes() || atteint.size() < graphe.getNbNodes()){
			if(!atteint.contains(i)){
				debut[i] = compteur;
				compteur ++;			
				System.out.println("debut de "+i + " : " + debut[i]);
				atteint.add(i);
				for (int s : graphe.getSuccessors(i) ) {
					if (!atteint.contains(s)) {
						atteint.add(s);
						System.out.println("debut de "+s+ " : " + compteur);
						debut[s] = compteur;
						compteur = explorerSommet(s, atteint, graphe,compteur, debut, fin);
						fin[s] = compteur;
						compteur++;
						System.out.println("fin de " + s + " : " + fin[s]);
					} 
				}

				fin[i] = compteur;
				compteur++;
				System.out.println("fin de " + i + " : "+ fin[i]);
			}
			i++;
		}
		return atteint;
	}
}
