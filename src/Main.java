public class Main {
	private static CLI cli = new CLI();
	
	public static void main(String[] args) throws Exception {
		cli.run("CREATE NO_LABEL_UNDIRECTED");
		cli.run("ADD VERTEX 0");
		cli.run("ADD VERTEX 1");
		cli.run("ADD VERTEX 2");
		cli.run("ADD VERTEX 3");
		cli.run("ADD VERTEX 4");
		cli.run("ADD EDGE 0 1");
		cli.run("ADD EDGE 0 2");
		cli.run("ADD EDGE 0 3");
		cli.run("ADD EDGE 0 4");
		cli.run("ADD EDGE 2 1");
		cli.run("ADD EDGE 4 3");
		cli.run("ADD EDGE 4 2");
		cli.run("PRINT");
		cli.run("COUNT CYCLES");
	}
	
}
