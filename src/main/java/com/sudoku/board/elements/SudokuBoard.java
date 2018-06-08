package com.sudoku.board.elements;

import com.sudoku.board.elements.prototype.Prototype;

import java.util.*;
import java.util.stream.IntStream;

public class SudokuBoard extends Prototype {

    private List<SudokuRow> sudokuRows;

    public SudokuBoard(SudokuRow... sudokuRows) {

        this.sudokuRows = new ArrayList<>();

        IntStream.range(0, sudokuRows.length)
                .forEach(n -> this.sudokuRows.add(sudokuRows[n]));

    }

    public List<SudokuRow> getSudokuRows() {
        return sudokuRows;
    }

    public SudokuBoard deepCopy() throws CloneNotSupportedException {

        SudokuBoard clonedBoard = (SudokuBoard) super.clone();
        clonedBoard.sudokuRows = new ArrayList<>();

        for (int i = 0; i < sudokuRows.size(); i++) {

            clonedBoard.sudokuRows.add(i, new SudokuRow());
            List<SudokuElement> sudokuElements = sudokuRows.get(i).getSudokuElements();

            for (int j = 0; j < sudokuElements.size(); j++) {

                clonedBoard.sudokuRows.get(i).getSudokuElements().add(j, new SudokuElement());

                int value = sudokuElements.get(j).getValue();

                Set<Integer> possibleValues = sudokuElements.get(j).getPossibleValues();

                clonedBoard.sudokuRows.get(i).getSudokuElements().get(j).setValue(value);
                clonedBoard.sudokuRows.get(i).getSudokuElements().get(j).getPossibleValues().clear();

                for (Integer possibleValue : possibleValues) {
                    clonedBoard.sudokuRows.get(i).getSudokuElements().get(j).getPossibleValues().add(possibleValue);
                }
            }
        }
        return clonedBoard;
    }

    public void setElementValue(int rowIndex, int elementIndex, int value) {
        if (!sudokuRows.get(rowIndex).getSudokuElements().get(elementIndex).isEmpty()) {
            System.out.println("NOT EMPTY FIELD");
        }
        sudokuRows.get(rowIndex).getSudokuElements().get(elementIndex).setValue(value);
        removeFromPossibleValues(rowIndex, elementIndex, value);
    }

    private void removeFromPossibleValues(int rowIndex, int elementIndex, int value) {

        removeFromRowPossibleValues(rowIndex, value);
        removeFromColumnPossibleValues(elementIndex, value);
        removeFromBlockPossibleValues(rowIndex, elementIndex, value);

    }

    private void removeFromRowPossibleValues(int rowIndex, int value) {

        List<SudokuElement> sudokuElements = sudokuRows.get(rowIndex).getSudokuElements();

        for (int i = 0; i < sudokuElements.size(); i++) {
            sudokuElements.get(i).getPossibleValues().remove(value);
        }

        //sudokuRows.get(rowIndex).getSudokuElements()
        //.forEach(n -> n.getPossibleValues().remove(value));
    }

    private void removeFromColumnPossibleValues(int elementIndex, int value) {

        for (int i = 0; i < sudokuRows.size(); i++) {
            sudokuRows.get(i).getSudokuElements().get(elementIndex).getPossibleValues().remove(value);
        }
        // sudokuRows.stream()
        // .map(n -> n.getSudokuElements().get(elementIndex))
        // .forEach(n -> n.getPossibleValues().remove(value));
    }

    private void removeFromBlockPossibleValues(int rowIndex, int elementIndex, int value) {


        Block blockY = Block.evaluateBlock(rowIndex);
        Block blockX = Block.evaluateBlock(elementIndex);

        for (int i : blockY.getPossibleValues()) {

            for (int j : blockX.getPossibleValues()) {

                sudokuRows.get(i).getSudokuElements().get(j).getPossibleValues().remove(value);
            }
        }


       // Arrays.stream(blockY.getPossibleValues())
               // .forEach(i -> Arrays.stream(blockX.getPossibleValues())
                       // .forEach(j -> sudokuRows.get(i).getSudokuElements().get(j).getPossibleValues().remove(value)));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SudokuBoard)) return false;
        SudokuBoard that = (SudokuBoard) o;
        return Objects.equals(sudokuRows, that.sudokuRows);
    }

    @Override
    public int hashCode() {

        return Objects.hash(sudokuRows);
    }
}