import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;
import java.nio.charset.Charset;
import java.nio.file.Files;

public class Heuristic {

    public static void main(String[] args) {
        Heuristic solve = new Heuristic();

        try {
            Data[] data = solve.read_file();
            System.out.println("Obtiene datos");

            solve.heuristic_values(data);
            solve.heuristic_weight(data);
            solve.heuristic_ratio(data);
            

        } catch (IOException e) {
            System.out.println("An error ocurred.");
            e.printStackTrace();
        }

    } 

    private Data[] read_file() throws IOException {
        Data[] data = new Data[1];
        
        try {
            File instance = new File("instance.txt");

            BufferedReader reader = new BufferedReader(new FileReader("instance.txt"));
            int lines = 0;
            while (reader.readLine() != null) lines++;
            reader.close();

            int items = 0;
            double capacity = 0;
            int[] index = new int[lines - 1];
            int[] values = new int[lines - 1];
            int[] weight = new int[lines - 1];
            int count = 0;


            List<String> list = new ArrayList<String>();
            try { 
                list = Files.readAllLines(instance.toPath(), Charset.defaultCharset());
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            for(String line : list) {
                if(count != 0) {
                    String[] arr = line.split("\t");
                    index[count - 1] = Integer.parseInt(arr[0]);
                    values[count - 1] = Integer.parseInt(arr[1]);
                    weight[count -1] = Integer.parseInt(arr[2]);

                } else {
                    String[] arr = line.split("\t");
                    items = Integer.parseInt(arr[0]);
                    capacity = Double.parseDouble(arr[1]);
                }
                
                count ++;
            }

            Data dat = new Data(items, capacity, index, values, weight);
            data[0] = dat;


        } catch(FileNotFoundException e) {
            System.out.println("An error ocurred.");
            e.printStackTrace();
        }
        return data;
    }

    private void heuristic_values(Data[] data) {
        int items = data[0].getItems();
        double capacity = data[0].getCapacity();
        int[] index = data[0].getIndex();
        int[] values = data[0].getValues();
        int[] weight = data[0].getWeight();

        for(int i = 0; i < items; i++) {
            for(int j = i + 1; j < items; j++) {
                if((values[i] < values[j]) && (i != j)) {
                    int t_values = values[j];
                    values[j] = values[i];
                    values[i] = t_values;

                    int t_index = index[j];
                    index[j] = index[i];
                    index[i] = t_index;

                    int t_weight= weight[j];
                    weight[j] = weight[i];
                    weight[i] = t_weight;
                }
            }
        }

        int current_weight = 0;
        int x = 0;
        for(int i = 0; i < items; i++) {
            if(weight[i] <= capacity) {
                capacity -= weight[i];
                current_weight += weight[i];
                x += values[i];
            } else {
                break;
            }
        }

        System.out.println("Objective function sorting values: " + x);

    }

    private void heuristic_weight(Data[] data) {
        int items = data[0].getItems();
        double capacity = data[0].getCapacity();
        int[] index = data[0].getIndex();
        int[] values = data[0].getValues();
        int[] weight = data[0].getWeight();

        
        for(int i = 0; i < items; i++) {
            for(int j = i + 1; j < items; j++) {
                if((weight[i] > weight[j]) && (i != j)) {
                    int t_weight = weight[j];
                    weight[j] = weight[i];
                    weight[i] = t_weight;

                    int t_index = index[j];
                    index[j] = index[i];
                    index[i] = t_index;

                    int t_values = values[j];
                    values[j] = values[i];
                    values[i] = t_values;
                }
            }
        }

        int current_weight = 0;
        int x = 0;
        for(int i = 0; i < items; i++) {
            if(weight[i] <= capacity) {
                capacity -= weight[i];
                current_weight += weight[i];
                x += values[i];
            } else {
                break;
            }
        }

        System.out.println("Objective function sorting weight: " + x);

    }

    public void heuristic_ratio(Data[] data) {
        int items = data[0].getItems();
        double capacity = data[0].getCapacity();
        int[] index = data[0].getIndex();
        int[] values = data[0].getValues();
        int[] weight = data[0].getWeight();

        double[] ratio = new double[items];

        for(int i = 0; i < items; i++) {
            if(values[i] != 0) {
                ratio[i] = ((double)values[i]) / weight[i];
            }
        }

        for(int i = 0; i < items; i++) {
            for(int j = 0; j < items; j++) {
                if((ratio[i] > ratio[j]) && (i != j)) {
                    double t_ratio = ratio[j];
                    ratio[j] = ratio[i];
                    ratio[i] = t_ratio;

                    int t_weight = weight[j];
                    weight[j] = weight[i];
                    weight[i] = t_weight;

                    int t_index = index[j];
                    index[j] = index[i];
                    index[i] = t_index;

                    int t_values = values[j];
                    values[j] = values[i];
                    values[i] = t_values;
                }
            }
        }

        int current_weight = 0;
        int x = 0;
        for(int i = 0; i < items; i++) {
            if(weight[i] <= capacity) {
                capacity -= weight[i];
                current_weight += weight[i];
                x += values[i];
            } else {
                break;
            }
        }

        System.out.println("Objective function sorting ratio: " + x);

        for(int i = 0; i < items; i++) {
            System.out.println(index[i] + "\t" + values[i] + "\t" + weight[i] + "\t" + ratio[i]);
        }

    }

}