import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import Matrix.*;
import Util.BurnPGM;
import Util.CycleCount;
import Util.MinCut;
import Util.PGMGraph;
import Util.PGMHightlightBackgroundAlt;
import Util.PGMMatrix;

public class CLI {

	Scanner scanner = new Scanner(System.in);
	Matrix matrix = null;
	boolean DEBUG = false;

	// chamada para execução do CLI
	public void run() {
		String command = "";
		do {
			command = terminal();
			run(command);
		} while (!command.equals("exit"));
		scanner.close();
	}

	// receber comandos prontos
	public void run(String command) {
		if (interpreter(command)) {
			if (DEBUG)
				System.out.println("Command recognized!");
		} else {
			if (DEBUG)
				System.out.println("Command not recognized!");
		}
	}

	// terminal para receber comandos
	private String terminal() {
		System.out.print("graphg >> ");
		return scanner.nextLine();
	}

	// interpresta a atitude a ser tomada antes de tentar decodificar
	private boolean interpreter(String command) {
		String[] code = command.split(" ");
		switch (code[0]) {
		case "HELP":
			return help();
		case "DEBUG_ON":
			return debugSet(code[0]);
		case "DEBUG_OFF":
			return debugSet(code[0]);
		case "CREATE":
			return matrixCreate(code[1]);
		case "ADD":
			return commandsDecode(code);
		case "PGM":
			return pgmMatrix(code[1], code[2]);
		case "PGMDEMO":
			return pgmDemo();
		case "PGMALT":
			return pgmHightlightBackground(code[1],code[2]);
		case "PRINT":
			return matrix.print();
		case "COUNT":
			return (code.length == 3) ? countCycles(code[1], code[2]) : countCycles(code[1]);
		default:
			return false;
		}

	}

	// Decodificacao dos comandos inseridos
	private boolean commandsDecode(String[] code) {

		// se for um comando de acição
		if (code[0].equalsIgnoreCase("ADD")) {

			// verifica se é um vertex ou uma aresta, caso nenhum, comando inválido
			switch (code[1]) {
			case "VERTEX":
				return createVertex(code);
			case "V":
				return createVertex(code);
			case "EDGE":
				return createEdge(code);
			case "E":
				return createEdge(code);
			default:
				return false;
			}
		}

		return false;
	}

	// change debug mode
	private boolean debugSet(String mode) {
		if (mode.equalsIgnoreCase("DEBUG_ON"))
			DEBUG = true;
		else if (mode.equalsIgnoreCase("DEBUG_OFF"))
			DEBUG = false;
		return true;
	}

	// create vertex
	private boolean createVertex(String[] code) {
		return matrix.addVertex(code[2]);
	}

	// create edge
	private boolean createEdge(String[] code) {
		try {
			if (matrix.getClass() == MatrixLabeled.class)
				return matrix.addEdge(code[2], code[3], code[4]);
			else
				return matrix.addEdge(code[2], code[3], "⬤");
		} catch (ArrayIndexOutOfBoundsException e) {
			if (DEBUG)
				System.out.println("Argument missing, try to explicitly place the label!");
		}
		return false;
	}

	// Principais comandos do programa
	private boolean help() {
		System.out.println(
				"HELP - Give this help.\n\n" + "CREATE COMMAND:\n\n" + "CREATE WITH_LABEL to create a labeled graph.\n"
						+ "CREATE NO_LABEL to create a not labeled graph.\n" 
						+ "CREATE NO_LABEL_UNDIRECTED to create a not labeled graph undirected.\n"
						+ "\nADD COMMAND:\n\n"
						+ "ADD VERTEX 1 to implement a vertex, or ADD VERTEX A to implement a labeled vertex.\n"
						+ "ADD EDGE 1 2 to implement a edge between VERTEX 1 and 2, \n"
						+ "or ADD VERTEX 1 2 A to implement a labeled edge between VERTEX 1 and 2.\n"
						+ "\nCOUNT COMMAND:\n\n"
						+ "COUNT CYCLES to show all cycles in graph with any size.\n"
						+ "COUNT CYCLES 4 to show all cycles in graph with 4 vertex size.\n"
						+ "\nPGM COMMAND:\n\n"
						+ "PGM /origin_source /destiny_source."
						+ "PGMALT /origin_source /destiny_source."
						+ "PGMDEMO start a demo of PGM implementation."
						+ "PRINT COMMAND:\n\n" + "Print your graph.\n");
		return true;
	}

	// cria matriz a depender do tipo
	private boolean matrixCreate(String type) {
		switch (type) {
		case "WITH_LABEL":
			matrix = new MatrixLabeled();
			return true;
		case "NO_LABEL":
			matrix = new MatrixNotLabeled();
			return true;
		case "NO_LABEL_UNDIRECTED":
			matrix = new MatrixNotLabeledUndirected();
			return true;
		default:
			return false;
		}
	}

	private boolean countCycles(String... type) {
		switch (type[0]) {
		case "CYCLES":
			CycleCount cc = new CycleCount();
			if (type.length == 1)
				System.out.println("Cycle count = " + cc.countAll(matrix.getMatrix()));
			else
				System.out.println("Cycle count of lenght " + type[1] + " = "
						+ cc.countBySize(matrix.getMatrix(), Integer.parseInt(type[1])));
			return true;

		default:
			return false;
		}
	}

	private boolean pgmMatrix(String... type){
		String source = type[0];
		String destiny = type[1];

		System.out.println("Your selected image is: " + source);
		
		MinCut minCut = new MinCut();
		PGMMatrix pgmMatrix = new PGMMatrix(source);

		PGMGraph pgmGraph = new PGMGraph(pgmMatrix);
		int[][] segmentedMatrix = pgmGraph.cut(minCut.minCut(pgmGraph.getGraph(), pgmGraph.size, pgmGraph.size +1));

		BurnPGM bpmg = new BurnPGM();

		bpmg.burn(destiny, pgmMatrix, segmentedMatrix);

		System.out.println("Your processed image is in: " + destiny);

		return true;

	}

	private boolean pgmHightlightBackground(String... type){
		String source = type[0];
		String destiny = type[1];

		System.out.println("Your selected image is: " + source);
		
		PGMHightlightBackgroundAlt pgmHBA = new PGMHightlightBackgroundAlt();
		PGMMatrix pgmMatrix = pgmHBA.highlightBackground(new PGMMatrix(source));

		BurnPGM bpmg = new BurnPGM();
		bpmg.burn(destiny, pgmMatrix, pgmMatrix.getMatrix());

		System.out.println("Your processed image is in: " + destiny);

		return true;
	}

	private boolean pgmDemo(){
		String source = new File("silvio.pgm").getAbsolutePath();		

		System.out.println("Segmenting background from image in two diferent methods:");
		System.out.println("First method: network flow and min cut.");
		pgmMatrix(source, new File("silvio_flow.pgm").getAbsolutePath());
		System.out.println("Second method: imperative ( procedural ) mode.");
		pgmHightlightBackground(source, new File("silvio_highlight.pgm").getAbsolutePath());
		
		return true;
	}

}
