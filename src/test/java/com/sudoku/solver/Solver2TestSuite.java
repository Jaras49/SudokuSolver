package com.sudoku.solver;

import com.sudoku.board.Drawer;
import com.sudoku.board.SudokuBoardInitializer;
import com.sudoku.board.elements.SudokuBoard;
import org.junit.Test;

import static org.junit.Assert.*;

public class Solver2TestSuite {

    @Test
    public void shouldFindSolution() throws CloneNotSupportedException {

        //Given
        Solver2 solver = new Solver2();
        SudokuBoard board = new SudokuBoardInitializer().createBoard(9);
        board.setElementValue(0,0, 5);
        board.setElementValue(0,2, 4);

        //When
        solver.findSolution(board);
        SudokuBoard solvedBoard = solver.getBoard();

        System.out.println(new Drawer().draw(solvedBoard));

        //Then

    }
}