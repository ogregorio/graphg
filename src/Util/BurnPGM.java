package Util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class BurnPGM extends BurnMatrix{

    public void burn(String filename, PGMMatrix pgmMatrix, int[][] matrix) {
        File file = new File(filename);
        try {
            file.createNewFile();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            bw.write(pgmMatrix.getFormat() + "\n");
            bw.write(pgmMatrix.getName() + "\n");
            bw.write(pgmMatrix.getHeight() + " " + pgmMatrix.getLenght() + "\n");
            bw.write(pgmMatrix.getMaxSize() + "\n");
            bw.close();
            super.burn(filename, matrix);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

// PGM /home/gregorio/feep2.pgm /home/gregorio/pgm/feep.pgm