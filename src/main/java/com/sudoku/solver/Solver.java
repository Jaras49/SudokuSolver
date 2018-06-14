package com.sudoku.solver;

import com.sudoku.board.elements.SudokuBoard;

public interface Solver {

    boolean findSolution(SudokuBoard sudokuBoard);

    SudokuBoard getBoard();
}
