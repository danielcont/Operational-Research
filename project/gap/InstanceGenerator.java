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

public class InstanceGenerator {

    public static void main(String[] args) {
        InstanceGenerator instance = new InstanceGenerator();
        instance.inputData();
    }

    // Method to ask user for input data. PROMPT ONLY
    protected void inputData() {
        Scanner sc = new Scanner(System.in);
        
        System.out.println("\nGENERATE RANDOM EXPERIMENTAL DATA\n");
        
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

        System.out.print("Minimum resource -> ");
        int rmin = sc.nextInt();

        System.out.print("Maximum resource -> ");
        int rmax = sc.nextInt();

        createFile(instances, m, n, cmin, cmax, rmin, rmax);
    }

    // Method that creates a file.
    private void createFile(int instances, int m, int n, double cmin, double cmax, int rmin, int rmax) {
        do {
            String path = "";
            try {
                // Delay to get different time in the instances file name.
                Thread.sleep(1000); // Delay of 1 second
                DateFormat dateFormat = new SimpleDateFormat("yyyy MM dd - HH mm ss");
                Date date = new Date();
                String strDate = dateFormat.format(date);
                path = path + m + "x" + n + " " + strDate + ".txt";
                try {
                    // Path to access to the 'instances' folder.
                    File f = new File(System.getProperty("user.dir"));
                    File Parent = f.getParentFile();
                    String instances_folder = Parent.getPath() + File.separator + "instances";

                    FileWriter instance = new FileWriter(new File(instances_folder, path));
                    
                    int[][][] values = new int[2][m][n];
                    String txt = ""; 

                    // Write cost and resources needed for each task to each agent.
                    for(int table = 0; table < 2; table++) {
                        for(int i = 0; i < m; i++) {
                            for(int j = 0; j < n; j++) {
                                int random_value;
                                if(table == 0) {
                                    random_value = (int)(Math.random() * (cmax - cmin + 1) + cmin);
                                } else {
                                    random_value = (int)(Math.random() * (rmax - rmin + 1) + rmin);
                                } 
                                
                                values[table][i][j] = random_value;
                                txt = values[table][i][j] + "\t";
                                instance.write(txt);
                            }
                            instance.write("\n");
                        }
                        instance.write("\n");
                    }

                    // Write resources available from agents.
                    float sum_resources;
                    float d = (float)(5 * m + 25) / (float)(15 * (m + 1));
                    for(int i = 0; i < m; i++) {
                        sum_resources = 0;
                        for(int j = 0; j < n; j++) {
                            sum_resources += values[1][i][j];
                        }
                        sum_resources /= m;
                        int bi = (int)Math.round(((n / m) * 0.85 * d * sum_resources));
                        instance.write(bi + "\n");
                    }

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
