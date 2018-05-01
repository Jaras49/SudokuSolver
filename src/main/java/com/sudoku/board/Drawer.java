package com.sudoku.board;

import com.sudoku.board.elements.SudokuBoard;

public class Drawer {

    public String draw(SudokuBoard board) {

        String result = "";

        for (int i = 0; i < board.getSudokuRows().size(); i++) {

            result += "|";

            for (int k = 0; k < board.getSudokuRows().get(0).getSudokuElements().size(); k++) {

                if (board.getSudokuRows().get(i).getSudokuElements().get(k).getValue() == -1) {
                    result += " ";
                } else {
                    result += board.getSudokuRows().get(i).getSudokuElements().get(k).getValue();
                }
                result += "|";
            }
            result += "\n";
        }
        return result;
    }
}
