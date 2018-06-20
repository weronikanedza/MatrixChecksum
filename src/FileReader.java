import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileReader {

    public List<Integer> getDataFromFile(String fileName){
        System.out.println("JESTEM");
        Scanner scanner;
        List<Integer> fileData=new ArrayList<>();
            try {
                scanner = new Scanner(new File(fileName));
                while (scanner.hasNextInt()) {
                    fileData.add(scanner.nextInt());
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                return null;
            }

            if(fileData.size() > 2 && (fileData.get(0) * fileData.get(1)) * 2 + 2 == fileData.size())
                return fileData;

       return null;
    }
}
