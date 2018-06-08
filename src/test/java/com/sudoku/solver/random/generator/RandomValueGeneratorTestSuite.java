package com.sudoku.solver.random.generator;

import com.sudoku.board.SudokuBoardInitializer;
import com.sudoku.board.elements.SudokuBoard;
import com.sudoku.solver.check.exception.InvalidSudokuException;
import org.junit.Test;

import static org.junit.Assert.*;

public class RandomValueGeneratorTestSuite {

    @Test
    public void shouldGenerateRandomValue() throws InvalidSudokuException {

        //Given
        RandomValueGenerator generator = new RandomValueGenerator();
        SudokuBoard board = new SudokuBoardInitializer().createBoard(3);
        board.setElementValue(0, 0, 5);
        board.setElementValue(0, 1, 3);
        board.setElementValue(0, 2, 6);
        board.setElementValue(1, 0, 1);
        board.setElementValue(1, 1, 2);
        board.setElementValue(1, 2, 4);
        board.setElementValue(2, 0, 7);
        board.setElementValue(2, 1, 8);

        //When
        RandomValue randomValue = generator.generateRandomValue(board);
        int elementIndex = randomValue.getElementIndex();
        int rowIndex = randomValue.getRowIndex();
        int guessedValue = randomValue.getGuessedValue();

        //Then
        assertEquals(2, rowIndex);
        assertEquals(2, elementIndex);
        assertEquals(9, guessedValue);
    }

}