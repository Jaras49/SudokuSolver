package com.sudoku.solver.check;

import com.sudoku.solver.check.exception.InvalidSudokuException;
import com.sudoku.board.elements.SudokuBoard;

/**@deprecated
 * This interface and its implementations are deprecated. SolverProcessor has its functions
 */
public interface Check {

    SudokuBoard check(SudokuBoard board, int rowIndex, int elementIndex) throws InvalidSudokuException;

}
