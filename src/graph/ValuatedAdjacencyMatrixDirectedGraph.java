package graph;

public class ValuatedAdjacencyMatrixDirectedGraph implements IDirectedGraph {
	
	private int[][] adjacencyMatrix;
	
	public ValuatedAdjacencyMatrixDirectedGraph(int[][] adjacencyMatrix) {
		this.adjacencyMatrix = adjacencyMatrix;
	}

	public ValuatedAdjacencyMatrixDirectedGraph(int n) {
		int[][] adjacencyMatrix = new int[n][n];
		for (int i = 0 ; i< n ; i++){
			for (int j = 0 ; j < n ; j++){
			    if(i!=j){
					adjacencyMatrix[i][j] = Integer.MAX_VALUE;
				}
				else{
					adjacencyMatrix[i][i] = 0;
				}
			}
		}
		this.adjacencyMatrix = adjacencyMatrix;
	}

	public int get(int x , int y ){
		return this.adjacencyMatrix[x][y];
	}
	public void set(int x, int y , int value){
		this.adjacencyMatrix[x][y] = value;
	}
	
	
	@Override
	public int getNbNodes() {
		return this.toAdjacencyMatrix().length;
	}

	@Override
	public int[][] toAdjacencyMatrix() {
		return this.adjacencyMatrix;
	}

	public int[][] getAdjacencyMatrix() {
		return adjacencyMatrix;
	}

	public void setAdjacencyMatrix(int[][] adjacencyMatrix) {
		this.adjacencyMatrix = adjacencyMatrix;
	}

	@Override
	public int getNbArcs() {
		int compteur = 0;
		for (int i = 0 ; i<this.toAdjacencyMatrix().length ; i++){
			for (int j = 0 ; j<this.toAdjacencyMatrix()[0].length ; j++){
				if(this.get(i, j) != Integer.MAX_VALUE){
					compteur++;
				}
			}
		}
		return compteur;
	}

	@Override
	public boolean isArc(int from, int to) {
		return this.get(from, to) != Integer.MAX_VALUE && from!=to;
	}

	@Override
	public void removeArc(int from, int to) {
		this.set(from, to, Integer.MAX_VALUE);
	}

	
	public void addArc(int from, int to, int value) {
		this.set(from, to, value);
	}
	
	@Override
	public void addArc(int from, int to) {
		this.set(from, to, 1);
	}

	@Override
	public int[] getSuccessors(int x) {
		int nbSucc = 0 ;
		for (int i = 0 ; i< this.getAdjacencyMatrix().length ; i++){
			if (this.isArc(x, i)){
				nbSucc++;
			}
		}
		int compteur = 0;
		int[] res = new int[nbSucc];
		for (int index = 0 ; index<this.getAdjacencyMatrix().length ; index++){
			if(this.isArc(x, index)){
			res[compteur] = index;
			compteur++;
			}
		}
		return res;
	}

	@Override
	public int[] getPredecessors(int y) {
		int nbPrec = 0 ;
		for (int i = 0 ; i< this.getAdjacencyMatrix().length ; i++){
			if (this.isArc(i, y)){
				nbPrec++;
			}
		}
		int compteur = 0;
		int[] res = new int[nbPrec];
		for (int index = 0 ; index<this.getAdjacencyMatrix().length ; index++){
			if(this.isArc(index, y)){
			res[compteur] = index;
			compteur++;
			}
		}
		return res;
	}

	
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
