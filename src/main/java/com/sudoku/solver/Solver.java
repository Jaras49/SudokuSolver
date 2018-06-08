package com.sudoku.solver;

import com.sudoku.solver.backtrack.BackTrack;
import com.sudoku.solver.check.*;
import com.sudoku.solver.check.exception.InvalidSudokuException;
import com.sudoku.solver.random.generator.RandomValue;
import com.sudoku.solver.random.generator.RandomValueGenerator;
import com.sudoku.board.Drawer;
import com.sudoku.board.elements.Block;
import com.sudoku.board.elements.SudokuBoard;
import com.sudoku.board.elements.SudokuElement;
import com.sudoku.board.elements.SudokuRow;
import com.sudoku.validator.Validator;

import java.util.*;
import java.util.stream.IntStream;
//TODO this is POC, need to refactor this code

/**
 * @deprecated
 * this class is POC and is going to be deleted when not needed
 */
public class Solver {

    private SudokuBoard board;
    private RandomValueGenerator valueGenerator;
    private List<BackTrack> backTracks;
    private List<BackTrack> backTracks2;
    private List<Check> checks;
    private SolverProcessor processor = new SolverProcessor();
    private Validator validator;


    public Solver(SudokuBoard sudokuBoard) {

        this.board = sudokuBoard;
        this.valueGenerator = new RandomValueGenerator();
        this.backTracks = new ArrayList<>();
        this.backTracks2 = new ArrayList<>();
        this.checks = new ArrayList<>();
        this.checks.add(new RowCheck());
        this.checks.add(new ColumnCheck());
        this.checks.add(new BlockCheck());
        this.validator = new Validator();
    }

    public SudokuBoard solve() throws CloneNotSupportedException {

        while (!isSolved()) {

            List<SudokuRow> sudokuRows = board.getSudokuRows();
            SudokuBoard clonedBoard = this.board.deepCopy();


            for (int i = 0; i < sudokuRows.size(); i++) {

                List<SudokuElement> sudokuElements = sudokuRows.get(i).getSudokuElements();

                for (int j = 0; j < sudokuElements.size(); j++) {

                    saveBackTrack();
                    SudokuElement sudokuElement = sudokuElements.get(j);

                    if (!sudokuElement.isEmpty()) {
                        continue;
                    }
                    process(i, j);


                    if (!validator.validate(board)) {
                        System.out.println("ERROR VALIDATING");
                        board = backTracks2.get(backTracks2.size() - 1).getBoard();
                        backTracks2.remove(backTracks2.size() - 1);


                    } else {
                        System.out.println("VALID");
                    }
                    if (checkStatus(clonedBoard) && !isSolved()) {

                        try {
                            generateValue();
                        } catch (InvalidSudokuException e) {
                            error();
                        }

                        if (!validator.validate(board)) {
                            System.out.println("GUESS INVALID");
                            error();
                        } else {
                            System.out.println("GUESS VALID");

                        }
                    }
                }
            }
            if (checkStatus(clonedBoard)) {
                error();
            }

            System.out.println(new Drawer().draw(board));
        }
        return board;
    }

    private void checkAll(int rowIndex, int elementIndex) {

        SudokuRow sudokuRow = board.getSudokuRows().get(rowIndex);

        IntStream.range(0, 9)
                .forEach(n -> checkField(rowIndex, n));

        IntStream.range(0, 9)
                .forEach(n -> checkField(n, elementIndex));

        Block blockY = Block.evaluateBlock(rowIndex);
        Block blockX = Block.evaluateBlock(elementIndex);

        IntStream.of(blockY.getPossibleValues())
                .forEach(i -> IntStream.of(blockX.getPossibleValues())
                        .forEach(j -> checkField(i, j)));

    }

    private void checkField(int rowIndex, int elementIndex) {

        try {
            for (Check check : checks) {
                this.board = check.check(board, rowIndex, elementIndex);
            }
        } catch (InvalidSudokuException e) {
            error();
        }
    }

    private void generateValue() throws CloneNotSupportedException, InvalidSudokuException {

        int guessedValue = -1;
        int rowIndex = -1;
        int elementIndex = -1;

        boolean validFlag = false;
        while (!validFlag) {

            SudokuBoard deepCopy = this.board.deepCopy();
            RandomValue randomValue = valueGenerator.generateRandomValue(board);

            guessedValue = randomValue.getGuessedValue();
            rowIndex = randomValue.getRowIndex();
            elementIndex = randomValue.getElementIndex();
            deepCopy.setElementValue(rowIndex, elementIndex, guessedValue);
            validFlag = validator.validate(deepCopy);
        }
        System.out.println("GENERATE VALUE VALID" + guessedValue);

        saveBackTrack(rowIndex, elementIndex, guessedValue);

        this.board.setElementValue(rowIndex, elementIndex, guessedValue);
    }

    private void saveBackTrack(int rowIndex, int elementIndex, int guessedValue) throws CloneNotSupportedException {
        backTracks.add(new BackTrack(board.deepCopy(), rowIndex, elementIndex, guessedValue));
    }

    private void saveBackTrack() throws CloneNotSupportedException {
        //backTracks2.add(new BackTrack(board.deepCopy()));
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

    private boolean checkStatus(SudokuBoard clonedBoard) {

        return board.equals(clonedBoard);
    }

    private void process(int rowIndex, int elementIndex) {

        try {
            board = processor.check(board, rowIndex, elementIndex);
        } catch (InvalidSudokuException e) {
            error();
        }
    }
}
