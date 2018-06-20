import java.util.Arrays;

public class Checksum {
    private int rows;
    private int columns;
    private int Asum[], Bsum[], Resum[], ReRevsum[];
    private int iterator;

    public Checksum(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.Asum = new int[rows + columns];
        this.Bsum = new int[rows + columns];
        this.Resum = new int[rows + columns];
        this.ReRevsum = new int[rows + columns];
    }

    private void clearCheckSum(){
        Arrays.fill(Asum, 0);
        Arrays.fill(Bsum, 0);
        Arrays.fill(Resum, 0);
    }

    public int[] checkMatrixAdd(int A[][], int B[][], int C[][]) { //count values of rows and columns
        clearCheckSum();

        controlSumOfMatrix(A, Asum);
        controlSumOfMatrix(B, Bsum);
        controlSumOfMatrix(C, Resum);

        return controlSumOfAddOperation();
    }

    public int[] checkMatrixSubstract(int A[][], int B[][], int C[][]) {
        clearCheckSum();

        controlSumOfMatrix(A, Asum);
        controlSumOfMatrix(B, Bsum);
        controlSumOfMatrix(C, Resum);

        return controlSumOfSubstractOperation();
    }

    public int[] checkMatrixReversed(int res[][],int resRev[][]){
        clearCheckSum();

        controlSumOfMatrix(res, Resum);
        controlSumOfMatrix(resRev, ReRevsum);

        return controlSumOfReversed();
    }

    public void showChecksumMatrix(int tab[]) {
        for (int i = 0; i < rows * columns; i++) {
            System.out.print(tab[i] + " ");
        }
        System.out.println();
    }

    private int[] controlSumOfAddOperation() {
        int[] errors = new int[rows + columns];
        Arrays.fill(errors, 0);
        for (int i = 0; i < rows + columns; i++) {
            if (Asum[i] + Bsum[i] != Resum[i])
                errors[i] = -1;
        }
        return errors;
    }

    private int[] controlSumOfSubstractOperation() {
        int[] errors = new int[rows + columns];
        Arrays.fill(errors, 0);
        for (int i = 0; i < rows + columns; i++) {
            if (Asum[i] - Bsum[i] != Resum[i])
                errors[i] = -1;
        }
        return errors;
    }

    private int[] controlSumOfReversed() {
        int[] errors = new int[rows + columns];
        Arrays.fill(errors, 0);
        for (int i = 0; i < rows + columns; i++) {
            if (Asum[i] != Bsum[i])
                errors[i] = -1;
        }
        return errors;
    }

    private void controlSumOfMatrix(int A[][], int tabSum[]) {
        iterator = 0;
        countControlSumColumn(A, tabSum);
        countControlSumRow(A, tabSum); //count row values in Asum/Bsum/Csum
    }

    private void controlSumOfRev(int A[][], int tabSum[]){
        iterator = 0;
        countControlSumColumn(A, tabSum);
        countControlSumRow(A, tabSum); //count row values in Asum/Bsum/Csum
    }

    private void countControlSumRow(int A[][], int tabSum[]) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                tabSum[iterator] += A[j][i];
            }
            iterator++;
        }
    }

    private void countControlSumColumn(int A[][], int tabSum[]) {
        for (int j = 0; j < columns; j++) {
            for (int i = 0; i < rows; i++) {
                tabSum[iterator] += A[j][i];
            }
            iterator++;
        }
    }

    public int[] getAsum() {
        return Asum;
    }

    public int[] getBsum() {
        return Bsum;
    }

    public int[] getResum() {
        return Resum;
    }

    public int[] getReRevsum() {
        return ReRevsum;
    }
}

