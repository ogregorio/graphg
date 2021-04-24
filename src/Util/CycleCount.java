package Util;

public class CycleCount {

	// Number of vertices
	public static int vertexCount = 0;
	static int cycleCount = 0;

	static void DFS(int graph[][], boolean marked[], int n, int vert, int start) {

		marked[vert] = true;

		if (n == 0) {
			marked[vert] = false;

			if (graph[vert][start] == 1) {
				cycleCount++;
				return;
			} else
				return;
		}

		for (int i = 0; i < vertexCount; i++)
			if (!marked[i] && graph[vert][i] == 1)

				// DFS for searching path by
				// decreasing length by 1
				DFS(graph, marked, n - 1, i, start);

		// marking vert as unvisited to make it
		// usable again
		marked[vert] = false;
	}

	public int[][] parseMatrix(String[][] matrix) {
		int[][] graph = new int[matrix.length][matrix.length];

		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix.length; j++) {
				graph[i][j] = ("◯".equals(matrix[i][j])) ? 0 : 1;
			}
		}

		return graph;
	}

	public int countBySize(String[][] matrix, int size) {
		int[][] graph = parseMatrix(matrix);
		
		vertexCount = matrix.length;

		boolean marked[] = new boolean[graph.length];

		for (int i = 0; i < vertexCount - (size - 1); i++) {
			DFS(graph, marked, size - 1, i, i);

			marked[i] = true;
		}

		return cycleCount / 2;
	}

	public int countAll(String[][] matrix) {
		
		int totalCycles = 0;

		for (int i = 3; i <= matrix.length; i++) {
			cycleCount = 0;
			totalCycles += countBySize(matrix, i);
		}

		return totalCycles;
	}

}
