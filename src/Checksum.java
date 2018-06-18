public class Checksum {
    private int rows;
    private int columns;
    public Checksum(int rows,int columns){
        this.rows=rows;
        this.columns=columns;
    }
    public boolean checkMatrixAdd(int A[][],int B[][],int C[][]){
        for (int i=0;i<rows;i++){
            for(int j=0;j<columns;j++){
                if(A[i][j]+B[i][j]!=C[i][j]) return false;
            }
        }
        return true;
    }

    public boolean checkMatrixSubstract(int A[][],int B[][],int C[][]){
        for (int i=0;i<rows;i++){
            for(int j=0;j<columns;j++){
                if(A[i][j]-B[i][j]!=C[i][j]) return false;
            }
        }
        return true;
    }

    public boolean checkMatrixReversed(int res[][],int resRev[][]){
        for (int i=0;i<rows;i++){
            for(int j=0;j<columns;j++){
                if(res[i][j]!=resRev[i][j]) return false;
            }
        }
        return true;
    }

    public boolean checkMatrixMultiply(int A[][],int Bmulti[][],int resMultiDiv[][]){
        int temp[]=new int[rows*rows];
        int iteratorTemp=0;
        int iteratorBmulti=0;

        for (int i=0;i<rows;i++){
            iteratorBmulti=0;
            for (int x=0;x<rows;x++) {
                for (int j = 0; j < columns; j++) {
                    temp[iteratorTemp]+=A[i][j]*Bmulti[j][iteratorBmulti];
                }
                iteratorTemp++;
                iteratorBmulti++;
            }
        }
        iteratorTemp=0;
        for (int i=0;i<rows;i++){
            for (int j=0;j<rows;j++){
                if(resMultiDiv[i][j]!=temp[iteratorTemp++]) return false;
            }
        }
        return true;
    }
 }

