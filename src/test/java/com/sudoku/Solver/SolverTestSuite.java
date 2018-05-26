package com.sudoku.Solver;

import com.sudoku.board.Drawer;
import com.sudoku.board.SudokuBoardInitializer;
import com.sudoku.board.elements.SudokuBoard;
import com.sudoku.validator.Validator;
import org.junit.Test;

import static org.junit.Assert.*;

public class SolverTestSuite {

    @Test
    public void shouldSolveSudoku() throws CloneNotSupportedException {

        //Given
        SudokuBoard board = new SudokuBoardInitializer().createBoard(9);
        board.setElementValue(0, 1, 2);
        board.setElementValue(1, 2, 1);

        Solver solver = new Solver(board);

        //When
        SudokuBoard solvedBoard = solver.solve();
        System.out.println(new Drawer().draw(solvedBoard));


        //Then
        assertTrue(new Validator(solvedBoard).validate());
    }

}