package com.sudoku.Solver.check;

import com.sudoku.Solver.check.exception.InvalidSudokuException;
import com.sudoku.board.Drawer;
import com.sudoku.board.SudokuBoardInitializer;
import com.sudoku.board.elements.SudokuBoard;
import org.junit.Test;

import static org.junit.Assert.*;

public class ColumnCheckTestSuite {

    @Test
    public void shouldCheckColums() throws InvalidSudokuException {

        //Given
        SudokuBoard board = new SudokuBoardInitializer().createBoard(3);
        Check check = new ColumnCheck();

        board.setElementValue(0, 0, 3);
        board.getSudokuRows().get(1).getSudokuElements().get(0).getPossibleValues().clear();
        board.getSudokuRows().get(1).getSudokuElements().get(0).getPossibleValues().add(4);
        board.getSudokuRows().get(1).getSudokuElements().get(0).getPossibleValues().add(3);

        //When
        SudokuBoard solvedBoard = check.check(board, 1, 0);
        int resultValue = solvedBoard.getSudokuRows().get(1).getSudokuElements().get(0).getValue();
        System.out.println(new Drawer().draw(solvedBoard));

        //Then
        assertEquals(4, resultValue);
    }
}