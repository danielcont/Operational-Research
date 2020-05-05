/*
Class that get all the instances from the instances folder and return the data
*/
import java.io.File;
import java.util.List;

public class GetFile {

    public static void main(String[] args) {
        GetFile readFile = new GetFile();
        readFile.getFile();
    }
    
    protected void getFile() {
        // Accesing to the instances folder
        File f = new File(System.getProperty("user.dir"));
        File Parent = f.getParentFile();
        String instances_folder = Parent.getPath() + File.separator + "instances";

        final File folder = new File(instances_folder);
        File[] listOfFiles = folder.listFiles();

        for(int i = 0; i < listOfFiles.length; i++) {
            if(listOfFiles[i].isFile()) {
                System.out.println(listOfFiles[i].getName());
            }
        }

        //int[][][] data = new int[2][2][2];


        //return data;
    }
}