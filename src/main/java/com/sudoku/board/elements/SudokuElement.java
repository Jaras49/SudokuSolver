package com.sudoku.board.elements;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.IntStream;

public class SudokuElement {

    private static final int EMPTY = -1;
    private int value;
    private boolean isEmpty;
    private Set<Integer> possibleValues;

    public SudokuElement() {

        value = EMPTY;
        possibleValues = new HashSet<>();
        isEmpty = true;
        IntStream.range(1, 10)
                .forEach(n -> possibleValues.add(n));
    }

    public int getValue() {

        return value;
    }

    public Set<Integer> getPossibleValues() {

        return possibleValues;
    }

    public boolean isEmpty() {
        return isEmpty;
    }

    void setValue(int value) {
        if (value != -1) {

            this.value = value;
            isEmpty = false;
            possibleValues.clear();
        } else {
            this.value = value;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SudokuElement)) return false;
        SudokuElement that = (SudokuElement) o;
        return value == that.value &&
                isEmpty == that.isEmpty &&
                Objects.equals(possibleValues, that.possibleValues);
    }

    @Override
    public int hashCode() {

        return Objects.hash(value, isEmpty, possibleValues);
    }
}