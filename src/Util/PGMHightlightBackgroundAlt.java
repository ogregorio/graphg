package Util;

import java.util.HashMap;

public class PGMHightlightBackgroundAlt {

  public PGMMatrix highlightBackground(PGMMatrix pgmMatrix) {
    HashMap < Integer, Integer > valuesCount = new HashMap < Integer, Integer >();
    int[][] matrix = pgmMatrix.getMatrix();
    for (int i = 0; i < matrix.length; i++) {
      for (int j = 0; j < matrix[0].length; j++) {
        int value = matrix[i][j];
        if (valuesCount.containsKey(value)) {
          int temp = valuesCount.get(value);
          valuesCount.put(value, temp++);
        } else {
          valuesCount.put(value, 1);
        }
      }
    }

    int bgValue = Integer.MIN_VALUE;
    int bgOccurrences = 0;

    for (HashMap.Entry < Integer, Integer > entry: valuesCount.entrySet()) {
      if (entry.getValue() > bgOccurrences) {
        bgOccurrences = entry.getValue();
        bgValue = entry.getKey();
      }
    }

    for (int i = 0; i < matrix.length; i++) {
      for (int j = 0; j < matrix[0].length; j++) {
        if (matrix[i][j] == bgValue)
          matrix[i][j] = pgmMatrix.getMaxSize() * 10;
      }
    }
    return new PGMMatrix(matrix, pgmMatrix.getFormat(), pgmMatrix.getName(), pgmMatrix.getMaxSize() * 10, pgmMatrix.getDimensions());
  }
}
