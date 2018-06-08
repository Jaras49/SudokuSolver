package com.sudoku.solver;

import com.sudoku.board.Drawer;
import com.sudoku.board.SudokuBoardInitializer;
import com.sudoku.board.elements.SudokuBoard;
import com.sudoku.solver.check.exception.InvalidSudokuException;
import com.sudoku.validator.Validator;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SolverServiceTestSuite {

    private SudokuBoard board;
    private SolverService solver;

    @Before
    public void setUp() throws Exception {
        board = new SudokuBoardInitializer().createBoard(9);
        solver = new SolverService();
    }

    @Test
    public void shouldSolveSudokuHardSudoku() throws CloneNotSupportedException, InvalidSudokuException {

        //Given
        board.setElementValue(0, 0, 4);
        board.setElementValue(0, 4, 1);
        board.setElementValue(1, 4, 3);
        board.setElementValue(2, 3, 6);
        board.setElementValue(2, 4, 7);
        board.setElementValue(2, 5, 9);
        board.setElementValue(0, 6, 7);
        board.setElementValue(0, 8, 8);
        board.setElementValue(1, 8, 2);
        board.setElementValue(3, 0, 1);
        board.setElementValue(3, 1, 7);
        board.setElementValue(4, 2, 5);
        board.setElementValue(5, 1, 4);
        board.setElementValue(5, 2, 3);
        board.setElementValue(3, 3, 8);
        board.setElementValue(3, 4, 6);
        board.setElementValue(5, 4, 2);
        board.setElementValue(5, 5, 7);
        board.setElementValue(4, 6, 4);
        board.setElementValue(4, 7, 8);
        board.setElementValue(5, 6, 9);
        board.setElementValue(5, 8, 1);
        board.setElementValue(6, 2, 1);
        board.setElementValue(8, 0, 6);
        board.setElementValue(8, 1, 2);

        System.out.println(new Validator().validate(board));
        System.out.println(new Drawer().draw(board));

        //When
        SudokuBoard solvedBoard = solver.findSolution(board);
        System.out.println(new Drawer().draw(solvedBoard));


        //Then
        assertTrue(new Validator().validate(solvedBoard));
    }

    @Test
    public void shouldSolveEmptySudoku() throws CloneNotSupportedException, InvalidSudokuException {

        //Given

        //When
        SudokuBoard solvedBoard = solver.findSolution(board);
        System.out.println(new Drawer().draw(solvedBoard));

        //Then
        assertTrue(new Validator().validate(solvedBoard));
    }

    @Test
    public void shouldSolveSudoku() throws CloneNotSupportedException, InvalidSudokuException {

        //Given
        board.setElementValue(0,0, 5);
        board.setElementValue(0,2, 4);

        //When
        SudokuBoard solvedBoard = solver.findSolution(board);
        System.out.println(new Drawer().draw(solvedBoard));

        //Then
        assertTrue(new Validator().validate(solvedBoard));
    }
}