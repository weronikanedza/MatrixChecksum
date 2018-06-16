import java.io.BufferedReader;
import java.io.IOException;
import java.io.*;public class FileReader {

    public static void main(String[] args) throws IOException {
        File file = new File("matrix.txt");
        try (FileInputStream fis = new FileInputStream(file)) {
            int content;
            while ((content = fis.read()) != -1) {
                // convert to char and display it
                if ((char)content==' ')
                    System.out.println(".");
                else
                System.out.print((char) content);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
