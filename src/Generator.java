import java.util.Random;

public class Generator {

    public int[][] generateMatrix(int rowNumber,int columnNumber){
        int matrix[][]=new int[rowNumber][columnNumber];
        Random rand=new Random();
        for(int i=0;i<rowNumber;i++)
            for (int j=0;j<columnNumber;j++)
                matrix[i][j]=rand.nextInt(20)-10;

        return  matrix;
    }
}
