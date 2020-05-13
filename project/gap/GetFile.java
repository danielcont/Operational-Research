
/*
Class that get all the instances from the instances folder and return the data.
*/
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GetFile {

    public static void main(String[] args) {
        GetFile readFile = new GetFile();
        try {
            readFile.getFile();
        } catch (IOException e) {
            System.out.println("An error ocurred");
            e.printStackTrace();
        }
    }
    
    protected int[][][] getFile() throws IOException {
        // Accesing to the instances folder.
        File f = new File(System.getProperty("user.dir"));
        File Parent = f.getParentFile();
        String instances_folder = Parent.getPath() + File.separator + "instances";

        final File folder = new File(instances_folder);
        File[] listOfFiles = folder.listFiles();

        // Printing all posible options for the user.
        System.out.println("\nAvailable Files:");
        for(int i = 0; i < listOfFiles.length; i++) {
            if(listOfFiles[i].isFile() && (i != 0)) {
                System.out.println(i + ". " + listOfFiles[i].getName());
            }
        }

        // User is able to choose one file
        System.out.println();
        int option = 0;
        boolean flag;
        do {
            try {
                Scanner sc = new Scanner(System.in);
                System.out.print("Choose a file that you want to solve: -> ");
                option = sc.nextInt();
                if((option <= 0) && (option > listOfFiles.length - 1)) {
                    System.out.println("** Please choose an existing file **");
                    flag = true;
                } else {
                    flag = false;
                }
            } catch(Exception e) {
                System.out.println("Enter an integer value...");
                flag = true;
            }
        } while(flag);
        int index_m = listOfFiles[option].getName().indexOf('x');
        int m = Integer.parseInt(listOfFiles[option].getName().substring(0, index_m));
        int index_n = listOfFiles[option].getName().indexOf(' ');
        int n = Integer.parseInt(listOfFiles[option].getName().substring(index_m + 1, index_n));

        int[][][] data = new int[3][m][n];

        // The file is being readed
        List<String> list = new ArrayList<String>();
        try {
            list = Files.readAllLines(listOfFiles[option].toPath(), Charset.defaultCharset());
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        int i = 0, j = 0;
        for(String listItem : list) {
            String[] arr = listItem.split("\t");
            if(!listItem.equals("")) {
                for(int k = 0; k < arr.length; k ++) {
                    data[i][j][k] = Integer.parseInt(arr[k]);
                }
                j ++;
            } else {
                i ++;
                j = 0;
            }
        }

        return data;
    }
}