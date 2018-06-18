import java.util.List;
import java.util.Scanner;

public class Matrix {
    private int A[][],B[][];
    private int bR[][],aR[][]; //reversed matrix A and B
    private int res[][],resRev[][],resMulDiv[][]; //res = result of operation on A and B /resRev reversed result
    private int Bmulti[][];
    private int rowNumber;
    private int columnNumber;
    private Checksum checksum;

    public Matrix(){ }

    public Matrix(List<Integer> fileData){
        this(fileData.get(0),fileData.get(1));
        getMatrixFromFile(fileData);
    }

    public Matrix(int A[][],int B[][],int rowNumber,int columnNumber){
        this(rowNumber,columnNumber);
        this.A=A;
        this.B=B;
        this.rowNumber=rowNumber;
        this.columnNumber=columnNumber;
    }

    public Matrix(int rowNumber,int columnNumber){
        this.rowNumber=rowNumber;
        this.columnNumber=columnNumber;
        this.res=new int[rowNumber][columnNumber];
        this.resRev=new int[columnNumber][rowNumber];
        this.checksum=new Checksum(rowNumber,columnNumber);
        this.A=new int[rowNumber][columnNumber];
        this.B=new int[rowNumber][columnNumber];
        this.Bmulti=new int[columnNumber][rowNumber];
        this.resMulDiv=new int[rowNumber][rowNumber];
        fillBmulti();
    }
    /*
    get matrix A and B from list which is generated from file
    */
    private void getMatrixFromFile(List<Integer> fileData) {
        int iterator=2;

        for (int i=0;i<rowNumber;i++){
            for (int j=0;j<columnNumber;j++,iterator++){
                System.out.println(iterator);
                A[i][j]=fileData.get(iterator);
            }
        }

        for (int i=0;i<rowNumber;i++){
            for (int j=0;j<columnNumber;j++,iterator++){
                B[i][j]=fileData.get(iterator);
            }
        }
    }
    /*
    get matrix A and B from input == initialize them
     */
    public void getMatrixFromInput(){
        System.out.println("Podaj wartości macierzy : ");
        Scanner scanner=new Scanner(System.in);
        for (int i=0;i<rowNumber;i++){
            for (int j=0;j<columnNumber;j++){
                A[i][j]=scanner.nextInt();
            }
        }
        System.out.println("Podaj wartości macierzy : ");
        for (int i=0;i<rowNumber;i++){
            for (int j=0;j<columnNumber;j++){
                B[i][j]=scanner.nextInt();
            }
        }
    }

    public void add(int matrixA[][],int matrixB[][],int matrixRes[][]){
        for (int i=0 ; i < rowNumber ; i++ ){
            for (int j=0 ; j<columnNumber ; j++ )
                matrixRes[i][j]=matrixA[i][j]+matrixB[i][j];
        }
    }
    /*
     adding reversed matrix
     */
    public void addRev(int matrixA[][],int matrixB[][],int matrixRes[][]){
        for (int i=0 ; i < columnNumber ; i++ ){
            for (int j=0 ; j<rowNumber ; j++ )
                matrixRes[i][j]=matrixA[i][j]+matrixB[i][j];
        }
    }

    public void multiply(){
        int temp[]=new int[rowNumber*rowNumber];
        int iteratorTemp=0;
        int iteratorBmulti=0;

        for (int i=0;i<rowNumber;i++){
            iteratorBmulti=0;
            for (int x=0;x<rowNumber;x++) {
                for (int j = 0; j < columnNumber; j++) {
                    temp[iteratorTemp]+=A[i][j]*Bmulti[j][iteratorBmulti];
                }
                iteratorTemp++;
                iteratorBmulti++;
            }
        }

        iteratorTemp=0;
        for (int i=0;i<rowNumber;i++){
            for (int j=0;j<rowNumber;j++){
                resMulDiv[i][j]=temp[iteratorTemp++];
            }
        }
    }

    public void fillBmulti(){
        Generator generator=new Generator();
        Bmulti=generator.generateMatrix(columnNumber,rowNumber);
    }

    public void substract(){
        for (int i=0 ; i < rowNumber ; i++ ){
            for (int j=0 ; j<columnNumber ; j++ )
                res[i][j]=A[i][j]-B[i][j];
        }
    }


    public void showMatrix(int A[][],int row,int column){
        for (int i=0;i<row;i++) {
            for (int j = 0; j < column; j++) {
                System.out.print(A[i][j] + " ");
            }
            System.out.println(" ");
        }
    }

    public void allOperation(){
        System.out.println("-----------DODAWANIE----------");
        add(A,B,res);
        showAll();
        checksum.checkMatrixAdd(A,B,res);

        System.out.println("-----------ODEJMOWANIE----------");
        substract();
        showAll();
        checksum.checkMatrixSubstract(A,B,res);

        System.out.println("----------ODWRÓCONE-------------");
        aR=reverseMatrix(A,rowNumber,columnNumber); //reverse A before adding
        bR=reverseMatrix(B,rowNumber,columnNumber); //reverse before adding
        addRev(aR,bR,resRev);
        showAllReversed();
        showAll();
        resRev=reverseMatrix(resRev,columnNumber,rowNumber); //reverse again to compare
        checksum.checkMatrixReversed(res,resRev);

        if(rowNumber==columnNumber)  Bmulti=B;  //dont use genereated matrix if its square

        System.out.println("---------------MNOZENIE------------");
        multiply();
        showMultiply();

        if(rowNumber==columnNumber) {
            System.out.println("--------DZIELENIE-------------");
            Bmulti = reverseMatrix(Bmulti, columnNumber, rowNumber); //reverse matrix B before multiply
            multiply();
            showMultiply();
        }
    }

    public int[][] reverseMatrix(int matrix[][],int row,int column){
        int temp[][]=new int[column][row];

        for(int i=0;i<row;i++){
            for(int j=0;j<column;j++){
                temp[j][i]=matrix[i][j];
            }
        }
        return temp;
    }

    public void showAllReversed(){
        System.out.println("\nMacierz aR");
        showMatrix(aR,columnNumber,rowNumber);
        System.out.println("\nMacierz bR");
        showMatrix(bR,columnNumber,rowNumber);
        System.out.println("\nMacierz ResultReversed");
        showMatrix(resRev,columnNumber,rowNumber);
    }

    public void showAll(){
        System.out.println("\nMacierz A");
        showMatrix(A,rowNumber,columnNumber);
        System.out.println("\nMacierz B");
        showMatrix(B,rowNumber,columnNumber);
        System.out.println("\nMacierz Result");
        showMatrix(res,rowNumber,columnNumber);
    }

    public void showMultiply(){
        System.out.println("\nMacierz A");
        showMatrix(A,rowNumber,columnNumber);
        System.out.println("\nMacierz Bmulti");
        showMatrix(Bmulti,columnNumber,rowNumber);
        System.out.println("\nMacierz Result");
        showMatrix(resMulDiv,rowNumber,rowNumber);
    }
    public int[][] getA() {
        return A;
    }

    public int[][] getB() {
        return B;
    }

    public int[][] getRes() {
        return res;
    }

}

