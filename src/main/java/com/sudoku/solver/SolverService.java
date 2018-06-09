package com.sudoku.solver;

import com.sudoku.board.Drawer;
import com.sudoku.board.elements.SudokuBoard;
import com.sudoku.board.elements.SudokuElement;
import com.sudoku.board.elements.SudokuRow;
import com.sudoku.solver.backtrack.BackTrack;
import com.sudoku.solver.check.exception.InvalidSudokuException;
import com.sudoku.validator.Validator;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
/**
 * @deprecated
 * This class is deprecated
 */
public class SolverService {

    private SudokuBoard board;
    private List<BackTrack> backTracks;
    private List<BackTrack> softBackTracks;

    public SolverService() {
        this.backTracks = new ArrayList<>();
        this.softBackTracks = new ArrayList<>();
    }

    public SudokuBoard findSolution(SudokuBoard sudokuBoard) throws InvalidSudokuException, CloneNotSupportedException {

        this.board = sudokuBoard;

        List<SudokuRow> sudokuRows = board.getSudokuRows();

        while (!isSolved()) {

            SudokuBoard copiedBoard = board.deepCopy();

            for (int row = 0; row < sudokuRows.size(); row++) {
                List<SudokuElement> sudokuElements = sudokuRows.get(row).getSudokuElements();
                saveBackTrack();

                for (int element = 0; element < sudokuElements.size(); element++) {

                    SudokuElement sudokuElement = sudokuElements.get(element);
                    Set<Integer> possibleValues = sudokuElement.getPossibleValues();


                    if (!sudokuElement.isEmpty()) {
                        continue;
                    }

                    if (possibleValues.size() == 1) {
                        System.out.println(possibleValues.iterator().next());
                        board.setElementValue(row, element, possibleValues.iterator().next());
                        System.out.println("row " + row + " element" + element);
                        if (new Validator().validate(board)) {
                            System.out.println("SET OK");
                        }
                    }

                    System.out.println(new Drawer().draw(board));
                    if (checkIfThereAreFieldsWithZeroPossibleValues()) {
                        System.out.println("Loading");
                        loadBackTrack();

                        if (!new Validator().validate(board)) {
                            System.out.println("ITERATION FAILED");
                        } else {
                            System.out.println("ITERATION TRUE");
                        }
                    }else {
                        System.out.println("NOT LOADING");
                    }
                }
            }
            if (checkStatus(copiedBoard) && !isSolved()) {
                guessValueFromElementWithLeastPossibilities();
                if (!new Validator().validate(board)) {
                    System.out.println("Validation failed");
                } else {
                    System.out.println("VALIDATION TRUE");
                }
            }
        }
        return board;
    }

    private boolean checkIfThereAreFieldsWithZeroPossibleValues() {

        return board.getSudokuRows().stream()
                .flatMap(n -> n.getSudokuElements().stream())
                .filter(SudokuElement::isEmpty)
                .anyMatch(n -> n.getPossibleValues().isEmpty());
    }

    private void saveBackTrack(int rowIndex, int elementIndex, int value) throws CloneNotSupportedException {

        backTracks.add(new BackTrack(board.deepCopy(), rowIndex, elementIndex, value));
    }

    private void saveBackTrack() throws CloneNotSupportedException {

        softBackTracks.add(new BackTrack(board.deepCopy()));
    }

    private void loadBackTrack() throws InvalidSudokuException {

        if (!backTracks.isEmpty()) {
            BackTrack backTrack = backTracks.get(backTracks.size() - 1);

            board = backTrack.getBoard();
            SudokuRow sudokuRow = board.getSudokuRows().get(backTrack.getRowIndex());
            SudokuElement sudokuElement = sudokuRow.getSudokuElements().get(backTrack.getElementIndex());
            sudokuElement.getPossibleValues().remove(backTrack.getGuessedValue());

            backTracks.remove(backTracks.size() - 1);
        } else {
            throw new InvalidSudokuException();
        }
    }

    private void loadSoftBackTrack() throws InvalidSudokuException {

        if (!softBackTracks.isEmpty()) {
            BackTrack backTrack = softBackTracks.get(softBackTracks.size() - 1);

            board = backTrack.getBoard();
            softBackTracks.remove(softBackTracks.size() - 1);
        }else {
            throw new InvalidSudokuException();
        }
    }

    private void guessValueFromElementWithLeastPossibilities() throws CloneNotSupportedException {

        List<SudokuRow> sudokuRows = board.getSudokuRows();

        int rowIndex = -1;
        int elementIndex = -1;
        int numberOfPossibleValues = 1000;

        for (int row = 0; row < sudokuRows.size(); row++) {
            List<SudokuElement> sudokuElements = sudokuRows.get(row).getSudokuElements();

            for (int element = 0; element < sudokuElements.size(); element++) {
                SudokuElement sudokuElement = sudokuElements.get(element);

                if (!sudokuElement.isEmpty()) {
                    continue;
                }
                Set<Integer> possibleValues = sudokuElement.getPossibleValues();
                int size = possibleValues.size();

                if (size < numberOfPossibleValues) {
                    rowIndex = row;
                    elementIndex = element;
                    numberOfPossibleValues = size;
                }

            }
        }
        Set<Integer> possibleValues = board.getSudokuRows().get(rowIndex).getSudokuElements().get(elementIndex).getPossibleValues();
        Integer value = possibleValues.iterator().next();


        saveBackTrack(rowIndex, elementIndex, value);
        board.setElementValue(rowIndex, elementIndex, value);
    }


    private boolean isSolved() {

        return board.getSudokuRows().stream()
                .flatMap(n -> n.getSudokuElements().stream())
                .noneMatch(SudokuElement::isEmpty);
    }

    private boolean checkStatus(SudokuBoard copiedBoard) {
        return this.board.equals(copiedBoard);
    }

    private boolean getValidPossibleValue() {
        return false;
    }

    private boolean validate(SudokuBoard board, int rowIndex, int elementIndex, int value) throws CloneNotSupportedException {

        SudokuBoard clonedBoard = board.deepCopy();
        clonedBoard.setElementValue(rowIndex, elementIndex, value);

        return new Validator().validate(clonedBoard);
    }
}
