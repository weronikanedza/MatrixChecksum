import java.util.List;
import java.util.Scanner;

public class Matrix {
    private int A[][],B[][];
    private int bR[][],aR[][]; //reversed matrix A and B
    private int res[][],resRev[][],resMulDiv[][]; //res = result of operation on A and B /resRev reversed result
    private int Bmulti[][];
    private int rowNumber, columnNumber;
    private Checksum checksum;

    public Matrix(){ }

    public Matrix(List<Integer> fileData){
        this(fileData.get(0),fileData.get(1));
        getMatrixFromFile(fileData);
        this.aR = reverseMatrix(A, rowNumber, columnNumber);
        this.bR = reverseMatrix(B, rowNumber, columnNumber);
    }

    public Matrix(int A[][],int B[][],int rowNumber,int columnNumber){
        this(rowNumber,columnNumber);
        this.A=A;
        this.B=B;
        this.aR = reverseMatrix(A, rowNumber, columnNumber);
        this.bR = reverseMatrix(B, rowNumber, columnNumber);
    }

    public Matrix(int rowNumber,int columnNumber){
        this.rowNumber=rowNumber;
        this.columnNumber=columnNumber;
        this.res=new int[columnNumber][rowNumber];
        this.resRev=new int[rowNumber][columnNumber];
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

    public int[] add(){
        for (int i=0 ; i < columnNumber; i++ ){
            for (int j=0 ; j < rowNumber ; j++ ) {
                res[i][j] = A[i][j] + B[i][j];
            }
        }

        return checksum.checkMatrixAdd(A,B,res);
    }

    public int[] checkAdd(int[][] A, int [][] B, int[][] res){
        this.A = A;
        this.B = B;
        this.aR = reverseMatrix(A, rowNumber, columnNumber);
        this.bR = reverseMatrix(B, rowNumber, columnNumber);
        this.res = res;
        return checksum.checkMatrixAdd(A,B,res);
    }

    public int[] checkSub(int[][] A, int [][] B, int[][] res){
        this.A = A;
        this.B = B;
        this.aR = reverseMatrix(A, rowNumber, columnNumber);
        this.bR = reverseMatrix(B, rowNumber, columnNumber);
        this.res = res;
        return checksum.checkMatrixSubstract(A,B,res);
    }
    /*
     adding reversed matrix
     */
    public int[] addRev(){
        aR=reverseMatrix(A,rowNumber,columnNumber); //reverse A before adding
        bR=reverseMatrix(B,rowNumber,columnNumber); //reverse before adding

        add();

        for (int i=0 ; i < rowNumber ; i++ ){
            for (int j=0 ; j < columnNumber ; j++ )
                resRev[i][j]=aR[i][j]+bR[i][j];
        }

        return checksum.checkMatrixReversed(res, reverseMatrix(resRev,columnNumber,rowNumber));
    }

    public int[] substract(){
        for (int i=0 ; i < columnNumber ; i++ ){
            for (int j=0 ; j< rowNumber ; j++ )
                res[i][j]=A[i][j]-B[i][j];
        }
        return checksum.checkMatrixSubstract(A,B,res);
    }

    public int[] substractRev(){
        aR=reverseMatrix(A,rowNumber,columnNumber); //reverse A before adding
        bR=reverseMatrix(B,rowNumber,columnNumber); //reverse before adding

        substract();

        for (int i=0 ; i < rowNumber ; i++ ){
            for (int j=0 ; j < columnNumber ; j++ )
                resRev[i][j]=aR[i][j]-bR[i][j];
        }

        return checksum.checkMatrixReversed(res, reverseMatrix(resRev,columnNumber,rowNumber));
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
        add();
        showAll();
        checksum.checkMatrixAdd(A,B,res);

        System.out.println("-----------ODEJMOWANIE----------");
        substract();
        showAll();
        checksum.checkMatrixSubstract(A,B,res);

        System.out.println("----------ODWRÓCONE-------------");
        addRev();
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
        int temp[][]=new int[row][column];

        for(int i=0;i<column;i++){
            for(int j=0;j<row;j++){
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

    public int getRowNumber() {
        return rowNumber;
    }

    public int getColumnNumber() {
        return columnNumber;
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

    public int[][] getbR() {
        return bR;
    }

    public int[][] getaR() {
        return aR;
    }

    public int[][] getResRev() {
        return resRev;
    }

    public int[] getAsum() {
        return checksum.getAsum();
    }

    public int[] getBsum() {
        return checksum.getBsum();
    }

    public int[] getResum() {
        return checksum.getResum();
    }

    public int[] getReRevsum() {
        return checksum.getReRevsum();
    }
}

