/*
This class will find a feasible solution for the Generalized Assignment Problem
*/
import java.io.IOException;

public class ConstructiveHeuristic {

    public static void main(String[] args) {
        ConstructiveHeuristic ch = new ConstructiveHeuristic();
        ch.constructiveHeuristic();
    }

    protected void constructiveHeuristic() {
        GetFile read_file = new GetFile();

        try {
            int[][][] data = read_file.getFile();
            int x = greedyHeuristic(data);
            System.out.println("This is the objective function: " + of);
        } catch(IOException ex) {
            System.out.println("An error ocurred");
            ex.printStackTrace();
        }
    }

    protected int greedyHeuristic(int[][][] data) {
        int task = data[0][1].length;
        int of = 0; // This is the objective function

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
            for(int k = 0; k < data[0][1].length; k++) {
                if((data[1][argj][k] < wimin) && (data[1][argj][k] != 0)) {
                    wimin = data[1][argj][k];
                    argk = k;
                }
            }
            // Step 3
            if((bimax - wimin) >= 0) {
                // Step 4
                data[2][argj][0] -=  wimin;
                of += data[0][argj][argk];
                // Step 5
                for(int m = 0; m < data[0].length; m ++) {
                    data[1][m][argk] = 0;
                }
                task -= 1;
            } else {
                // Part of Step 3
                data[2][argj][0] = 0;
            }
        } while(task != 0);

        return of;
    }
    
}