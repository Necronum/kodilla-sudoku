package sudoku;

public class SudokuElement {
    private int value;
    public static final int EMPTY = -1;

    public SudokuElement(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
