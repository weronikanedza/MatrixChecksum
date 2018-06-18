public class Checksum {
    private int rows;
    private int columns;
    private int Asum[], Bsum[], Resum[];
    private int iterator;

    public Checksum(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.Asum = new int[rows * columns];
        this.Bsum = new int[rows * columns];
        this.Resum = new int[rows * columns];
    }

    public void checkMatrixAdd(int A[][], int B[][], int C[][]) { //count values of rows and columns
        controlSumOfMatrix(A, Asum);
        controlSumOfMatrix(B, Bsum);
        controlSumOfMatrix(C, Resum);

        System.out.print("Asum : ");
        showChecksumMatrix(Asum);
        System.out.print("Bsum : ");
        showChecksumMatrix(Bsum);
        System.out.print("Resum : ");
        showChecksumMatrix(Resum);

        if (controlSumOfAddOperation())
            System.out.println("Sumy kontrolne prawidlowe");
        else System.out.println("Sumy kontrolne nieprawidłowe");

        Asum=new int[rows*columns];
        Bsum=new int[rows*columns];
        Resum=new int[rows*columns];
    }

    public void showChecksumMatrix(int tab[]) {
        for (int i = 0; i < rows * columns; i++) {
            System.out.print(tab[i] + " ");
        }
        System.out.println();
    }

    public boolean controlSumOfAddOperation() {
        for (int i = 0; i < rows * columns; i++) {
            if (Asum[i] + Bsum[i] != Resum[i]) return false;
        }
        return true;
    }

    public boolean controlSumOfSubstractOperation() {
        for (int i = 0; i < rows * columns; i++) {
            if (Asum[i] - Bsum[i] != Resum[i]) return false;
        }
        return true;
    }

    public boolean controlSumOfReversed() {
        for (int i = 0; i < rows * columns; i++) {
            if (Asum[i] != Bsum[i]) return false;
        }
        return true;
    }

    public void controlSumOfMatrix(int A[][], int tabSum[]) {
        iterator = 0;
        countControlSumRow(A, tabSum); //count row values in Asum/Bsum/Csum
        countControlSumColumn(A, tabSum);
    }

    public void controlSumOfRev(int A[][], int tabSum[]){
        iterator = 0;
        countControlSumColumn(A, tabSum);
        countControlSumRow(A, tabSum); //count row values in Asum/Bsum/Csum
    }
    public void countControlSumRow(int A[][], int tabSum[]) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                tabSum[iterator] += A[i][j];
            }
            iterator++;
        }
    }

    public void countControlSumColumn(int A[][], int tabSum[]) {
        for (int i = 0; i < columns; i++) {
            for (int j = 0; j < rows; j++) {
                tabSum[iterator] += A[j][i];
            }
            iterator++;
        }
    }

    public void checkMatrixSubstract(int A[][], int B[][], int C[][]) {
        controlSumOfMatrix(A, Asum);
        controlSumOfMatrix(B, Bsum);
        controlSumOfMatrix(C, Resum);

        System.out.print("Asum : ");
        showChecksumMatrix(Asum);
        System.out.print("Bsum : ");
        showChecksumMatrix(Bsum);
        System.out.print("Resum : ");
        showChecksumMatrix(Resum);

        if (controlSumOfSubstractOperation())
            System.out.println("Sumy kontrolne prawidlowe");
        else System.out.println("Sumy kontrolne nieprawidłowe");

        Asum=new int[rows*columns];
        Bsum=new int[rows*columns];
        Resum=new int[rows*columns];
    }


    public void checkMatrixReversed(int res[][],int resRev[][]){
        controlSumOfMatrix(res, Asum);
        controlSumOfRev(resRev, Bsum);

        System.out.print("Asum : ");
        showChecksumMatrix(Asum);
        System.out.print("Bsum : ");
        showChecksumMatrix(Bsum);

        if (controlSumOfReversed())
            System.out.println("Sumy kontrolne prawidlowe");
        else System.out.println("Sumy kontrolne nieprawidłowe");

        Asum=new int[rows*columns];
        Bsum=new int[rows*columns];
        Resum=new int[rows*columns];
    }

 }

