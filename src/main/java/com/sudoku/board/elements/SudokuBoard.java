package com.sudoku.board.elements;

import com.sudoku.board.elements.prototype.Prototype;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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
                int value = sudokuElements.get(i).getValue();
                clonedBoard.sudokuRows.get(i).getSudokuElements().get(j).setValue(value);
            }
        }
        return clonedBoard;
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