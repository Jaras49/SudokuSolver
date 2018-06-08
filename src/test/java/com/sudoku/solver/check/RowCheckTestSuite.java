package com.sudoku.solver.check;

import com.sudoku.solver.check.exception.InvalidSudokuException;
import com.sudoku.board.SudokuBoardInitializer;
import com.sudoku.board.elements.SudokuBoard;
import org.junit.Test;

import static org.junit.Assert.*;

public class RowCheckTestSuite {

    @Test
    public void shouldTestRow() throws InvalidSudokuException {

        //Given
        SudokuBoard board = new SudokuBoardInitializer().createBoard(4);
        Check check = new RowCheck();

        board.setElementValue(0, 0, 4);
        board.getSudokuRows().get(0).getSudokuElements().get(1).getPossibleValues().clear();
        board.getSudokuRows().get(0).getSudokuElements().get(1).getPossibleValues().add(4);
        board.getSudokuRows().get(0).getSudokuElements().get(1).getPossibleValues().add(1);

        //When
        int resultValue = check.check(board, 0, 1).getSudokuRows().get(0).getSudokuElements()
                .get(1).getValue();

        //Then
        assertEquals(1, resultValue);
    }

}