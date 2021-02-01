package sudoku;

import java.util.ArrayList;
import java.util.List;

public class SudokuBoard extends Prototype<SudokuBoard>{
    public final static int MIN_INDEX = 0;
    public final static int MAX_INDEX = 8;
    private List<SudokuRow> sudokuBoard = new ArrayList<>();

    public SudokuBoard() {
    }

    public void createBoard(){
        for (int i = 0; i<9; i++){
            sudokuBoard.add(new SudokuRow());
        }
        for (SudokuRow row: sudokuBoard){
            row.getEmptyRow();
        }
    }

    public void addInputToSudoku(int x, int y, int value){
        sudokuBoard.get(x-1).getSudokuRows().set(y-1, new SudokuElement(value));
    }

    public List<SudokuRow> getSudokuBoard() {
        return sudokuBoard;
    }

    public SudokuBoard deepCopy() throws CloneNotSupportedException{
        SudokuBoard backtrackBoard = super.clone();
        backtrackBoard.sudokuBoard = new ArrayList<>();
        for (SudokuRow row: sudokuBoard){
            SudokuRow backtrackRow = new SudokuRow();
            for (SudokuElement element: row.getSudokuRows()){
                backtrackRow.getSudokuRows().add(element);
            }
            backtrackBoard.getSudokuBoard().add(backtrackRow);
        }
        return backtrackBoard;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int n = MIN_INDEX; n <= MAX_INDEX; n++) {
            result.append("|");
            for (int k = MIN_INDEX; k <= MAX_INDEX; k++) {
                if (sudokuBoard.get(n).getSudokuRows().get(k).getValue() == -1) {
                    result.append("-");
                } else {
                    result.append((sudokuBoard.get(n).getSudokuRows().get(k).getValue()));
                }
                result.append("|");
            }
            result.append("\n");
        }
        return result.toString();
    }
}
