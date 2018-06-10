package com.sudoku.board.elements;

import java.util.Objects;

public class SudokuElement {

    private static final int EMPTY = -1;
    private int value;
    private boolean isEmpty;

    public SudokuElement() {

        value = EMPTY;
        isEmpty = true;
    }

    public int getValue() {

        return value;
    }

    public boolean isEmpty() {
        return isEmpty;
    }

    void setValue(int value) {
        if (value != -1) {

            this.value = value;
            isEmpty = false;
        } else {
            this.value = value;
            isEmpty = true;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SudokuElement)) return false;
        SudokuElement that = (SudokuElement) o;
        return value == that.value &&
                isEmpty == that.isEmpty;
    }

    @Override
    public int hashCode() {

        return Objects.hash(value, isEmpty);
    }
}