package com.sudoku.Solver;

import com.sudoku.Solver.backtrack.BackTrack;
import com.sudoku.board.elements.Block;
import com.sudoku.board.elements.SudokuBoard;
import com.sudoku.board.elements.SudokuElement;
import com.sudoku.board.elements.SudokuRow;

import java.util.*;

public class Solver {

    private SudokuBoard board;
    private List<BackTrack> backTracks;

    public Solver(SudokuBoard sudokuBoard) {
        this.board = sudokuBoard;
        this.backTracks = new ArrayList<>();
    }

    public SudokuBoard solve() {

        SudokuBoard clonedBoard = null;
        try {
            clonedBoard = board.deepCopy();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        List<SudokuRow> sudokuRows = board.getSudokuRows();

        while (!isSolved()) {


        for (int i = 0; i < sudokuRows.size(); i++) {

            List<SudokuElement> sudokuElements = sudokuRows.get(i).getSudokuElements();

            for (int j = 0; j < sudokuElements.size(); j++) {

                SudokuElement sudokuElement = sudokuElements.get(j);

                if (!sudokuElement.isEmpty()) {
                    continue;
                }
                solveRow(i, j);
                solveColumn(i, j);
                solveBlock(i, j);
            }
            if (!checkStatus(clonedBoard)) {
                guess();

            }
        }}
        return board;
    }

    private void solveRow(int rowIndex, int elementIndex) {

        List<SudokuElement> sudokuElements = board.getSudokuRows().get(rowIndex).getSudokuElements();

        for (int i = 0; i < sudokuElements.size(); i++) {

            SudokuElement sudokuElement = sudokuElements.get(i);
            Set<Integer> possibleValues = sudokuElement.getPossibleValues();

            for (Iterator<Integer> iterator = possibleValues.iterator(); iterator.hasNext();) {

                Integer possibleValue = iterator.next();
                switch (checkRow(rowIndex, i, possibleValue)) {
                    case 0:
                        break;
                    case 1:
                        if (possibleValues.size() == 1) {
                            error();
                            //TODO throw ERROR
                            break;
                        }
                        iterator.remove();
                        if (possibleValues.size() == 1) {
                            board.getSudokuRows().get(rowIndex).getSudokuElements().get(i).setValue(possibleValue);
                        }
                        break;
                    case 2:
                        board.getSudokuRows().get(rowIndex).getSudokuElements().get(i).setValue(possibleValue);
                        break;
                }
            }
        }
    }

    private int checkRow(int rowIndex, int elementIndex, int possibleValue) {

        List<SudokuElement> sudokuElements = board.getSudokuRows().get(rowIndex).getSudokuElements();

        for (int i = 0; i < sudokuElements.size(); i++) {

            SudokuElement sudokuElement = sudokuElements.get(i);

            if (i == elementIndex) {
                continue;
            }
            int value = sudokuElement.getValue();

            if (possibleValue == value) {
                return 1;
            }
        }
        boolean contains = false;
        for (int i = 0; i < sudokuElements.size(); i++) {

            if (i == elementIndex) {
                continue;
            }
            SudokuElement sudokuElement = sudokuElements.get(i);
            Set<Integer> possibleValues = sudokuElement.getPossibleValues();

            if (possibleValues.contains(possibleValue)) {
                contains = true;
            }
        }
        if (!contains) {
            return 2;
        } else {
            return 0;
        }
    }

    private void solveColumn(int rowIndex, int elementIndex) {

        List<SudokuRow> sudokuRows = board.getSudokuRows();

        for (int i = 0; i < sudokuRows.size(); i++) {

            SudokuElement sudokuElement = sudokuRows.get(i).getSudokuElements().get(elementIndex);
            Set<Integer> possibleValues = sudokuElement.getPossibleValues();

            for (Iterator<Integer> iterator = possibleValues.iterator(); iterator.hasNext();) {

                Integer possibleValue = iterator.next();
                switch (checkColumn(i, elementIndex, possibleValue)) {
                    case 0:
                        break;
                    case 1:
                        if (possibleValues.size() == 1) {
                            error();
                            //TODO throw ERROR
                            break;
                        }
                        iterator.remove();
                        if (possibleValues.size() == 1) {
                            sudokuElement.setValue(possibleValue);
                        }
                        break;
                    case 2:
                        sudokuElement.setValue(possibleValue);
                        break;
                }
            }
        }
    }

    private int checkColumn(int rowIndex, int elementIndex, int possibleValue) {

        List<SudokuRow> sudokuRows = board.getSudokuRows();

        for (int i = 0; i < sudokuRows.size(); i++) {

            SudokuElement sudokuElement = sudokuRows.get(i).getSudokuElements().get(elementIndex);

            if (i == rowIndex) {
                continue;
            }
            int value = sudokuElement.getValue();

            if (possibleValue == value) {
                return 1;
            }
        }
        boolean contains = false;
        for (int i = 0; i < sudokuRows.size(); i++) {

            if (i == rowIndex) {
                continue;
            }
            SudokuElement sudokuElement = sudokuRows.get(i).getSudokuElements().get(elementIndex);
            Set<Integer> possibleValues = sudokuElement.getPossibleValues();

            if (possibleValues.contains(possibleValue)) {
                contains = true;
            }
        }
        if (!contains) {
            return 2;
        } else {
            return 0;
        }
    }

    private void solveBlock(int rowIndex, int elementIndex) {

        Block blockY = Block.evaluateBlock(rowIndex);
        Block blockX = Block.evaluateBlock(elementIndex);

        for (int i : blockY.getPossibleValues()) {

            SudokuRow sudokuRow = board.getSudokuRows().get(i);

            for (int j : blockX.getPossibleValues()) {

                SudokuElement sudokuElement = sudokuRow.getSudokuElements().get(j);
                Set<Integer> possibleValues = sudokuElement.getPossibleValues();

                for (Iterator<Integer> iterator = possibleValues.iterator(); iterator.hasNext();) {

                    Integer possibleValue = iterator.next();
                    switch (checkBlock(i, j, possibleValue)) {
                        case 0:
                            break;
                        case 1:
                            if (possibleValues.size() == 1) {
                                error();
                                //TODO throw ERROR
                                break;
                            }
                            iterator.remove();
                            if (possibleValues.size() == 1) {
                                sudokuElement.setValue(possibleValue);
                            }
                            break;
                        case 2:
                            sudokuElement.setValue(possibleValue);
                            break;
                    }
                }
            }
        }
    }

    private int checkBlock(int rowIndex, int elementIndex, int possibleValue) {

        Block blockY = Block.evaluateBlock(rowIndex);
        Block blockX = Block.evaluateBlock(elementIndex);

        List<SudokuRow> sudokuRows = board.getSudokuRows();

        for (int i : blockY.getPossibleValues()) {

            SudokuRow sudokuRow = sudokuRows.get(i);

            for (int j : blockX.getPossibleValues()) {

                if (rowIndex == i && elementIndex == j) {
                    continue;
                }
                int value = sudokuRow.getSudokuElements().get(j).getValue();

                if (possibleValue == value) {
                    return 1;
                }
            }
        }
        boolean contains = false;
        for (int i : blockY.getPossibleValues()) {

            SudokuRow sudokuRow = sudokuRows.get(i);

            for (int j : blockX.getPossibleValues()) {

                SudokuElement sudokuElement = sudokuRow.getSudokuElements().get(j);
                Set<Integer> possibleValues = sudokuElement.getPossibleValues();

                if (possibleValues.contains(possibleValue)) {
                    contains = true;
                }
            }
        }
        if (!contains) {
            return 2;
        } else {
            return 0;
        }
    }

    private boolean checkStatus(SudokuBoard board) {

        return this.board.equals(board);
    }

    private void guess() {

        List<SudokuRow> sudokuRows = board.getSudokuRows();

        for (int i = 0; i < sudokuRows.size() ; i++) {

            List<SudokuElement> sudokuElements = sudokuRows.get(i).getSudokuElements();

            for (int j = 0; j < sudokuElements.size(); j++) {

                SudokuElement sudokuElement = sudokuElements.get(j);

                if (!sudokuElement.isEmpty()) {
                    continue;
                }
                Set<Integer> possibleValues = sudokuElement.getPossibleValues();

                Random random = new Random();
                int value = 0;

                while (!possibleValues.contains(value)) {
                    value = random.nextInt(9 + 1);
                }
                try {
                    backTracks.add(new BackTrack(board.deepCopy(), i, j, value));
                } catch (CloneNotSupportedException e) {
                    e.printStackTrace();
                }
                board.getSudokuRows().get(i).getSudokuElements().get(j).setValue(value);
                return;
            }
        }
    }

    private void error() {
        BackTrack backTrack = backTracks.get(backTracks.size() - 1);
        board = backTrack.getBoard();
        board.getSudokuRows().get(backTrack.getRowIndex()).getSudokuElements().get(backTrack.getElementIndex()).getPossibleValues().remove(backTrack.getGuessedValue());
        backTracks.remove(backTracks.size() - 1);
    }

    private boolean isSolved() {
        long count = board.getSudokuRows().stream()
                .flatMap(n -> n.getSudokuElements().stream())
                .filter(n -> n.isEmpty())
                .count();

        if (count > 0) {
            return false;
        }
        return true;
    }
}
