package com.sudoku.board;

import com.sudoku.board.elements.SudokuBoard;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DrawerTestSuite {

    private Drawer drawer;
    private SudokuBoard board;

    @Before
    public void setUp() throws Exception {

        //Given
        drawer = new Drawer();
        board = new SudokuBoardInitializer().createBoard(9);
    }

    @Test
    public void shouldDrawEmptyBoard() {

        //Given
        String expected = "+---+---+---+---+---+---+---+---+---+\n" +
                "|   |   |   |   |   |   |   |   |   |\n" +
                "+---+---+---+---+---+---+---+---+---+\n" +
                "|   |   |   |   |   |   |   |   |   |\n" +
                "+---+---+---+---+---+---+---+---+---+\n" +
                "|   |   |   |   |   |   |   |   |   |\n" +
                "+---+---+---+---+---+---+---+---+---+\n" +
                "|   |   |   |   |   |   |   |   |   |\n" +
                "+---+---+---+---+---+---+---+---+---+\n" +
                "|   |   |   |   |   |   |   |   |   |\n" +
                "+---+---+---+---+---+---+---+---+---+\n" +
                "|   |   |   |   |   |   |   |   |   |\n" +
                "+---+---+---+---+---+---+---+---+---+\n" +
                "|   |   |   |   |   |   |   |   |   |\n" +
                "+---+---+---+---+---+---+---+---+---+\n" +
                "|   |   |   |   |   |   |   |   |   |\n" +
                "+---+---+---+---+---+---+---+---+---+\n" +
                "|   |   |   |   |   |   |   |   |   |\n" +
                "+---+---+---+---+---+---+---+---+---+\n";

        //When
        String drawedBoard = drawer.draw(board);

        //Then
        assertEquals(expected, drawedBoard);
    }

    @Test
    public void shouldBuildBoardWithValues() {

        //Given
        String expected = "+---+---+---+---+---+---+---+---+---+\n" +
                "|   |   |   |   |   |   |   |   |   |\n" +
                "+---+---+---+---+---+---+---+---+---+\n" +
                "|   | 5 |   |   |   |   |   |   |   |\n" +
                "+---+---+---+---+---+---+---+---+---+\n" +
                "|   |   | 7 |   |   |   |   |   |   |\n" +
                "+---+---+---+---+---+---+---+---+---+\n" +
                "|   |   |   |   |   | 5 |   |   |   |\n" +
                "+---+---+---+---+---+---+---+---+---+\n" +
                "|   |   |   |   |   |   |   |   |   |\n" +
                "+---+---+---+---+---+---+---+---+---+\n" +
                "|   |   |   |   |   |   | 5 |   |   |\n" +
                "+---+---+---+---+---+---+---+---+---+\n" +
                "|   |   |   |   | 5 |   |   |   |   |\n" +
                "+---+---+---+---+---+---+---+---+---+\n" +
                "|   |   |   |   |   |   |   |   | 2 |\n" +
                "+---+---+---+---+---+---+---+---+---+\n" +
                "|   |   |   |   |   |   |   |   |   |\n" +
                "+---+---+---+---+---+---+---+---+---+\n";

        board.getSudokuRows().get(2).getSudokuElements().get(2).setValue(7);
        board.getSudokuRows().get(5).getSudokuElements().get(6).setValue(5);
        board.getSudokuRows().get(6).getSudokuElements().get(4).setValue(5);
        board.getSudokuRows().get(7).getSudokuElements().get(8).setValue(2);
        board.getSudokuRows().get(1).getSudokuElements().get(1).setValue(5);
        board.getSudokuRows().get(3).getSudokuElements().get(5).setValue(5);

        //When
        String drawedBoard = drawer.draw(board);

        //Then
        assertEquals(expected, drawedBoard);
    }
}