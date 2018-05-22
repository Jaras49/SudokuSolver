package com.sudoku.board.elements;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

public class SudokuBoard {

    private List<SudokuRow> sudokuRows;

    public SudokuBoard(SudokuRow... sudokuRows) {

        this.sudokuRows = new ArrayList<>();

        IntStream.range(0, sudokuRows.length)
                .forEach(n -> this.sudokuRows.add(sudokuRows[n]));

    }

    public List<SudokuRow> getSudokuRows() {
        return sudokuRows;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SudokuBoard)) return false;
        SudokuBoard that = (SudokuBoard) o;
        return Objects.equals(sudokuRows, that.sudokuRows);
    }

    @Override
    public int hashCode() {

        return Objects.hash(sudokuRows);
    }
}