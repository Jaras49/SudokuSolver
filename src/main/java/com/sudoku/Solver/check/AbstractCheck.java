package com.sudoku.Solver.check;

import com.sudoku.Solver.check.exception.InvalidSudokuException;
import com.sudoku.board.elements.SudokuBoard;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public abstract class AbstractCheck {

    protected SudokuBoard board;

    public SudokuBoard check(SudokuBoard board, int rowIndex, int elementIndex) throws InvalidSudokuException {

        this.board = board;
        Set<Integer> possibleValues = board.getSudokuRows().get(rowIndex).getSudokuElements().get(elementIndex).getPossibleValues();

        List<Integer> listToDeleteFromPossibleValues = new ArrayList<>();
        int value = -1;
        for (Integer possibleValue : possibleValues) {

            if (checkIfIsTaken(rowIndex, elementIndex, possibleValue)) {

                listToDeleteFromPossibleValues.add(possibleValue);

            } else if (!checkIfIsAmongPossibleValues(rowIndex, elementIndex, possibleValue)) {

                value = possibleValue;
            }
        }
        if (value != -1) {
            board.getSudokuRows().get(rowIndex).getSudokuElements().get(elementIndex).setValue(value);
        }
        deleteFromPossibleValues(listToDeleteFromPossibleValues, rowIndex, elementIndex);
        return board;
    }

    protected abstract boolean checkIfIsTaken(int rowIndex, int elementIndex, int possibleValue);

    protected abstract boolean checkIfIsAmongPossibleValues(int rowIndex, int elementIndex, int possibleValue);

    protected abstract void deleteFromPossibleValues
            (List<Integer> listToDelete, int rowIndex, int elementIndex) throws InvalidSudokuException;
}
