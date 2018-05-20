package com.sudoku.validator;

import com.sudoku.board.SudokuBoardInitializer;
import com.sudoku.board.elements.SudokuBoard;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ValidatorTestSuite {

    private SudokuBoard sudokuBoard;

    @Before
    public void setUp() throws Exception {
        sudokuBoard = new SudokuBoardInitializer().createBoard(9);
    }

    @Test
    public void shouldValidateBoard() {

        //Given
        sudokuBoard.getSudokuRows().get(0).getSudokuElements().get(0).setValue(1);
        sudokuBoard.getSudokuRows().get(0).getSudokuElements().get(1).setValue(4);
        sudokuBoard.getSudokuRows().get(1).getSudokuElements().get(1).setValue(6);
        sudokuBoard.getSudokuRows().get(8).getSudokuElements().get(1).setValue(1);

        //When
        boolean validateResult = new Validator(sudokuBoard).validate();

        //Then
        assertTrue(validateResult);
    }

    @Test
    public void shouldNotValidateBoard() {

        //Given
        sudokuBoard.getSudokuRows().get(0).getSudokuElements().get(0).setValue(1);
        sudokuBoard.getSudokuRows().get(0).getSudokuElements().get(1).setValue(1);
        sudokuBoard.getSudokuRows().get(1).getSudokuElements().get(0).setValue(1);
        sudokuBoard.getSudokuRows().get(1).getSudokuElements().get(1).setValue(1);

        //When
        boolean validateResult = new Validator(sudokuBoard).validate();

        //Then
        assertFalse(validateResult);
    }

}