import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.scene.control.TextField;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Controller extends Application{

    int  rowNumber, columnNumber;
    Matrix matrix;
    boolean reversedA = false, reversedB = false, reversedSumResult = true, reversedSubResult = true;

    @FXML
    private TextField width, height, filePath;
    @FXML
    private GridPane viewMatrixA, viewMatrixB, viewMatrixARev, viewMatrixBRev,
            viewMatrixSumResult, viewReversedSumMatrixResult, viewUnreversedSumMatrixResult,
            viewMatrixSubstractResult, viewReversedSubbstractMatrixResult, viewUnreversedSubbstractMatrixResult;
    @FXML
    private Text errorText, reversedALabel, reversedBLabel, reversedSRLabel, reversedSubRLabel;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("mainWindow.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        primaryStage.setTitle("Systemy Odporne na błędy - Projekt");
        primaryStage.setScene(new Scene(root, 1190, 750));

        primaryStage.show();
        primaryStage.setMaxWidth(1190);
        primaryStage.setMaxHeight(750);
    }

    private void createMatrixView(GridPane matrix, int[][] matrixValues){
        matrix.getChildren().removeAll(matrix.getChildren());
        TextField matrixCell;

        for(int i = 0; i < matrixValues[0].length; i++){
            for(int j = 0; j < matrixValues.length; j++){
                matrixCell = new TextField(matrixValues[j][i]+"");
                matrixCell.setPrefWidth(40);
                matrix.add(matrixCell, j, i);
            }
        }

        matrix.setGridLinesVisible(true);
    }

    private void addCheckSumToMatrixView(GridPane matrix, int[] val, int[] errorSumIndex){
        for(int i = 0; i < columnNumber; i++){
            if(errorSumIndex[i] != -1)
                matrix.add(getCheckSumField(val[i]+"", false), i, rowNumber);
            else
                matrix.add(getCheckSumField(val[i]+"", true), i, rowNumber);
        }

        for(int i = 0; i < rowNumber; i++){
            if(errorSumIndex[columnNumber + i] != -1)
                matrix.add(getCheckSumField(val[columnNumber + i]+"", false), columnNumber, i);
            else
                matrix.add(getCheckSumField(val[columnNumber + i]+"", true), columnNumber, i);
        }
        matrix.setGridLinesVisible(true);
    }

    private TextField getCheckSumField(String val, boolean errorSum){
        TextField matrixCell;
        matrixCell = new TextField(val);
        matrixCell.setPrefWidth(30);
        if (!errorSum)
            matrixCell.setStyle("-fx-text-fill: Chartreuse; -fx-background-color: grey");
        else
            matrixCell.setStyle("-fx-text-fill: white; -fx-background-color: red");
        return matrixCell;
    }

    private int[][] getEmptyMatrix(int columnNumber, int rowNumber){
        int tab[][] = new int[columnNumber][rowNumber];
        for(int i = 0; i < columnNumber; i++)
            Arrays.fill(tab[i], 0);

        return tab;
    }

    private boolean setSize(){
        if(width.getText() != null && height.getText() != null){
            try{
                rowNumber = Integer.parseInt(height.getText());
                columnNumber = Integer.parseInt(width.getText());
                errorText.setText("");
                if(rowNumber > 0 && rowNumber < 8 && columnNumber > 0 && columnNumber < 8) {
                    return true;
                } else
                    errorText.setText("Dopuszczalne wartości dla obu wymiarów to min 1 i max 8.");

            }catch(Exception e) {
                errorText.setText("Rozmiar musi być podany jako liczby całkowiete");
            }
        }
        else
            errorText.setText("Podaj oba wymiary macierzy.");

        return false;
    }

    @FXML
    public void createEmptyMatrix(){
        if(setSize()) {
            createMatrixView(viewMatrixA, getEmptyMatrix(columnNumber, rowNumber));
            createMatrixView(viewMatrixB, getEmptyMatrix(columnNumber, rowNumber));
            matrix = new Matrix(getMatrixFromView(viewMatrixA), getMatrixFromView(viewMatrixB),rowNumber, columnNumber);
            createMatrixView(viewMatrixARev, matrix.getaR());
            createMatrixView(viewMatrixBRev, matrix.getbR());
        }
    }

    @FXML
    public void generateMatrix(){
        if(setSize()) {
            Generator generator=new Generator();
            errorText.setText("");

            createMatrixView(viewMatrixA, generator.generateMatrix(columnNumber, rowNumber));
            createMatrixView(viewMatrixB, generator.generateMatrix(columnNumber, rowNumber));
            matrix = new Matrix(getMatrixFromView(viewMatrixA), getMatrixFromView(viewMatrixB),rowNumber, columnNumber);
            createMatrixView(viewMatrixARev, matrix.getaR());
            createMatrixView(viewMatrixBRev, matrix.getbR());
        }
    }

    @FXML
    public void createMartrixFromFile(){
        FileReader fileReader = new FileReader();
        List<Integer> fileData;
        String fileName = filePath.getText();
        errorText.setText("");

        if(fileName != null){
            fileData = fileReader.getDataFromFile(fileName);
            if(fileData != null){
                matrix = new Matrix(fileData);
                rowNumber = matrix.getRowNumber();
                columnNumber = matrix.getColumnNumber();
                if(rowNumber > 0 && rowNumber < 8 && columnNumber > 0 && columnNumber < 8) {
                    createMatrixView(viewMatrixA, matrix.getA());
                    createMatrixView(viewMatrixB, matrix.getB());
                    createMatrixView(viewMatrixARev, matrix.getaR());
                    createMatrixView(viewMatrixBRev, matrix.getbR());
                } else
                    errorText.setText("Dopuszczalne wartości dla obu wymiarów to min 1 i max 8.");

            }else
                errorText.setText("Plik o tej nazwie nie istnieje lub zawiera błędne dane.");
        } else
            errorText.setText("Nie wprowadzono nazwy pliku.");

    }

    private int[][] getMatrixFromView(GridPane matrixView){
        int matrix[][] = new int[columnNumber][rowNumber];

        ObservableList<Node> matrixViewCells = matrixView.getChildren();

        if(matrixViewCells.size() < 1)
            return null;

        try {
            for(int i = 0; i < rowNumber; i++){
                for(int j = 0; j < columnNumber; j++){
                    matrix[j][i] = Integer.parseInt(((TextField)matrixViewCells.get(columnNumber*i + j)).getText());
                }
            }
        }catch(Exception e) {
            return null;
        }
        return matrix;
    }

    private void displayEquation(Matrix matrix, GridPane resultMatrix, int[] errorSumIndex){
        createMatrixView(viewMatrixA, matrix.getA());
        addCheckSumToMatrixView(viewMatrixA, matrix.getAsum(), errorSumIndex);
        createMatrixView(viewMatrixB, matrix.getB());
        addCheckSumToMatrixView(viewMatrixB, matrix.getBsum(), errorSumIndex);
        createMatrixView(resultMatrix, matrix.getRes());
        addCheckSumToMatrixView(resultMatrix, matrix.getResum(), errorSumIndex);
    }

    private void displayRevRes(Matrix matrix, GridPane resultMatrix, GridPane unreversedResultMatrix, int[] errorSumIndex){
        createMatrixView(resultMatrix, matrix.getResRev());
        createMatrixView(unreversedResultMatrix, matrix.reverseMatrix(matrix.getResRev(),columnNumber,rowNumber));
        addCheckSumToMatrixView(unreversedResultMatrix, matrix.getReRevsum(), errorSumIndex);
        unreversedResultMatrix.setVisible(false);
        resultMatrix.setVisible(true);
        reversedSumResult = reversedSubResult = true;
    }

    private void count(String action){
        int[][] A, B;
        int[] errorSumIndexes;
        A = getMatrixFromView(viewMatrixA);
        B = getMatrixFromView(viewMatrixB);

        if(A != null && B != null){
            matrix = new Matrix(A, B, rowNumber, columnNumber);
            switch(action) {
                case "sum":
                    errorSumIndexes = matrix.add();
                    displayEquation(matrix, viewMatrixSumResult, errorSumIndexes);
                    break;
                case "sub":
                    errorSumIndexes = matrix.substract();
                    displayEquation(matrix, viewMatrixSubstractResult, errorSumIndexes);
                    break;
                case "revSum":
                    errorSumIndexes = matrix.addRev();
                    displayRevRes(matrix, viewReversedSumMatrixResult, viewUnreversedSumMatrixResult, errorSumIndexes);
                    break;
                case "revSub":
                    errorSumIndexes = matrix.substractRev();
                    displayRevRes(matrix, viewReversedSubbstractMatrixResult, viewUnreversedSubbstractMatrixResult, errorSumIndexes);
                    break;
            }


        }else
            errorText.setText("Macierz może zawierać tylko liczby całkowiete.");

    }

    @FXML
    public void countSum(){
        count("sum");
    }

    @FXML
    public void countRevSum(){
        count("revSum");
    }

    @FXML
    public void countSubstract(){
        count("sub");
    }

    @FXML
    public void countRevSubstract(){
        count("revSub");
    }

    public void check(String action, GridPane viewResultMatrix){
        int[][] A, B, res;
        int[] errorSumIndexes;
        A = getMatrixFromView(viewMatrixA);
        B = getMatrixFromView(viewMatrixB);
        res = getMatrixFromView(viewResultMatrix);

        if(A != null && B != null && res != null){
            switch(action) {
                case "sum":
                    errorSumIndexes = matrix.checkAdd(A, B, res);
                    displayEquation(matrix, viewResultMatrix, errorSumIndexes);
                    break;
                case "sub":
                    errorSumIndexes = matrix.checkSub(A, B, res);
                    displayEquation(matrix, viewResultMatrix, errorSumIndexes);
                    break;
            }
        }else
            errorText.setText("Macierz może zawierać tylko liczby całkowiete.");
    }

    @FXML
    public void checkSum(){
        check("sum", viewMatrixSumResult);
    }

    @FXML
    public void checkSubstract(){
        check("sub", viewMatrixSubstractResult);
    }

    @FXML
    public void reversMatrixA(){
        reversedA = reversView(reversedA, viewMatrixARev, viewMatrixA, reversedALabel);
    }

    @FXML
    public void reversMatrixB(){
        reversedB = reversView(reversedB, viewMatrixBRev, viewMatrixB, reversedBLabel);
    }

    @FXML
    public void reversMatrixSumResult(){
        reversedSumResult = reversView(reversedSumResult, viewReversedSumMatrixResult, viewUnreversedSumMatrixResult, reversedSRLabel);
    }

    @FXML
    public void reversMatrixSubResult(){
        reversedSubResult = reversView(reversedSubResult, viewReversedSubbstractMatrixResult, viewUnreversedSubbstractMatrixResult, reversedSubRLabel);
    }

    private boolean reversView(boolean isReversed, GridPane reversed, GridPane notRevered, Text message) {
        if(isReversed) {
            message.setVisible(false);
            reversed.setVisible(false);
            notRevered.setVisible(true);
            isReversed = false;
        }else {
            message.setVisible(true);
            reversed.setVisible(true);
            notRevered.setVisible(false);
            isReversed = true;
        }
        return isReversed;
    }



}
