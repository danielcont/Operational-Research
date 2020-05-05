/*
This is the main class where the user can create new instances and also get the objective function from them.
*/
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        //Main main = new Main();

        InstanceGenerator generator = new InstanceGenerator();
        ConstructiveHeuristic constructive = new ConstructiveHeuristic();
        //LocalSearchHeuristic localSearch = new LocalSearchHeuristic();

        int op;
        do {
            System.out.println("\nGENERALIZED ASSIGNMENT PROBLEM\n");
            System.out.print("Choose an option\n1. Generate Instances\n2. Constructive Heuristic\n3. Local Search Heuristic\n4. Exit\n-> ");
            Scanner sc = new Scanner(System.in);
            op = sc.nextInt();
            switch(op) {
                case 1:
                    generator.inputData();
                    break;
                case 2:
                    constructive.constructiveHeuristic();
                    break;
                case 3:
                    //localSearch.localSearchHeuristic;
                    break;
                case 4:
                    break;
                default:
                    System.out.println("Option not valid");
                    break;
    
            }
        } while(op != 4);
        

    }
}