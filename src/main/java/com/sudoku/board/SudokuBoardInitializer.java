package com.sudoku.board;

import com.sudoku.board.elements.SudokuBoard;
import com.sudoku.board.elements.SudokuElement;
import com.sudoku.board.elements.SudokuRow;

import java.util.stream.IntStream;

public class SudokuBoardInitializer {

    public SudokuBoard createBoard (int size) {

        return new SudokuBoard (IntStream.range(0, size)
                .mapToObj(n -> new SudokuRow (IntStream.range(0, size)
                        .mapToObj(x -> new SudokuElement())
                        .toArray(SudokuElement[]::new)))
                .toArray(SudokuRow[]::new));
    }
}
