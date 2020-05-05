/*
This class will find a feasible solution for the Generalized Assignment Problem
*/
import java.io.IOException;

public class ConstructiveHeuristic {

    protected void constructiveHeuristic() {
        GetFile read_file = new GetFile();

        try {
            int[][][] data = read_file.getFile();
        } catch(IOException ex) {
            System.out.println("An error ocurred");
            ex.printStackTrace();
        }
    }

    protected int greedyHeuristic(int[][][] data) {
        


        return 3;
    } 
    
}