package graph;

/**
 * 
 * Matrice avec poids. poids > 0
 */
public class ValuatedAdjacencyMatrixUndirectedGraph implements IValuatedUndirectGraph {
	private int[][] adjacencyMatrix;
	
	public ValuatedAdjacencyMatrixUndirectedGraph(int[][] adjacencyMatrix) {
		this.adjacencyMatrix = adjacencyMatrix;
	}
	
	public ValuatedAdjacencyMatrixUndirectedGraph(int n){
		adjacencyMatrix = new int[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				adjacencyMatrix[i][j] = Integer.MAX_VALUE;
			}
		}
	}
	
	public int get(int x , int y ){
		return this.adjacencyMatrix[x][y];
	}
	public void set(int x, int y , int value){
		this.adjacencyMatrix[x][y] = value;
	}
	

	public void setAdjacencyMatrix(int[][] adjacencyMatrix) {
		this.adjacencyMatrix = adjacencyMatrix;
	}

	@Override
	public int getNbNodes() {
		return this.toAdjacencyMatrix().length;
	}

	@Override
	public int[][] toAdjacencyMatrix() {
		return this.adjacencyMatrix;
	}

	@Override
	public int getNbEdges() {
		int res = 0 ;
		for (int i=0; i<this.adjacencyMatrix.length;i++){
			for (int j = i; j<this.adjacencyMatrix[i].length ; j++){
				if(this.adjacencyMatrix[i][j] < Integer.MAX_VALUE){
					res++;
				}
			}
		}
		return res;
	}

	@Override
	public boolean isEdge(int x, int y) {
		return this.get(x, y)<Integer.MAX_VALUE;
	}

	@Override
	public void removeEdge(int x, int y) {
		this.set(x, y, Integer.MAX_VALUE);
		this.set(y, x, Integer.MAX_VALUE);
	}

	
	@Override
	public void addEdge(int x, int y, int value) {
		this.set(x, y, value);
		this.set(y, x, value);
	}
	
	@Override
	public void addEdge(int x, int y) {
		this.set(x, y, Integer.MAX_VALUE);
		this.set(y, x, Integer.MAX_VALUE);
	}
	
	

	@Override
	public int[] getNeighbors(int x) {
		int compteur = 0 ; 
		for (int i = 0 ; i<this.adjacencyMatrix[x].length ; i++){
			if (x!=i && this.get(x, i) < Integer.MAX_VALUE){
				compteur++;
			}
		}

		int[] voisins = new int[compteur];
		int offset = 0;
		for (int j = 0 ; j< this.adjacencyMatrix[x].length ; j++){
			if( x!=j && this.get(x, j) < Integer.MAX_VALUE){
				voisins[offset] = j;
				offset++;
			}
		}
		return voisins;
	}
	
	@Override
	public int weightOfEdge(int x, int y) {
		return adjacencyMatrix[x][y];
	}
}
