public class Checksum {
    private int rows;
    private int columns;
    private int counter;
    public Checksum(int rows,int columns){
        this.rows=rows;
        this.columns=columns;
    }
    public boolean checkMatrixAdd(int A[][],int B[][],int C[][]){
        counter=0;
        for (int i=0;i<rows;i++){
            for(int j=0;j<columns;j++){
                if(A[i][j]+B[i][j]==C[i][j])
                    counter++;
                else return false;
            }
        }
        return true;
    }

    public boolean checkMatrixSubstract(int A[][],int B[][],int C[][]){
        counter=0;
        for (int i=0;i<rows;i++){
            for(int j=0;j<columns;j++){
                if(A[i][j]-B[i][j]==C[i][j])
                    counter++;
                else return false;
            }
        }
        return true;
    }
}
