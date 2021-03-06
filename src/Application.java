import java.util.List;
import java.util.Scanner;

public class Application {
    private Scanner scanner;
    private Matrix matrix;
    private FileReader fileReader;

    public Application(){
        this.scanner=new Scanner(System.in);
        this.matrix=new Matrix();
        this.fileReader=new FileReader();
    }
    public void chooseOption(){
        int option=0;
        while (option!=-1){
            showMenu();
            System.out.print("Wybierz opcje: ");
            option=scanner.nextInt();
            switch (option){
                case 1:
                    fileData();
                    break;
                case 2:
                    inputData();
                    break;
                case 3:
                    randomData();
                    break;
                default:
                    option=-1;
                    break;
            }
        }
    }
    private void fileData(){
        List<Integer> fileData=fileReader.getDataFromFile("matrix.txt");
        Matrix matrix=new Matrix(fileData);
        matrix.allOperation();
    };
    private void inputData(){
        System.out.println("\nPodaj wymiary macierzy:");
        int rows=scanner.nextInt();
        int columns=scanner.nextInt();
        Matrix matrix=new Matrix(rows,columns);
        matrix.getMatrixFromInput();
        matrix.allOperation();
    };
    private void randomData(){
        System.out.println("\nPodaj wymiary macierzy:");
        int rows=scanner.nextInt();
        int columns=scanner.nextInt();
        Generator generator=new Generator();
        Matrix matrix=
              new Matrix(generator.generateMatrix(rows,columns),
                      generator.generateMatrix(rows,columns),rows,columns); //generate matrix to tests
       matrix.allOperation();  //make all operations add/subtract idk
    };
    private void showMenu(){
        System.out.println("\nMENU");
        System.out.println("1-data from file");
        System.out.println("2-input data");
        System.out.println("3-random data");
    }
}
