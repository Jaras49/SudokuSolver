package com.sudoku.Solver.check;

import com.sudoku.Solver.check.exception.InvalidSudokuException;
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

    @Override
    protected void deleteFromPossibleValues(List<Integer> listToDelete, int rowIndex, int elementIndex) throws InvalidSudokuException {

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
