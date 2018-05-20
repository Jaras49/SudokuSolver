package com.sudoku.board.elements;

public enum Block {
    ONE(new int[]{0, 1, 2}),
    TWO(new int[]{3, 4, 5}),
    THREE(new int[]{6, 7, 8});

    Block(int[] possibleValues) {
        this.possibleValues = possibleValues;
    }

    private int[] possibleValues;

    public int[] getPossibleValues() {
        return possibleValues;
    }

    public static Block evaluateBlock(int value) {

        for (Block block : Block.values()) {
            for (int possibleValue : block.possibleValues) {
                if (possibleValue == value) {
                    return block;
                }
            }

        }
        return null;
    }
}
