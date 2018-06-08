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

        board.setElementValue(2, 2, 7);
        board.setElementValue(5, 6, 5);
        board.setElementValue(6, 4, 5);
        board.setElementValue(7, 8, 2);
        board.setElementValue(1, 1, 5);
        board.setElementValue(3, 5, 5);

        //When
        String drawedBoard = drawer.draw(board);

        //Then
        assertEquals(expected, drawedBoard);
    }
}