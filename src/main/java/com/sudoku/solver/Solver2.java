package com.sudoku.solver;

import com.sudoku.board.elements.SudokuBoard;
import com.sudoku.board.elements.SudokuElement;
import com.sudoku.board.elements.SudokuRow;
import com.sudoku.validator.Validator;

import java.util.List;

public class Solver2 {

    private static final int EMPTY = -1;
    private Validator validator = new Validator();
    private SudokuBoard board;

    public boolean findSolution(SudokuBoard board) {

        this.board = board;

        List<SudokuRow> sudokuRows = board.getSudokuRows();
        for (int i = 0; i < sudokuRows.size(); i++) {
            List<SudokuElement> sudokuElements = sudokuRows.get(i).getSudokuElements();

            for (int j = 0; j < sudokuElements.size(); j++) {
                SudokuElement sudokuElement = sudokuElements.get(j);

                if (sudokuElement.isEmpty()) {

                    for (int n = 1; n <= 9; n++) {

                        this.board.setElementValue(i, j, n);

                        if (validator.validate(this.board) && findSolution(this.board)) {
                            return true;
                        }
                        this.board.setElementValue(i, j, EMPTY);
                    }
                    return false;
                }
            }
        }
        return true;
    }

    public SudokuBoard getBoard() {
        return board;
    }
}
