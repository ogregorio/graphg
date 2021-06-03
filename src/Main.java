import Util.PGMMatrix;
import Util.BurnMatrix;
import Util.MinCut;
import Util.PGMGraph;

import java.util.ArrayList;
import java.util.Map;

public class Main {
	// private static CLI cli = new CLI();

	public static void main(String[] args) throws Exception {
		// cli.run();
		MinCut minCut = new MinCut();
		PGMMatrix pgmMatrix = new PGMMatrix("C:/Users/iangu/Desktop/feep.pgm");
		int[] pixels = pgmMatrix.getPixels();
		for (int i = 0; i < pixels.length; i++) {
			// System.out.println( "POSIÇÃO: " + i + ", VALOR: " + pixels[i] );
		}
		// BurnMatrix bm = new BurnMatrix();
		// bm.burn("C:Users/iangu/Desktop/feepgraph.txt", pgmGraph.getGraph());
		PGMGraph pgmGraph = new PGMGraph(pgmMatrix);
		int[][] segmentedMatrix = pgmGraph.cut(minCut.minCut(pgmGraph.getGraph(), 168, 169));

		for (int row = 0; row < segmentedMatrix.length; row++) {
			for (int col = 0; col < segmentedMatrix[0].length; col++) {
				System.out.printf("%3d", segmentedMatrix[row][col]);
			}
			System.out.println();
		}

	}

}
