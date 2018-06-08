package com.sudoku.board.elements;

import com.sudoku.board.Drawer;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;

import static org.junit.Assert.*;

public class SudokuBoardTestSuite {

    private SudokuBoard board;

    @Before
    public void setUp() throws Exception {

        //Given
        SudokuRow[] sudokuRows = new SudokuRow[9];

        for (int i = 0; i < 9; i++) {

            SudokuElement[] sudokuElements = new SudokuElement[9];

            for (int j = 0; j < 9; j++) {

                sudokuElements[j] = new SudokuElement();
            }
            sudokuRows[i] = new SudokuRow(sudokuElements);
        }

        board = new SudokuBoard(sudokuRows);
    }

    @Test
    public void shouldMakeDeepCopy() {

        //When
        SudokuBoard clonedBoard = null;
        try {
            clonedBoard = board.deepCopy();
        }
        catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        clonedBoard.getSudokuRows().get(0).getSudokuElements().get(0).setValue(5);
        System.out.println(new Drawer().draw(board));
        System.out.println(new Drawer().draw(clonedBoard));

        //Then
        assertNotEquals(5, board.getSudokuRows().get(0).getSudokuElements().get(0).getValue());
        assertEquals(5, clonedBoard.getSudokuRows().get(0).getSudokuElements().get(0).getValue());
        assertEquals(Collections.emptySet(), clonedBoard.getSudokuRows().get(0).getSudokuElements().get(0).getPossibleValues());

    }

    @Test
    public void sholdMakeEqualBoards() throws CloneNotSupportedException {

        //Given
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

        //When
        SudokuBoard clonedBoard = board.deepCopy();
        boolean equals = board.equals(clonedBoard);

        //Then
        assertTrue(equals);
        assertEquals(board, clonedBoard);

    }
}