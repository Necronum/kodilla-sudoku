package sudoku;

public class Solver {
    private final SudokuBoard backtrackSudokuBoard;

    public Solver(SudokuBoard sudokuBoard) throws CloneNotSupportedException {
        this.backtrackSudokuBoard = sudokuBoard.deepCopy();
    }

    public SudokuBoard solve() throws CloneNotSupportedException{
        if (!solveBoard()){
            throw new IllegalArgumentException("Sudoku is not possible to solve");
        }
        return backtrackSudokuBoard.deepCopy();
    }

    private boolean solveBoard(){
        for (int i =0; i<9; i++){
            for (int j=0; j<9; j++){
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
        for (int v =1; v<10; v++){
            if(possibleFill(x, y, v)){
                backtrackSudokuBoard.getSudokuBoard().get(x).getSudokuRows().get(y).setValue(v);
                if (solveBoard()){
                    return true;
                }
                backtrackSudokuBoard.getSudokuBoard().get(x).getSudokuRows().get(y).setValue(SudokuElement.EMPTY);
            }
        }
        return false;
    }

    private boolean possibleFill(int x, int y, int value){
        return possibleInLine(x, y, value) && possibleInUnit(x, y, value);
    }

    private boolean possibleInUnit(int x, int y, int value){
        int startX = x-x%3;
        int startY = y-y%3;
        for (int i = 0; i<3; i++){
            for (int j=0; j<3;j++){
                if(backtrackSudokuBoard.getSudokuBoard().get(startX+i).getSudokuRows().get(startY+j).getValue()==value)
                {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean possibleInLine(int x, int y, int value){
        for (int i =0; i<9; i++){
            if (
                    backtrackSudokuBoard.getSudokuBoard().get(i).getSudokuRows().get(y).getValue() == value ||
                            backtrackSudokuBoard.getSudokuBoard().get(x).getSudokuRows().get(i).getValue() == value
            ) return false;
        }
        return true;
    }
}
