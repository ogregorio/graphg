import java.util.Scanner;

import Matrix.*;
import Util.CycleCount;

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
				return createVertex(code);
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
						+ "CREATE NO_LABEL to create a not labeled graph.\n" + "ADD COMMAND:\n\n"
						+ "ADD VERTEX 1 to implement a vertex, or ADD VERTEX A to implement a labeled vertex.\n"
						+ "ADD EDGE 1 2 to implement a edge between VERTEX 1 and 2, \n"
						+ "or ADD VERTEX 1 2 A to implement a labeled edge between VERTEX 1 and 2.\n"
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

}
