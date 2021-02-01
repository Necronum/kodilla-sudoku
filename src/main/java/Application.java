import sudoku.SudokuGame;

public class Application {
    public static void main(String[] args){

        boolean gameFinished = false;
        while(!gameFinished){
            SudokuGame theGame = new SudokuGame();
            gameFinished = theGame.resolve();
        }
    }
}