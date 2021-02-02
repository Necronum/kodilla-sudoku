package sudoku;

import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

public class Solver {
    private final static int FIRST_VALUE = 1;
    private final static int LAST_VALUE = 10;
    public final static int LAST_INDEX = 9;
    private final static int UNIT_INDEX = 3;
    private final SudokuBoard backtrackSudokuBoard;

    public Solver(SudokuBoard sudokuBoard){
        this.backtrackSudokuBoard = sudokuBoard;
    }

    public SudokuBoard solve() throws CloneNotSupportedException{
        if (!solveBoard()){
            throw new IllegalArgumentException("Sudoku is not possible to solve");
        }
        return backtrackSudokuBoard.deepCopy();
    }

    private boolean solveBoard(){
        for (int i =0; i<LAST_INDEX; i++){
            for (int j=0; j<LAST_INDEX; j++){
                if(emptyField(i,j)){
                    return solveField(i,j);
                }
            }
        }
        return true;
    }

    private boolean emptyField(int x, int y){
        return backtrackSudokuBoard.getSudokuBoard().get(x).getSudokuRows().get(y).getValue() == SudokuElement.EMPTY;
    }

    private boolean solveField(int x, int y){
        List<Integer> values = IntStream.range(FIRST_VALUE,LAST_VALUE).boxed().collect(toList());
        Random rnd = new Random();
        do{
          int index = rnd.nextInt(values.size());
          int v = values.remove(index);
            if(possibleFill(x, y, v)){
                backtrackSudokuBoard.getSudokuBoard().get(x).getSudokuRows().get(y).setValue(v);
                if (solveBoard()){
                    return true;
                }
                backtrackSudokuBoard.getSudokuBoard().get(x).getSudokuRows().get(y).setValue(SudokuElement.EMPTY);
            }
        } while (!values.isEmpty());
        return false;
    }

    private boolean possibleFill(int x, int y, int value){
        return possibleInLine(x, y, value) && possibleInUnit(x, y, value);
    }

    private boolean possibleInUnit(int x, int y, int value){
        int startX = x-x%UNIT_INDEX;
        int startY = y-y%UNIT_INDEX;
        for (int i = 0; i<UNIT_INDEX; i++){
            for (int j=0; j<UNIT_INDEX;j++){
                if(backtrackSudokuBoard.getSudokuBoard().get(startX+i).getSudokuRows().get(startY+j).getValue()==value)
                {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean possibleInLine(int x, int y, int value){
        for (int i =0; i<LAST_INDEX; i++){
            if (
                    backtrackSudokuBoard.getSudokuBoard().get(i).getSudokuRows().get(y).getValue() == value ||
                            backtrackSudokuBoard.getSudokuBoard().get(x).getSudokuRows().get(i).getValue() == value
            ) return false;
        }
        return true;
    }
}
