package com.sudoku.validator;

import com.sudoku.board.elements.SudokuBoard;
import com.sudoku.board.elements.SudokuElement;
import com.sudoku.board.elements.SudokuRow;

import java.util.List;

public class Validator {

    private SudokuBoard sudokuBoard;

    public Validator(SudokuBoard sudokuBoard) {

        this.sudokuBoard = sudokuBoard;
    }

    public boolean validate() {
        List<SudokuRow> sudokuRows = sudokuBoard.getSudokuRows();

        for (int i = 0; i < sudokuRows.size(); i++) {
            List<SudokuElement> sudokuElements = sudokuRows.get(i).getSudokuElements();

            for (int j = 0; j < sudokuElements.size(); j++) {

                SudokuElement sudokuElement = sudokuElements.get(j);
                if (sudokuElement.isEmpty()) {
                    continue;
                }

                int value = sudokuElement.getValue();

                if (!checkRow(i, j, value)) {
                    return false;
                }
                if (!checkColumn(i, j, value)) {
                    return false;
                }
                if (!checkBlock(i, j, value)) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean checkRow(int rowIndex, int elementIndex, int value) {

        SudokuRow sudokuRow = sudokuBoard.getSudokuRows().get(rowIndex);

        for (int i = 0; i < sudokuRow.getSudokuElements().size(); i++) {

            if (i == elementIndex) {
                continue;
            }
            if (sudokuRow.getSudokuElements().get(i).getValue() == value) {
                return false;
            }
        }
        return true;
    }

    private boolean checkColumn(int rowIndex, int elementIndex, int value) {

        List<SudokuRow> sudokuRows = sudokuBoard.getSudokuRows();

        for (int i = 0; i < sudokuRows.size(); i++) {

            if (i == rowIndex) {
                continue;
            }
            if (sudokuRows.get(i).getSudokuElements().get(elementIndex).getValue() == value) {
                return false;
            }
        }
        return true;
    }

    private boolean checkBlock(int rowIndex, int elementIndex, int value) {

        Block blockY = Block.evaluateBlock(rowIndex);
        Block blockX = Block.evaluateBlock(elementIndex);

        for (int possibleValue : blockY.possibleValues) {

            SudokuRow sudokuRow = sudokuBoard.getSudokuRows().get(possibleValue);

            for (int i : blockX.possibleValues) {

                if (possibleValue == rowIndex && i == elementIndex) {
                    continue;
                }

                if (sudokuRow.getSudokuElements().get(i).getValue() == value) {
                    return false;
                }
            }

        }
        return true;
    }

    private enum Block {
        ONE(new int[]{0, 1, 2}),
        TWO(new int[]{3, 4, 5}),
        THREE(new int[]{6, 7, 8});

        Block(int[] possibleValues) {
            this.possibleValues = possibleValues;
        }

        private int[] possibleValues;

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
}
