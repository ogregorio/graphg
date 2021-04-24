package Matrix;

import java.util.ArrayList;

public class MatrixNotLabeledUndirected implements Matrix{
	
	//Uso esse arraylist para guardar os vetores já usados
	private ArrayList<String> addedVertex = new ArrayList<String>();
	
	//definição da matriz de adjacência
	private String[][] matrix;
	
	public MatrixNotLabeledUndirected() {
		matrix = new String[0][0];
	}
	
	//Verifica se o vértice foi adicionado, se não, adiciona
	@Override
	public boolean addVertex(String vertex) {
		if(!addedVertex.contains(vertex)) {
			addNewVertex(String.valueOf(vertex));
			return true;
		}else {
			return false;
		}
	}
	
	//adicionando arestas a matriz
	@Override
	public boolean addEdge(String vertex,String vertex2, String label) {
		if(addedVertex.contains(vertex) && addedVertex.contains(vertex2)) {
			int i = addedVertex.indexOf(vertex);
			int j = addedVertex.indexOf(vertex2);
			matrix[i][j] = "⬤";
			matrix[j][i] = "⬤";
			return true;
		}else {
			return false;
		}
	}
	
	//adiciona um novo vertice a matriz
	private void addNewVertex(String vertex) {
		addedVertex.add(vertex);
		increaseMatrix();
	}
	
	//aumenta o tamanho da matriz a cada acresção
	@Override
	public void increaseMatrix() {
		int totalVertex = addedVertex.size();
		String[][] tempMatrix = new String[totalVertex][totalVertex];
		for(int i = 0; i < totalVertex-1;i++) {
			for(int j = 0;j < totalVertex -1;j++) {
				tempMatrix[i][j] = matrix[i][j];
			}
		}
		matrix = tempMatrix;
	}
	
	//imprime a matriz
	@Override
	public boolean print() {
		fill("◯");
		return printer();
	}
	
	//preenche a matriz de valores vazios
	@Override
	public void fill(String string) {
        for(int i=0 ; i < matrix.length ; i++){
            for(int j = 0; j < matrix.length ; j ++){
            	if(matrix[i][j] == null)
            		matrix[i][j] = string;
            }
        }
		
	}
	
	@Override	
	public String[][] getMatrix() {
		return matrix;
	}
	
	//imprime a matriz
	protected boolean printer() {
        System.out.println("\n Your adjacency matrix: \n");
        System.out.printf("\t %s \t", " ");
        for(int i=0 ; i < matrix.length ; i++){
        		System.out.printf("\t %s \t", addedVertex.get(i));
        }
        System.out.println();
        for(int i=0 ; i < matrix.length ; i++){
        		System.out.printf("\t %s \t", addedVertex.get(i));
            for(int j = 0; j < matrix.length ; j ++){
            		System.out.printf("\t %s \t", matrix[i][j]);
            }
            System.out.println();
        }
        return true;
	}

}
