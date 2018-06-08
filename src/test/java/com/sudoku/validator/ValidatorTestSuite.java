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
        sudokuBoard.setElementValue(0, 0, 1);
        sudokuBoard.setElementValue(0, 1, 4);
        sudokuBoard.setElementValue(1, 1, 6);
        sudokuBoard.setElementValue(8, 1, 1);

        //When
        boolean validateResult = new Validator().validate(sudokuBoard);

        //Then
        assertTrue(validateResult);
    }

    @Test
    public void shouldNotValidateBoard() {

        //Given
        sudokuBoard.setElementValue(0, 0, 1);
        sudokuBoard.setElementValue(0, 1, 1);
        sudokuBoard.setElementValue(1, 0, 1);
        sudokuBoard.setElementValue(1, 1, 1);

        //When
        boolean validateResult = new Validator().validate(sudokuBoard);

        //Then
        assertFalse(validateResult);
    }

}