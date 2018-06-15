public class Matrix {
    private int A[][];
    private int B[][];
    private int res[][];
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
        this.checksum=new Checksum(rowNumber,columnNumber);
    }

    public void add(){
        for (int i=0 ; i < rowNumber ; i++ ){
            for (int j=0 ; j<columnNumber ; j++ )
                res[i][j]=A[i][j]+B[i][j];
        }
    }

    public void substract(){
        for (int i=0 ; i < rowNumber ; i++ ){
            for (int j=0 ; j<columnNumber ; j++ )
                res[i][j]=A[i][j]-B[i][j];
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
        add();
        showAll();
        if(checksum.checkMatrixAdd(A,B,res)) System.out.println("SUMY KONTROLNE PRAWIDLOWE");
        else System.out.println("SUMY KONTROLNE NIEPRAWIDLOWE");

        System.out.println("-----------ODEJMOWANIE----------");
        substract();
        showAll();
        if(checksum.checkMatrixSubstract(A,B,res)) System.out.println("SUMY KONTROLNE PRAWIDLOWE");
        else System.out.println("SUMY KONTROLNE NIEPRAWIDLOWE");
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

