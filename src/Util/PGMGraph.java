package Util;

import java.util.ArrayList;
import java.util.Map;

public class PGMGraph {

  PGMMatrix pgmMatrix;
  private int[][] matrix;
  private int size;

  public PGMGraph(PGMMatrix pgmMatrix) {
    this.pgmMatrix = pgmMatrix;
  }

  public int[][] getGraph() {
    this.size = this.pgmMatrix.getLenght() * this.pgmMatrix.getHeight();
    this.matrix = new int[this.size + 2][this.size + 2]; // +2 -> S and T vertex
    int nlink = this.size;
    int tlink = this.size + 1;
    getMatrix(this.pgmMatrix.getPixels(), tlink, nlink);
    return this.matrix;
  }

  public int boundaryPenalty(int ip, int iq) {
    int bp = (int) (100 * Math.exp(-Math.pow(ip - iq, 2) / (2 * Math.pow(30, 2))));
    return bp;
  }

  public void getMatrix(int[] pixels, int tlink, int nlink) {
    int k = Integer.MIN_VALUE;
    for (int i = 0; i < pixels.length; i++) {
      try {
        if (i % this.pgmMatrix.getLenght() != 0) {
          int bp = boundaryPenalty(pixels[i], pixels[i + 1]);
          this.matrix[i][i + 1] = bp;
          this.matrix[i + 1][i] = bp;
          k = Math.max(bp, k);
        }

        if (i - 1 % this.pgmMatrix.getLenght() == 0) {
          int bp = boundaryPenalty(pixels[i], pixels[i - 1]);
          this.matrix[i][i - 1] = bp;
          this.matrix[i - 1][i] = bp;
          k = Math.max(bp, k);
        }

        if (i + 1 <= pixels.length) {
          int bp = boundaryPenalty(pixels[i + 1], pixels[i]);
          this.matrix[i + 1][i] = bp;
          this.matrix[i][i + 1] = bp;
          k = Math.max(bp, k);
        }

        if (i + 1 >= pixels.length) {
          int bp = boundaryPenalty(pixels[i - 1], pixels[i]);
          this.matrix[i - 1][i] = bp;
          this.matrix[i][i - 1] = bp;
          k = Math.max(bp, k);
        }

      } catch (IndexOutOfBoundsException e) {
      }
    }
    for (int i = 0; i < pixels.length; i++) {
      if (pixels[i] == 0)
        this.matrix[i][tlink] = k; // foreground
      else
        this.matrix[nlink][i] = k; // background
    }

  }

  public int[][] cut(Map<Integer, ArrayList<Integer>> cuts) {

    int[][] cutMatrix = pgmMatrix.getMatrix();

    for (Map.Entry<Integer, ArrayList<Integer>> entry : cuts.entrySet()) {
      try {
        ArrayList list = entry.getValue();
        int key = entry.getKey();
        int x = key % pgmMatrix.getLenght();
        int y = key / pgmMatrix.getLenght();
        cutMatrix[x][y] = 99;
        for (int i = 0; i < list.size(); i++) {
          x = (int) list.get(i) % pgmMatrix.getLenght();
          y = (int) list.get(i) / pgmMatrix.getLenght();
          cutMatrix[x][y] = 99;
        }
      } catch (ArrayIndexOutOfBoundsException e) {

      }
    }

    return cutMatrix;
  }
}
