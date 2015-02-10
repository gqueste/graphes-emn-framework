package graph;

public class AdjacencyMatrixUndirectedGraph implements IUndirectedGraph {
	private int[][] adjacencyMatrix;


	//On considère que la matrice en paramètre est obligatoirement symétrique.
	public AdjacencyMatrixUndirectedGraph(int n , int m) {
		this.adjacencyMatrix = GraphTools.generateGraphData(n, m, true);
	}

	public AdjacencyMatrixUndirectedGraph(int[][] gD){
		this.adjacencyMatrix = gD;
	}
	
	public AdjacencyMatrixUndirectedGraph(IUndirectedGraph iug){
		this.adjacencyMatrix = iug.toAdjacencyMatrix();
	}
	
	@Override
	public int getNbNodes() {
		return this.adjacencyMatrix.length;
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
				if(this.adjacencyMatrix[i][j] ==1){
					res++;
				}
			}
		}
		return res;
	}

	@Override
	public boolean isEdge(int x, int y) {
		return this.adjacencyMatrix[x][y] == 1; 
	}

	@Override
	public void removeEdge(int x, int y) {
		this.adjacencyMatrix[x][y] = 0;
		this.adjacencyMatrix[y][x] = 0;
	}

	@Override
	public void addEdge(int x, int y) {
		this.adjacencyMatrix[x][y] = 1;
		this.adjacencyMatrix[y][x] = 1;
	}

	@Override
	public int[] getNeighbors(int x) {
		int compteur = 0 ; 
		for (int i = 0 ; i<this.adjacencyMatrix[x].length ; i++){
			if (this.adjacencyMatrix[x][i] == 1){
				compteur++;
			}
		}

		int[] voisins = new int[compteur];
		int offset = 0;
		for (int j = 0 ; j< this.adjacencyMatrix[x].length ; j++){
			if( this.adjacencyMatrix[x][j] == 1){
				voisins[offset] = j;
				offset++;
			}
		}
		return voisins;

	}
}
