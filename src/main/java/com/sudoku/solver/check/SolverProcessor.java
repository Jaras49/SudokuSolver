package com.sudoku.solver.check;

import com.sudoku.solver.check.exception.InvalidSudokuException;
import com.sudoku.board.elements.Block;
import com.sudoku.board.elements.SudokuBoard;
import com.sudoku.board.elements.SudokuElement;
import com.sudoku.board.elements.SudokuRow;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SolverProcessor {

    private SudokuBoard board;

    public SudokuBoard check(SudokuBoard board, int rowIndex, int elementIndex) throws InvalidSudokuException {

        this.board = board;

        Set<Integer> possibleValues = this.board.getSudokuRows().get(rowIndex).getSudokuElements().get(elementIndex).getPossibleValues();
        Set<Integer> elementsValues = checkAll(rowIndex, elementIndex);

        if (elementsValues.size() > 0) {

            possibleValues.removeAll(elementsValues);

            if (possibleValues.size() == 0) {

                throw new InvalidSudokuException();
            } else if (possibleValues.size() == 1) {

                board.setElementValue(rowIndex, elementIndex, possibleValues.iterator().next());
                return board;
            } else {
                return board;
            }
        } else if (elementsValues.size() == 0) {
            checkPossibleValues(rowIndex, elementIndex, possibleValues);
            return board;
        }
        return board;
    }

    private Set<Integer> checkAll(int rowIndex, int elementIndex) {

        Set<Integer> values = new HashSet<>();

        Set<Integer> rowValues = checkRow(rowIndex);
        Set<Integer> columnValues = checkColumn(elementIndex);
        Set<Integer> blockValues = checkBlock(rowIndex, elementIndex);

        values.addAll(rowValues);
        values.addAll(columnValues);
        values.addAll(blockValues);

        return values;
    }

    private Set<Integer> checkRow(int rowIndex) {

        List<SudokuElement> sudokuElements = board.getSudokuRows().get(rowIndex).getSudokuElements();

        return sudokuElements.stream()
                .filter(n -> !n.isEmpty())
                .map(SudokuElement::getValue)
                .collect(Collectors.toSet());
    }

    private Set<Integer> checkColumn(int elementIndex) {

        List<SudokuRow> sudokuRows = board.getSudokuRows();

        return sudokuRows.stream()
                .map(n -> n.getSudokuElements().get(elementIndex))
                .filter(n -> !n.isEmpty())
                .map(SudokuElement::getValue)
                .collect(Collectors.toSet());
    }

    private Set<Integer> checkBlock(int rowIndex, int elementIndex) {

        List<SudokuRow> sudokuRows = board.getSudokuRows();

        Block blockY = Block.evaluateBlock(rowIndex);
        Block blockX = Block.evaluateBlock(elementIndex);

        return IntStream.of(blockY.getPossibleValues())
                .mapToObj(i -> {
                    List<SudokuElement> sudokuElements = sudokuRows.get(i).getSudokuElements();

                    return IntStream.of(blockX.getPossibleValues())
                            .mapToObj(sudokuElements::get)
                            .filter(n -> !n.isEmpty())
                            .map(SudokuElement::getValue)
                            .collect(Collectors.toList());

                })
                .flatMap(Collection::stream)
                .collect(Collectors.toSet());
    }

    @Deprecated
    private Set<Integer> testBlockCheck(int rowIndex, int elementIndex) {

        List<SudokuRow> sudokuRows = board.getSudokuRows();

        Block blockY = Block.evaluateBlock(rowIndex);
        Block blockX = Block.evaluateBlock(elementIndex);

        return Arrays.stream(blockY.getPossibleValues())
                .mapToObj(sudokuRows::get)
                .map(SudokuRow::getSudokuElements)
                .flatMap(row -> Arrays.stream(blockX.getPossibleValues())
                        .mapToObj(row::get)
                        .filter(element -> !element.isEmpty())
                        .map(SudokuElement::getValue)
                        .collect(Collectors.toSet()).stream())
                .collect(Collectors.toSet());
    }

    private void checkPossibleValues(int rowIndex, int elementIndex, Set<Integer> possibleValue) {

        for (Integer integer : possibleValue) {

            boolean amongRowPossibleValues = isAmongRowPossibleValues(rowIndex, integer);
            boolean amongColumPossibleValues = isAmongColumPossibleValues(elementIndex, integer);
            boolean amongBlockPossibleValues = isAmongBlockPossibleValues(rowIndex, elementIndex, integer);

            if (!amongRowPossibleValues && !amongColumPossibleValues && !amongBlockPossibleValues) {

                board.setElementValue(rowIndex, elementIndex, integer);
                break;
            }
        }
    }

    private boolean isAmongRowPossibleValues(int rowIndex, int possibleValue) {

        List<SudokuElement> sudokuElements = board.getSudokuRows().get(rowIndex).getSudokuElements();

        int count = (int) sudokuElements.stream()
                .map(SudokuElement::getPossibleValues)
                .filter(n -> n.contains(possibleValue))
                .count() - 0;

        return count > 1;
    }

    private boolean isAmongColumPossibleValues(int elementIndex, int possibleValue) {

        List<SudokuRow> sudokuRows = board.getSudokuRows();

        int count = (int) sudokuRows.stream()
                .map(i -> i.getSudokuElements().get(elementIndex))
                .map(SudokuElement::getPossibleValues)
                .filter(n -> n.contains(possibleValue))
                .count() - 1;

        return count > 0;
    }

    private boolean isAmongBlockPossibleValues(int rowIndex, int elementIndex, int possibleValue) {

        List<SudokuRow> sudokuRows = board.getSudokuRows();

        Block blockY = Block.evaluateBlock(rowIndex);
        Block blockX = Block.evaluateBlock(elementIndex);

        int count = (int) Arrays.stream(blockY.getPossibleValues())
                .mapToObj(sudokuRows::get)
                .map(SudokuRow::getSudokuElements)
                .flatMap(elements -> Arrays.stream(blockX.getPossibleValues())
                        .mapToObj(elements::get))
                .map(SudokuElement::getPossibleValues)
                .filter(n -> n.contains(possibleValue))
                .count() - 1;

        return count > 0;
    }
}
