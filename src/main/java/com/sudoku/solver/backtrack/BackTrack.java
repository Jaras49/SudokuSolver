package com.sudoku.solver.backtrack;

import com.sudoku.board.elements.SudokuBoard;

public class BackTrack {

    private final SudokuBoard board;
    private final int rowIndex;
    private final int elementIndex;
    private final int guessedValue;

    public BackTrack(SudokuBoard board, int rowIndex, int elementIndex, int guessedValue) {
        this.board = board;
        this.rowIndex = rowIndex;
        this.elementIndex = elementIndex;
        this.guessedValue = guessedValue;
    }

    public BackTrack(SudokuBoard board) {
        this.board = board;
        this.rowIndex = -1;
        this.elementIndex = -1;
        this.guessedValue = -1;
    }

    public SudokuBoard getBoard() {
        return board;
    }

    public int getRowIndex() {
        return rowIndex;
    }

    public int getElementIndex() {
        return elementIndex;
    }

    public int getGuessedValue() {
        return guessedValue;
    }
}
