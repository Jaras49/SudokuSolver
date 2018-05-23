package com.sudoku.Solver.check;

import com.sudoku.Solver.check.exception.InvalidSudokuException;
import com.sudoku.board.elements.SudokuBoard;

public interface Check {

    SudokuBoard check(SudokuBoard board, int rowIndex, int elementIndex) throws InvalidSudokuException;

}
