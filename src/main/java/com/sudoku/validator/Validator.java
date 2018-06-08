package com.sudoku.validator;

import com.sudoku.board.elements.Block;
import com.sudoku.board.elements.SudokuBoard;
import com.sudoku.board.elements.SudokuElement;
import com.sudoku.board.elements.SudokuRow;

import java.util.List;

public class Validator {

    private SudokuBoard sudokuBoard;

    public boolean validate(SudokuBoard board) {
        this.sudokuBoard = board;
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

        for (int possibleValue : blockY.getPossibleValues()) {

            SudokuRow sudokuRow = sudokuBoard.getSudokuRows().get(possibleValue);

            for (int i : blockX.getPossibleValues()) {

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
}
