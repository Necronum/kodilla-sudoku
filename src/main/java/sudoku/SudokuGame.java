package sudoku;

import java.util.List;

public class SudokuGame {
    private final SudokuBoard board = new SudokuBoard();

    public boolean resolve(){
        board.createBoard();
        fillSudoku();
        solveSudoku();
     return true;
    }

    private void fillSudoku(){
        IOService.welcome();
        System.out.println(board);
        List<String> inputList = IOService.getInput();
        for(String input: inputList){
            board.addInputToSudoku(Character.getNumericValue(input.charAt(0)), Character.getNumericValue(input.charAt(1))
                    ,Character.getNumericValue(input.charAt(2)));
            System.out.println(board);
        }
    }

    private void solveSudoku(){
        IOService.sudokuSolveQuestion();
        try {
            Solver solver = new Solver(board.deepCopy());
            SudokuBoard result = solver.solve();
            System.out.println(result);
        } catch (CloneNotSupportedException e){
            IOService.cloneBoardError();
        }
    }
}
