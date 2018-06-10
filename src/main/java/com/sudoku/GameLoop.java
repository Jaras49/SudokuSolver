package com.sudoku;


import com.sudoku.board.Drawer;
import com.sudoku.board.SudokuBoardInitializer;
import com.sudoku.board.elements.SudokuBoard;
import com.sudoku.input.handler.InputHandler;
import com.sudoku.input.handler.InvalidInputException;
import com.sudoku.input.reader.InputReader;
import com.sudoku.solver.Solver2;
import com.sudoku.solver.InvalidSudokuException;
import com.sudoku.validator.Validator;

public class GameLoop {

    private final Drawer drawer;
    private final InputReader inputReader;
    private final InputHandler inputHandler;
    private final SudokuBoardInitializer sudokuBoardInitializer;
    private final Solver2 solver;
    private final Validator validator;
    private boolean firstRun;

    private SudokuBoard board;

    public GameLoop(Drawer drawer, InputReader inputReader, InputHandler inputHandler,
                    SudokuBoardInitializer sudokuBoardInitializer, Solver2 solver, Validator validator) {
        this.drawer = drawer;
        this.inputReader = inputReader;
        this.inputHandler = inputHandler;
        this.sudokuBoardInitializer = sudokuBoardInitializer;
        this.solver = solver;
        this.validator = validator;
        firstRun = true;
    }

    public void controlLoop() throws InvalidSudokuException {

        board = sudokuBoardInitializer.createBoard(9);
        drawer.draw(board);

        boolean stop = false;
        while (!stop) {

            printOptions();

            String input = inputReader.getInput();
            if ("stop".equalsIgnoreCase(input)) {
                stop = true;
            } else if ("solve".equalsIgnoreCase(input)) {
                solve(board);
                tryAgain();
            } else if ("reset".equalsIgnoreCase(input)) {
                board = sudokuBoardInitializer.createBoard(9);
                firstRun = true;
                printOptions();
            } else if ("V".equalsIgnoreCase(input)) {
                if (validator.validate(board)) {
                    System.out.println("Sudoku is valid :)");
                } else {
                    throw new InvalidSudokuException();
                }
            } else {
                try {
                    String handledInput = inputHandler.handleInput(input);
                    setInputToBoard(handledInput);
                } catch (InvalidInputException | IndexOutOfBoundsException e) {
                    System.out.println("Invalid input , try again \n");
                }
            }
            System.out.println(drawer.draw(board));
        }
    }

    private void solve(SudokuBoard board) throws InvalidSudokuException {

        if (solver.findSolution(board)) {
            System.out.println(drawer.draw(solver.getBoard()));
        } else {
            throw new InvalidSudokuException();
        }
    }

    private void printOptions() {

        if (firstRun) {
            System.out.println("WELCOME TO SUDOKU_SOLVER_APP");
            System.out.println("Have fun :)");
        }
        System.out.println("Type \"STOP\" to stop game");
        System.out.println("Type \"SOLVE\" to start solving sudoku");
        System.out.println("Type \"V\" to check if current sudoku is valid");
        System.out.println("Type \"RESET\" to reset board");
        System.out.println("You can insert values to elements by typing 1,2,3 where 1 is row index");
        System.out.println("2 is element index and 3 is value");
        System.out.println("Inputs like 1,2,3,4,5,6 are also possible to set multiple values in one input");
        firstRun = false;
    }

    private void setInputToBoard(String input) {

        String[] split = input.split(",");

        int count = 0;
        int i = 0;
        int j = 0;
        int k = 0;
        for (String s : split) {

            if (count == 3) {
                count = 0;
            }
            if (count == 0) {
                i = Integer.parseInt(s);
            }
            if (count == 1) {
                j = Integer.parseInt(s);
            }
            if (count == 2) {
                k = Integer.parseInt(s);

                board.setElementValue(i, j, k);
            }
            count++;
        }
    }

    private void tryAgain() {

        boolean proceed = false;
        while (!proceed) {
            System.out.println("Try again ?? Y/N ??");

            String input = inputReader.getInput();
            if ("Y".equalsIgnoreCase(input)) {
                board = sudokuBoardInitializer.createBoard(9);
                firstRun = true;
                proceed = true;
            } else if ("N".equalsIgnoreCase(input)) {
                System.exit(1);
            }
        }
    }
}
