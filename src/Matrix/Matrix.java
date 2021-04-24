package Matrix;


public interface Matrix{

	//Verifica se o vértice foi adicionado, se não, adiciona
	public boolean addVertex(String vertex);
	
	//adicionando arestas a matriz
	public boolean addEdge(String vertex,String vertex2, String label);
	
	//aumenta o tamanho da matriz a cada acresção
	public void increaseMatrix();
	
	//imprime a matriz
	public boolean print();
	
	//preenche a matriz de valores vazios
	public void fill(String string);
	
}
