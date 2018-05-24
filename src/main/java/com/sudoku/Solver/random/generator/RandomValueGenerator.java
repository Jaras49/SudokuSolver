package com.sudoku.Solver.random.generator;

import com.sudoku.board.elements.SudokuBoard;
import com.sudoku.board.elements.SudokuElement;
import com.sudoku.board.elements.SudokuRow;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class RandomValueGenerator {

    public RandomValue generateRandomValue(SudokuBoard board) {

        return takeOneRandomValue(findAllEmptySudokuElements(board));
    }

    private List<RandomValue> findAllEmptySudokuElements(SudokuBoard board) {

        List<RandomValue> randomValues = new ArrayList<>();

        List<SudokuRow> sudokuRows = board.getSudokuRows();

        for (int i = 0; i < sudokuRows.size(); i++) {

            List<SudokuElement> sudokuElements = sudokuRows.get(i).getSudokuElements();

            for (int j = 0; j < sudokuElements.size(); j++) {

                SudokuElement sudokuElement = sudokuElements.get(j);

                if (sudokuElement.isEmpty()) {

                    Set<Integer> possibleValues = sudokuElement.getPossibleValues();

                    RandomValue randomValue = new RandomValue(i, j, takeOneValueFromPossibleValues(possibleValues));
                    randomValues.add(randomValue);
                }
            }
        }
        return randomValues;
    }

    private int takeOneValueFromPossibleValues(Set<Integer> possibleValues) {

        List<Integer> list = new ArrayList<>(possibleValues);

        Random random = new Random();
        int value = random.nextInt(list.size());

        return list.get(value);
    }

    private RandomValue takeOneRandomValue(List<RandomValue> randomValues) {

        Random random = new Random();
        int value = random.nextInt(randomValues.size());

        return randomValues.get(value);
    }
}
