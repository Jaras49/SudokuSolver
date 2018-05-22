package com.sudoku.board.elements;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

public class SudokuRow {

    private List<SudokuElement> sudokuElements;

    public SudokuRow(SudokuElement... sudokuElements) {

        this.sudokuElements = new ArrayList<>();

        IntStream.range(0, sudokuElements.length)
                .forEach(n -> this.sudokuElements.add(sudokuElements[n]));
    }

    public List<SudokuElement> getSudokuElements() {
        return sudokuElements;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SudokuRow)) return false;
        SudokuRow sudokuRow = (SudokuRow) o;
        return Objects.equals(sudokuElements, sudokuRow.sudokuElements);
    }

    @Override
    public int hashCode() {

        return Objects.hash(sudokuElements);
    }
}