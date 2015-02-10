package graph;

public class AdjacencyMatrixDirectedGraph implements IDirectedGraph{
	private int[][] adjacencyMatrix;

	//Cette fois-ci, peu importe si la matrice est symétrique ou non
	public AdjacencyMatrixDirectedGraph(int[][] gD) {
		this.adjacencyMatrix = gD;
	}

	public AdjacencyMatrixDirectedGraph(int n, int m){
		this.adjacencyMatrix = GraphTools.generateGraphData(n, m, false);
	}
	public AdjacencyMatrixDirectedGraph(IDirectedGraph idg){
		this.adjacencyMatrix = idg.toAdjacencyMatrix();
	}

	// retourne le nombre de noeuds du graphe
	@Override
	public int getNbNodes() {
		return this.adjacencyMatrix.length;
	}

	//retourne la matrice d'adjacence associée
	@Override
	public int[][] toAdjacencyMatrix() {
		return this.adjacencyMatrix;
	}

	//retourne le nombre d'arcs du graphe
	@Override
	public int getNbArcs() {
		int compteur = 0;
		for (int i = 0 ; i<this.adjacencyMatrix.length ; i++){
			for(int j = 0 ; j<this.adjacencyMatrix.length ; j++){
				if(this.adjacencyMatrix[i][j] == 1){
					compteur++;
				}

			}
		}
		return compteur;
	}

	//renvoie true si l'arc existe, false sinon
	@Override
	public boolean isArc(int from, int to) {
		return this.adjacencyMatrix[from][to]==1;
	}

	//supprime l'arc
	@Override
	public void removeArc(int from, int to) {
		this.adjacencyMatrix[from][to] = 0;
	}

	//ajoute l'arc
	@Override
	public void addArc(int from, int to) {
		this.adjacencyMatrix[from][to] = 1;
	}

	//renvoie un tableau formé des successeurs
	@Override
	public int[] getSuccessors(int x) {
		int compteur = 0;
		for (int i=0; i<this.adjacencyMatrix[x].length; i++){
			if(this.adjacencyMatrix[x][i] == 1){
				compteur++;
			}
		}
		int[] res = new int[compteur];
		int i = 0;
		for(int j = 0 ; j<this.adjacencyMatrix[x].length; j++){
			if(this.adjacencyMatrix[x][j] == 1 ){
				res[i] = j;
				i++;
			}
		}
		return res;
	}

	//renvoie un tableau formé des prédécesseurs
	@Override
	public int[] getPredecessors(int y) {
		int compteur = 0;
		for (int i=0; i<this.adjacencyMatrix.length; i++){
			if(this.adjacencyMatrix[i][y] == 1){
				compteur++;
			}
		}
		int i = 0;
		int[] res = new int[compteur];
		for(int j = 0 ; j<this.adjacencyMatrix.length; j++){
			if(this.adjacencyMatrix[j][y] == 1 ){
				res[i] = j;
				i++;
			}
		}
		return res;
	}

	//renvoie le graphe inverse
	@Override
	public IDirectedGraph computeInverse() {
		int[][] inverse = new int[this.adjacencyMatrix.length][this.adjacencyMatrix[0].length];
		//normalement elle est carrée donc pas de problème pour la taille

		for (int i = 0; i<this.adjacencyMatrix.length; i++){
			for(int j = i; j<this.adjacencyMatrix[i].length;j++){
				if(i!=j){
					inverse[j][i] = this.adjacencyMatrix[i][j];
					inverse[i][j] = this.adjacencyMatrix[j][i];
				}
				else{
					inverse[i][i] = 0;
				}
			}
		}
		return new AdjacencyMatrixDirectedGraph(inverse);
	}
}
