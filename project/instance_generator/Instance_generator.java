/*
Class that generates random instances for the Generalized Assignment Problem and store them in CSV files.
*/
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.io.FileWriter;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.Date;

public class Instance_generator {

    public static void main(String[] args) {
        Instance_generator instance = new Instance_generator();
        instance.input_data();
    }

    // Method to ask user for input data. PROMPT ONLY
    private void input_data() {
        Scanner sc = new Scanner(System.in);

        System.out.print("How many instances do you want? -> ");
        int instances = sc.nextInt();

        System.out.print("Number of agents available -> ");
        int m = sc.nextInt();

        System.out.print("Number of tasks to be assigned -> ");
        int n = sc.nextInt();

        System.out.print("Minimum cost - > ");
        double cmin = sc.nextDouble();

        System.out.print("Maximum cost -> ");
        double cmax = sc.nextDouble();

        create_file(instances, m, n, cmin, cmax);
    }

    // Method that creates a file
    private void create_file(int instances, int m, int n, double cmin, double cmax) {
        do {
            String path = "";
            try {
                // Delay to get different time in the instances file name
                Thread.sleep(1000);
                DateFormat dateFormat = new SimpleDateFormat("yyyy MM dd - HH mm ss");
                Date date = new Date();
                String strDate = dateFormat.format(date);
                path = path + m + "x" + n + " " + strDate + ".txt";
                try {
                    // Path to access to the 'instances' folder
                    File f = new File(System.getProperty("user.dir"));
                    File Parent = f.getParentFile();
                    String instances_folder = Parent.getPath() + File.separator + "instances";

                    FileWriter instance = new FileWriter(new File(instances_folder, path));

                    String text = ("Hello, this instance #" + instances);

                    instance.write(text);

                    instance.close();
                    
                } catch(IOException e) {
                    System.out.println("An error ocurred.");
                    e.printStackTrace();
                }

            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }

            instances -= 1;
        } while(instances != 0);

    }
}
