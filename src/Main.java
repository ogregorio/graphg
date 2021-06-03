import Util.PGMMatrix;
import Util.BurnMatrix;
import Util.MinCut;
import Util.PGMGraph;

public class Main {
	//private static CLI cli = new CLI();
	
	public static void main(String[] args) throws Exception {
		//cli.run();
		PGMMatrix pgmMatrix = new PGMMatrix("/home/gregorio/feep2.pgm");
		int[] pixels = pgmMatrix.getPixels();
		for( int i = 0; i < pixels.length; i++ ){
		//	System.out.println( "POSIÇÃO: " + i + ", VALOR: " + pixels[i] );
		}
		BurnMatrix bm = new BurnMatrix();
		PGMGraph pgmGraph = new PGMGraph(pgmMatrix);
		bm.burn("/home/gregorio/feepgraph", pgmGraph.getGraph());

		MinCut.minCut(pgmGraph.getGraph(), 0, 50);

	}
	
}
