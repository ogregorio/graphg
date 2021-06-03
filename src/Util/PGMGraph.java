package Util;

public class PGMGraph {

  PGMMatrix pgmMatrix;
  private int[][] matrix;

  public PGMGraph(PGMMatrix pgmMatrix) {
    this.pgmMatrix = pgmMatrix;
  }

  public int[][] getGraph() {
    int size = this.pgmMatrix.getLenght() * this.pgmMatrix.getHeight();
    this.matrix = new int[size + 2][size + 2]; // +2 -> S and T vertex
    int nlink = size;
    int tlink = size + 1;
    getMatrix(this.pgmMatrix.getPixels(), tlink);
    for (int i = 0; i < size; i++) {
      matrix[nlink][i] = 1; //background
    }
    return this.matrix;
  }

  public void getMatrix(int[] pixels, int tlink) {
    for (int i = 0; i < pixels.length; i++) {
      try {
        if (i % this.pgmMatrix.getLenght() != 0) {
          this.matrix[i][i + 1] = (pixels[i] + pixels[i + 1]) / 2;
          this.matrix[i + 1][i] = (pixels[i] + pixels[i + 1]) / 2;
        }

        if (i - 1 % this.pgmMatrix.getLenght() == 0) {
          this.matrix[i][i - 1] = (pixels[i] + pixels[i - 1]) / 2;
          this.matrix[i - 1][i] = (pixels[i] + pixels[i - 1]) / 2;
        }

        if (i + 1 <= pixels.length) {
          this.matrix[i + 1][i] = (pixels[i + 1] + pixels[i]) / 2;
          this.matrix[i][i + 1] = (pixels[i + 1] + pixels[i]) / 2;
        }

        if (i + 1 >= pixels.length) {
          this.matrix[i - 1][i] = (pixels[i - 1] + pixels[i]) / 2;
          this.matrix[i][i - 1] = (pixels[i - 1] + pixels[i]) / 2;
        }

        this.matrix[i][tlink] = 3; //foreground

      } catch (IndexOutOfBoundsException e) {
      }
    }
  }
}
