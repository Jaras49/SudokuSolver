import com.sudoku.GameLoop;
import com.sudoku.board.Drawer;
import com.sudoku.board.SudokuBoardInitializer;
import com.sudoku.input.handler.InputHandler;
import com.sudoku.input.reader.InputReader;
import com.sudoku.solver.Solver2;
import com.sudoku.solver.check.exception.InvalidSudokuException;
import com.sudoku.validator.Validator;

public class Main {

    public static void main(String[] args) {

        Drawer drawer = new Drawer();
        InputReader inputReader = new InputReader();
        InputHandler inputHandler = new InputHandler();
        SudokuBoardInitializer boardInitializer = new SudokuBoardInitializer();
        Solver2 solver = new Solver2();
        Validator validator = new Validator();

        GameLoop sudoku = new GameLoop(drawer, inputReader, inputHandler, boardInitializer, solver, validator);

        try {
            sudoku.controlLoop();
        } catch (InvalidSudokuException e) {
            System.out.println("THIS SUDOKU IS INVALID !!!!");
        }
    }
}
