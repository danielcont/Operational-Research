import java.lang.StrictMath;
import java.util.stream.IntStream;
import java.util.Arrays;

public class hw_4 {
    
    public static void main(String[] args) {
        hw_4 tsp = new hw_4();
        int[] tour = {1, 2, 3, 4, 5, 6, 7, 8};
        int[][] distance = tsp.get_distances();
        //System.out.println(Arrays.deepToString(distance));
        
        tsp.first_found(tour, distance);
    }

    private int[][] get_distances(){
        double[][] coordinates = {
            {86, 37},
            {17, 94},
            {3, 65},
            {48, 43},
            {78, 70},
            {17, 55},
            {62, 91},
            {78, 91},
        };

        int[][] distance = new int[8][8];
        for(int i = 0; i < coordinates.length; i++) {
            for(int j = 0; j < coordinates.length; j++) {
                int hypotlen = (int)(StrictMath.hypot((coordinates[j][0] - coordinates[i][0]), (coordinates[j][1] - coordinates[i][1])));
                if(hypotlen != 0) { distance[i][j] = hypotlen; } else { distance[i][j] = 0; }
            }
        }

       return distance;
    }

    // First Found method
    private void first_found(int[] tour, int[][] distance) {
        int[] actual_tour = tour.clone();;
        boolean exit = false;
        int best_distance = calculate_distance(actual_tour, distance);
        System.out.println("First tour:" + Arrays.toString(actual_tour) +"First distance: " + best_distance);
        do {
            best_distance = calculate_distance(actual_tour, distance);
            outerloop:
            for(int i = 1; i <= tour.length - 1; i++) {
                for(int j = i + 1; j < tour.length; j++) {
                    int[] new_tour = optSwap(tour, i, j);
                    int new_distance = calculate_distance(new_tour, distance);
                    System.out.println(Arrays.toString(new_tour) + "\t" + new_distance);
                    if(new_distance < best_distance) {
                        actual_tour = new_tour;
                        System.out.println("\nThis is the best tour:" + Arrays.toString(new_tour) + "\t" + new_distance);
                        break outerloop;
                    }
                }
            }
            if(exit == false) {
                actual_tour = null;
                exit = true;
            }
        } while(exit == false);
    }

    // 2-OPT method to swap tour
    private int[] optSwap(int[] tour, int i, int j) {
        int[] new_tour = new int[tour.length];

        int[] slice_1 = Arrays.copyOfRange(tour, 0, i - 1); // take tour from tour[0] to tour[i - 1]
        int[] slice_2 = Arrays.copyOfRange(tour, i, j); // take tour from tour[i] to tour[j] and make a reversed version
        for(int k = 0; k < slice_2.length/2; k++){ 
            int temp = slice_2[k]; 
            slice_2[k] = slice_2[slice_2.length - k - 1]; 
            slice_2[slice_2.length - k - 1] = temp; 
        }
        int[] slice_3 = Arrays.copyOfRange(tour, j - 1, tour.length); // The the rest of the tour
        
        //System.out.println(Arrays.toString(slice_1));
        //System.out.println(Arrays.toString(slice_2));
        //System.out.println(Arrays.toString(slice_3));

        new_tour = IntStream.concat(Arrays.stream(slice_1), IntStream.concat(Arrays.stream(slice_2), Arrays.stream(slice_3))).toArray();
       
        return new_tour;
    }

    private int calculate_distance(int[] tour, int[][] distance) {
        int new_distance = 0;
        for(int i = 0; i < tour.length; i++) {
            if(i != (tour.length - 1)) {
                new_distance += distance[i][i + 1];
            } else {
                new_distance += distance[0][i];
            }
        }

        return new_distance;
    }

}