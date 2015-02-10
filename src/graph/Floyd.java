package graph;

public class Floyd {
	
	
	public static int[][] floyd(IValuatedUndirectGraph graph, int s){
		int[][] cout = graph.toAdjacencyMatrix();
		int n = graph.getNbNodes();
		
		//Initialisation
		int[][] p = new int[n][n];	//Matrice des prédécesseurs
		int[][] v = new int[n][n];	//Matrice des plus courts chemins
		for (int i=0; i<n; i++) {
			for (int j=0; j<n; j++) {
				if (i==j) {
					v[i][j]=0; 
					p[i][j]=i;
				} else {
					v[i][j]=Integer.MAX_VALUE; 
					p[i][j]=0;
				}
			}

			for (int t : graph.getNeighbors(i)) {
				v[i][t]=cout[i][t];
				p[i][t]=i;
			}
		}
		
		// calcul des matrices successives
		for (int k=0; k<n; k++) {
			for (int i=0; i<n; i++) {
				for (int j=0; j<n; j++) {
					if(v[i][k] != Integer.MAX_VALUE && v[k][j] != Integer.MAX_VALUE) {
						if (v[i][k]+v[k][j]<v[i][j]) {
							v[i][j]= v[i][k]+v[k][j];
							p[i][j]=p[k][j];
						}
					}
				}
			}
		}
		//normalement on fait une derniere verification pour être sûr que p n'a pas de coeff négatif
		return v;
	}
}
