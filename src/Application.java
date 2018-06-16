import java.util.Scanner;

public class Application {
    private Scanner scanner;
    private Matrix matrix;
    private Checksum checksum;

    public Application(){
        scanner=new Scanner(System.in);
        matrix=new Matrix();
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
        System.out.println("Datafile");
    };
    private void inputData(){ System.out.println("inputData");};
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

    private void check(int rows,int columns){
        Checksum checksum=new Checksum(rows,columns);
        if(checksum.checkMatrixAdd(matrix.getA(),matrix.getB(),matrix.getRes()))
            System.out.println("SUMY KONTROLNE PRAWIDŁOWE");
        else  System.out.println("SUMY KONTROLNE NIEPRAWIDŁOWE");
    }
}
