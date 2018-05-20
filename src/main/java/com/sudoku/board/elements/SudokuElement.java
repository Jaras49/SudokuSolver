package com.sudoku.board.elements;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.IntStream;

public class SudokuElement {

    private static final int EMPTY = -1;
    private int value;
    private boolean isEmpty = true;
    private Set<Integer> possibleValues;

    public SudokuElement() {

        value = EMPTY;
        possibleValues = new HashSet<>();
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

    public void setValue(int value) {
        this.value = value;
        isEmpty = false;
        possibleValues.remove(value);
    }
}