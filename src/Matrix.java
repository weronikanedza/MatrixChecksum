public class Matrix {
    private int A[][],B[][];
    private int bR[][],aR[][];
    private int res[][],resRev[][];
    private int rowNumber;
    private int columnNumber;
    private Checksum checksum;

    public Matrix(){}

    public Matrix(int A[][],int B[][],int rowNumber,int columnNumber){
        this.A=A;
        this.B=B;
        this.rowNumber=rowNumber;
        this.columnNumber=columnNumber;
        this.res=new int[rowNumber][columnNumber];
        this.resRev=new int[columnNumber][rowNumber];
        this.checksum=new Checksum(rowNumber,columnNumber);
    }

    public void add(int matrixA[][],int matrixB[][],int matrixRes[][]){
        for (int i=0 ; i < rowNumber ; i++ ){
            for (int j=0 ; j<columnNumber ; j++ )
                matrixRes[i][j]=matrixA[i][j]+matrixB[i][j];
        }
    }

    public void addRev(int matrixA[][],int matrixB[][],int matrixRes[][]){
        for (int i=0 ; i < columnNumber ; i++ ){
            for (int j=0 ; j<rowNumber ; j++ )
                matrixRes[i][j]=matrixA[i][j]+matrixB[i][j];
        }
    }
    public void substract(){
        for (int i=0 ; i < rowNumber ; i++ ){
            for (int j=0 ; j<columnNumber ; j++ )
                res[i][j]=A[i][j]-B[i][j];
        }
    }

    public void showMatrixRev(int A[][]){
        for (int i=0;i<columnNumber;i++) {
            for (int j = 0; j < rowNumber; j++) {
                System.out.print(A[i][j] + " ");
            }
            System.out.println(" ");
        }
    }
    public void showMatrix(int A[][]){
        for (int i=0;i<rowNumber;i++) {
            for (int j = 0; j < columnNumber; j++) {
                System.out.print(A[i][j] + " ");
            }
            System.out.println(" ");
        }
    }

    public void allOperation(){
        System.out.println("-----------DODAWANIE----------");
        add(A,B,res);
        showAll();
        if(checksum.checkMatrixAdd(A,B,res)) System.out.println("SUMY KONTROLNE PRAWIDLOWE");
        else System.out.println("SUMY KONTROLNE NIEPRAWIDLOWE");

        System.out.println("-----------ODEJMOWANIE----------");
        substract();
        showAll();
        if(checksum.checkMatrixSubstract(A,B,res)) System.out.println("SUMY KONTROLNE PRAWIDLOWE");
        else System.out.println("SUMY KONTROLNE NIEPRAWIDLOWE");

        System.out.println("----------ODWRÓCONE-------------");
        aR=reverseMatrix(A,rowNumber,columnNumber);
        bR=reverseMatrix(A,rowNumber,columnNumber);
        addRev(aR,bR,resRev);
        showAllReversed();
        resRev=reverseMatrix(resRev,columnNumber,rowNumber); //reverse again to compare
        if(checksum.checkMatrixReversed(res,resRev)) System.out.println("SUMY KONTROLNE PRAWIDLOWE");
        else System.out.println("SUMY KONTROLNE NIEPRAWIDLOWE");
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
        showMatrixRev(aR);
        System.out.println("\nMacierz bR");
        showMatrixRev(bR);
        System.out.println("\nMacierz Result");
        showMatrixRev(resRev);
    }

    public void showAll(){
        System.out.println("\nMacierz A");
        showMatrix(A);
        System.out.println("\nMacierz B");
        showMatrix(B);
        System.out.println("\nMacierz Result");
        showMatrix(res);
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

    public int getRowNumber() {
        return rowNumber;
    }

    public int getColumnNumber() {
        return columnNumber;
    }
}

