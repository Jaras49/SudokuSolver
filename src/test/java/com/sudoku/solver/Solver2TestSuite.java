package com.sudoku.solver;

import com.sudoku.board.Drawer;
import com.sudoku.board.SudokuBoardInitializer;
import com.sudoku.board.elements.SudokuBoard;
import com.sudoku.validator.Validator;
import org.junit.Test;

import static org.junit.Assert.*;

public class Solver2TestSuite {

    @Test
    public void shouldFindSolution() {

        //Given
        Validator validator = new Validator();
        Solver2 solver = new Solver2(validator);
        SudokuBoard board = new SudokuBoardInitializer().createBoard(9);
        board.setElementValue(0,0, 5);
        board.setElementValue(0,2, 4);

        //When
        solver.findSolution(board);
        SudokuBoard solvedBoard = solver.getBoard();

        System.out.println(new Drawer().draw(solvedBoard));

        //Then
        assertTrue(validator.validate(solvedBoard));
    }

    @Test
    public void shouldSolveHardBoard() {

        //Given
        Validator validator = new Validator();
        Solver2 solver = new Solver2(validator);
        SudokuBoard board = new SudokuBoardInitializer().createBoard(9);

        board.setElementValue(0, 0, 8);
        board.setElementValue(2, 5, 6);
        board.setElementValue(2, 0, 5);
        board.setElementValue(2,1,9);
        board.setElementValue(0,4,2);
        board.setElementValue(0,5,1);
        board.setElementValue(1,4,5);
        board.setElementValue(2,5,6);
        board.setElementValue(2,6,6);
        board.setElementValue(0,8,3);
        board.setElementValue(1,7,8);
        board.setElementValue(2,6,7);
        board.setElementValue(2,8,2);
        board.setElementValue(3,2,3);
        board.setElementValue(4,0,1);
        board.setElementValue(4,2,8);
        board.setElementValue(3,4,9);
        board.setElementValue(3,5,5);
        board.setElementValue(5,3,4);
        board.setElementValue(3,7,2);
        board.setElementValue(4,8,5);
        board.setElementValue(5,7,1);
        board.setElementValue(6,0,3);
        board.setElementValue(7,0,9);
        board.setElementValue(6,2,7);
        board.setElementValue(8,2,6);
        board.setElementValue(8,3,3);
        board.setElementValue(8,4,8);
        board.setElementValue(6,8,4);
        board.setElementValue(7,8,7);
        board.setElementValue(8,7,9);

        System.out.println(validator.validate(board));
        System.out.println(new Drawer().draw(board));

        //When
        solver.findSolution(board);
        SudokuBoard solvedBoard = solver.getBoard();
        System.out.println(new Drawer().draw(solvedBoard));

        //Then
        assertTrue(validator.validate(solvedBoard));
    }

    @Test
    public void shouldSolveEmptySudoku() {

        //Given
        Validator validator = new Validator();
        SudokuBoard board = new SudokuBoardInitializer().createBoard(9);
        Solver2 solver2 = new Solver2(validator);

        //When
        solver2.findSolution(board);
        SudokuBoard solvedBoard = solver2.getBoard();

        //Then
        assertTrue(validator.validate(solvedBoard));
    }
}