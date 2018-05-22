package com.sudoku.board.elements;

import com.sudoku.board.Drawer;
import org.junit.Test;

import java.util.Collections;

import static org.junit.Assert.*;

public class SudokuBoardTestSuite {

    @Test
    public void shouldMakeDeepCopy() {

        //Given
        SudokuRow[] sudokuRows = new SudokuRow[9];

        for (int i = 0; i < 9; i++) {

            SudokuElement[] sudokuElements = new SudokuElement[9];

            for (int j = 0; j < 9; j++) {

                sudokuElements[j] = new SudokuElement();
            }
            sudokuRows[i] = new SudokuRow(sudokuElements);
        }

        SudokuBoard board = new SudokuBoard(sudokuRows);

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

}