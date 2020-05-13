
/*
This class will find a feasible solution for the Generalized Assignment Problem
*/
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class ConstructiveHeuristic {

    public static void main(String[] args) {
        ConstructiveHeuristic ch = new ConstructiveHeuristic();
        ch.constructiveHeuristic();
    }

    protected void constructiveHeuristic() {
        GetFile readFile = new GetFile();

        long startTime = System.nanoTime(); // Get the start of execution
        try {
            int[][][] data = readFile.getFile();
            greedyHeuristic(data);
        } catch(IOException ex) {
            System.out.println("An error ocurred");
            ex.printStackTrace();
        }
        long endTime = System.nanoTime(); // Get the end of execution
        long timeElapsed = endTime - startTime; // Calculate the time of execution
        System.out.print("Total time ConstructiveHeuristic: " + timeElapsed / 1000000 + "\n");
    }

    protected ArrayList<DataLS> greedyHeuristic(int[][][] data) {
        ArrayList<DataLS> items = new ArrayList<DataLS>();
        boolean x[][] = new boolean[data[0].length][data[0][1].length]; // x will be the result if task is j is assigned to agent i

        long startTime = System.nanoTime(); // Get the start of execution
        int task = data[0][1].length;
        int of = 0; // This is the objective function

        int[][][] originalData = new int[data.length][data[0].length][data[0][1].length];
        for(int i = 0; i < data.length; i ++) {
            for(int j = 0; j < data[0].length; j ++) {
                for(int k = 0; k < data[0][1].length; k ++) {
                    originalData[i][j][k] = data[i][j][k];
                }
            }
        }

        do {
            // Step 1
            int argj = 0;
            int bimax = 0;
            for(int j = 0; j < data[1].length; j++) {
                if((data[2][j][0] > bimax) && (data[2][j][0] != 0)) {
                    bimax = data[2][j][0];
                    argj = j;
                }
            }
            // Step 2
            int argk = 0;
            int wimin = 0;
            int num = 0;
            do {
                if(data[1][argj][num] == 0) {
                    num ++;
                } else {
                    wimin = data[1][argj][num];
                    argk = num;
                }
            } while(wimin == 0);
            for(int k = 0; k < data[0][1].length; k++) {
                if((data[1][argj][k] < wimin) && (data[1][argj][k] != 0)) {
                    wimin = data[1][argj][k];
                    argk = k;
                }
            }
            // Step 3
            if((bimax - wimin) >= 0) {
                // Step 4
                data[2][argj][0] = bimax - wimin;
                of += data[0][argj][argk];
                // Step 5
                for(int m = 0; m < data[0].length; m ++) {
                    data[1][m][argk] = 0;
                }
                task -= 1;
                x[argj][argk] = true;
            } else {
                // Part of Step 3
                System.out.println("Loop");                
                data[2][argj][0] = 0;
            }

        } while(task != 0);
        
        long endTime = System.nanoTime(); // Get the end of execution
        long timeElapsed = endTime - startTime; // Calculate the time of execution
        System.out.println("\nThis is the objective function CH: " + of); 
        System.out.println("Execution time to solve the problem by Constructive Heuristic in miliseconds: " + timeElapsed / 1000000 + "\n");

        int[] bi = new int[data[0].length];
        for(int i = 0; i < data[0].length; i ++) {
            bi[i] = data[2][i][0];
        }

        DataLS dataLS = new DataLS(originalData, x, bi, of);
        items.add(dataLS);

        return items;
    }
    
}