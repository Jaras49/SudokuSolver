import com.sudoku.GameLoop;
import com.sudoku.board.Drawer;
import com.sudoku.board.SudokuBoardInitializer;
import com.sudoku.input.handler.InputHandler;
import com.sudoku.input.reader.InputReader;
import com.sudoku.solver.Solver2;
import com.sudoku.solver.InvalidSudokuException;
import com.sudoku.validator.Validator;

public class Main {

    private static final String VERSION = "1.0";
    private static final String INVALID_SUDOKU = "THIS SUDOKU IS INVALID";

    private void run() {

        try {
            new GameLoop.GameLoopBuilder()
                    .drawer(new Drawer())
                    .inputHandler(new InputHandler())
                    .inputReader(new InputReader())
                    .solver(new Solver2(new Validator()))
                    .sudokuBoardInitializer(new SudokuBoardInitializer())
                    .validator(new Validator())
                    .build().controlLoop();
        } catch (InvalidSudokuException e) {
            System.out.println(INVALID_SUDOKU);
        }
    }

    public static void main(String[] args) {

        Main main = new Main();
        main.run();
    }
}
