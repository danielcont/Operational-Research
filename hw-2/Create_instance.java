import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Create_instance {

    public static void main(String[] args) {
        Create_instance input = new Create_instance();
        input.range();
    }

    private void range() {
        int vmax, vmin, wmax, wmin, n;
        Scanner sc = new Scanner(System.in);
        System.out.println("How many items do you have? -> ");
        n = sc.nextInt();
        System.out.println("Minimum value -> ");
        vmin = sc.nextInt();
        System.out.println("Maximum value -> ");
        vmax = sc.nextInt();
        System.out.println("Minimum weight -> ");
        wmin = sc.nextInt();
        System.out.println("Maximum weight -> ");
        wmax = sc.nextInt();

        Create_instance create_file = new Create_instance();
        create_file.file(n, vmin, vmax, wmin, wmax);
    }

    private void file(int n, int vmin, int vmax, int wmin, int wmax) {
        String text = "";

        try {
            FileWriter instance = new FileWriter("instance.txt");
            for (int i = 0; i <= n; i++) {
                if(i != 0) {
                    int random_value = (int)(Math.random() * (vmax - vmin + 1) + vmin);
                    int random_weight = (int)(Math.random() * (wmax - wmin + 1) + wmin);

                    text = i + "\t" + random_value + "\t" + random_weight + "\n";

                    instance.write(text); 
                } else {
                    double capacity = (n * ((vmax + vmin) / 2) * 0.3);
                    text = n + "\t" + capacity + "\n";
                    instance.write(text);
                }
                  
            }

            instance.close();

        } catch(IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();

        }
    }



}