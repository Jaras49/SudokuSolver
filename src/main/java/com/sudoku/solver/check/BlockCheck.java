package com.sudoku.solver.check;


import com.sudoku.board.elements.Block;
import com.sudoku.board.elements.SudokuElement;

import java.util.List;
import java.util.Set;
/**@deprecated
 * This class is deprecated. SolverProcessor has its functions
 */
public class BlockCheck extends AbstractCheck implements Check {

    @Override
    protected boolean checkIfIsTaken(int rowIndex, int elementIndex, int possibleValue) {

        Block blockY = Block.evaluateBlock(rowIndex);
        Block blockX = Block.evaluateBlock(elementIndex);

        for (int i : blockY.getPossibleValues()) {

            List<SudokuElement> sudokuElements = board.getSudokuRows().get(i).getSudokuElements();

            for (int j : blockX.getPossibleValues()) {

                if (i == rowIndex && j == elementIndex) {
                    continue;
                }

                int value = sudokuElements.get(j).getValue();

                if (value == possibleValue) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    protected boolean checkIfIsAmongPossibleValues(int rowIndex, int elementIndex, int possibleValue) {

        Block blockY = Block.evaluateBlock(rowIndex);
        Block blockX = Block.evaluateBlock(elementIndex);

        for (int i : blockY.getPossibleValues()) {

            List<SudokuElement> sudokuElements = board.getSudokuRows().get(i).getSudokuElements();

            for (int j : blockX.getPossibleValues()) {

                if (i == rowIndex && j == elementIndex) {
                    continue;
                }

                Set<Integer> possibleValues = sudokuElements.get(j).getPossibleValues();

                if (possibleValues.contains(possibleValue)) {
                    return true;
                }
            }
        }
        return false;
    }
}
