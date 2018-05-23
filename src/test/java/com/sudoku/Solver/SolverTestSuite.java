package com.sudoku.Solver;

import com.sudoku.board.Drawer;
import com.sudoku.board.SudokuBoardInitializer;
import com.sudoku.board.elements.SudokuBoard;
import org.junit.Test;

import static org.junit.Assert.*;

public class SolverTestSuite {

    @Test
    public void shouldSolveSudoku() {

        //Given
        SudokuBoard board = new SudokuBoardInitializer().createBoard(9);
        board.getSudokuRows().get(0).getSudokuElements().get(0).setValue(2);
        board.getSudokuRows().get(1).getSudokuElements().get(2).setValue(1);

        Solver solver = new Solver(board);

        //When
        SudokuBoard solvedBoard = solver.solve();
        System.out.println(new Drawer().draw(solvedBoard));

        //Then
    }

}