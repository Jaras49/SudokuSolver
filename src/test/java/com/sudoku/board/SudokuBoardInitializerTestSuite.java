package com.sudoku.board;

import com.sudoku.board.elements.SudokuBoard;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;

import static org.junit.Assert.*;

public class SudokuBoardInitializerTestSuite {

    private SudokuBoardInitializer initializer;

    @Test
    public void shouldCreateBoard() {

        //Given
        int size = 9;
        initializer = new SudokuBoardInitializer();

        //When
        SudokuBoard sudokuBoard = initializer.createBoard(9);
        sudokuBoard.getSudokuRows().get(1).getSudokuElements().get(1).setValue(2);

        //Then
        assertEquals(size, sudokuBoard.getSudokuRows().size());
        assertEquals(size, sudokuBoard.getSudokuRows().get(8).getSudokuElements().size());

        assertEquals(-1, sudokuBoard.getSudokuRows().get(1).getSudokuElements().get(8).getValue());
        assertEquals(2, sudokuBoard.getSudokuRows().get(1).getSudokuElements().get(1).getValue());
        assertEquals(new HashSet<>(Arrays.asList()),
                sudokuBoard.getSudokuRows().get(1).getSudokuElements().get(1).getPossibleValues());
    }
}