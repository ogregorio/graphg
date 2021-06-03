package Util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class BurnMatrix {
    
    public void burn(String filename, int[][] matrix) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(filename));
    
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[i].length; j++) {
                    if(j == matrix[i].length - 1){    
                        bw.write(String.format("%d", matrix[i][j]));
                    } else{
                        bw.write(String.format("%d", matrix[i][j]));
                    }        
                }
                bw.newLine();
            }
            bw.flush();
            bw.close();
        } catch (IOException e) {
            System.out.println("Failed on write matrix.");
        }
    }
}
