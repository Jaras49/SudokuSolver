package com.sudoku.Solver.check;

import com.sudoku.Solver.check.exception.InvalidSudokuException;
import com.sudoku.board.SudokuBoardInitializer;
import com.sudoku.board.elements.SudokuBoard;
import org.junit.Test;

import static org.junit.Assert.*;

public class BlockCheckTestSuite {

    @Test
    public void shouldCheckBlock() throws InvalidSudokuException {

        //Given
        SudokuBoard board = new SudokuBoardInitializer().createBoard(4);
        Check check = new BlockCheck();

        board.setElementValue(0, 0, 5);
        board.getSudokuRows().get(1).getSudokuElements().get(1).getPossibleValues().clear();
        board.getSudokuRows().get(1).getSudokuElements().get(1).getPossibleValues().add(5);
        board.getSudokuRows().get(1).getSudokuElements().get(1).getPossibleValues().add(8);

        //When
        SudokuBoard solvedBoard = check.check(board, 1, 1);
        int resultValue = solvedBoard.getSudokuRows().get(1).getSudokuElements().get(1).getValue();

        //Then
        assertEquals(8, resultValue);

    }

}