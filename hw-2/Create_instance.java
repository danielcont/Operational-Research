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
            int i;

        do {
            i = 0;
            System.out.print("How many items do you have? -> ");
            n = sc.nextInt();
            if(n > 0) {
                System.out.println(i);
            } else { System.out.println("** Data must be positive integer **"); }

        } while (i == 0);

        do {
            i = 0;
            System.out.print("Minimum value -> ");
            vmin = sc.nextInt();
            if(vmin > 0) {
                i = 1;
            } else { System.out.println("** Data must be positive integer **"); }

        } while (i == 0);

        do {
            i = 0;
            System.out.print("Maximum value -> ");
            vmax = sc.nextInt();
            if(vmax > vmin) {
                i = 1;
            } else { System.out.println("** The maximum value must be greater than the minimum value **"); }

        } while (i == 0);

        do {
            i = 0;
            System.out.print("Minimum weight -> ");
            wmin = sc.nextInt();
            if(wmin > 0) {
                i = 1;
            } else { System.out.println("** Data must be positive integer **"); }

        } while (i == 0);

        do {
            i = 0;
            System.out.print("Maximum value -> ");
            wmax = sc.nextInt();
            if(wmax > wmin) {
                i = 1;
            } else { System.out.println("** The maximum value must be greater than the minimum value **"); }

        } while (i == 0);

        Create_instance create_file = new Create_instance();
        create_file.file(n, vmin, vmax, wmin, wmax);
    }

    private void file(int n, int vmin, int vmax, int wmin, int wmax) {
        String text = "";
        Scanner sc = new Scanner(System.in);
        String file_path = "";
        System.out.print("Give a name to the file -> ");
        file_path = sc.nextLine();

        try {
            FileWriter instance = new FileWriter(file_path);
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