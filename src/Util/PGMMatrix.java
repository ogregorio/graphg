package Util;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.stream.Stream;

public class PGMMatrix {

    private int[][] matrix;
    private String format;
    private String name;
    private int maxSize;

    // Dimensions[0] = colunas, dimensions[1] = linhas
    private int[] dimensions;

    public PGMMatrix(String path){
       try {
           readPGM(path);
       }
       catch (FileNotFoundException e) {
           System.out.println("Arquivo não encontrado!");
       }
        
    }

    private void readPGM(String path) throws FileNotFoundException {
        File file = new File(path);
        Scanner scanner = new Scanner(file);
        this.format = scanner.nextLine();
        this.name = scanner.nextLine();
        this.dimensions = StringArrayToIntArray(scanner.nextLine().split(" "));
        this.maxSize = Integer.parseInt(scanner.nextLine());
        this.matrix = new int[dimensions[1]][dimensions[0]];
        for (int i = 0; scanner.hasNextLine(); i++) {
            int[] arr = StringArrayToIntArray(scanner.nextLine().replace("  ", " ").split(" "));
            composeImage(arr, i);
        }
        scanner.close();
    }

    private void composeImage(int[] line, int count) {
        for (int i = 0; i < dimensions[0]; i++) {
            this.matrix[count][i] = line[i];
        }
    }

    private int[] StringArrayToIntArray(String[] stringArray) {
        return Stream.of(stringArray).mapToInt(Integer::parseInt).toArray();
    }

    public void printer() {
        System.out.println("Format: " + this.format);
        System.out.println("Name: " + this.name);
        System.out.println("Dimension: " + this.dimensions[0] + "x" + this.dimensions[1]);
        System.out.println("Max Size: " + this.maxSize);
        for (int row = 0; row < dimensions[1]; row++) {
            for (int col = 0; col < dimensions[0]; col++) {
                System.out.printf("%3d", matrix[row][col]);
            }
            System.out.println();
        }
    }

    public int[] getPixels(){
        int[] pixels = new int[this.dimensions[0]*this.dimensions[1]];
        int count = 0;
        for (int i = 0; i < dimensions[1]; i++) {
            for (int j = 0; j < dimensions[0]; j++) {
                pixels[count++] = this.matrix[i][j];
            }
        }
        return pixels;
    }

    public int[][] getMatrix(){
        return this.matrix;
    }

    public int getMaxSize() {
        return this.maxSize;
    }

    public int getLenght() {
        return this.dimensions[1];
    }

    public int getHeight() {
        return this.dimensions[0];
    }

}
