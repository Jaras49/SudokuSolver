package com.sudoku.Solver.random.generator;

final class RandomValue {

    private final int rowIndex;
    private final int elementIndex;
    private final int guessedValue;

    RandomValue(int rowIndex, int elementIndex, int guessedValue) {
        this.rowIndex = rowIndex;
        this.elementIndex = elementIndex;
        this.guessedValue = guessedValue;
    }

    public int getRowIndex() {
        return rowIndex;
    }

    public int getElementIndex() {
        return elementIndex;
    }

    public int getGuessedValue() {
        return guessedValue;
    }
}