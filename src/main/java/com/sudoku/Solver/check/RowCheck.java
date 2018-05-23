package com.sudoku.Solver.check;

import com.sudoku.board.elements.SudokuElement;

import java.util.List;
import java.util.Set;

public class RowCheck extends AbstractCheck implements Check {

    @Override
    protected boolean checkIfIsTaken(int rowIndex, int elementIndex, int possibleValue) {

        List<SudokuElement> sudokuElements = board.getSudokuRows().get(rowIndex).getSudokuElements();

        for (int i = 0; i < sudokuElements.size(); i++) {

            if (i == elementIndex) {
                continue;
            }

            int value = sudokuElements.get(i).getValue();

            if (possibleValue == value) {
                return true;
            }
        }
        return false;
    }

    @Override
    protected boolean checkIfIsAmongPossibleValues(int rowIndex, int elementIndex, int possibleValue) {

        List<SudokuElement> sudokuElements = board.getSudokuRows().get(rowIndex).getSudokuElements();

        for (int i = 0; i < sudokuElements.size(); i++) {

            if (i == elementIndex) {
                continue;
            }

            Set<Integer> possibleValues = sudokuElements.get(i).getPossibleValues();

            if (possibleValues.contains(possibleValue)) {
                return true;
            }
        }
        return false;
    }
}
