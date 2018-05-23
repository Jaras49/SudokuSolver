package com.sudoku.Solver.check;

import com.sudoku.Solver.check.exception.InvalidSudokuException;
import com.sudoku.board.elements.SudokuBoard;
import com.sudoku.board.elements.SudokuElement;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class RowCheck implements Check {

    private SudokuBoard board;

    @Override
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

    private boolean checkIfIsTaken(int rowIndex, int elementIndex, int possibleValue) {

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

    private boolean checkIfIsAmongPossibleValues(int rowIndex, int elementIndex, int possibleValue) {

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

    private void deleteFromPossibleValues(List<Integer> listToDelete, int rowIndex, int elementIndex) throws InvalidSudokuException {

        Set<Integer> possibleValues = board.getSudokuRows().get(rowIndex).getSudokuElements().get(elementIndex).getPossibleValues();

        for (Integer integer : listToDelete) {

            if (possibleValues.size() == 1 && possibleValues.contains(integer)) {

                throw new InvalidSudokuException();
            }
            possibleValues.remove(integer);

            if (possibleValues.size() == 1) {
                board.getSudokuRows().get(rowIndex).getSudokuElements().get(elementIndex).setValue(possibleValues.iterator().next());
            }
        }
    }
}
