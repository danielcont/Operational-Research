import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.Scanner;

public class Heuristic {

    public static void main(String[] args) {
        Heuristic solve = new Heuristic();
        ArrayList<Data> readItems = new ArrayList<Data>();
        Scanner sc = new Scanner(System.in);
        System.out.print("Name of the instance file -> ");
        String file_path = sc.nextLine();

        try {
            ArrayList<Data> data = solve.read_file(file_path);
            solve.option(data, file_path);

            boolean terminate = false;
            do {
                System.out.print("Would you like to run a different Heuristic? (y/n) -> ");
                String option2 = sc.nextLine();
                switch(option2) {
                    case "y":
                        solve.option(data, file_path);
                        break;
                    case "n":
                        terminate = true;
                        break;
                    default:
                        System.out.println("Option not recognized");
                }
            } while(terminate != true);
            
        } catch (IOException e) {
            System.out.println("An error ocurred.");
            e.printStackTrace();
        }
    }

    private ArrayList<Data> read_file(String file_path) throws IOException {
        ArrayList<Data> readItems = new ArrayList<Data>();
        try {
            File instance = new File(file_path);

            BufferedReader reader = new BufferedReader(new FileReader(file_path));
            int lines = 0;
            while (reader.readLine() != null) lines++;
            reader.close();

            int index, values, weight, items;
            double ratio, capacity;
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
                    index = Integer.parseInt(arr[0]);
                    values = Integer.parseInt(arr[1]);
                    weight = Integer.parseInt(arr[2]);   
                    ratio = ((double)values) / weight;
                    Data dat = new Data(index, values, weight, ratio);
                    readItems.add(dat);
                } else {
                    String[] arr = line.split("\t");
                    items = Integer.parseInt(arr[0]);
                    capacity = Double.parseDouble(arr[1]);
                }
                count ++;
            }
        } catch(FileNotFoundException e) {
            System.out.println("An error ocurred.");
            e.printStackTrace();
        }
        return readItems;
    }


    private void option(ArrayList<Data> data, String file_path) {
        File instance = new File(file_path);
        int items = 0;
        double capacity = 0.0;
        List<String> list = new ArrayList<String>();
        try { 
            list = Files.readAllLines(instance.toPath(), Charset.defaultCharset());
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        int count = 0;
        for(String line : list) {
            if(count == 0) {
                String[] arr = line.split("\t");
                items = Integer.parseInt(arr[0]);
                capacity = Double.parseDouble(arr[1]);
            } else { break; }
            count ++;
        }
        Heuristic solve = new Heuristic();
        System.out.print("Which Heuristic would you like tu run?\n1. Sort by Values\n2. Sort by Weight\n3. Sort by Ratio\n-> ");
        Scanner sc = new Scanner(System.in);
        int option = sc.nextInt();
        boolean terminate = false;
        switch(option) {
            case 1:
                solve.heuristic_values(data, items, capacity);
                break;
            case 2:
                solve.heuristic_weight(data, items, capacity);
                break;
            case 3:
                solve.heuristic_ratio(data, items, capacity);
                break;
            default:
                System.out.println("Option not recognized");
        }
    }

    private void heuristic_values(ArrayList<Data> data, int items, double capacity) {
        Collections.sort(data, Data.itemValuesComparator);
        int x = heuristic(data, items, capacity);
        System.out.println("Objective function sorting values: " + x);
    }

    private void heuristic_weight(ArrayList<Data> data, int items, double capacity) {
        Collections.sort(data, Data.itemWeightComparator);
        int x = heuristic(data, items, capacity);
        System.out.println("Objective function sorting weight: " + x);
    }

    private void heuristic_ratio(ArrayList<Data> data, int items, double capacity) {
        Collections.sort(data, Data.itemRatioComparator);
        int x = heuristic(data, items, capacity);
        System.out.println("Objective function sorting ratio: " + x);
    }

    private int heuristic(ArrayList<Data> data, int items, double capacity) {
        int current_weight = 0;
        int x = 0;
        double new_capacity = capacity;

        for(int i = 0; i < items; i++) {
            if(data.get(i).getWeight() <= new_capacity) {
                new_capacity -= data.get(i).getWeight();
                current_weight += data.get(i).getWeight();
                x += data.get(i).getValues();
            } else {
                break;
            }
        }
        return x;
    }
}