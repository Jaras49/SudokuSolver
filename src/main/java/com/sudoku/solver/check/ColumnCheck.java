package com.sudoku.solver.check;

import com.sudoku.board.elements.SudokuRow;

import java.util.List;
import java.util.Set;
/**@deprecated
 * This class is deprecated. SolverProcessor has its functions
 */
public class ColumnCheck extends AbstractCheck implements Check {

    @Override
    protected boolean checkIfIsTaken(int rowIndex, int elementIndex, int possibleValue) {

        List<SudokuRow> sudokuRows = board.getSudokuRows();

        for (int i = 0; i < sudokuRows.size(); i++) {

            if (i == rowIndex) {
                continue;
            }

            int value = sudokuRows.get(i).getSudokuElements().get(elementIndex).getValue();

            if (value == possibleValue) {
                return true;
            }
        }
        return false;
    }

    @Override
    protected boolean checkIfIsAmongPossibleValues(int rowIndex, int elementIndex, int possibleValue) {

        List<SudokuRow> sudokuRows = board.getSudokuRows();

        for (int i = 0; i < sudokuRows.size(); i++) {

            if (i == rowIndex) {
                continue;
            }

            Set<Integer> possibleValues = sudokuRows.get(i).getSudokuElements().get(elementIndex).getPossibleValues();

            if (possibleValues.contains(possibleValue)) {
                return true;
            }
        }
        return false;
    }
}
