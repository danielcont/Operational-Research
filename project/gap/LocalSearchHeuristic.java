/*
Class that solves the Generalized Assginment Problem by a Local Search Heuristic
*/
import java.io.IOException;
import java.util.ArrayList;

public class LocalSearchHeuristic {

    public static void main(String[] args) {
        LocalSearchHeuristic lsh = new LocalSearchHeuristic();
        lsh.localSearchHeuristic();
    }
    
    protected void localSearchHeuristic() {
        ConstructiveHeuristic ch = new ConstructiveHeuristic();
        GetFile readFile = new GetFile();
        
        long startTime = System.nanoTime(); // Get the start of execution
        try {
            int[][][] data = readFile.getFile();
            ArrayList<DataLS> items = ch.greedyHeuristic(data);
            bestFoundSolution(items);
        } catch(IOException ex) {
            System.out.println("An error ocurred");
            ex.printStackTrace();
        }
        long endTime = System.nanoTime(); // Get the end of execution
        long timeElapsed = endTime - startTime; // Calculate the time of execution
        System.out.println("Total time LocalSearchHeuristic: " + timeElapsed / 1000000 + "\n");

    }

    protected void bestFoundSolution(ArrayList<DataLS> items) {
        long startTime = System.nanoTime(); // Get the start of execution
        boolean x[][] = items.get(0).getX();
        int[][][] data = items.get(0).getData();
        int[] bi = items.get(0).getBi();
        int of = items.get(0).getOf();

        int argn = 0, argm = 0, argi = 0;
        boolean flag;
        do {
            flag = false;
            // Step 1
            for(int n = 0; n < x[0].length; n ++) {
                for(int m = 0; m < x.length; m ++) { // Getting the agent that will do n task
                    if(x[m][n]) { 
                        for(int i = 0; i < data[0].length; i ++) { // This makes the "move"
                            if(m != i) { // Avoid same solution
                                // Step 2
                                if((bi[i] - data[1][i][n] >= 0) && (data[0][m][n] - data[0][i][n] > 0)) { // Only get better and feasible solution
                                    if((of - (data[0][m][n] - data[0][i][n])) < of) { //
                                        argm = m;
                                        argn = n;   
                                        argi = i;
                                        flag = true; // A change was made
                                    }
                                }
                            }
                        }
                    }
                }
            }
            // Updating data
            bi[argi] -= data[1][argi][argn];
            bi[argm] += data[1][argi][argn];
            x[argi][argn] = true;
            x[argm][argn] = false;
            of = getObjectiveFunction(x, data);
        } while(flag); // Keep doing this until no changes are made

        long endTime = System.nanoTime(); // Get the end of execution
        long timeElapsed = endTime - startTime; // Calculate the time of execution
        System.out.println("\nThis is the objective function LSH: " + of); 
        System.out.println("Execution time to solve the problem by Local Search Heuristic in miliseconds: " + timeElapsed / 1000000 + "\n");

    }

    private int getObjectiveFunction(boolean[][] x, int[][][] data) {
        int objectiveFunction = 0;
        for(int i = 0; i < x.length; i ++) {
            for(int j = 0; j < x[0].length; j ++) {
                if(x[i][j]) {
                    objectiveFunction += data[0][i][j];
                }
            }
        }
        return objectiveFunction;
    }

}