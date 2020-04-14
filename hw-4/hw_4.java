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
        int[] subTour = tour.clone();
        int[] slice = {subTour[0]};
        int[] new_tour = IntStream.concat(Arrays.stream(subTour), Arrays.stream(slice)).toArray();

        int best_distance = calculate_distance(new_tour, distance);
        System.out.println("First tour: " + Arrays.toString(new_tour) + "\t" + best_distance);

        int[] actual_tour = new_tour.clone();
        
        int exit = 0;
        do {
            best_distance = calculate_distance(new_tour, distance);
            exit = 0;
            outerloop:
            for(int i = 0; i < tour.length; i++) {
                for(int j = i + 1; j < tour.length - 1; j++) {
                    new_tour = optSwap(actual_tour, i, j);
                    int new_distance = calculate_distance(new_tour, distance);
                    System.out.println(Arrays.toString(new_tour) + "\t" + new_distance);
                    if(new_distance < best_distance) {
                        actual_tour = new_tour;
                        best_distance = new_distance;
                        System.out.println("\nThis is the best tour:" + Arrays.toString(new_tour) + "\t" + new_distance);
                        break outerloop;
                    }
                    exit += 1;
                }
            }
        } while(exit <= 20);
    }

    // 2-OPT method to swap tour
    private int[] optSwap(int[] tour, int i, int j) {
        int[] new_tour = new int[tour.length];
        int[] slice_1 = Arrays.copyOfRange(tour, 0, i); // take tour from tour[0] to tour[i - 1]
        int[] slice_2 = Arrays.copyOfRange(tour, i, j); // take tour from tour[i] to tour[j] and make a reversed version
        for(int k = 0; k < slice_2.length/2; k++){ 
            int temp = slice_2[k]; 
            slice_2[k] = slice_2[slice_2.length - k - 1]; 
            slice_2[slice_2.length - k - 1] = temp; 
        }
        int[] slice_3 = Arrays.copyOfRange(tour, j, tour.length); // The the rest of the tour
        
        //System.out.println(Arrays.toString(slice_1));
        //System.out.println(Arrays.toString(slice_2));
        //System.out.println(Arrays.toString(slice_3));

        new_tour = IntStream.concat(Arrays.stream(slice_1), IntStream.concat(Arrays.stream(slice_2), Arrays.stream(slice_3))).toArray();
        
        //int[] slice_4 = {new_tour[0]};
        //int[] final_tour = IntStream.concat(Arrays.stream(new_tour), Arrays.stream(slice_4)).toArray();
        
        new_tour[new_tour.length - 1] = new_tour[0];

        return new_tour;
    }

    private int calculate_distance(int[] tour, int[][] distance) {
        int new_distance = 0;
        for(int i = 0; i < tour.length; i++) {
            if(i != (tour.length - 1)) {
                int x = tour[i] - 1;
                int y = tour[i + 1] - 1;
                new_distance += distance[x][y];
            } else {
                int x = tour[i] - 1;
                int y = tour[0] - 1;
                new_distance += distance[x][y];
            }
        }

        return new_distance;
    }

}